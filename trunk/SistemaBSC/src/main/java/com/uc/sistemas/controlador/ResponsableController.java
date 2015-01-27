package com.uc.sistemas.controlador;

import com.uc.sistemas.modelo.Responsable;

import java.io.Serializable;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

@Named("responsableController")
@SessionScoped
public class ResponsableController extends AbstractController<Responsable> implements Serializable {

    @EJB
    private com.uc.sistemas.facade.ResponsableFacade ejbFacade;

    public ResponsableController() {
        super(Responsable.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

    @Override
    public void create() {
        Date d = new Date();
        this.getSelected().setFechaModificacion(d);
        super.create(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update() {
        Date d = new Date();
        this.getSelected().setFechaModificacion(d);
        super.update(); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    protected void setEmbeddableKeys() {
        getSelected().getResponsablePK().setIdObjetivo(getSelected().getObjetivoEstrategico().getIdObjetivoEstrategico());
        getSelected().getResponsablePK().setIdUsuario(getSelected().getUsuario().getIdUsuario());
    }

    protected void initializeEmbeddableKey() {
        getSelected().setResponsablePK(new com.uc.sistemas.modelo.ResponsablePK());
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
