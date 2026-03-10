/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chat;
import java.io.*;
import java.net.*;
/**
 *
 * @author Jhendef
 */
public class ClientHandler extends Thread {
    final DataInputStream dis;
    final DataOutputStream dos;
    final Socket s;
    String nombre;

    public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos) {
        this.s = s;
        this.dis = dis;
        this.dos = dos;
    }

    @Override
    public void run() {
        try {
            dos.writeUTF("Ingrese su nombre:");
            this.nombre = dis.readUTF();
            
            Chat.difundir("--- " + nombre + " se ha unido al chat ---", this);

            String mensaje;
            while (true) {
                mensaje = dis.readUTF();

                if (mensaje.equalsIgnoreCase("exit")) {
                    break;
                }
              
                Chat.difundir(nombre + ": " + mensaje, this);
            }

        } catch (IOException e) {
            System.out.println("Se desconecto" + nombre);
        } finally {
            Chat.clientes.remove(this);
            Chat.difundir(nombre + " ha salido del chat", this);
            try {
                this.s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}