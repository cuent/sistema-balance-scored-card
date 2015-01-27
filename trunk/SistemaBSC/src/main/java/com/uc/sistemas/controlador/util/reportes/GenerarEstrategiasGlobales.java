/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uc.sistemas.controlador.util.reportes;

import com.itextpdf.text.DocumentException;
import com.uc.sistemas.modelo.EstrategiaGlobal;
import java.util.List;
import java.util.Vector;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import jq733100.utilidades.CodigoBarra;
import jq733100.utilidades.Documento;
import jq733100.utilidades.Encabezado;
import jq733100.utilidades.EspacioBlanco;
import jq733100.utilidades.Imagen;
import jq733100.utilidades.LineaCentrada;
import jq733100.utilidades.LineaNormal;
import jq733100.utilidades.TablaHorizontal;
import jq733100.utilidades.TablaVertical;
import jq733100.utilidades.Titulo1;
import jq733100.utilidades.Titulo2;
import jq733100.utilidades.Titulo3;
import jq733100.utilidades.Titulo4;

/**
 *
 * @author pablito
 */
public class GenerarEstrategiasGlobales {
    
    String rutaDestino = "";
    private Encabezado encabezado;
    private CodigoBarra codigoBarra;
    
    private LineaNormal lineaNormal;
    private LineaCentrada lineaCentrada;
    private TablaHorizontal tablaHorizontal;
    private TablaVertical tablaVertical;
    private Imagen imagen;
    private Titulo1 titulo1;
    private Titulo2 titulo2;
    private Titulo3 titulo3;
    private Titulo4 titulo4;
    private EspacioBlanco espacioBlanco;
    private Documento documento;
     private ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
    public GenerarEstrategiasGlobales() {
        
        encabezado = new Encabezado();
        codigoBarra = new CodigoBarra();
        espacioBlanco = new EspacioBlanco();
        lineaNormal = new LineaNormal();
        lineaCentrada = new LineaCentrada();
        tablaHorizontal = new TablaHorizontal();
        tablaVertical = new TablaVertical();
        imagen = new Imagen();
        titulo1 = new Titulo1();
        titulo2 = new Titulo2();
        titulo3 = new Titulo3();
        titulo4 = new Titulo4();
    }
    
    public void generardocumento(List<EstrategiaGlobal> estrategiasBlobales) throws DocumentException {
       String ruta = getEc().getRealPath("Archivos");
       
        documento = new Documento(ruta+"/globales.pdf");
        documento.setMargins(60, 30, 30, 40);
        documento.open();
        cabezera(estrategiasBlobales);
        documento.close();
        
    }

    public void cabezera(List<EstrategiaGlobal> estrategiasBlobales) throws DocumentException {
        titulo1.setTexto("Reporte Estrategias Globales y Objetivos Estratégicos");
        espacioBlanco.getElemento();
        //imagen.setDir("");
        tablaHorizontal.setPosicion(0);
        documento.add(titulo1.getElemento());
        tablaVertical.limpiar(2);
        
         
        tablaVertical.setTitulos("ID", "Descripcion");
        Vector vector = new Vector();
        for (EstrategiaGlobal estrategiasBlobale : estrategiasBlobales) {
            vector.add(estrategiasBlobale);
        }
        
        
        tablaVertical.setContenidos(vector.toArray());
        tablaVertical.setAlineamientos(new int[] { 0, 0 });
        tablaVertical.llenarTabla(false);
        tablaVertical.setPosicion(2);
        tablaVertical.setTamanos(new int[] { 25, 75 });
        tablaVertical.setAnchoTabla(100);
        documento.add(tablaVertical.getElemento());  
    }
    
    public void cuerpo() {
        
    }
    
    public void piePaguina() {
        
    }

    /**
     * @return the ec
     */
    public ExternalContext getEc() {
        return ec;
    }

    /**
     * @param ec the ec to set
     */
    public void setEc(ExternalContext ec) {
        this.ec = ec;
    }
    
}
