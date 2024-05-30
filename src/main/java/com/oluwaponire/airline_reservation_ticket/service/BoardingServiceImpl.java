package com.oluwaponire.airline_reservation_ticket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oluwaponire.airline_reservation_ticket.dto.BoardingDto;
import com.oluwaponire.airline_reservation_ticket.entity.Boarding;
import com.oluwaponire.airline_reservation_ticket.entity.CheckIn;
import com.oluwaponire.airline_reservation_ticket.entity.User;
import com.oluwaponire.airline_reservation_ticket.exception.ResourceNotFoundException;
import com.oluwaponire.airline_reservation_ticket.repository.BoardingRepository;
import com.oluwaponire.airline_reservation_ticket.repository.CheckInRepository;
import com.oluwaponire.airline_reservation_ticket.repository.UserRepository;

@Service
public class BoardingServiceImpl implements BoardingService {

    @Autowired
    private BoardingRepository boardingRepository;

    @Autowired
    private CheckInRepository checkInRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public BoardingDto createBoarding(BoardingDto boardingDto) {
        Boarding boarding = new Boarding();

        CheckIn checkIn = checkInRepository.findById(boardingDto.getCheckInId())
                .orElseThrow(() -> new ResourceNotFoundException("Check-in not found"));

        User user = userRepository.findById(boardingDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        boarding.setCheckIn(checkIn);
        boarding.setUser(user);
        boarding.setBoardingTime(boardingDto.getBoardingTime());
        boarding.setGateNumber(boardingDto.getGateNumber());

        Boarding savedBoarding = boardingRepository.save(boarding);
        return convertToDto(savedBoarding);
    }

    @Override
    public BoardingDto getBoardingById(Long id) {
        Boarding boarding = boardingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Boarding not found"));
        return convertToDto(boarding);
    }

    @Override
    public BoardingDto updateBoarding(Long id, BoardingDto boardingDto) {
        Boarding boarding = boardingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Boarding not found"));

        CheckIn checkIn = checkInRepository.findById(boardingDto.getCheckInId())
                .orElseThrow(() -> new ResourceNotFoundException("Check-in not found"));

        User user = userRepository.findById(boardingDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        boarding.setCheckIn(checkIn);
        boarding.setUser(user);
        boarding.setBoardingTime(boardingDto.getBoardingTime());
        boarding.setGateNumber(boardingDto.getGateNumber());

        Boarding updatedBoarding = boardingRepository.save(boarding);
        return convertToDto(updatedBoarding);
    }

    @Override
    public void deleteBoarding(Long id) {
        Boarding boarding = boardingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Boarding not found"));
        boardingRepository.delete(boarding);
    }

    private BoardingDto convertToDto(Boarding boarding) {
        BoardingDto boardingDto = new BoardingDto();
        boardingDto.setId(boarding.getId());
        boardingDto.setCheckInId(boarding.getCheckIn().getId());
        boardingDto.setUserId(boarding.getUser().getId());
        boardingDto.setBoardingTime(boarding.getBoardingTime());
        boardingDto.setGateNumber(boarding.getGateNumber());
        return boardingDto;
    }
}