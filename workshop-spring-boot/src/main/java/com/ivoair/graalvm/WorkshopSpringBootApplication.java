package com.ivoair.graalvm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.micrometer.core.instrument.binder.jvm.JvmThreadMetrics;

@SpringBootApplication
public class WorkshopSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorkshopSpringBootApplication.class, args);
	}

	@Bean
	JvmThreadMetrics threadMetrics() {
		return new JvmThreadMetrics();
	}

}
