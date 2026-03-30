/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.jgroup;

import java.util.Scanner;

/**
 *
 * @author Jhendef
 */
public class Principal {

    public static void main(String[] args) {
        System.setProperty("java.net.preferIPv4Stack", "true");
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Selecciona: \n1- Sincronizacion \n2- Votacion");
            int choice = sc.nextInt();
            sc.nextLine(); 
            if (choice == 1) {
                new StateSyncCluster().start();
            } else if (choice == 2) {
                new Votacion().start();
            }
        } catch (Exception ex) {
            System.getLogger(Principal.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
}