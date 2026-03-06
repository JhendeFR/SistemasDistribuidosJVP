/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soquetstcpmultihilo;

/**
 *
 * @author Carlos
 */
// Java implementation for a client
// Save file as Client.java
import java.io.*;
import java.net.*;
import java.util.Scanner;

// Client class
public class ClienteMultihilo {
    //String ipserver = ""
    public static void main(String[] args) throws IOException {
        try {
            Scanner scn = new Scanner(System.in);

            // getting localhost ip
            InetAddress ip = InetAddress.getByName("10.8.221.221");

            // establish the connection with server port 5056
            Socket s = new Socket(ip, 5056);

            // obtaining input and out streams
            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

            // the following loop performs the exchange of
            // information between client and client handler
            while (true) {
                System.out.println(dis.readUTF());
                String tosend = scn.nextLine();
                dos.writeUTF(tosend);

                // If client sends exit,close this connection
                // and then break from the while loop
                if (tosend.equals("Exit")) {
                    System.out.println("Closing this connection : " + s);
                    s.close();
                    break;
                }
                
                
                //manda la operacion
                System.out.println(dis.readUTF());
                String op = scn.nextLine();
                dos.writeUTF(op);
                
                //envia n2
                System.out.println(dis.readUTF());
                String n2 = scn.nextLine();
                dos.writeUTF(n2);
                
                // printing date or time as requested by client
                String received = dis.readUTF();
                System.out.println(received);
            }

            // closing resources
            scn.close();
            dis.close();
            dos.close();
            scn.close();
            System.out.println("Conexión finalizada.");
        } catch (IOException e) {
            //e.printStackTrace();
             System.out.println("Error en el cliente: " + e.getMessage());
        }
    }
}