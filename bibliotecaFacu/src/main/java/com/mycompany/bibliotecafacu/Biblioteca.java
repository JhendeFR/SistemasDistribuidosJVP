/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecafacu;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jhendef
 */
public class Biblioteca {
    private String nombre;
    private double tamanio;
    private List<Armario> armarios;
    
    public Biblioteca(String nombre, double tamanio){
        this.nombre = nombre;
        this.tamanio = tamanio;
        this.armarios = new ArrayList<>();
    }
    public void agregarArmario(Armario a){
        armarios.add(a);
    }
    public List<Armario> getArmarios(){
        return armarios;
    }
    public void listarBiblio(){
        System.out.println("Estado de la Biblioteca" + nombre );
        for (Armario a : armarios){
            System.out.println(a);
            for (Publicacion p : a.getPublicaciones()){
                System.out.println(" ->" + p);
                }
        }
    }
}
