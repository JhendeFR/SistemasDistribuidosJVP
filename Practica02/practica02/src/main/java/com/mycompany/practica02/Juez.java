/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practica02;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

/**
 *
 * @author Jhendef
 */
public class Juez {

    public static void main(String[] args) {
        try {
            IJusticia cn = (IJusticia) Naming.lookup("rmi://localhost/Justicia");
            Scanner sc = new Scanner(System.in);
            int op;
            do {
                System.out.println("Operaciones");
                System.out.println("1.Solicitar informacion");
                System.out.println("2.Ordenar congelamiento cuentas");
                System.out.println("3.Salir");
                op = sc.nextInt();
                sc.nextLine();
                switch (op) {
                    case 1:
                        System.out.println("Ingresa el C.I.-");
                        String ci = sc.nextLine();
                        System.out.println("Nombres:");
                        String nombres = sc.nextLine();
                        System.out.println("Apellidos:");
                        String apellidos = sc.nextLine();
                        RespuestaCuenta res = cn.ConsultarCuentas(ci, nombres, apellidos);
                        for (Cuenta c : res.cuentas) {
                            System.out.println(c.getBanco() + " - Cuenta: " + c.getNrocuenta() + " - Saldo: " + c.getSaldo());
                        }
                        break;
                    case 2:
                        System.out.print("Ingrese el CI para buscar cuentas a congelar: ");
                        String ciBusca = sc.nextLine();
                        RespuestaCuenta respuesta = cn.ConsultarCuentas(ciBusca, "Juan", "Perez");

                        if (respuesta.cuentas.isEmpty()) {
                            System.out.println("No se encontraron cuentas");
                        } else {
                            for (int i = 0; i < respuesta.cuentas.size(); i++) {
                                System.out.println(i + ". " + respuesta.cuentas.get(i).getNrocuenta() + " (" + respuesta.cuentas.get(i).getBanco() + ")");
                            }
                            System.out.print("Seleccione el indice de la cuenta: ");
                            int idx = sc.nextInt();
                            System.out.print("Ingrese el monto a congelar: ");
                            float montoC = sc.nextFloat();

                            if (cn.Congelar(respuesta.cuentas.get(idx), montoC)) {
                                System.out.println("Fondos congelados");
                            } else {
                                System.out.println("Error al congelar");
                            }
                        }
                        break;
                    default:
                        break;
                }
            } while (op != 3);

        } catch (RemoteException | NotBoundException | MalformedURLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
}