package com.mycompany.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class EmitLogTopic {
    private static final String EXCHANGE_NAME = "topic_logs";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost"); // RabbitMQ local

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            // El tipo DEBE ser "topic"
            channel.exchangeDeclare(EXCHANGE_NAME, "topic");

            String routingKey = "comida.perro.croqueta"; 
            String message = "Pedido de alimento para mascotas";

            channel.basicPublish(EXCHANGE_NAME, routingKey, null, message.getBytes("UTF-8"));
            System.out.println(" [x] Enviado '" + routingKey + "':'" + message + "'");
        }
    }
}