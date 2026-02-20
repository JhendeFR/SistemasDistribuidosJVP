/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.calculadora;

/**
 *
 * @author Jhendef
 */
public class Cuadrado implements Figura {
    
    private double lado;
    public Cuadrado(double l){
        this.lado = l;
    }
    @Override
    public double calcular(){
        return lado*lado;
    }
}
