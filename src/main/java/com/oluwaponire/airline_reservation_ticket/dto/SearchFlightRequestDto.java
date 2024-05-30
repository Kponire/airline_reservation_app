package com.oluwaponire.airline_reservation_ticket.dto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchFlightRequestDto {

    private String fromDestination;
    private String toDestination;
    private Date departureDate;
    private Date returnDate;
    private String travelClass;
    private int numberOfPassengers;
    private String tripType;
    private List<Leg> legs;

    @Data
    public static class Leg {
        private String fromDestination;
        private String toDestination;
        private Date departureDate;
    }

}
