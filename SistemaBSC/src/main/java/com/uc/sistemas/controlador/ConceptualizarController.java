package com.uc.sistemas.controlador;

import com.uc.sistemas.controlador.util.GeneracionDiagrama;
import com.uc.sistemas.modelo.Conceptualizar;
import com.uc.sistemas.modelo.Conceptualizar_;
import java.io.IOException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

@Named("conceptualizarController")
@SessionScoped
public class ConceptualizarController extends AbstractController<Conceptualizar> implements Serializable {

    @EJB
    private com.uc.sistemas.facade.ConceptualizarFacade ejbFacade;
    private String ruta = "";

    public ConceptualizarController() {
        super(Conceptualizar.class);
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
    
    

    public void generarMapaEstrategico() {

        ArrayList<Conceptualizar> listaDefinirFinaciera = new ArrayList<Conceptualizar>();
        ArrayList<Conceptualizar> listaDefinirCliente = new ArrayList<Conceptualizar>();
        ArrayList<Conceptualizar> listaDefinirProceso = new ArrayList<Conceptualizar>();
        ArrayList<Conceptualizar> listaDefinirAprendizaje = new ArrayList<Conceptualizar>();

        for (Conceptualizar definir : getItems()) {
            String perspectiva = definir.getIdObjetivoEstrategico().getIdPerspectiva().getDescripcion();
            if (perspectiva.equalsIgnoreCase("Financiera")) {
                listaDefinirFinaciera.add(definir);
            }
            if (perspectiva.equalsIgnoreCase("Cliente")) {
                listaDefinirCliente.add(definir);
            }
            if (perspectiva.equalsIgnoreCase("Procesos internos")) {
                listaDefinirProceso.add(definir);
            }
            if (perspectiva.equalsIgnoreCase("Aprendizaje")) {
                listaDefinirAprendizaje.add(definir);
            }
        }
      
        GeneracionDiagrama diagrama = new GeneracionDiagrama();
        diagrama.setListaDefinirAprendizaje(listaDefinirAprendizaje);
        diagrama.setListaDefinirProceso(listaDefinirProceso);
        diagrama.setListaDefinirCliente(listaDefinirCliente);
        diagrama.setListaDefinirFinaciera(listaDefinirFinaciera);
        try {
            
            diagrama.generarArchivo();
            diagrama.generardiagrama();
        } catch (IOException ex) {
            Logger.getLogger(ConceptualizarController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        setRuta(diagrama.getRutaImagen());
        System.out.println("Generar Diagrama");
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
     * @return the ruta
     */
    public String getRuta() {
        return ruta;
    }

    /**
     * @param ruta the ruta to set
     */
    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

}
