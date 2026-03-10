/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chat;
import java.io.*;
import java.net.*;
import java.util.Scanner;
/**
 *
 * @author Jhendef
 */
public class Cliente {
    public static void main(String[] args) {
        try {
            Scanner scn = new Scanner(System.in);
            Socket s = new Socket("localhost", 5056);
            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

            Thread leerMensajes = new Thread(() -> {
                try {
                    while (true) {
                        String msg = dis.readUTF();
                        System.out.println(msg);
                        System.out.print("> ");
                    }
                } catch (IOException e) {
                    System.out.println("Conexion con el servidor cerrada");
                }
            });
            leerMensajes.start();

            while (true) {
                String mensajeEnviar = scn.nextLine();
                dos.writeUTF(mensajeEnviar);

                if (mensajeEnviar.equalsIgnoreCase("exit")) {
                    break;
                }
            }

            s.close();
            System.exit(0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
