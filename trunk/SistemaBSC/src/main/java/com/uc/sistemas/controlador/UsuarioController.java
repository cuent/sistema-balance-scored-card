package com.uc.sistemas.controlador;

import com.uc.sistemas.modelo.Usuario;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

@Named("usuarioController")
@SessionScoped
public class UsuarioController extends AbstractController<Usuario> implements Serializable {

    @EJB
    private com.uc.sistemas.facade.UsuarioFacade ejbFacade;

    public UsuarioController() {
        super(Usuario.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
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
}
