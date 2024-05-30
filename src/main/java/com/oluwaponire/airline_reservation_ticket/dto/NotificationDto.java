package com.oluwaponire.airline_reservation_ticket.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationDto {

    private Long userId;
    private String type;
    private String message;
    private Date timestamp;
    private boolean read;
}
