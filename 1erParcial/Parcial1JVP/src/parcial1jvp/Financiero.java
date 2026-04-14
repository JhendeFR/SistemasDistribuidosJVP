/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parcial1jvp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.nio.channels.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 *
 * @author TECNOLOGIA
 */
public class Financiero {
    public static void main(String[] args) throws SocketException, IOException{
        DatagramSocket socket = new DatagramSocket(5002);
        System.out.println("udplisto");
        while(true){
            byte[] buffer = new byte[1024];
            DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);
            socket.receive(peticion);
            String data = new String(peticion.getData(), 0, peticion.getLength());
            String respuesta = data.equals("1234567") ? "" : "error";
            byte[] mensaje = respuesta.getBytes();
            socket.send(new DatagramPacket(mensaje, mensaje.length, peticion.getAddress(), peticion.getPort()));
        }
    }
}
