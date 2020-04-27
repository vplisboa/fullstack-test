package com.example.weatherMusic

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@EnableFeignClients
@SpringBootApplication
class WeatherMusicApplication

fun main(args: Array<String>) {
	runApplication<WeatherMusicApplication>(*args)
}
