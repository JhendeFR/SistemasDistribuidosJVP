/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parcial1jvp;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

/**
 *
 * @author TECNOLOGIA
 */
public class ClienteUnivesitario {
    public static void main(String[] args) throws NotBoundException, MalformedURLException, RemoteException {
        IUniversitario cn = (IUniversitario) Naming.lookup("rmi://localhost/Universitario");
        Scanner sc = new Scanner(System.in);
        int op;
        do{
            System.out.println("Operaciones");
            System.out.println("1. Solicitar beca");
            System.out.println("2. Salir");
            op = sc.nextInt();
            sc.nextLine();
            if(op==1){
                System.out.println("Ingresar C.I.-");
                String ci = sc.nextLine();
                System.out.println("Ingresar Nombre");
                String nombres = sc.nextLine();
                System.out.println("Ingresar Apellidos");
                String apellidos = sc.nextLine();
                RespuestaBeca res = cn.SolicitarBeca(ci, nombres, apellidos);
                
            }
        }while(op!=2);
    }
}
