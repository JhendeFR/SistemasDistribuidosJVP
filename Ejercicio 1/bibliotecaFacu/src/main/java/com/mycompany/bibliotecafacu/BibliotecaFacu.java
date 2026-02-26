/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.bibliotecafacu;

import java.util.*;
import java.util.Scanner;

/**
 *
 * @author Jhendef
 */
public class BibliotecaFacu {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Biblioteca miBiblioteca = new Biblioteca("Biblioteca USFX", 500.0);
        int opcion;
        do {
            System.out.println("Gestion biblioteca");
            System.out.println("1.crear armario y añadir a la biblioteca");
            System.out.println("2. crear publicacion y cargar al armario");
            System.out.println("3. listar Biblioteca");
            opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {
                case 1:
                    System.out.print("Codigo armario: ");
                    String codigo = sc.nextLine();
                    System.out.print("Material(Madera/Metalico): ");
                    String material = sc.nextLine();
                    miBiblioteca.agregarArmario(new Armario(codigo, material));
                    System.out.println("Armario añadido");
                    break;
                case 2:
                    if (miBiblioteca.getArmarios().isEmpty()) {
                        System.out.println("Debe crear un armario primero");
                        break;
                    }
                    System.out.println("Seleccione: 1.Libro, 2.Revista, 3.Periodico");
                    int tipo = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    Publicacion nueva = null;
                    if (tipo == 1) {
                        System.out.print("Autor: ");
                        String aut = sc.nextLine();
                        System.out.print("Editorial: ");
                        String ed = sc.nextLine();
                        System.out.print("Año: ");
                        int anio = sc.nextInt();
                        nueva = new Libro(nombre, aut, ed, anio);
                    } else if (tipo == 2) {
                        System.out.print("Mes: ");
                        String mes = sc.nextLine();
                        System.out.print("Año: ");
                        int anio = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Tipo (tenologhia/moda/ciencia): ");
                        String t = sc.nextLine();
                        nueva = new Revista(nombre, mes, anio, t);
                    }
                    System.out.println("En que armario quiere cargarlo?");
                    for (int i = 0; i < miBiblioteca.getArmarios().size(); i++) {
                        System.out.println(i + ". " + miBiblioteca.getArmarios().get(i).getCodigo());
                    }
                    int idx = sc.nextInt();
                    miBiblioteca.getArmarios().get(idx).agregarPubli(nueva);
                    System.out.println("Publicacion cargada");
                    break;
                case 3:
                    miBiblioteca.listarBiblio();
                    break;
            }
        } while (opcion != 4);
    }
}
