package com.oluwaponire.airline_reservation_ticket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oluwaponire.airline_reservation_ticket.entity.Baggage;

@Repository
public interface BaggageRepository extends JpaRepository<Baggage, Long> {
    List<Baggage> findByUserId(Long userId);
    List<Baggage> findByReservationId(Long reservationId);
    List<Baggage> findByStatus(String status);
}