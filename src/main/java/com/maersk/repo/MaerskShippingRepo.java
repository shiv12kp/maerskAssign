package com.maersk.repo;


import org.springframework.data.jpa.repository.JpaRepository;

import com.maersk.dto.BookingRequest;

public interface MaerskShippingRepo extends  JpaRepository<BookingRequest, Long> {

}
