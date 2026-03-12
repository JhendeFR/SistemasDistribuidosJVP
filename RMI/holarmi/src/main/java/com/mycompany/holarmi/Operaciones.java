/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.holarmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Jhendef
 */
public class Operaciones extends UnicastRemoteObject implements IOperaciones {

    public Operaciones() throws RemoteException {
        super();
    }

    @Override
    public int factorial(int n) throws RemoteException {
        int res = 1;
        for (int i = 1; i <= n; i++) {
            res *= i;
        }
        return res;
    }

    @Override
    public String fibonacci(int n) throws RemoteException {
        String serie = "";

        int a = 0, b = 1, c;

        for (int i = 0; i < n; i++) {

            serie = serie + a + " ";

            c = a + b;
            a = b;
            b = c;
        }

        return serie;
    }

    @Override
    public int sumatoria(int n) throws RemoteException {
        return (n * (n + 1)) / 2;
    }
}
