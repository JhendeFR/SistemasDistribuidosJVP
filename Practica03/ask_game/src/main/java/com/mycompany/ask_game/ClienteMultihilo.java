/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ask_game;

import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 *
 * @author Jhendef
 */
public class ClienteMultihilo {
    public static void main(String[] args) {
        try (Scanner scn = new Scanner(System.in);
             Socket s = new Socket("localhost", 5056);
             DataInputStream dis = new DataInputStream(s.getInputStream());
             DataOutputStream dos = new DataOutputStream(s.getOutputStream())) {

            while (true) {
                String mensajeServidor = dis.readUTF();
                System.out.println(mensajeServidor);

                if (mensajeServidor.contains("Juego terminado")) break;

                if (mensajeServidor.contains(":") || mensajeServidor.contains("?")) {
                    String respuesta = scn.nextLine();
                    dos.writeUTF(respuesta);
                }
            }

        } catch (Exception e) {
            System.out.println("Desconectado del servidor");
        }
    }
}
