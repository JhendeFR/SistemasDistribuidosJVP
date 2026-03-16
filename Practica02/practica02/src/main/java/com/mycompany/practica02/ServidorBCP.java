/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practica02;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 *
 * @author Jhendef
 */
public class ServidorBCP {

    public static void main(String[] args) throws Exception {
        DatagramSocket socketUDP = new DatagramSocket(5002);
        System.out.println("Banco BCP (UDP) listo");

        while (true) {
            byte[] buffer = new byte[1024];
            DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);
            socketUDP.receive(peticion);

            String data = new String(peticion.getData(), 0, peticion.getLength());

            String[] partes = data.split(":");
            String op = partes[0];

            if (op.equals("buscar")) {
                String ci = partes[1];
                String respuesta = ci.equals("11021654") ? "657654-6000" : "";
                byte[] msg = respuesta.getBytes();
                socketUDP.send(new DatagramPacket(msg, msg.length, peticion.getAddress(), peticion.getPort()));
            } else if (op.equals("congelar")) {
                String nroCuenta = partes[1];
                String monto = partes[2];
                System.out.println("BCP congelando " + monto + " en cuenta " + nroCuenta);
                byte[] msg = "SI".getBytes();
                socketUDP.send(new DatagramPacket(msg, msg.length, peticion.getAddress(), peticion.getPort()));
            }
        }
    }
}
