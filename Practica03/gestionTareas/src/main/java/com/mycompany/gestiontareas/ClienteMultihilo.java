package com.mycompany.gestiontareas;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClienteMultihilo {
    public static void main(String[] args) throws IOException {
        try {
            Scanner scn = new Scanner(System.in);
            InetAddress ip = InetAddress.getByName("localhost");
            Socket s = new Socket(ip, 5056);

            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

            while (true) {
         
                System.out.println(dis.readUTF());
                String opcion = scn.nextLine();
                dos.writeUTF(opcion);

                if (opcion.equalsIgnoreCase("exit")) {
                    break;
                }

                
                if (opcion.equals("1") || opcion.equals("2")) {
             
                    System.out.println(dis.readUTF()); 
                    String dato = scn.nextLine();
                    dos.writeUTF(dato);
                    
                    
                    System.out.println(dis.readUTF()); 
                } 
                else if (opcion.equals("3")) {
                    
                    System.out.println(dis.readUTF());
                } 
                else {
                    System.out.println(dis.readUTF());
                }
            }

            s.close();
            scn.close();
            System.out.println("Conexion cerrada.");

        } catch (Exception e) {
            System.out.println("Error: Conexion terminada.");
        }
    }
}