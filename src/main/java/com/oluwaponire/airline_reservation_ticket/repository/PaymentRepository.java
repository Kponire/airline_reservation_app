package com.oluwaponire.airline_reservation_ticket.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oluwaponire.airline_reservation_ticket.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findByReservationId(Long reservationId);
    List<Payment> findByUserId(Long userId);
    List<Payment> findByPaymentStatus(String paymentStatus);
    List<Payment> findByPaymentDateBetween(Date startDate, Date endDate);
}
