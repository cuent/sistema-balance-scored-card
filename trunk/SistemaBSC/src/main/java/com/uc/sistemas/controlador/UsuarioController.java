package com.uc.sistemas.controlador;

import com.uc.sistemas.controlador.util.Mensaje;
import com.uc.sistemas.modelo.Sistema;
import com.uc.sistemas.modelo.Usuario;
import com.uc.sistemas.security.ConnectUsuario;
import com.uc.sistemas.security.Sesion;
import java.io.IOException;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletException;

@Named("usuarioController")
@SessionScoped
public class UsuarioController extends AbstractController<Usuario> implements Serializable {

    @EJB
    private com.uc.sistemas.facade.UsuarioFacade ejbFacade;
    @EJB
    private com.uc.sistemas.facade.SistemaFacade ejbSistemaFacade;
    // Usado para el ingreso del username de la venta de logear
    private String username;
    // Usado para el ingreso de la contraseña de la venta de logear
    private String contrasena;
    private String confirmaContrasena;

    public UsuarioController() {
        super(Usuario.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        this.setSelected(new Usuario());
        setLoginUsuario(ConnectUsuario.getUsuario());
    }

    @Override
    public void create() {
        Date d = new Date();
        this.getSelected().setFechaModificacion(d);
        super.create(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void destroy() {
        System.out.println("esta aki");
        if(!Objects.equals(ConnectUsuario.getUsuario().getIdUsuario(), this.getSelected().getIdUsuario())){
        super.destroy(); //To change body of generated methods, choose Tools | Templates.
        }else{
            Mensaje.addError(" Esta cuenta esta logeado actualemente");
        }
    }
    
    
    public void validarUsername() {
        if (getSelected().getUsername() != null) {
            if (ejbFacade.getUsuarioUsername(getSelected().getUsername()) != null) {
                System.out.println("Ya existe");
                Mensaje.addError(" Ya existe");
                getSelected().setUsername(null);
            } else {
                Mensaje.addSatisfactorio(" User correcto");
                System.out.println(" User correcto");
            }
        }
    }
    public boolean permisos(){
        if(ConnectUsuario.getTipoUsuario()=='A'){
         return true;
        }
        return false;
        
    }
    public void validarMail() {
        if (getSelected().getEmail() != null) {
            if (ejbFacade.getUsuarioEmail(getSelected().getEmail()) != null) {
                System.out.println("Ya existe");
                Mensaje.addError(" Ya existe");
                getSelected().setEmail(null);
            } else {
                System.out.println(" User correcto");
                Mensaje.addSatisfactorio(" User correcto");
            }
        }
    }

    public void validarContrasena() {
        if (contrasena != null) {
            if (contrasena.length() < 6 || contrasena.length() > 15) {
                System.out.println("La contrasena debe tener minimo 6 caracteres y maximo 16");
                Mensaje.addError("La contrasena debe tener minimo 6 caracteres y maximo 16");
                getSelected().setContrasena(null);
                contrasena = null;
            } else {
                System.out.println("Pass correcto");
                Mensaje.addSatisfactorio("Pass correcto");

            }
            confirmaContrasena = null;
        }
    }

    public void validarConfirmacionContrasena() {
        if (contrasena != null) {
            if (confirmaContrasena != null) {
                if (!contrasena.equals(confirmaContrasena)) {
                    System.out.println("no coinciden las pass");
                    Mensaje.addError("no coinciden las pass");
                    confirmaContrasena = null;
                } else {
                    cifrarContrasena();
                    System.out.println("verificacion correcta");
                    Mensaje.addSatisfactorio("verificacion correcta");
                }
            } else {
                System.out.println("Ingrese la confirmacion de contrasena");
                Mensaje.addError("Ingrese la confirmacion de contrasena");
            }
        } else {
            confirmaContrasena = null;
        }
    }

    public void cifrarContrasena() {
        if (contrasena != null) {
            try {
                getSelected().setContrasena(Sesion.MD5(contrasena));
                System.out.println("contra:" + getSelected().getContrasena());
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("Ingrese una contrasena");
            Mensaje.addError("Ingrese una contrasena");
        }
    }

    public void guardar() {
        boolean dir =false;
        if (ConnectUsuario.getTipoUsuario() == 'A') {
            if (this.getSelected() != null
                    && this.getSelected().getNombres() != null
                    && this.getSelected().getApellidos() != null
                    && this.getSelected().getUsername() != null
                    && this.getSelected().getEmail() != null
                    && this.getSelected().getContrasena() != null
                    && this.getSelected().getTipoUsuario() != null) {
                this.create();
                dir = true;
            }
        } else {
            System.out.println("No tiene los permisos de administrador para registrar nuevo usuario");
            Mensaje.addError("No tiene los permisos de administrador para registrar nuevo usuario");
        }
        this.setSelected(new Usuario());
        if(dir ==true){
            try {
                Sesion.redireccionaPagina("http://localhost:8080/SistemaBSC/faces/home.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void logear() {
        Usuario user;
        System.out.println("Usuario " + this.getUsername());
        if (ejbFacade.getUsuario(username) != null) {
            user = ejbFacade.getUsuario(username);
            try {
                if (user.getContrasena().equals(Sesion.MD5(contrasena))) {
                    if (ejbSistemaFacade.getSistema(user.getIdUsuario()) == null) {
                        System.out.println("Si logeo...");
                        this.setSelected(user);

                        Sistema sistema = new Sistema();
                        sistema.setIdUsuario(user);
                        ejbSistemaFacade.create(sistema);

                        ConnectUsuario.setUsuario(this.getSelected());
                        ConnectUsuario.setCodigoUsuario(this.getSelected().getIdUsuario());
                        ConnectUsuario.setTipoUsuario(this.getSelected().getTipoUsuario());
                        // Colocando el tiempo de inactividad que tiene el sistema
                        Sesion.tiempoInactividad(1000);
                        Sesion.creaSesion();
                        Sesion.redireccionaPagina("http://localhost:8080/SistemaBSC/faces/home.xhtml");
                        this.setSelected(new Usuario());
                    } else {
                        System.out.println("Usuario ya se encuentra logeado");
                        Mensaje.addError("Usuario ya se encuentra logeado");
                    }
                } else {
                    System.out.println("Contraseña invalida");
                    Mensaje.addError("Contraseña invalida");
                }
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("No existe usuario");
            Mensaje.addError("No existe usuario");
        }
    }

    public void desLogear() {
        if (ConnectUsuario.getUsuario() != null) {
            try {
                Sistema sistema = new Sistema();
                sistema = ejbSistemaFacade.getSistema(ConnectUsuario.getCodigoUsuario());
                ejbSistemaFacade.remove(sistema);

                ConnectUsuario.setUsuario(null);
                ConnectUsuario.setCodigoUsuario(0);
                ConnectUsuario.setTipoUsuario('N');
                Sesion.cerrarSesion();
                Sesion.redireccionaPagina("http://localhost:8080/SistemaBSC/faces/loginPlantilla.xhtml");

            } catch (ServletException ex) {
                Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                System.out.println("Redireccion Pagino no vale");
                Mensaje.addError("Redireccion Pagino no vale");
            }
        }
    }

    public void validSesionAdministrador() {
        System.out.println("Si entro a validar....");
        if ((Sesion.getVariable("usuario") == null) || ConnectUsuario.getTipoUsuario() != 'A') {
            try {
                Sesion.redireccionaPagina("http://localhost:8080/SistemaBSC/faces/home.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void validSesion() {
        try {
            Sesion.validaSesion();
        } catch (IOException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void setEmbeddableKeys() {

        this.getSelected().setFechaModificacion(new Date());

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

    /**
     * @return the confirmaContrasena
     */
    public String getConfirmaContrasena() {
        return confirmaContrasena;
    }

    /**
     * @param confirmaContrasena the confirmaContrasena to set
     */
    public void setConfirmaContrasena(String confirmaContrasena) {
        this.confirmaContrasena = confirmaContrasena;
    }

    public Usuario getLoginUsuario() {
        return ConnectUsuario.getUsuario();
    }

    public void setLoginUsuario(Usuario loginUsuario) {
        ConnectUsuario.setUsuario(loginUsuario);
    }
}
