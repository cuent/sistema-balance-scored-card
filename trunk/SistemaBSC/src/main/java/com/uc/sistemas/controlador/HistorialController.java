/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uc.sistemas.controlador;

import com.uc.sistemas.controlador.util.Historial;
import com.uc.sistemas.modelo.Actividades;
import com.uc.sistemas.modelo.Conceptualizar;
import com.uc.sistemas.modelo.Indicador;
import com.uc.sistemas.modelo.Kpi;
import com.uc.sistemas.modelo.Meta;
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
import javax.faces.event.ActionEvent;
import javax.inject.Named;

/**
 *
 * @author mivkys
 */
@Named("historialController")
@SessionScoped
public class HistorialController extends AbstractController<ObjetivoEstrategico> implements Serializable {

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

    private List<Historial> fechas;
    private Historial fechaSeleccionada;
    private List<Kpi> itemsKpi;
    private List<Conceptualizar> itemsConceptualizar;
    private List<Meta> itemsMeta;
    private List<Indicador> itemsIndicador;
    private List<Actividades> itemsActividades;

    public HistorialController() {
        super(ObjetivoEstrategico.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        this.setSelected(new ObjetivoEstrategico());
        setFechaSeleccionada(new Historial());
        setFechas(new ArrayList<Historial>());
        fechaModificaciones();
        itemsKpi = new ArrayList<>();
        itemsConceptualizar = new ArrayList<>();
        itemsMeta = new ArrayList<>();
        itemsIndicador = new ArrayList<>();
        itemsActividades = new ArrayList<>();
    }

    public void fechaModificaciones() {
        getFechas().addAll(ejbFacade.getItemsFechaModificacion());
//        getFechas().addAll(ejbPerspectivaFacade.getItemsFechaModificacion());
//        getFechas().addAll(ejbActividadesFacade.getItemsFechaModificacion());
//        getFechas().addAll(ejbConceptualizarFacade.getItemsFechaModificacion());
//        getFechas().addAll(ejbEstrategiaGlobalFacade.getItemsFechaModificacion());
//        getFechas().addAll(ejbIndicadorFacade.getItemsFechaModificacion());
//        getFechas().addAll(ejbKpiFacade.getItemsFechaModificacion());
//        getFechas().addAll(ejbMetaFacade.getItemsFechaModificacion());
//        getFechas().addAll(ejbResponsableFacade.getItemsFechaModificacion());
    }

    public void buscar(ActionEvent event) {
        System.out.println("Sip");
//        if (fechaSeleccionada != null && fechaSeleccionada.getFechaModificacion() != null) {
//            if (fechaSeleccionada.getClase().equals(ObjetivoEstrategico.class.getSimpleName())) {
//                System.out.println("mirad: " + fechaSeleccionada.getId());
//                this.setSelected(ejbFacade.find(fechaSeleccionada.getId()));
//            }
//        }

        itemsKpi = ejbKpiFacade.getItemsObjetivoEstrategico(this.getSelected().getIdObjetivoEstrategico());
        itemsConceptualizar = ejbConceptualizarFacade.getItemsObjetivoEstrategico(this.getSelected().getIdObjetivoEstrategico());
        itemsMeta = ejbMetaFacade.getItemsObjetivoEstrategico(this.getSelected().getIdObjetivoEstrategico());
        itemsIndicador = ejbIndicadorFacade.getItemsObjetivoEstrategico(this.getSelected().getIdObjetivoEstrategico());
        itemsActividades = ejbActividadesFacade.getItemsObjetivoEstrategico(this.getSelected().getIdObjetivoEstrategico());
    }

    public String estadoActual(Object li, Object ls, Object act) {
        if (li != null && ls != null && act != null) {
            int inferior = Integer.parseInt(li.toString());
            int superior = Integer.parseInt(ls.toString());
            int actual = Integer.parseInt(act.toString());
            System.out.println("sipER"+ inferior + " "+superior+" "+ actual);
            if (actual < inferior) {
                return "../resources/image/rojo.gif";
            } else {
                if (actual > superior) {
                    return "../resources/image/verde.gif";
                } else {
                    return "../resources/image/amarillo.gif";
                }
            }
        }
        return "../resources/image/rojo.gif";
    }

    /**
     * @return the fechas
     */
    public List<Historial> getFechas() {
        return fechas;
    }

    /**
     * @param fechas the fechas to set
     */
    public void setFechas(List<Historial> fechas) {
        this.fechas = fechas;
    }

    /**
     * @return the fechaSeleccionada
     */
    public Historial getFechaSeleccionada() {
        return fechaSeleccionada;
    }

    /**
     * @param fechaSeleccionada the fechaSeleccionada to set
     */
    public void setFechaSeleccionada(Historial fechaSeleccionada) {
        this.fechaSeleccionada = fechaSeleccionada;
    }

    /**
     * @return the itemsKpi
     */
    public List<Kpi> getItemsKpi() {
        return itemsKpi;
    }

    /**
     * @param itemsKpi the itemsKpi to set
     */
    public void setItemsKpi(List<Kpi> itemsKpi) {
        this.itemsKpi = itemsKpi;
    }

    /**
     * @return the itemsConceptualizar
     */
    public List<Conceptualizar> getItemsConceptualizar() {
        return itemsConceptualizar;
    }

    /**
     * @param itemsConceptualizar the itemsConceptualizar to set
     */
    public void setItemsConceptualizar(List<Conceptualizar> itemsConceptualizar) {
        this.itemsConceptualizar = itemsConceptualizar;
    }

    /**
     * @return the itemsMeta
     */
    public List<Meta> getItemsMeta() {
        return itemsMeta;
    }

    /**
     * @param itemsMeta the itemsMeta to set
     */
    public void setItemsMeta(List<Meta> itemsMeta) {
        this.itemsMeta = itemsMeta;
    }

    /**
     * @return the itemsIndicador
     */
    public List<Indicador> getItemsIndicador() {
        return itemsIndicador;
    }

    /**
     * @param itemsIndicador the itemsIndicador to set
     */
    public void setItemsIndicador(List<Indicador> itemsIndicador) {
        this.itemsIndicador = itemsIndicador;
    }

    /**
     * @return the itemsActividades
     */
    public List<Actividades> getItemsActividades() {
        return itemsActividades;
    }

    /**
     * @param itemsActividades the itemsActividades to set
     */
    public void setItemsActividades(List<Actividades> itemsActividades) {
        this.itemsActividades = itemsActividades;
    }

}
