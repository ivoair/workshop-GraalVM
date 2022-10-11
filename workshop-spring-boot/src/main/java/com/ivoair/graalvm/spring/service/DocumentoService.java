package com.ivoair.graalvm.spring.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DocumentoService {

	@Value("${app.configuracion.fichero:/docus/war-and-peace.txt}")
	String fichero;

	public int countCaracteres() {
		
		int vocales = 0;
		
		try (BufferedReader reader = new BufferedReader(
				new InputStreamReader(this.getClass().getResourceAsStream(this.fichero)))) {

			String line = reader.readLine();
			while (line != null) {
				vocales += line.chars().filter(ch -> ('a' == ch || 'e' == ch || 'i' == ch || 'o' == ch || 'u' == ch
						|| 'A' == ch || 'E' == ch || 'I' == ch || 'O' == ch || 'U' == ch)).count();

				line = reader.readLine();
			}

		} catch (IOException ioe) {
			log.error("Error al leer el fichero", ioe);
		}

		return vocales;
	}


}
