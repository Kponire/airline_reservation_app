package com.oluwaponire.airline_reservation_ticket.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oluwaponire.airline_reservation_ticket.dto.BaggageDto;
import com.oluwaponire.airline_reservation_ticket.entity.Baggage;
import com.oluwaponire.airline_reservation_ticket.entity.Reservation;
import com.oluwaponire.airline_reservation_ticket.entity.User;
import com.oluwaponire.airline_reservation_ticket.exception.ResourceNotFoundException;
import com.oluwaponire.airline_reservation_ticket.repository.BaggageRepository;
import com.oluwaponire.airline_reservation_ticket.repository.ReservationRepository;
import com.oluwaponire.airline_reservation_ticket.repository.UserRepository;

@Service
public class BaggageServiceImpl implements BaggageService {

    @Autowired
    private BaggageRepository baggageRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public BaggageDto createBaggage(BaggageDto baggageDto) {
        Baggage baggage = new Baggage();

        Reservation reservation = reservationRepository.findById(baggageDto.getReservationId())
                .orElseThrow(() -> new ResourceNotFoundException("Reservation not found"));

        User user = userRepository.findById(baggageDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        baggage.setReservation(reservation);
        baggage.setUser(user);
        baggage.setBaggageTag(baggageDto.getBaggageTag());
        baggage.setStatus(baggageDto.getStatus());
        baggage.setCheckedInTime(baggageDto.getCheckedInTime());
        baggage.setClaimedTime(baggageDto.getClaimedTime());

        Baggage savedBaggage = baggageRepository.save(baggage);
        return convertToDto(savedBaggage);
    }

    @Override
    public BaggageDto getBaggageById(Long id) {
        Baggage baggage = baggageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Baggage not found"));
        return convertToDto(baggage);
    }

    @Override
    public BaggageDto updateBaggage(Long id, BaggageDto baggageDto) {
        Baggage baggage = baggageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Baggage not found"));

        Reservation reservation = reservationRepository.findById(baggageDto.getReservationId())
                .orElseThrow(() -> new ResourceNotFoundException("Reservation not found"));

        User user = userRepository.findById(baggageDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        baggage.setReservation(reservation);
        baggage.setUser(user);
        baggage.setBaggageTag(baggageDto.getBaggageTag());
        baggage.setStatus(baggageDto.getStatus());
        baggage.setCheckedInTime(baggageDto.getCheckedInTime());
        baggage.setClaimedTime(baggageDto.getClaimedTime());

        Baggage updatedBaggage = baggageRepository.save(baggage);
        return convertToDto(updatedBaggage);
    }

    @Override
    public void deleteBaggage(Long id) {
        Baggage baggage = baggageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Baggage not found"));
        baggageRepository.delete(baggage);
    }

    @Override
    public List<BaggageDto> getBaggageByUserId(Long userId) {
        List<Baggage> baggages = baggageRepository.findByUserId(userId);
        return baggages.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<BaggageDto> getBaggageByReservationId(Long reservationId) {
        List<Baggage> baggages = baggageRepository.findByReservationId(reservationId);
        return baggages.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<BaggageDto> getBaggageByStatus(String status) {
        List<Baggage> baggages = baggageRepository.findByStatus(status);
        return baggages.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private BaggageDto convertToDto(Baggage baggage) {
        BaggageDto baggageDto = new BaggageDto();
        baggageDto.setId(baggage.getId());
        baggageDto.setReservationId(baggage.getReservation().getId());
        baggageDto.setUserId(baggage.getUser().getId());
        baggageDto.setBaggageTag(baggage.getBaggageTag());
        baggageDto.setStatus(baggage.getStatus());
        baggageDto.setCheckedInTime(baggage.getCheckedInTime());
        baggageDto.setClaimedTime(baggage.getClaimedTime());
        return baggageDto;
    }
}