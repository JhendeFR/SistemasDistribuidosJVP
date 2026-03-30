/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.jgroup;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import org.jgroups.JChannel;
import org.jgroups.Message;
import org.jgroups.ReceiverAdapter;

/**
 *
 * @author Jhendef
 */
public class Votacion {

    private JChannel channel;
    private Map<String, Integer> results = new HashMap<>();
    private String currentQuestion = "";

    public void start() throws Exception {
        channel = new JChannel();
        channel.setReceiver(new ReceiverAdapter() {
            @Override
            public void receive(Message msg) {
                String content = (String) msg.getObject();
                if (content.startsWith("PREGUNTA:")) {
                    currentQuestion = content.substring(9);
                    results.clear();
                    System.out.println("\nNueva Votacion: " + currentQuestion);
                } else if (content.startsWith("VOTO:")) {
                    String opcion = content.substring(5);
                    results.put(opcion, results.getOrDefault(opcion, 0) + 1);
                    System.out.println("Resultados parciales: " + results);
                }
            }
        });
        channel.connect("VotingCluster");
        menu();
    }

    private void menu() throws Exception {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n"
                    + "1. Iniciar Votacion\n"
                    + "2. Votar\n"
                    + "3. Salir");
            String op = sc.nextLine();
            if (op.equals("1")) {
                System.out.print("Pregunta: ");
                String q = sc.nextLine();
                channel.send(new Message(null, "PREGUNTA:" + q));
            } else if (op.equals("2")) {
                System.out.print("Tu voto: ");
                String v = sc.nextLine();
                channel.send(new Message(null, "VOTO:" + v));
            } else if (op.equals("3")) {
                break;
            }
        }
        channel.close();
    }
}
