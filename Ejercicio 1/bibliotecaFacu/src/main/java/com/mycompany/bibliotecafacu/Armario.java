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
public class Armario {
    private String codigo;
    private String material;
    private List<Publicacion> publicaciones;
    public Armario(String codigo, String material){
        this.codigo = codigo;
        this.material = material;
        this.publicaciones = new ArrayList();
    }
    public void agregarPubli(Publicacion p){
        publicaciones.add(p);
    }
    public String getCodigo(){
        return codigo;
    }
    public List<Publicacion> getPublicaciones(){
        return publicaciones;
    }
    public String getArmario(){
        return "Armario: " + codigo + " ( " + material + " ) ";
    }
}
