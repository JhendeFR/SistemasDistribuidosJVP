/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package parcial1jvp;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author TECNOLOGIA
 */
public interface IBienestar extends Remote{
    public ArrayList<Nota> ObtenerHistorial(String ci) throws RemoteException;
}
