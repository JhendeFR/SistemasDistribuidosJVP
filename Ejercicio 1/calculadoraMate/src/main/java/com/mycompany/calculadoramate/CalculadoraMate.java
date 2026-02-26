/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.calculadoramate;
import java.util.Scanner;

/**
 *
 * @author Jhendef
 */
public class CalculadoraMate {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion,n = 0;
        boolean nIntroc = false;
        do{
            System.out.println("1. Introducir n");
            System.out.println("2.fibonacci");
            System.out.println("3.factorial");
            System.out.println("4.sumatoria");
            System.out.println("Elija una opcion");
            opcion = sc.nextInt();
            
            switch(opcion){
                case 1:
                    System.out.println("Ingrese el valor de n:");
                    n = sc.nextInt();
                    nIntroc = true;
                    break;
                case 2:
                    System.out.println("fibonacci de " +n+ " es:" + cFibonacci(n));
                    break;
                case 3:
                    System.out.println("factorial es:" +n+ "es: " + cFactorial(n));
                    break;
                case 4:
                    System.out.println("sumatoria es:" +n+ "es: " + cSumatoria(n));
                    break;
            }
        }while(opcion !=5);
    }
    public static int cFibonacci(int n){
        if(n<=1){
            return n;
        }
        int a = 0, b = 1, temp;
        for(int i=2; i<=n; i++){
            temp = a + b;
            a = b;
            b = temp;
        }
        return b;
    }
    public static int cFactorial(int n){
        int fac = 1;
        for(int i=1; i<=n; i++){
            fac *= i;
        }
        return fac;
    }
    public static int cSumatoria(int n){
        return n*(n+1)/2;
    }
}
