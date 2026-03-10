/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.chat;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
/**
 *
 * @author Jhendef
 */
public class Chat {
    static List<ClientHandler> clientes = new CopyOnWriteArrayList<>();

    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(5056);
        System.out.println("Servidor iniciado");

        while (true) {
            Socket s = ss.accept();
            System.out.println("Nueva conexion " + s);

            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

            ClientHandler handler = new ClientHandler(s, dis, dos);
            clientes.add(handler);
            handler.start();
        }
    }

    public static void difundir(String mensaje, ClientHandler emisor) {
        for (ClientHandler cliente : clientes) {

            if (cliente != emisor) {
                try {
                    cliente.dos.writeUTF(mensaje);
                } catch (IOException e) {
                    clientes.remove(cliente);
                }
            }
        }
    }
}
