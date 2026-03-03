/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.server;

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
public class Server {

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
                toClient.println("Ingrese el primer numero");
                String s1 = fromClient.readLine();
                double n1 = Double.parseDouble(s1);
                System.out.println("Mensaje recibido: " + n1);
                toClient.println("Ingrese la operacion (+ , - , * , /)");
                String op = fromClient.readLine();
                System.out.println("Mensaje recibido: " + op);
                toClient.println("Ingrese el segundo numero");
                String s2 = fromClient.readLine();
                double n2 = Double.parseDouble(s2);
                System.out.println("Mensaje recibido: " + n2);

                //Invertir
                double resultado=0;
                String error = "";
                switch (op) {
                    case "+" -> resultado = n1 + n2;
                    case "-" -> resultado = n1 - n2;
                    case "*" -> resultado = n1 * n2;
                    case "/" -> resultado = n1 / n2;
                    default -> error = "Op no valida";
                }

                // Enviar
                toClient.println(resultado);

                client.close();
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
