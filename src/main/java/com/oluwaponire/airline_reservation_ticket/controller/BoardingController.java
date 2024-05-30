package com.oluwaponire.airline_reservation_ticket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.oluwaponire.airline_reservation_ticket.dto.BoardingDto;
import com.oluwaponire.airline_reservation_ticket.service.BoardingService;

@RestController
@RequestMapping("/api/boardings")
public class BoardingController {

    @Autowired
    private BoardingService boardingService;

    @PostMapping
    public ResponseEntity<BoardingDto> createBoarding(@RequestBody BoardingDto boardingDto) {
        BoardingDto createdBoarding = boardingService.createBoarding(boardingDto);
        return ResponseEntity.ok(createdBoarding);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoardingDto> getBoardingById(@PathVariable Long id) {
        BoardingDto boardingDto = boardingService.getBoardingById(id);
        return ResponseEntity.ok(boardingDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BoardingDto> updateBoarding(@PathVariable Long id, @RequestBody BoardingDto boardingDto) {
        BoardingDto updatedBoarding = boardingService.updateBoarding(id, boardingDto);
        return ResponseEntity.ok(updatedBoarding);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoarding(@PathVariable Long id) {
        boardingService.deleteBoarding(id);
        return ResponseEntity.noContent().build();
    }
}