package com.oluwaponire.airline_reservation_ticket.dto;

import java.sql.Date;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Valid
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentDto {
    private Long reservationId;
    private Long userId;
    private String paymentMethod;
    private String paymentStatus;
    private Date paymentDate;
    private double amount;
    private String transactionId;
}
