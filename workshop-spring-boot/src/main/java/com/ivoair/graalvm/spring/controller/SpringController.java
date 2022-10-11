package com.ivoair.graalvm.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ivoair.graalvm.spring.service.DocumentoService;
import com.ivoair.graalvm.spring.service.HiloService;

import io.micrometer.core.annotation.Timed;

@RestController
@RequestMapping(path = "/springboot")
public class SpringController {

	@Autowired
	DocumentoService documentoService;

	@Autowired
	HiloService hiloService;

	@GetMapping("/hello")
	@Timed(percentiles = { 0.5, 0.95, 0.999 }, histogram = true)
	public ResponseEntity<String> hello() {

		return new ResponseEntity<String>("Hola Figura!", HttpStatus.OK);

	}

	@GetMapping("/vocales")
	@Timed(percentiles = { 0.5, 0.95, 0.999 }, histogram = true)
	public ResponseEntity<String> vocales() {

		return new ResponseEntity<String>("Vocales " + documentoService.countCaracteres(), HttpStatus.OK);

	}

	@GetMapping("/hilos")
	@Timed(percentiles = { 0.5, 0.95, 0.999 }, histogram = true)
	public ResponseEntity<String> hilos() {

		return new ResponseEntity<String>("Hilos " + hiloService.crear(), HttpStatus.OK);

	}

}
