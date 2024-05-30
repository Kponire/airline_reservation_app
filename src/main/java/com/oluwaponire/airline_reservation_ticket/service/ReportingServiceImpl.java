package com.oluwaponire.airline_reservation_ticket.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oluwaponire.airline_reservation_ticket.dto.ReportDto;
import com.oluwaponire.airline_reservation_ticket.entity.Payment;
import com.oluwaponire.airline_reservation_ticket.entity.Reservation;
import com.oluwaponire.airline_reservation_ticket.entity.User;
import com.oluwaponire.airline_reservation_ticket.repository.PaymentRepository;
import com.oluwaponire.airline_reservation_ticket.repository.ReservationRepository;
import com.oluwaponire.airline_reservation_ticket.repository.UserRepository;

@Service
public class ReportingServiceImpl implements ReportingService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public ReportDto getFlightBookingReport(Date startDate, Date endDate) {
        List<Reservation> bookings = reservationRepository.findByBookingDateBetween(startDate, endDate);
        ReportDto report = new ReportDto();
        report.setTitle("Flight Booking Report");
        report.setStartDate(startDate);
        report.setEndDate(endDate);
        report.setDetails(bookings.size() + " bookings made");
        return report;
    }

    @Override
    public ReportDto getUserRegistrationReport(Date startDate, Date endDate) {
        List<User> users = userRepository.findByRegistrationDateBetween(startDate, endDate);
        ReportDto report = new ReportDto();
        report.setTitle("User Registration Report");
        report.setStartDate(startDate);
        report.setEndDate(endDate);
        report.setDetails(users.size() + " users registered");
        return report;
    }

    @Override
    public ReportDto getRevenueReport(Date startDate, Date endDate) {
        List<Payment> payments = paymentRepository.findByPaymentDateBetween(startDate, endDate);
        double totalRevenue = payments.stream().mapToDouble(Payment::getAmount).sum();
        ReportDto report = new ReportDto();
        report.setTitle("Revenue Report");
        report.setStartDate(startDate);
        report.setEndDate(endDate);
        report.setDetails("Total revenue: $" + totalRevenue);
        return report;
    }

    @Override
    public List<ReportDto> getAllReports() {
        Date startDate = new Date();
        Date endDate = new Date();
        return List.of(
                getFlightBookingReport(startDate, endDate),
                getUserRegistrationReport(startDate, endDate),
                getRevenueReport(startDate, endDate)
        );
    }

}
