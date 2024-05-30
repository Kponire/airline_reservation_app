package com.oluwaponire.airline_reservation_ticket.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReportDto {

    private String title;
    private Date startDate;
    private Date endDate;
    private String details;
}
