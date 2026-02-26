/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.server.hola.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Dell
 */
public class ServerHolaTcp {

    public static void main(String[] args) {
        int port = 5002;
        try {
            ServerSocket server = new ServerSocket(port);
            System.out.println("Servidor iniciado en puerto " + port);

            while (true) {
                Socket client = server.accept();
                System.out.println("Cliente conectado");

                BufferedReader fromClient = new BufferedReader(
                        new InputStreamReader(client.getInputStream()));

                PrintStream toClient = new PrintStream(client.getOutputStream());

                String recibido = fromClient.readLine();
                System.out.println("Mensaje recibido: " + recibido);

                //Invertir
                String invertida = new StringBuilder(recibido).reverse().toString();

                // Enviar
                toClient.println(invertida);

                client.close();
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
