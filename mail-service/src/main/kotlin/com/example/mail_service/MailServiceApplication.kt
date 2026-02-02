package com.example.mail_service

import org.apache.kafka.clients.admin.NewTopic
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate

@SpringBootApplication
class MailServiceApplication {




	@KafkaListener(id = "myId", topics = ["topic1"]) // start automatic a kafka-consumer and listen to topic
	// id = listener container id
	// topics = topics which are listen from this consumer
	fun listen(value: String) { // listen() = always when kafka gets a message this function will be executed
		println("kafka listener")
		println(value)
	}

	@KafkaListener(id = "user_created_id", topics = ["user_created"]) // start automatic a kafka-consumer and listen to topic
	// id = listener container id
	// topics = topics which are listen from this consumer
	fun sendWelcomeMessage(value: String) { // listen() = always when kafka gets a message this function will be executed
		println("A new user was created now!")
		println(value)
		println("send welcome message")
	}

}

fun main(args: Array<String>) {
	runApplication<MailServiceApplication>(*args)
}
