package com.maersk.controller;

import java.io.IOException;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.maersk.dto.BookingRequest;
import com.maersk.dto.CheckAvaiableRequest;
import com.maersk.service.CheckAvailableServiceImpl;

@RestController
@RequestMapping(value = "/api/bookings")
public class MaerskShippingController {

	Logger logger = LoggerFactory.getLogger(MaerskShippingController.class);
	@Autowired
	CheckAvailableServiceImpl checkAvailableServiceImpl;

	@PostMapping(value = "/checkAvailable", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public boolean checkAvailable(@RequestBody @Validated() CheckAvaiableRequest checkAvaiableRequest)
			throws IOException, JSONException {
		this.logger.debug("Inside controller, incoming request {}",checkAvaiableRequest);
		return checkAvailableServiceImpl.getAvailabilityInfo();

	}

	@PostMapping(value = "/bookContainer", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Long bookContainer(@RequestBody @Validated() BookingRequest bookingRequest) {

		this.logger.debug("Inside controller, incoming request {}", bookingRequest);
		return this.checkAvailableServiceImpl.saveBookingInfo(bookingRequest);

	}

}
