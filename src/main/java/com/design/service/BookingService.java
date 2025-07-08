package com.design.service;

import java.util.Arrays;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.design.dto.BookingHistoryResponse;
import com.design.entity.Booking;
import com.design.exception.ResourceNotFoundException;
import com.design.repository.BookingRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingService {
	
	private final BookingRepository bookingRepository;

	
	public Page<BookingHistoryResponse> getBookingHistoryByUserId(Long userId, Pageable pageable) {
	    Page<Booking> bookingsPage = bookingRepository.findByUserId(userId, pageable);

	    if (bookingsPage.isEmpty()) {
	        throw new ResourceNotFoundException("Invalid userId; no bookings found");
	    }
	    return bookingsPage.map(booking -> BookingHistoryResponse.builder()
	            .bookingId(booking.getId())
	            .showId(booking.getShow().getId())
	            .totalTickets(booking.getTotalTickets())
	            .seatNumbers(Arrays.asList(booking.getSeatNumbers().split(",")))
	            .status(booking.getStatus().name())
	            .bookingTime(booking.getBookingTime())
	            .build());
	}
	
	
	
	
	
//  public List<BookingHistoryResponse> getBookingHistoryByUserId(Long userId) {
//  List<Booking> bookings = bookingRepository.findByUserId(userId);
//  if (bookings.isEmpty()) {
//      throw new ResourceNotFoundException("Invalid userId; no bookings found");
//  }
//
//  return bookings.stream().map(booking -> BookingHistoryResponse.builder()
//          .bookingId(booking.getId())
//          .showId(booking.getShow().getId())
//          .totalTickets(booking.getTotalTickets())
//          .seatNumbers(Arrays.asList(booking.getSeatNumbers().split(",")))
//          .status(booking.getStatus().name())
//          .bookingTime(booking.getBookingTime())
//          .build()).collect(Collectors.toList());
//}

} 
