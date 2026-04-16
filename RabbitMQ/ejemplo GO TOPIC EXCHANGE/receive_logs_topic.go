package main

import (
	"log"
	"os"

	amqp "github.com/rabbitmq/amqp091-go"
)

func failOnError(err error, msg string) {
	if err != nil {
		log.Panicf("%s: %s", msg, err)
	}
}

func main() {
	// 1. Conectar a RabbitMQ
	conn, err := amqp.Dial("amqp://guest:guest@localhost:5672/")
	failOnError(err, "Error al conectar a RabbitMQ")
	defer conn.Close()

	ch, err := conn.Channel()
	failOnError(err, "Error al abrir un canal")
	defer ch.Close()

	// 2. Declarar el mismo Exchange (por si el receptor inicia primero)
	err = ch.ExchangeDeclare(
		"logs_topic", "topic", true, false, false, false, nil,
	)
	failOnError(err, "Error al declarar el exchange")

	// 3. Crear una cola temporal y exclusiva
	q, err := ch.QueueDeclare(
		"",    // Nombre vacío para que RabbitMQ genere uno aleatorio
		false, // durable
		false, // delete when unused
		true,  // exclusive (se borra al desconectar)
		false, // no-wait
		nil,   // arguments
	)
	failOnError(err, "Error al declarar la cola")

	// 4. Leer los patrones de la consola y "Unir" (Bind) la cola al exchange
	if len(os.Args) < 2 {
		log.Printf("Uso: %s [binding_key]...", os.Args[0])
		os.Exit(0)
	}

	for _, s := range os.Args[1:] {
		log.Printf("Uniendo cola con el patrón: %s", s)
		err = ch.QueueBind(
			q.Name,       // nombre de la cola
			s,            // routing key (el patrón con * o #)
			"logs_topic", // nombre del exchange
			false,
			nil)
		failOnError(err, "Error al unir la cola")
	}

	// 5. Consumir los mensajes
	msgs, err := ch.Consume(
		q.Name, "", true, false, false, false, nil,
	)
	failOnError(err, "Error al registrar el consumidor")

	var forever chan struct{}

	go func() {
		for d := range msgs {
			log.Printf(" [x] Recibido [%s]: %s", d.RoutingKey, d.Body)
		}
	}()

	log.Printf(" [*] Esperando logs. Presiona CTRL+C para salir.")
	<-forever
}