/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.holarmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Jhendef
 */
public interface IOperaciones extends Remote {

    int factorial(int n) throws RemoteException;

    String fibonacci(int n) throws RemoteException;

    int sumatoria(int n) throws RemoteException;
}
