/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practica02;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Jhendef
 */
public class Justicia extends UnicastRemoteObject implements IJusticia {

    public Justicia() throws RemoteException {
        super();
    }

    @Override
    public RespuestaCuenta ConsultarCuentas(String ci, String nombres, String apellidos) {
        RespuestaCuenta respuesta = new RespuestaCuenta(false, "Consulta realizada");

        try (Socket socket = new Socket("localhost", 5001); DataInputStream in = new DataInputStream(socket.getInputStream()); DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {

            out.writeUTF(ci);
            out.writeUTF("buscar");
            String resTCP = in.readUTF(); // Formato: cuenta-saldo:cuenta-saldo
            procesarCadena(resTCP, Bancos.MERCANTIL, ci, nombres, apellidos, respuesta);

        } catch (IOException e) {
            System.out.println("Error con Mercantil: " + e.getMessage());
        }
        try (DatagramSocket udpSocket = new DatagramSocket()) {
            String msg = "buscar:" + ci;
            byte[] buffer = msg.getBytes();
            DatagramPacket pqtRequest = new DatagramPacket(buffer, buffer.length, InetAddress.getByName("localhost"), 5002);
            udpSocket.send(pqtRequest);

            byte[] receiveBuffer = new byte[1024];
            DatagramPacket pqtResponse = new DatagramPacket(receiveBuffer, receiveBuffer.length);
            udpSocket.receive(pqtResponse);

            String resUDP = new String(pqtResponse.getData(), 0, pqtResponse.getLength());
            procesarCadena(resUDP, Bancos.BCP, ci, nombres, apellidos, respuesta);

        } catch (IOException e) {
            System.out.println("Error con BCP: " + e.getMessage());
        }

        return respuesta;
    }

    @Override
    public Boolean Congelar(Cuenta cuenta, Float monto) throws RemoteException {
        boolean exito = false;

        if (cuenta.getBanco() == Bancos.MERCANTIL) {
            try (Socket socket = new Socket("localhost", 5001); DataInputStream in = new DataInputStream(socket.getInputStream()); DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {

                out.writeUTF(cuenta.getCi());     
                out.writeUTF("congelar");        
                out.writeUTF(cuenta.getNrocuenta()); 
                out.writeFloat(monto);        

                exito = in.readBoolean();

            } catch (IOException e) {
                System.out.println("Error TCP al congelar: " + e.getMessage());
            }
        } else if (cuenta.getBanco() == Bancos.BCP) {
            try (DatagramSocket udpSocket = new DatagramSocket()) {
                String msg = "congelar:" + cuenta.getNrocuenta() + ":" + monto;
                byte[] buffer = msg.getBytes();
                DatagramPacket pqtRequest = new DatagramPacket(buffer, buffer.length,
                        InetAddress.getByName("localhost"), 5002);
                udpSocket.send(pqtRequest);

                byte[] receiveBuffer = new byte[1024];
                DatagramPacket pqtResponse = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                udpSocket.receive(pqtResponse);

                String resUDP = new String(pqtResponse.getData(), 0, pqtResponse.getLength());
                exito = resUDP.trim().equalsIgnoreCase("SI");

            } catch (IOException e) {
                System.out.println("Error UDP al congelar: " + e.getMessage());
            }
        }

        return exito;
    }

    private void procesarCadena(String cadena, Bancos banco, String ci, String nom, String ape, RespuestaCuenta resp) {
        if (cadena == null || cadena.isEmpty()) {
            return;
        }
        String[] registros = cadena.split(":");
        for (String reg : registros) {
            String[] datos = reg.split("-"); // cuenta-saldo
            resp.cuentas.add(new Cuenta(banco, datos[0], ci, nom, ape, Double.parseDouble(datos[1])));
        }
    }
}
