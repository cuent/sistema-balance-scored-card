/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uc.sistemas.security;

import com.uc.sistemas.modelo.Usuario;

/**
 *
 * @author mivkys
 */
public class ConnectUsuario {
    // -------------------------------------------------------------------------
    // PROPIEDAD SET
   
    public static void setCodigoUsuario(int codigoUsuario) {
        Sesion.setVariable("codigoUsuario", codigoUsuario);
    }
    public static void setUsuario(Usuario usuario) {
        Sesion.setVariable("usuario", usuario);
    }
    // -------------------------------------------------------------------------
    // PROPIEDAD GET
    /**
     * @return the codigoUsuario
     */
    public static int getCodigoUsuario() {
        return Integer.parseInt((Sesion.getVariable("codigoUsuario") != null) ? Sesion.getVariable("codigoUsuario").toString() : null);
    }
    public static Usuario getUsuario() {
        return (Usuario) Sesion.getVariable("usuario");
    }
}
