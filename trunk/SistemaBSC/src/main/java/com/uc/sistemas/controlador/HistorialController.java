/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uc.sistemas.controlador;

import com.uc.sistemas.modelo.ObjetivoEstrategico;
import com.uc.sistemas.modelo.Usuario;
import com.uc.sistemas.security.ConnectUsuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author mivkys
 */
@Named("historialController")
@SessionScoped
public class HistorialController extends AbstractController<ObjetivoEstrategico> implements Serializable{
    @EJB
    private com.uc.sistemas.facade.ObjetivoEstrategicoFacade ejbFacade;
    @EJB
    private com.uc.sistemas.facade.PerspectivaFacade ejbPerspectivaFacade;
    @EJB
    private com.uc.sistemas.facade.ActividadesFacade ejbActividadesFacade;
    @EJB
    private com.uc.sistemas.facade.ConceptualizarFacade ejbConceptualizarFacade;
    @EJB
    private com.uc.sistemas.facade.EstrategiaGlobalFacade ejbEstrategiaGlobalFacade;
    @EJB
    private com.uc.sistemas.facade.IndicadorFacade ejbIndicadorFacade;
    @EJB
    private com.uc.sistemas.facade.KpiFacade ejbKpiFacade;
    @EJB
    private com.uc.sistemas.facade.MetaFacade ejbMetaFacade;
    @EJB
    private com.uc.sistemas.facade.ResponsableFacade ejbResponsableFacade;
    ///<!-- #{usuarioController.validSesion()} -->
    
    private List<Date> fechas;
    private Date fechaSeleccionada;
    public HistorialController() {
        super(ObjetivoEstrategico.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        this.setSelected(new ObjetivoEstrategico());
        fechaSeleccionada = new Date();
        setFechas(new ArrayList<Date>());
        fechaModificaciones();
    }
    public void fechaModificaciones(){
        getFechas().addAll(ejbFacade.getItemsFechaModificacion());
        getFechas().addAll(ejbPerspectivaFacade.getItemsFechaModificacion());
        getFechas().addAll(ejbActividadesFacade.getItemsFechaModificacion());
        getFechas().addAll(ejbConceptualizarFacade.getItemsFechaModificacion());
        getFechas().addAll(ejbEstrategiaGlobalFacade.getItemsFechaModificacion());
        getFechas().addAll(ejbIndicadorFacade.getItemsFechaModificacion());
        getFechas().addAll(ejbKpiFacade.getItemsFechaModificacion());
        getFechas().addAll(ejbMetaFacade.getItemsFechaModificacion());
        getFechas().addAll(ejbResponsableFacade.getItemsFechaModificacion());
    }
    /**
     * @return the fechas
     */
    public List<Date> getFechas() {
        return fechas;
    }

    /**
     * @param fechas the fechas to set
     */
    public void setFechas(List<Date> fechas) {
        this.fechas = fechas;
    }

    /**
     * @return the fechaSeleccionada
     */
    public Date getFechaSeleccionada() {
        return fechaSeleccionada;
    }

    /**
     * @param fechaSeleccionada the fechaSeleccionada to set
     */
    public void setFechaSeleccionada(Date fechaSeleccionada) {
        this.fechaSeleccionada = fechaSeleccionada;
    }
}