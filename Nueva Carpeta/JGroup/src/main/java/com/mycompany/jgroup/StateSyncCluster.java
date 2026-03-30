/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.jgroup;
/**
 *
 * @author Jhoanelcrack
 */

import org.jgroups.JChannel;
import org.jgroups.Message;
import org.jgroups.ReceiverAdapter;
import org.jgroups.View;
import org.jgroups.util.Util;
import java.io.*;
import java.util.Scanner;

public class StateSyncCluster extends ReceiverAdapter {

    private JChannel channel;
    private String state = "";

    @Override
    public void receive(Message msg) {
        state = (String) msg.getObject();
        System.out.println("\n Nuevo estado recibido de " + msg.getSrc() + ": " + state);
        System.out.print(" Ingrese su sms: ");
    }

    @Override
    public void viewAccepted(View view) {
        System.out.println("\n Miembros del grupo: " + view.getMembers());
    }

    @Override
    public void getState(OutputStream output) throws Exception {
        synchronized (this) {
            Util.objectToStream(state, new DataOutputStream(output));
        }
    }
    
    @Override
    public void setState(InputStream input) throws Exception {
        synchronized (this) {
            state = (String) Util.objectFromStream(new DataInputStream(input));
        }
        System.out.println("Estado inicial sincronizado: " + state);
    }

    public void start() throws Exception {
        
        channel = new JChannel();
        channel.setReceiver(this);
        channel.connect("StateSyncCluster");         
        channel.getState(null, 10000); 
        System.out.println("Conectado al grupo 'StateSyncCluster'");
        eventLoop();
        channel.close();
    }

    private void eventLoop() {
    Scanner sc = new Scanner(System.in);
    while (true) {
        try {
            System.out.print("Escriba salir para salir \n");
            String input = sc.nextLine();
            if ("salir".equals(input)) break;            
            state = input; 
            Message msg = new Message(null, state);
            channel.send(msg);
        } catch (Exception e) {
            System.err.println("Error");
            sc.nextLine(); 
            }
        }
    }
}