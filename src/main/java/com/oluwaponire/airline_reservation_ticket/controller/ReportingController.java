package com.oluwaponire.airline_reservation_ticket.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oluwaponire.airline_reservation_ticket.dto.ReportDto;
import com.oluwaponire.airline_reservation_ticket.service.ReportingService;

@RestController
@RequestMapping("/api/reports")
public class ReportingController {

    @Autowired
    private ReportingService reportingService;

    @GetMapping("/flights-bookings")
    public ResponseEntity<ReportDto> getFlightBookingReport(@RequestParam Date startDate, @RequestParam Date endDate) {
        ReportDto report = reportingService.getFlightBookingReport(startDate, endDate);
        return ResponseEntity.ok(report);
    }

    @GetMapping("/user-registrations")
    public ResponseEntity<ReportDto> getUserRegistrationReport(@RequestParam Date startDate, @RequestParam Date endDate) {
        ReportDto report = reportingService.getUserRegistrationReport(startDate, endDate);
        return ResponseEntity.ok(report);
    }

    @GetMapping("/revenue")
    public ResponseEntity<ReportDto> getRevenueReport(@RequestParam Date startDate, @RequestParam Date endDate) {
        ReportDto report = reportingService.getRevenueReport(startDate, endDate);
        return ResponseEntity.ok(report);
    }

    @GetMapping
    public ResponseEntity<List<ReportDto>> getAllReports() {
        List<ReportDto> reports = reportingService.getAllReports();
        return ResponseEntity.ok(reports);
    }
    
}
