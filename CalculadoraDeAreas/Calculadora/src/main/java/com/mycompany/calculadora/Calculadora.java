/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.calculadora;
import java.util.Scanner;
import java.util.ArrayList;
/**
 *
 * @author Jhendef
 */
public class Calculadora {

    public static void main(String[] args) {
        int op = 0;
        ArrayList<Figura> figuras = new ArrayList<>();
        while(op!=3){
            System.out.println("Calculadora de Areas");
            System.out.println("Menu:");
            System.out.println("1.Agregar elemento al plano");
            System.out.println("2.Calcular el area ocupada por los elementos");
            System.out.println("3.Salir del programa");
            Scanner sc = new Scanner(System.in);
            op = sc.nextInt();
            switch (op){
                case 1:
                    System.out.println("1.cuadrado, 2.rectangulo, 3.triangulo");
                    int n = sc.nextInt();
                    if (n == 1){
                        System.out.print("Ingresa el lado (L): ");
                        figuras.add(new Cuadrado(sc.nextDouble()));
                    } else if (n == 2){
                        System.out.print("Ingresa el base (B): ");
                        double b = sc.nextDouble();
                        System.out.print("Ingresa el altura (A): ");
                        double a = sc.nextDouble();
                        figuras.add(new Rectangulo(b,a));
                    }
                    else if (n == 3){
                        System.out.print("Ingresa el base (B): ");
                        double b = sc.nextDouble();
                        System.out.print("Ingresa el altura (A): ");
                        double a = sc.nextDouble();
                        figuras.add(new Triangulo(b,a));
                    }
                    break;
                case 2:
                    double aT = 0;
                    for(Figura f: figuras){
                        aT += f.calcular();
                    }
                    System.out.println("El area es:" + aT);
                    break;
                default:
                    System.out.println("Selecciona uno de los 3 numeros");
            }
        }  
    }
}
