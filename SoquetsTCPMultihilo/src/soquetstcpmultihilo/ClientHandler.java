package soquetstcpmultihilo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * @author Carlos
 */
class ClientHandler extends Thread {

    final DataInputStream dis;
    final DataOutputStream dos;
    final Socket s;

    // Constructor
    public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos) {
        this.s = s;
        this.dis = dis;
        this.dos = dos;
    }

    @Override
    public void run() {
        String received;
        while (true) {
            try {
                // 1. Pedir el primer número o salir
                dos.writeUTF("Calculadora Multihilo" + "Escriba 'Exit' para salir o ingrese el primer numero:");

                received = dis.readUTF();

                if (received.equals("Exit")) {
                    System.out.println("Client " + this.s + " sends exit...");
                    System.out.println("Closing this connection.");
                    this.s.close();
                    System.out.println("Connection closed");
                    break;
                }

                double n1 = Double.parseDouble(received);
                dos.writeUTF("Ingrese la operacion (+ , - , * , /):");
                String op = dis.readUTF();
                dos.writeUTF("Ingrese el segundo numero:");
                String s2 = dis.readUTF();
                double n2 = Double.parseDouble(s2);

                //Calcular
                double resultado = 0;
                String error = "";

                switch (op) {
                    case "+":
                        resultado = n1 + n2;
                        break;
                    case "-":
                        resultado = n1 - n2;
                        break;
                    case "*":
                        resultado = n1 * n2;
                        break;
                    case "/":
                        resultado = n1 / n2;
                        break;
                    default:
                        error = "Op no valida";
                }

                dos.writeUTF("El resultado es: " + resultado + "\n---");

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        try {
            // closing resources
            this.dis.close();
            this.dos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
