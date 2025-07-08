package com.design.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingHistoryResponse {

	private Long bookingId;
    private Long showId;
    private int totalTickets;
    private List<String> seatNumbers;
    private String status;
    private LocalDateTime bookingTime;
}
