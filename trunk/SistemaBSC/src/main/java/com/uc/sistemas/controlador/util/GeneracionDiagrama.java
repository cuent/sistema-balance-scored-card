/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uc.sistemas.controlador.util;

import com.uc.sistemas.controlador.ObjetivoEstrategicoController;
import com.uc.sistemas.modelo.Conceptualizar;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;


/**
 *
 * @author pablito
 */
public class GeneracionDiagrama {

    private String codigoDiagrama;
    private ArrayList<Conceptualizar> listaDefinirFinaciera;
    private ArrayList<Conceptualizar> listaDefinirCliente;
    private ArrayList<Conceptualizar> listaDefinirProceso;
    private ArrayList<Conceptualizar> listaDefinirAprendizaje;
    private String rutaImagen;
    private String cabezera = "digraph g {\n"
            + "    graph [dpi=700,fontsize=8 fontname=\"Verdana\" compound=true]; ";

    private String finalcabezera = "}";

    private ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();

    private String llenarFinanciera() {
        String inicio = "subgraph clusterA { \n"
                + "graph [color=black, label=\"Financiera\", rank=min,style=dotted,fixedsize=true,width=1]; ";

        for (Conceptualizar definir : getListaDefinirFinaciera()) {
            inicio = inicio + "f" + definir.getIdConceptualizar() + " [label=\"" + definir.getDefinir() + "\",fillcolor=\"lightblue\",style=\"filled\"]; ";

        }
        for (Conceptualizar definir : getListaDefinirFinaciera()) {
            inicio = inicio + "f" + definir.getIdConceptualizar() + "; ";
        }
        return inicio + "};\n ";
    }
    
    private String llenarCliente(){
          String inicio = "subgraph clusterB { \n"
                + "graph [color=black, label=\"Cliente\", rank=min,style=dotted,fixedsize=true,width=1]; ";

        for (Conceptualizar definir : getListaDefinirCliente()) {
            inicio = inicio + "c" + definir.getIdConceptualizar() + " [label=\"" + definir.getDefinir() + "\",fillcolor=\"lightblue\",style=\"filled\"]; ";

        }
        for (Conceptualizar definir : getListaDefinirCliente()) {
            inicio = inicio + "c" + definir.getIdConceptualizar() + "; ";
        }
        return inicio + "};\n ";
    }
    
    public void generardiagrama() throws IOException {

        generarArchivo();

        String ruta = getEc().getRealPath("Archivos");

        try {

            String dotPath = "C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot.exe";
            String fileInputPath = ruta + "\\grafo1.txt";
            String fileOutputPath = ruta + "\\grafo1.jpg";
            String tParam = "-Tjpg";
            String tOParam = "-o";
            String[] cmd = new String[5];
            cmd[0] = dotPath;
            cmd[1] = tParam;
            cmd[2] = fileInputPath;
            cmd[3] = tOParam;
            cmd[4] = fileOutputPath;
            Runtime rt = Runtime.getRuntime();
            rt.exec(cmd);

            setRutaImagen("Archivos/grafo1.jpg");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
        }
    }

    public void generarArchivo() throws IOException {
        String ruta = getEc().getRealPath("Archivos") + "/grafo1.txt";
        System.out.println(getEc().getRealPath("Archivos"));
        File archivo = new File(ruta);
        BufferedWriter bw;
        if (archivo.exists()) {
            bw = new BufferedWriter(new FileWriter(archivo));
            //System.out.println(llenarFinanciera());
            bw.write(getCabezera() + llenarFinanciera() +llenarCliente()+ finalcabezera);
        } else {
            bw = new BufferedWriter(new FileWriter(archivo));
            bw.write("Acabo de crear el fichero de texto.");
        }
        bw.close();
    }

    /**
     * @return the rutaImagen
     */
    public String getRutaImagen() {
        return rutaImagen;
    }

    /**
     * @param rutaImagen the rutaImagen to set
     */
    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    /**
     * @return the codigoDiagrama
     */
    public String getCodigoDiagrama() {
        return codigoDiagrama;
    }

    /**
     * @param codigoDiagrama the codigoDiagrama to set
     */
    public void setCodigoDiagrama(String codigoDiagrama) {
        this.codigoDiagrama = codigoDiagrama;
    }

    /**
     * @return the listaDefinirFinaciera
     */
    public ArrayList<Conceptualizar> getListaDefinirFinaciera() {
        return listaDefinirFinaciera;
    }

    /**
     * @param listaDefinirFinaciera the listaDefinirFinaciera to set
     */
    public void setListaDefinirFinaciera(ArrayList<Conceptualizar> listaDefinirFinaciera) {
        this.listaDefinirFinaciera = listaDefinirFinaciera;
    }

    /**
     * @return the listaDefinirCliente
     */
    public ArrayList<Conceptualizar> getListaDefinirCliente() {
        return listaDefinirCliente;
    }

    /**
     * @param listaDefinirCliente the listaDefinirCliente to set
     */
    public void setListaDefinirCliente(ArrayList<Conceptualizar> listaDefinirCliente) {
        this.listaDefinirCliente = listaDefinirCliente;
    }

    /**
     * @return the listaDefinirProceso
     */
    public ArrayList<Conceptualizar> getListaDefinirProceso() {
        return listaDefinirProceso;
    }

    /**
     * @param listaDefinirProceso the listaDefinirProceso to set
     */
    public void setListaDefinirProceso(ArrayList<Conceptualizar> listaDefinirProceso) {
        this.listaDefinirProceso = listaDefinirProceso;
    }

    /**
     * @return the listaDefinirAprendizaje
     */
    public ArrayList<Conceptualizar> getListaDefinirAprendizaje() {
        return listaDefinirAprendizaje;
    }

    /**
     * @param listaDefinirAprendizaje the listaDefinirAprendizaje to set
     */
    public void setListaDefinirAprendizaje(ArrayList<Conceptualizar> listaDefinirAprendizaje) {
        this.listaDefinirAprendizaje = listaDefinirAprendizaje;
    }

    /**
     * @return the cabezera
     */
    public String getCabezera() {
        return cabezera;
    }

    /**
     * @param cabezera the cabezera to set
     */
    public void setCabezera(String cabezera) {
        this.cabezera = cabezera;
    }

    /**
     * @return the finalcabezera
     */
    public String getFinalcabezera() {
        return finalcabezera;
    }

    /**
     * @param finalcabezera the finalcabezera to set
     */
    public void setFinalcabezera(String finalcabezera) {
        this.finalcabezera = finalcabezera;
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
