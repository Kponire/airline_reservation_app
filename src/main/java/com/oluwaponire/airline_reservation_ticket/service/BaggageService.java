package com.oluwaponire.airline_reservation_ticket.service;

import java.util.List;

import com.oluwaponire.airline_reservation_ticket.dto.BaggageDto;

public interface BaggageService {
    BaggageDto createBaggage(BaggageDto baggageDto);
    BaggageDto getBaggageById(Long id);
    BaggageDto updateBaggage(Long id, BaggageDto baggageDto);
    void deleteBaggage(Long id);
    List<BaggageDto> getBaggageByUserId(Long userId);
    List<BaggageDto> getBaggageByReservationId(Long reservationId);
    List<BaggageDto> getBaggageByStatus(String status);
}