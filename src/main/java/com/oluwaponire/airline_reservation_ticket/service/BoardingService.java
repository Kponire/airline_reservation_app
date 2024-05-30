package com.oluwaponire.airline_reservation_ticket.service;

import com.oluwaponire.airline_reservation_ticket.dto.BoardingDto;

public interface BoardingService {
    BoardingDto createBoarding(BoardingDto boardingDto);
    BoardingDto getBoardingById(Long id);
    BoardingDto updateBoarding(Long id, BoardingDto boardingDto);
    void deleteBoarding(Long id);
}