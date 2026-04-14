/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package parcial1jvp;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author TECNOLOGIA
 */
public class Universitario extends UnicastRemoteObject implements IUniversitario {
    public Universitario() throws RemoteException{
        super();
    }
    @Override
    public RespuestaBeca SolicitarBeca(String ci, String nombre, String apellidos) throws RemoteException{
        RespuestaBeca respuesta = new RespuestaBeca(false, "", 0.00);
        try(Socket tcpsocket = new Socket("localhost",5001);
                DataInputStream input = new DataInputStream(tcpsocket.getInputStream());
                DataOutputStream output = new DataOutputStream(tcpsocket.getOutputStream())){
            output.writeUTF(ci);
            output.writeUTF("verificar");
            String respuestaTCP = input.readUTF();
            if(respuestaTCP.equals("encontrado")){
                IBienestar cnb = (IBienestar) Naming.lookup("rmi://localhost/Bienestar");
                ArrayList<Nota> resb = cnb.ObtenerHistorial(ci);
                //tendria que sacar el promedio con un foreach o algo asi y guardarlo en una variable auxiliar
                double promedio = 85;
                if(promedio>70){
                    try(DatagramSocket udpSocket = new DatagramSocket()){
                        String mensaje = ci;
                        byte[] buffer = mensaje.getBytes();
                        DatagramPacket paquete = new DatagramPacket(buffer, buffer.length, InetAddress.getByName("localhost"), 5002);
                        udpSocket.send(paquete);
                        
                        byte[] recibirBuffer = new byte[1024];
                        DatagramPacket recibido = new DatagramPacket(recibirBuffer, recibirBuffer.length);
                        udpSocket.receive(recibido);
                        String data = new String(recibido.getData(), 0, recibido.getLength());
                        Boolean respuestaUTP = data.equals("") ? true : false;
                        respuesta = new RespuestaBeca(respuestaUTP,"Elegible para Beca", promedio);
                    }
                }
            }
            
        } catch (IOException e){
            System.out.println("Error: Segip" + e.getMessage());
        } catch (NotBoundException ex) {
            System.getLogger(Universitario.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return respuesta;
    }
}
