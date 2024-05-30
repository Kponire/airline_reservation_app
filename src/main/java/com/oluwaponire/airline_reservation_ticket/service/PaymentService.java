package com.oluwaponire.airline_reservation_ticket.service;

import java.util.List;

import com.oluwaponire.airline_reservation_ticket.dto.PaymentDto;
import com.oluwaponire.airline_reservation_ticket.entity.Payment;

public interface PaymentService {

    Payment processPayment(PaymentDto paymentDto);

    Payment updatePayment(Long id, PaymentDto paymentDto);

    void deletePayment(Long id);

    Payment getPaymentById(Long id);

    List<Payment> getPaymentsByReservationId(Long reservationId);

    List<Payment> getPaymentsByUserId(Long userId);

    List<Payment> getPaymentsByStatus(String paymentStatus);

    List<Payment> getAllPayments();

}
