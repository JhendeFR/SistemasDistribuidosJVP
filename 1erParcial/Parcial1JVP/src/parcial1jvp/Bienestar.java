/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parcial1jvp;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 *
 * @author TECNOLOGIA
 */
public class Bienestar extends UnicastRemoteObject implements IBienestar{
    public Bienestar() throws RemoteException{
        super();
    }
    @Override
    public ArrayList<Nota> ObtenerHistorial(String ci) throws RemoteException{
        ArrayList<Nota> nota = new ArrayList<Nota>();
        String materia1 = "Distri";
        int n1 = 85;
        String materia2 = "Arqui";
        int n2 = 85;
        String materia3 = "QA";
        int n3 = 85;
        return nota;
    }
}
