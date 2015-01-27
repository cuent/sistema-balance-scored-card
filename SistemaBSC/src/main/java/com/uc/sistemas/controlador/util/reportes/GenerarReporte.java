/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uc.sistemas.controlador.util.reportes;

import com.itextpdf.text.DocumentException;
import com.uc.sistemas.modelo.EstrategiaGlobal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pablito
 */
public class GenerarReporte {
    
    
    
    public void EstrategiasGlobales(List<EstrategiaGlobal> estrategiasBlobales){
      GenerarEstrategiasGlobales generarEstrategia = new GenerarEstrategiasGlobales();
        try {
            generarEstrategia.generardocumento(estrategiasBlobales);
        } catch (DocumentException ex) {
            Logger.getLogger(GenerarReporte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void EstrategiasObjetivosEstrategicos(){
           
    }
}
