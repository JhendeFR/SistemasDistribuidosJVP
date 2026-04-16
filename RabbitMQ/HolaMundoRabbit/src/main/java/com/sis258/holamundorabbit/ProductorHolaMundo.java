/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sis258.holamundorabbit;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 *
 * @author Ruta Binar
 */
public class ProductorHolaMundo {

    private final static String QUEUE_NAME = "hola"; // El buzón se llama "hola"

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost"); // Apunta a tu Docker

        try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel()) {

            // Crea el buzón
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String mensaje = "¡Hola Mundo desde RabbitMQ!";

            // Envía la carta al buzón
            channel.basicPublish("", QUEUE_NAME, null, mensaje.getBytes());
            System.out.println(" [x] Acabo de enviar: '" + mensaje + "'");
        }
    }

}
