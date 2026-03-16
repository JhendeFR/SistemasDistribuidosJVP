/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.practica02;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Jhendef
 */
public interface IJusticia extends Remote {

    public RespuestaCuenta ConsultarCuentas(String ci, String nombres, String apellidos) throws RemoteException;

    public Boolean Congelar(Cuenta cuenta, Float monto) throws RemoteException;
}
