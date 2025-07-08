package com.design.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.design.dto.BookingHistoryResponse;
import com.design.service.BookingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {
	
	private final BookingService bookingService;

	@GetMapping("/user/{userId}")
	public ResponseEntity<Page<BookingHistoryResponse>> getUserBookingHistory(
	        @PathVariable Long userId,
	        @RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "5") int size) {

	    Pageable pageable = PageRequest.of(page, size);
	    Page<BookingHistoryResponse> history = bookingService.getBookingHistoryByUserId(userId, pageable);
	    return ResponseEntity.ok(history);
	} 

	
	
	
//  @GetMapping("/user/{userId}")
//  public ResponseEntity<List<BookingHistoryResponse>> getUserBookingHistory(@PathVariable Long userId) {
//      List<BookingHistoryResponse> history = bookingService.getBookingHistoryByUserId(userId);
//      return ResponseEntity.ok(history);
//  }
	
	
}
