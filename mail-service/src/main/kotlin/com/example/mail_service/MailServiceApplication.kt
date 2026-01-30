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

	@Bean // kafka topic definition as spring bean // @Bean spring managing it as object
	fun topic() = NewTopic("topic1", 10, 1) // creates a new topic
	// parameter
	// name = name of the topic
	// numPartitions = amount of the partitions // a partition is a datafile which contains sorted logs
	//     - inside a partition the messages are sorted
	//     - between partitions, they are not sorted
	// replicationFactor = amount of broker where the data are replicated
	//     - 1: no backup
	//     - >1: 1 "Leader" + remaining number "Replicas"


	@KafkaListener(id = "myId", topics = ["topic1"]) // start automatic a kafka-consumer and listen to topic
	// id = listener container id
	// topics = topics which are listen from this consumer
	fun listen(value: String) { // listen() = always when kafka gets a message this function will be executed
		println("kafka listener")
		println(value)
	}

}

fun main(args: Array<String>) {
	runApplication<MailServiceApplication>(*args)
}
