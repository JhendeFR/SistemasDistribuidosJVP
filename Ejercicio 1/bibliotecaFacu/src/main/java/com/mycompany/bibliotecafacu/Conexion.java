/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecafacu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Jhendef
 */
public class Conexion {

    private static final String URL = "jdbc:mysql://localhost:3306/bd_biblioteca";
    private static final String USER = "root"; // Usuario por defecto en XAMPP
    private static final String PASS = "";     // Contraseña vacía por defecto en XAMPP

    public static Connection conectar() {
        try {
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
            return null;
        }
    }
}
