package tdd.example.app

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TestDrivenDevelopmentApplication

fun main(args: Array<String>) {
	runApplication<TestDrivenDevelopmentApplication>(*args)
}
