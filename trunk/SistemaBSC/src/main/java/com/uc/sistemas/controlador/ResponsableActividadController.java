package com.uc.sistemas.controlador;

import com.uc.sistemas.modelo.ResponsableActividad;

import java.io.Serializable;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

@Named("responsableActividadController")
@SessionScoped
public class ResponsableActividadController extends AbstractController<ResponsableActividad> implements Serializable {

    @EJB
    private com.uc.sistemas.facade.ResponsableActividadFacade ejbFacade;

    public ResponsableActividadController() {
        super(ResponsableActividad.class);
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
        getSelected().getResponsableActividadPK().setIdUsuario(getSelected().getUsuario().getIdUsuario());
        getSelected().getResponsableActividadPK().setIdActividad(getSelected().getActividades().getIdActividades());
    }

    protected void initializeEmbeddableKey() {
        getSelected().setResponsableActividadPK(new com.uc.sistemas.modelo.ResponsableActividadPK());
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
