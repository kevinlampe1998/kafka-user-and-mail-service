package com.example.user_service

import com.example.user_service.user.UserRepository
import org.apache.kafka.clients.admin.NewTopic
import org.slf4j.LoggerFactory
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.event.EventListener
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@SpringBootApplication
class UserServiceApplication {

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

	@Bean
	fun userCreatedTopic() = NewTopic("user-created", 10, 2)

	@Bean
	fun runner(template: KafkaTemplate<String, String>) = ApplicationRunner {
		println("kafka runner")
		template.send("topic1", "test") }
}

fun main(args: Array<String>) {
	runApplication<UserServiceApplication>(*args)
}

@Component
class StartupRunner(private val repository: UserRepository) {
	private val logger = LoggerFactory.getLogger(StartupRunner::class.java)

	@EventListener(ApplicationReadyEvent::class)
	fun runAfterStartup() {
		val allUsers = repository.findAll()
		logger.info("allUsers.size ${allUsers.size}")
		allUsers.forEach { println(it) }
	}
}
