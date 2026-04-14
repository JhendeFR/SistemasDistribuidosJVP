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
class RespuestaBeca implements Serializable{
    public Boolean aprobado;
    public String motivo;
    public Double promedio;

    public RespuestaBeca(Boolean aprobado, String motivo, Double promedio) {
        this.aprobado = aprobado;
        this.motivo = motivo;
        this.promedio = promedio;
    }
}
