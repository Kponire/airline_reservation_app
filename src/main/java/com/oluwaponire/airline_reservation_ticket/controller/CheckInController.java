package com.oluwaponire.airline_reservation_ticket.controller;

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

import com.oluwaponire.airline_reservation_ticket.dto.CheckInDto;
import com.oluwaponire.airline_reservation_ticket.service.CheckInService;

@RestController
@RequestMapping("/api/checkins")
public class CheckInController {

    @Autowired
    private CheckInService checkInService;

    @PostMapping
    public ResponseEntity<CheckInDto> createCheckIn(@RequestBody CheckInDto checkInDto) {
        CheckInDto createdCheckIn = checkInService.createCheckIn(checkInDto);
        return ResponseEntity.ok(createdCheckIn);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CheckInDto> getCheckInById(@PathVariable Long id) {
        CheckInDto checkInDto = checkInService.getCheckInById(id);
        return ResponseEntity.ok(checkInDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CheckInDto> updateCheckIn(@PathVariable Long id, @RequestBody CheckInDto checkInDto) {
        CheckInDto updatedCheckIn = checkInService.updateCheckIn(id, checkInDto);
        return ResponseEntity.ok(updatedCheckIn);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCheckIn(@PathVariable Long id) {
        checkInService.deleteCheckIn(id);
        return ResponseEntity.noContent().build();
    }
}