/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parcial1jvp;

import java.io.Serializable;

/**
 *
 * @author TECNOLOGIA
 */
class Nota implements Serializable{
    public String materia;
    public int calificacion;

    public Nota(String materia, int calificacion) {
        this.materia = materia;
        this.calificacion = calificacion;
    }
    
}
