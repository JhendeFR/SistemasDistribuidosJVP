/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecafacu;

/**
 *
 * @author Jhendef
 */
public class Libro extends Publicacion {
    private String autor, editorial;
    private int anio;
    public Libro(String nombre, String autor, String editorial, int anio) {
        super(nombre);
        this.autor = autor;
        this.editorial = editorial;
        this.anio = anio;
    }
    public String getLibro(){
        return "Libro " + nombre + " Autor: " + autor + " (" + anio + ")";
    }
}
