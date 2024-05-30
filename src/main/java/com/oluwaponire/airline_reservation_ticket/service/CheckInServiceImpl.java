package com.oluwaponire.airline_reservation_ticket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oluwaponire.airline_reservation_ticket.dto.CheckInDto;
import com.oluwaponire.airline_reservation_ticket.entity.CheckIn;
import com.oluwaponire.airline_reservation_ticket.entity.Reservation;
import com.oluwaponire.airline_reservation_ticket.entity.User;
import com.oluwaponire.airline_reservation_ticket.exception.ResourceNotFoundException;
import com.oluwaponire.airline_reservation_ticket.repository.CheckInRepository;
import com.oluwaponire.airline_reservation_ticket.repository.ReservationRepository;
import com.oluwaponire.airline_reservation_ticket.repository.UserRepository;


@Service
public class CheckInServiceImpl implements CheckInService {

    @Autowired
    private CheckInRepository checkInRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public CheckInDto createCheckIn(CheckInDto checkInDto) {
        CheckIn checkIn = new CheckIn();

        Reservation reservation = reservationRepository.findById(checkInDto.getReservationId())
                .orElseThrow(() -> new ResourceNotFoundException("Reservation not found"));

        User user = userRepository.findById(checkInDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        checkIn.setReservation(reservation);
        checkIn.setUser(user);
        checkIn.setSeatNumber(checkInDto.getSeatNumber());
        checkIn.setCheckInTime(checkInDto.getCheckInTime());

        CheckIn savedCheckIn = checkInRepository.save(checkIn);
        return convertToDto(savedCheckIn);
    }

    @Override
    public CheckInDto getCheckInById(Long id) {
        CheckIn checkIn = checkInRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Check-in not found"));
        return convertToDto(checkIn);
    }

    @Override
    public CheckInDto updateCheckIn(Long id, CheckInDto checkInDto) {
        CheckIn checkIn = checkInRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Check-in not found"));

        Reservation reservation = reservationRepository.findById(checkInDto.getReservationId())
                .orElseThrow(() -> new ResourceNotFoundException("Reservation not found"));

        User user = userRepository.findById(checkInDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        checkIn.setReservation(reservation);
        checkIn.setUser(user);
        checkIn.setSeatNumber(checkInDto.getSeatNumber());
        checkIn.setCheckInTime(checkInDto.getCheckInTime());

        CheckIn updatedCheckIn = checkInRepository.save(checkIn);
        return convertToDto(updatedCheckIn);
    }

    @Override
    public void deleteCheckIn(Long id) {
        CheckIn checkIn = checkInRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Check-in not found"));
        checkInRepository.delete(checkIn);
    }

    private CheckInDto convertToDto(CheckIn checkIn) {
        CheckInDto checkInDto = new CheckInDto();
        checkInDto.setId(checkIn.getId());
        checkInDto.setReservationId(checkIn.getReservation().getId());
        checkInDto.setUserId(checkIn.getUser().getId());
        checkInDto.setSeatNumber(checkIn.getSeatNumber());
        checkInDto.setCheckInTime(checkIn.getCheckInTime());
        return checkInDto;
    }
}