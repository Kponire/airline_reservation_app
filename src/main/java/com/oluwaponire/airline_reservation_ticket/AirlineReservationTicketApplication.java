package com.oluwaponire.airline_reservation_ticket;

import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.oluwaponire.airline_reservation_ticket.repository")
@EnableJdbcRepositories(basePackages = "com.oluwaponire.airline_reservation_ticket.repository")
//@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class AirlineReservationTicketApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirlineReservationTicketApplication.class, args);
	}

}
