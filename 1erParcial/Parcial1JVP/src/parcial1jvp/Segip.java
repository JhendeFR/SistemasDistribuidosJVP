/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parcial1jvp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author TECNOLOGIA
 */
public class Segip {
    public static void main(String[] args) throws IOException{
        ServerSocket server = new ServerSocket(5001);
        System.out.println("tcplisto");
        while(true){
            try(Socket cliente = server.accept();
                DataInputStream input = new DataInputStream(cliente.getInputStream());
                DataOutputStream output = new DataOutputStream(cliente.getOutputStream())
                    ){
                String ci = input.readUTF();
                String op = input.readUTF();
                if(op.equals("verificar")){
                    String respuesta = ci.equals("1234567")  ? "encontrado" : "no-encontrado";
                    output.writeUTF(respuesta);
                }
            }
        }
    }
}
