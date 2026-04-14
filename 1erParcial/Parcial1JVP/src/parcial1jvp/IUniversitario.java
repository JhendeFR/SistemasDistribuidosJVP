/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package parcial1jvp;
import java.rmi.Remote;
import java.rmi.RemoteException;
/**
 *
 * @author TECNOLOGIA
 */
public interface IUniversitario extends Remote{
    public RespuestaBeca SolicitarBeca(String ci, String nombre, String apellidos) throws RemoteException;
}
