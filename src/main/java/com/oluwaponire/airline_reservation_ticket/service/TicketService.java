package com.oluwaponire.airline_reservation_ticket.service;

import com.oluwaponire.airline_reservation_ticket.dto.TicketDto;

public interface TicketService {

    TicketDto createTicket(TicketDto ticketDto);

    TicketDto getTicketById(Long id);

    TicketDto updateTicket(Long id, TicketDto ticketDto);

    void deleteTicket(Long id);

}
