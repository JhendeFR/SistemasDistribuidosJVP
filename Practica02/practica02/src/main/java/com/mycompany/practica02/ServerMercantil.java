/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practica02;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Jhendef
 */
public class ServerMercantil {

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(5001);
        System.out.println("Banco Mercantil (TCP) listo");
        while (true) {
            try (Socket cliente = server.accept(); DataInputStream in = new DataInputStream(cliente.getInputStream()); DataOutputStream out = new DataOutputStream(cliente.getOutputStream())) {

                String ci = in.readUTF();
                String op = in.readUTF();

                if (op.equals("buscar")) {
                    String respuesta = ci.equals("11021654") ? "1515-5200" : "";
                    out.writeUTF(respuesta);
                } else if (op.equals("congelar")) {
                    String nroCuenta = in.readUTF();
                    float monto = in.readFloat();
                    System.out.println("Congelando " + monto + " en cuenta " + nroCuenta);
                    out.writeBoolean(true);
                }
            }
        }
    }
}
