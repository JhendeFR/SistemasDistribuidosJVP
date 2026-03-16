/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practica02;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Jhendef
 */
class RespuestaCuenta implements Serializable{
    boolean error;
    String mensaje;
    ArrayList<Cuenta> cuentas = new ArrayList<>();

    public RespuestaCuenta(boolean error, String mensaje) {
        this.error = error;
        this.mensaje = mensaje;
    }
}
