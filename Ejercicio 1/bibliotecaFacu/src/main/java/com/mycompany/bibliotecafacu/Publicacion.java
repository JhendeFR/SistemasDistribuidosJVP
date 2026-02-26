/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecafacu;

/**
 * @author Jhendef
 */
public class Publicacion {
    protected String nombre;
    public Publicacion(String nombre){
        this.nombre = nombre;
    }
    public String getNombre(){
        return "Publicacion:" + nombre;
    }
}
