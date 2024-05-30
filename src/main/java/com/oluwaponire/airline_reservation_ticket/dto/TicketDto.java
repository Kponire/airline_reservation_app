package com.oluwaponire.airline_reservation_ticket.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketDto {

    private Long id;
    private String ticketNumber;
    private Long flightId;
    private Long reservationId;
    private Long userId;
    private String seatNumber;
    private String ticketStatus;
    private LocalDateTime bookingDate;
    private LocalDateTime departureDate;
    private LocalDateTime arrivalDate;
}
