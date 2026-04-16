/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sis258.holamundorabbit;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
/**
 *
 * @author Ruta Binar
 */
public class ConsumidorHolaMundo {
    private final static String QUEUE_NAME = "hola"; // Escucha en el buzón "hola"
    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Esperando mensajes... (Para salir cierra la ventana)");

        // Instrucciones de qué hacer cuando llega la carta
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String mensaje = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Acabo de recibir: '" + mensaje + "'");
        };
        
        // Empieza a escuchar
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
    }
}
