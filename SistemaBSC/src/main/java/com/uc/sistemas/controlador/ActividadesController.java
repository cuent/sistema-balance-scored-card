package com.uc.sistemas.controlador;

import com.uc.sistemas.modelo.Actividades;

import java.io.Serializable;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

@Named("actividadesController")
@SessionScoped
public class ActividadesController extends AbstractController<Actividades> implements Serializable {

    @EJB
    private com.uc.sistemas.facade.ActividadesFacade ejbFacade;
public ActividadesController() {
        super(Actividades.class);
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
