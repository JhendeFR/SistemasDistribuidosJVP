package main

import (
	"context"
	"log"
	"os"
	"strings"
	"time"

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

	// 2. Abrir un canal
	ch, err := conn.Channel()
	failOnError(err, "Error al abrir un canal")
	defer ch.Close()

	// 3. Declarar el Exchange de tipo "topic"
	err = ch.ExchangeDeclare(
		"logs_topic", // nombre del exchange
		"topic",      // tipo
		true,         // durable
		false,        // auto-deleted
		false,        // internal
		false,        // no-wait
		nil,          // arguments
	)
	failOnError(err, "Error al declarar el exchange")

	// 4. Preparar el mensaje y la llave de enrutamiento
	routingKey := severityFrom(os.Args)
	body := bodyFrom(os.Args)

	ctx, cancel := context.WithTimeout(context.Background(), 5*time.Second)
	defer cancel()

	// 5. Publicar el mensaje
	err = ch.PublishWithContext(ctx,
		"logs_topic", // exchange
		routingKey,   // routing key
		false,        // mandatory
		false,        // immediate
		amqp.Publishing{
			ContentType: "text/plain",
			Body:        []byte(body),
		})
	failOnError(err, "Error al publicar el mensaje")

	log.Printf(" [x] Enviado %s: '%s'", routingKey, body)
}

// Funciones de ayuda para leer los argumentos de la consola
func bodyFrom(args []string) string {
	var s string
	if (len(args) < 3) || os.Args[2] == "" {
		s = "hola"
	} else {
		s = strings.Join(args[2:], " ")
	}
	return s
}

func severityFrom(args []string) string {
	var s string
	if (len(args) < 2) || os.Args[1] == "" {
		s = "anonimo.info"
	} else {
		s = os.Args[1]
	}
	return s
}