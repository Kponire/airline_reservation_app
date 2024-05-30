package com.oluwaponire.airline_reservation_ticket.dto;

import java.sql.Date;
import java.time.LocalDate;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Valid
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationRequestDto {

    @NotBlank
    private String username;

    @NotBlank
    private String flight;

    @NotBlank
    private String fromDestination;

    @NotBlank
    private String toDestination;

    @NotBlank
    private Date departDate;

    private Date returnDate;

    private String travelClass;

    private int numberOfPassengers;

    private LocalDate bookingDate;
}
