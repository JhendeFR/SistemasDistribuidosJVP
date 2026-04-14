/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parcial1jvp;

import java.net.MalformedURLException;
import java.nio.channels.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 *
 * @author TECNOLOGIA
 */
public class ServerBienestar {
    public static void main(String[] args) throws AlreadyBoundException{
        try{
            Bienestar bienestar = new Bienestar();
            LocateRegistry.createRegistry(1098);
            Naming.bind("Bienestar", bienestar);
        } catch(RemoteException ex){
            System.getLogger(ServidorUniersitario.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (java.rmi.AlreadyBoundException ex) {
            System.getLogger(ServidorUniersitario.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (MalformedURLException ex) {
            System.getLogger(ServidorUniersitario.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
}
