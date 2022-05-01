package com.maersk;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

@SpringBootApplication
public class MaerskShippingApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(MaerskShippingApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		HttpServer server = HttpServer.create(new InetSocketAddress(8081), 0);
		server.createContext("/maersk.com/api/bookings/checkAvailable", new MyHandler());
		// Thread control is given to executor service.
		server.setExecutor(java.util.concurrent.Executors.newCachedThreadPool());
		server.start();

	}

	static class MyHandler implements HttpHandler {
		@Override
		public void handle(HttpExchange t) throws IOException {
			String response = "This is the response";
			long threadId = Thread.currentThread().getId();
			System.out.println("I am thread " + threadId);
			response = response + "Thread Id = " + threadId;
			t.sendResponseHeaders(200, response.length());
			OutputStream os = t.getResponseBody();
			os.write(response.getBytes());
			os.close();
		}
	}

}
