package com.oluwaponire.airline_reservation_ticket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oluwaponire.airline_reservation_ticket.dto.BaggageDto;
import com.oluwaponire.airline_reservation_ticket.service.BaggageService;

@RestController
@RequestMapping("/api/baggage")
public class BaggageController {

    @Autowired
    private BaggageService baggageService;

    @PostMapping
    public ResponseEntity<BaggageDto> createBaggage(@RequestBody BaggageDto baggageDto) {
        BaggageDto createdBaggage = baggageService.createBaggage(baggageDto);
        return ResponseEntity.ok(createdBaggage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaggageDto> getBaggageById(@PathVariable Long id) {
        BaggageDto baggageDto = baggageService.getBaggageById(id);
        return ResponseEntity.ok(baggageDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaggageDto> updateBaggage(@PathVariable Long id, @RequestBody BaggageDto baggageDto) {
        BaggageDto updatedBaggage = baggageService.updateBaggage(id, baggageDto);
        return ResponseEntity.ok(updatedBaggage);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBaggage(@PathVariable Long id) {
        baggageService.deleteBaggage(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BaggageDto>> getBaggageByUserId(@PathVariable Long userId) {
        List<BaggageDto> baggageDtos = baggageService.getBaggageByUserId(userId);
        return ResponseEntity.ok(baggageDtos);
    }

    @GetMapping("/reservation/{reservationId}")
    public ResponseEntity<List<BaggageDto>> getBaggageByReservationId(@PathVariable Long reservationId) {
        List<BaggageDto> baggageDtos = baggageService.getBaggageByReservationId(reservationId);
        return ResponseEntity.ok(baggageDtos);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<BaggageDto>> getBaggageByStatus(@PathVariable String status) {
        List<BaggageDto> baggageDtos = baggageService.getBaggageByStatus(status);
        return ResponseEntity.ok(baggageDtos);
    }
}