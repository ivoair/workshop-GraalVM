package com.ivoair.graalvm.spring.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class HiloService {

	@Value("${app.configuracion.hilos.numero:10}")
	int numeroHilos;

	@Value("${app.configuracion.hilos.sleep:300}")
	int sleepHilos;

	public String crear() {
		
		ExecutorService executor = Executors.newFixedThreadPool(this.numeroHilos);

		ArrayList<Callable<String>> callableTasks = new ArrayList<Callable<String>>();
		for (int index = 0; index < this.numeroHilos; index++) {

			final int posicion = index + 1;
			Callable<String> callableTask = () -> {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");
				log.info("Comienzo hilo " + posicion + " [" + LocalDateTime.now().format(formatter) + "]");
				TimeUnit.MILLISECONDS.sleep(this.sleepHilos);
				log.info("Ejecutado hilo " + posicion + " [" + LocalDateTime.now().format(formatter) + "]");
				return "OK";
			};
			callableTasks.add(callableTask);

		}

		String result = null;
		try {
			List<Future<String>> futures = executor.invokeAll(callableTasks);

			for (int index = 0; index < this.numeroHilos; index++) {

				try {
					result = futures.get(index).get(100 + (index * this.sleepHilos), TimeUnit.MILLISECONDS);
				} catch (TimeoutException exc) {
					log.error("Error en la ejecución del hilo " + index, exc);
					result = "KO";
				}
			}
		} catch (InterruptedException | ExecutionException ie) {
			log.error("Error al ejecutar los hilos", ie);
			result = "KO";
		}

		executor.shutdown();
		try {
			if (!executor.awaitTermination(800, TimeUnit.MILLISECONDS)) {
				executor.shutdownNow();
			}
		} catch (InterruptedException e) {
			executor.shutdownNow();
		}

		return result;
	}


}
