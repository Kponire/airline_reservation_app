package com.oluwaponire.airline_reservation_ticket.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oluwaponire.airline_reservation_ticket.entity.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByBookingDateBetween(Date startDate, Date endDate);

}
