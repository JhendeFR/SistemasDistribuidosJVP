/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ask_game;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author Jhendef
 */
public class Ask_game {
    
    static Map<String, Integer> marcador = new ConcurrentHashMap<>();
    static String[][] preguntas = {
        {"Que materia estamos pasando?", "Sistemas Distribuidos"},
        {"Que programa estamos utilizando?", "NetBeans"},
        {"Que lenguaje estamos usando?", "Java"},
        {"En que planeta estamos?", "La tierra"},
        {"Eres un alien?", "No"}
    };

    public static void main(String[] args) throws IOException{
        ServerSocket ss = new ServerSocket(5056);
        System.out.println("Server iniciado");
        while(true){
            Socket s = ss.accept();
            System.out.println("Nuevo jugador" + s);
            
            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            
            Thread t = new ClientHandler(s, dis, dos);
            t.start();
        }
    }
    public static String obtenerMarcador(){
        StringBuilder sb = new StringBuilder("Marcador Actual\n");
        marcador.forEach((nombre, puntos)->{
        sb.append(nombre).append(": ").append(puntos).append(" pts\n");
        });
        return sb.toString();
    }
}
