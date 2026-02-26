/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.sis258.client.hola.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 *
 * @author Dell
 */
public class ClientHolaTcp {

    public static void main(String[] args) {
        int port = 5002;

        try {
            String ipServidor = "10.8.221.102";
            Socket client = new Socket(ipServidor, port);

            BufferedReader teclado = new BufferedReader(
                    new InputStreamReader(System.in));

            PrintStream toServer = new PrintStream(client.getOutputStream());
            BufferedReader fromServer = new BufferedReader(
                    new InputStreamReader(client.getInputStream()));

            System.out.print("Ingrese una cadena: ");
            String cadena = teclado.readLine();

            //Enviar al servidor
            toServer.println(cadena);

            //Recibir
            String result = fromServer.readLine();
            System.out.println("Cadena invertida recibida: " + result);

            client.close();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
