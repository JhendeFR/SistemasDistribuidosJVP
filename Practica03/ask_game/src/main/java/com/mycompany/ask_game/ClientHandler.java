/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ask_game;

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
    String nombreJugador;

    public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos) {
        this.s = s;
        this.dis = dis;
        this.dos = dos;
    }

    @Override
    public void run() {
        try {
            dos.writeUTF("Ingrese su nombre: ");
            nombreJugador = dis.readUTF();
            Ask_game.marcador.put(nombreJugador, 0);
            for (int i = 0; i < Ask_game.preguntas.length; i++) {
                String pregunta = Ask_game.preguntas[i][0];
                String respuestaC = Ask_game.preguntas[i][1];

                dos.writeUTF("Pregunta " + (i + 1) + ": " + pregunta);
                String respuestaCliente = dis.readUTF();
                if (respuestaCliente.equalsIgnoreCase(respuestaC)) {
                    int puntosActuales = Ask_game.marcador.get(nombreJugador);
                    Ask_game.marcador.put(nombreJugador, puntosActuales + 10);
                    dos.writeUTF("Correcto");
                } else {
                    dos.writeUTF("Incorrecto, la respuesta era: " + respuestaC);
                }
                dos.writeUTF(Ask_game.obtenerMarcador());
            }
            dos.writeUTF("Juego terminado");
        } catch (IOException e) {
            System.out.println("jugador" + nombreJugador + "salio");
        }
        try {
            this.dis.close();
            this.dos.close();
            this.s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
