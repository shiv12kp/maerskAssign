package com.maersk.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.LowLevelHttpRequest;
import com.google.api.client.http.LowLevelHttpResponse;
import com.google.api.client.json.Json;
import com.google.api.client.testing.http.MockHttpTransport;
import com.google.api.client.testing.http.MockLowLevelHttpRequest;
import com.google.api.client.testing.http.MockLowLevelHttpResponse;
import com.google.common.base.Charsets;
import com.google.common.io.ByteStreams;
import com.maersk.dto.BookingRequest;
import com.maersk.repo.MaerskShippingRepo;

@Service
public class CheckAvailableServiceImpl {

	private static int count = 6;
	Logger logger = LoggerFactory.getLogger(CheckAvailableServiceImpl.class);

	@Autowired
	MaerskShippingRepo maerskShippingRepo;

	public boolean getAvailabilityInfo() throws IOException, JSONException {

		this.logger.info("Before Third party response");
		GenericUrl x = new GenericUrl(URI.create("https://maersk.com/api/bookings/checkAvailable"));

		HttpTransport transport = new MockHttpTransport() {
			@Override
			public LowLevelHttpRequest buildRequest(String method, String url) throws IOException {
				return new MockLowLevelHttpRequest() {
					@Override
					public LowLevelHttpResponse execute() throws IOException {
						MockLowLevelHttpResponse response = new MockLowLevelHttpResponse();
						response.setStatusCode(200);
						response.setContentType(Json.MEDIA_TYPE);

						String stringContent = "{\"availableSpace\"";
						stringContent = stringContent + ":" + count + "}";
						response.setContent(stringContent);
						return response;
					}
				};
			}
		};

		HttpRequest request = transport.createRequestFactory().buildGetRequest(x);
		com.google.api.client.http.HttpResponse response = request.execute();
		InputStream inputStream = response.getContent();
		String stringResponse = new String(ByteStreams.toByteArray(inputStream), Charsets.UTF_8);
		this.logger.debug("Response from thrid party service {}", stringResponse);
		JSONObject jsonObject = new JSONObject(stringResponse);
		int availableSpace = jsonObject.getInt("availableSpace");
		this.logger.info("Available space for the container {}", availableSpace);
		return true;
	}

	public Long saveBookingInfo(BookingRequest bookingRequest) {
		this.logger.info("Saving information");
		if (count > 0 && count > bookingRequest.getQuantity()) {
			BookingRequest savedBookingRequest = maerskShippingRepo.save(bookingRequest);
			extracted(bookingRequest);
			this.logger.info("Saved information");
			return savedBookingRequest.getUUID().longValue();
		} else {
			this.logger.info("Not enough quantity available.");
		}
		return 0l;

	}

	private static void extracted(BookingRequest bookingRequest) {
		count = count - bookingRequest.getQuantity();
	}

	public void findBookingEntity(Long id) {
		maerskShippingRepo.findById(id);

	}

}
