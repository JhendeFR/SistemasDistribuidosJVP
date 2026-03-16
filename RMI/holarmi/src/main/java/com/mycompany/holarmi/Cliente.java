/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.holarmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

/**
 *
 * @author Jhendef
 */
public class Cliente {

    public static void main(String[] args) {
        try {
            IOperaciones hola = (IOperaciones) Naming.lookup("rmi://localhost/Operaciones");
            Scanner sc = new Scanner(System.in);
            int n;
            int op;
            do {
                System.out.println("Operaciones");
                System.out.println("1.factorial");
                System.out.println("2.fibonaci");
                System.out.println("3.sumatoria");
                op = sc.nextInt();
                System.out.println("Ingresar el valor de n");
                n = sc.nextInt();
                switch (op) {
                    case 1:
                        System.out.println("Resultado Factorial: " + hola.factorial(n));
                        break;
                    case 2:
                        System.out.println("Resultado Factorial: " + hola.fibonacci(n));
                        break;
                    case 3:
                        System.out.println("Resultado Factorial: " + hola.sumatoria(n));
                    default:
                        break;
                }
            } while (op != 4);
        } catch (NotBoundException ex) {
            System.getLogger(Cliente.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (MalformedURLException ex) {
            System.getLogger(Cliente.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (RemoteException ex) {
            System.getLogger(Cliente.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

    }

}
