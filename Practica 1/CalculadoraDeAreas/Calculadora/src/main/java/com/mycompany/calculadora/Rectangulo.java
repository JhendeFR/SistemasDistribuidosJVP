/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.calculadora;

/**
 *
 * @author Jhendef
 */
public class Rectangulo implements Figura {
    private double b;
    private double a;
    public Rectangulo(double b, double l){
        this.b = b;
        this.a = a;
    }
    @Override
    public double calcular(){
        return b*a;
    }
}
