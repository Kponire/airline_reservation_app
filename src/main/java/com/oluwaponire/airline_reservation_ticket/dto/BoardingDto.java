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
public class BoardingDto {

    private Long id;
    private Long checkInId;
    private Long userId;
    private LocalDateTime boardingTime;
    private String gateNumber;

}
