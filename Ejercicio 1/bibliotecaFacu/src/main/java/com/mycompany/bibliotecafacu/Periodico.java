/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecafacu;
import java.util.List;
/**
 *
 * @author Jhendef
 */
public class Periodico extends Publicacion {
    private String fecha;
    private List<String> suplementos;
    public Periodico(String nombre, String fecha, List<String> suplementos) {
        super(nombre);
        this.fecha = fecha;
        this.suplementos = suplementos;
    }
    public String getPeriodico(){
        return "Periodico " + nombre + "fecha: " + fecha + "suplementos: " + suplementos;
    }
}
