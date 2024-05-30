package com.oluwaponire.airline_reservation_ticket.service;

import com.oluwaponire.airline_reservation_ticket.dto.CheckInDto;

public interface CheckInService {
    CheckInDto createCheckIn(CheckInDto checkInDto);
    CheckInDto getCheckInById(Long id);
    CheckInDto updateCheckIn(Long id, CheckInDto checkInDto);
    void deleteCheckIn(Long id);
}