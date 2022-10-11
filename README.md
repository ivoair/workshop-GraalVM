# workshop-GraalVM
Proyecto en el cual se van a ir realizando ejemplos basados en GraalVM utilizando los diferentes lenguajes y frameworks de desarrollo (Java, Spring, Quarkus, JavaScript, Python, Ruby, R...) ejecutando la misma lógica funcional para comparar rendimiento.

## Requisitos
- GraalVM 22.2
- Docker Desktop
- Apache JMeter

## Módulos
Para facilitar la comprensión de los desarrollos se va a modularizar cada componente.

### workshop-infraestructura
Módulo que va a disponer toda la infraestructura necesaria para el despliegue y ejecución de los diferentes componentes. Se van a desplegar imágenes Docker, por lo cual es preciso disponer de dicha herramienta instalada o disponible. Se distribuye un fichero docker-compose que permite el despliegue de forma ya automatizada. Comandos a tener en cuenta:
- docker-compose config
- docker-compose up --build
- docker-compose down

La infraestructura que se va a desplegar va a disponer un sistema de monitorización para la valoración de los resultados con las siguientes herramientas:
- __Netflix OSS Atlas:__ Pendiente de integrar. Versión Docker disponible 1.4.3 y se ha realizado la incorporación de la versión 1.6.5 del servidor sin éxito. Error de exposición de endpoint de publicación. Disponible en el puerto 7101.
- __Prometheus:__ Base de datos para registrar las métricas que se puedan ir exportando desde los diferentes desarrollos. Importante configurar bien los targets en el fichero _/prometheus/prometheus.yml._ Disponible en el puerto 9090.
- __Grafana:__ Herramienta para monitorizar utilizando dashboards y ver gráficamente la comparativa de las métricas. Dispone de dashboards predefinidos que se pueden reutilizar. Disponible en el puerto 3000 accesible con el usuario admin y contraseña admin (seguridad ante todo :sunglasses:).
- __AlertManager:__ Integrado pero deshabilitado. Disponible en el puerto 9093.
- __NodeExporter:__ Integrado pero deshabilitado. Disponible en el puerto 9100.
- __CaAdvidor:__ Integrado pero deshabilitado. Disponible en el puerto 8080.
- __PushGateway:__ Integrado pero deshabilitado. Disponible en el puerto 9091.
- __Caddy:__ Integrado pero deshabilitado.

También se disponde de un fichero de configuración JMeter para realizar pruebas y medir rendimiento. Disponible en la carpeta _jmeter_.

### workshop-spring-boot
Módulo desarrollado utilizando el framework Spring Boot 2.7.4.
Los servicios no están parametrizados mediante petición (request) aunque se pueden modificar ciertos valores por fichero de propiedades. Los servicios son:
- __[GET]/graalvm/springboot/hello.__ Ejemplo básico que devuelve un mensaje de texto plano.
- __[GET]/graalvm/springboot/vocales.__ Lee un fichero de prueba que va dentro de la distribución y cuenta en número de vocales que contiene.
- __[GET]/graalvm/springboot/hilos.__ Genera un pool de 10 hilos, los cuales tienen un retardo de 300 milisegundos.

### workshop-quarkus
Próximamente!!!

### workshop-nodejs
Uffff... si no queda más remedio :stuck_out_tongue_winking_eye:

### workshop-spring-native
Con ganas de ver capacidades

### workshop-python
A ver si llegamos a ello!