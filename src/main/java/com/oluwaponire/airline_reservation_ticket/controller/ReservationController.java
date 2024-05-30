package com.oluwaponire.airline_reservation_ticket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oluwaponire.airline_reservation_ticket.dto.ReservationRequestDto;
import com.oluwaponire.airline_reservation_ticket.entity.Reservation;
import com.oluwaponire.airline_reservation_ticket.service.ReservationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping("/book")
    public ResponseEntity<String> bookReservation(@Valid @RequestBody ReservationRequestDto reservationRequestDto) {
        reservationService.bookReservation(reservationRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Reservation booked successfully");
    }

    @GetMapping("/{reservationId}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable Long reservationId) {
        Reservation reservation = reservationService.getReservationById(reservationId);
        if (reservation == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(reservation);
    }

    @PutMapping("/{reservationId}")
    public ResponseEntity<String> updateReservation(@PathVariable Long reservationId, @Valid @RequestBody ReservationRequestDto reservationRequestDto) {
        boolean updated = reservationService.updateReservation(reservationId, reservationRequestDto);
        if (updated) {
            return ResponseEntity.ok("Reservation updated successfully");
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{reservationId}")
    public ResponseEntity<String> cancelReservation(@PathVariable Long reservationId) {
        boolean canceled = reservationService.cancelReservation(reservationId);
        if (canceled) {
            return ResponseEntity.ok("Reservation cancelled successfully");
        }
        return ResponseEntity.notFound().build();
    }

    
}
