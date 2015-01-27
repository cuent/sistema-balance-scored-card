package com.uc.sistemas.controlador;

import com.uc.sistemas.controlador.util.reportes.GenerarReporte;
import com.uc.sistemas.modelo.EstrategiaGlobal;
import com.uc.sistemas.modelo.ObjetivoEstrategico;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.util.Date;
import java.util.List;

@Named("estrategiaGlobalController")
@SessionScoped
public class EstrategiaGlobalController extends AbstractController<EstrategiaGlobal> implements Serializable {

    @EJB
    private com.uc.sistemas.facade.EstrategiaGlobalFacade ejbFacade;

    public EstrategiaGlobalController() {
        super(EstrategiaGlobal.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }
    public void generarInforme(){
      List<EstrategiaGlobal> listaGlobales = getItems();
      GenerarReporte generarReporte = new GenerarReporte();
      generarReporte.EstrategiasGlobales(listaGlobales);
    
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
