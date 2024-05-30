package com.oluwaponire.airline_reservation_ticket.service;

import java.util.Date;
import java.util.List;

import com.oluwaponire.airline_reservation_ticket.dto.ReportDto;

public interface ReportingService {

    ReportDto getFlightBookingReport(Date startDate, Date endDate);

    ReportDto getUserRegistrationReport(Date startDate, Date endDate);

    ReportDto getRevenueReport(Date startDate, Date endDate);

    List<ReportDto> getAllReports();

}
