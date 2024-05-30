package com.oluwaponire.airline_reservation_ticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oluwaponire.airline_reservation_ticket.entity.CheckIn;

@Repository
public interface CheckInRepository extends JpaRepository<CheckIn, Long> {
}