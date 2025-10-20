package es.dsw.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
@ComponentScan(basePackages="es.dsw")
@SpringBootApplication
public class TicketingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketingSystemApplication.class, args);
	}

}
