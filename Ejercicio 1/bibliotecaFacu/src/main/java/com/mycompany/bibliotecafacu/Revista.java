/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecafacu;

/**
 *
 * @author Jhendef
 */
public class Revista extends Publicacion{
    private String mes, tipo;
    private int anio;
    public Revista(String nombre, String mes, int anio, String tipo) {
        super(nombre);
        this.mes = mes;
        this.anio = anio;
        this.tipo = tipo;
    }
    public String getRevista(){
        return "Revista" + nombre + "tipo: " + tipo + " (" + mes + "/" + anio + ") ";
    }
}
