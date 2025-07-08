package com.design.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.design.entity.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

//	List<Booking> findByUserId(Long userId);
	
	Page<Booking> findByUserId(Long userId, Pageable pageable);
}
