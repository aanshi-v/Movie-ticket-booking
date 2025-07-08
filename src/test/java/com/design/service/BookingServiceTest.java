package com.design.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.design.dto.BookingHistoryResponse;
import com.design.entity.Booking;
import com.design.entity.Booking.BookingStatus;
import com.design.entity.Show;
import com.design.entity.User;
import com.design.exception.ResourceNotFoundException;
import com.design.repository.BookingRepository;

//@SpringBootTest                      //This loads the full Spring Boot application context, including database connection — which overrides your mock setup.
@ExtendWith(MockitoExtension.class)    //to avoid database and use only mocks.
public class BookingServiceTest {
	
	 @Mock
	    private BookingRepository bookingRepo;

	    @InjectMocks
	    private BookingService bookingService;


	   	       
	    @Test
	    void testGetBookingHistoryByUserId_whenBookingsExist_returnsList() {
	        // Given
	        Long userId = 1L;
	        Pageable pageable = PageRequest.of(0, 5);
	        
	        User mockUser = User.builder()
	                .id(userId)
	                .name("Test User")
	                .email("test@example.com")
	                .build();

	        Booking booking = Booking.builder()
	                .id(100L)
	                .user(mockUser)
	                .show(Show.builder().id(501L).build())
	                .seatNumbers("A1,A2")
	                .totalTickets(2)
	                .status(BookingStatus.BOOKED)
	                .bookingTime(LocalDateTime.of(2025, 7, 7, 12, 30))
	                .build();
	        
	     // Debug print to verify booking is built correctly
	        System.out.println("Mocked Booking: " + booking);
	        
	        List<Booking> bookingList = List.of(booking);
	        Page<Booking> bookingPage = new PageImpl<>(bookingList);

	        when(bookingRepo.findByUserId(userId, pageable)).thenReturn(bookingPage);

	        // When
	        try {
	        	Page<BookingHistoryResponse> result = bookingService.getBookingHistoryByUserId(userId, pageable);
	    	     // Debug print to verify what is returned by the service
	    	        System.out.println("Booking result: " + result);
	    	        
	    	        // Then
	    	        assertEquals(1, result.getSize());
	    	        BookingHistoryResponse response = result.getContent().get(0);
	    	        assertEquals(100L, response.getBookingId());
	    	        assertEquals(501L, response.getShowId());
	    	        assertEquals(2, response.getTotalTickets());
	    	        assertEquals(List.of("A1", "A2"), response.getSeatNumbers());
	    	        assertEquals("BOOKED", response.getStatus());
	    	        assertEquals(LocalDateTime.of(2025, 7, 7, 12, 30), response.getBookingTime());
	        	
			} catch (Exception e) {				
				System.err.println("❌ Exception occurred: " + e.getClass().getSimpleName() + " - " + e.getMessage());
		        e.printStackTrace(); // Very important to find the actual root cause
			}
	       
	        
}
	    
	    
	    
	        
	    @Test
	    void testGetBookingHistoryByUserId_whenNoBookings_throwsException() {
	        // Given
	        Long userId = 999L;
	        Pageable pageable = PageRequest.of(0, 5);
	        Page<Booking> emptyPage = Page.empty();
	        
	        when(bookingRepo.findByUserId(userId, pageable)).thenReturn(emptyPage);

	        // Then
	        ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class, () -> {
	            bookingService.getBookingHistoryByUserId(userId, pageable);
	        });

	        assertEquals("Invalid userId; no bookings found", ex.getMessage());
	    }
	    
	    
	    

}
