/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontareas;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 *
 * @author Jhendef
 */
public class ClientHandler extends Thread {

    final DataInputStream dis;
    final DataOutputStream dos;
    final Socket s;

    public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos) {
        this.s = s;
        this.dis = dis;
        this.dos = dos;
    }

    @Override
    public void run() {
        String r;
        while (true) {
            try {
                dos.writeUTF("Gestor de Tareas \n"
                        + "1.Agregar tearea \n"
                        + "2.Eliminar tarea \n"
                        + "3.Listar tarea \n"
                        + "Seleccione una opcion(exit para salir) :");
                r = dis.readUTF();
                if (r.equalsIgnoreCase("exit")) {
                    System.out.println("cliente " + this.s + "salio");
                    break;
                }
                switch (r) {
                    case "1":
                        dos.writeUTF("Descripcion de la tarea:");
                        String nuevaTarea = dis.readUTF();
                        GestionTareas.listaTareas.add(nuevaTarea);
                        dos.writeUTF("Tarea agregada");
                        break;
                    case "2":
                        dos.writeUTF("Ingrese el numero de tarea a eliminar:");
                        int indi = Integer.parseInt(dis.readUTF());
                        if (indi >= 0 && indi < GestionTareas.listaTareas.size()) {
                            GestionTareas.listaTareas.remove(indi);
                            dos.writeUTF("Tarea eliminada");
                        } else {
                            dos.writeUTF("No valido");
                        }
                        break;
                    case "3":
                        String lista = "";
                        if (GestionTareas.listaTareas.isEmpty()) {
                            lista = "vacio";
                        } else {
                            for (int i = 0; i < GestionTareas.listaTareas.size(); i++) {
                                lista += i + "." + GestionTareas.listaTareas.get(i) + "\n";
                            }
                        }
                        dos.writeUTF(lista);
                        break;
                    default:
                        dos.writeUTF("Opcion no valida");
                        break;
                }
                
            } catch (IOException e) {
                break;
            }
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
