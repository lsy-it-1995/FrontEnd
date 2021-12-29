package com.mockitotutorial.happyhotel.booking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.*;

import org.junit.jupiter.api.*;
import org.mockito.ArgumentCaptor;

class Test10ArgumentCaptors {

	private BookingService bookingService;
	private PaymentService paymentServiceMock;
	private RoomService roomServiceMock;
	private BookingDAO bookingDAOMock;
	private MailSender mailSenderMock;
	private ArgumentCaptor<Double> doubleCaptor;
	
	@BeforeEach
	void setup() {
		paymentServiceMock = mock(PaymentService.class);
		roomServiceMock = mock(RoomService.class);
		bookingDAOMock = spy(BookingDAO.class);
		mailSenderMock = mock(MailSender.class);
		
		this.bookingService = new BookingService(paymentServiceMock, roomServiceMock, bookingDAOMock, mailSenderMock);
		this.doubleCaptor = ArgumentCaptor.forClass(Double.class);
	}
	
	@Test
	void payCorrectPriceWhenOneCall() {
		BookingRequest bookingRequest = new BookingRequest("1", LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 5), 2, true);
		bookingService.makeBooking(bookingRequest);
		
		verify(paymentServiceMock, times(1)).pay(eq(bookingRequest), doubleCaptor.capture());
		double capturedArgument = doubleCaptor.getValue();
		assertEquals(capturedArgument, 400.0);
	}
	
	@Test
	void payCorrectPriceWhenMultipleCall() {
		BookingRequest bookingRequest = new BookingRequest("1", LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 5), 2, true);
		BookingRequest bookingRequest2 = new BookingRequest("2", LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 2), 2, true);
		List<Double> expect = Arrays.asList(400.0, 100.0);
		bookingService.makeBooking(bookingRequest);
		bookingService.makeBooking(bookingRequest2);
		verify(paymentServiceMock, times(2)).pay(any(), doubleCaptor.capture());
		List<Double> capturedArgument = doubleCaptor.getAllValues();
		
		assertEquals(capturedArgument, expect);
	}
}
