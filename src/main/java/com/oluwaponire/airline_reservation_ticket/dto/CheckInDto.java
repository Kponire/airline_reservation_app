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
public class CheckInDto {

    private Long id;
    private Long reservationId;
    private Long userId;
    private String seatNumber;
    private LocalDateTime checkInTime;

}
