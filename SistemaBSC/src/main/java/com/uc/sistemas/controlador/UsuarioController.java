package com.uc.sistemas.controlador;

import com.uc.sistemas.modelo.Usuario;
import com.uc.sistemas.security.ConnectUsuario;
import com.uc.sistemas.security.Sesion;
import java.io.IOException;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;

@Named("usuarioController")
@SessionScoped
public class UsuarioController extends AbstractController<Usuario> implements Serializable {

    @EJB
    private com.uc.sistemas.facade.UsuarioFacade ejbFacade;
    // Usado para el ingreso del username de la venta de logear
    private String username;
    // Usado para el ingreso de la contraseña de la venta de logear
    private String contrasena;
    public UsuarioController() {
        super(Usuario.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        this.setSelected(new Usuario());
    }
    public void logear(){
        Usuario user;
        System.out.println("Usuario "+ this.getUsername());
        if(ejbFacade.getUsuario(username)!=null){
            user=ejbFacade.getUsuario(username);
            try {
                if(user.getContrasena().equals(Sesion.MD5(contrasena))){
                    System.out.println("Si logeo...");
                    this.setSelected(user);
                    ConnectUsuario.setUsuario(this.getSelected());
                    // Colocando el tiempo de inactividad que tiene el sistema
                    Sesion.tiempoInactividad(1000);
                    Sesion.redireccionaPagina("http://localhost:8080/SistemaBSC/faces/index.xhtml");
                }else{
                    System.out.println("Contra invalida");
                }
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            System.out.println("No existe usuario");
        }
    }
    public void desLogear(){
    
    }
    @Override
    protected void setEmbeddableKeys() {
    }

    @Override
    protected void initializeEmbeddableKey() {
    }

    @Override
    protected void postCreate() {
    }

    @Override
    protected void postUpdate() {
    }

    @Override
    protected void postDestroy() {
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the contrasena
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * @param contrasena the contrasena to set
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
