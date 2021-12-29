package com.mockitotutorial.happyhotel.booking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class Test11Annotattions {

	@InjectMocks
	private BookingService bookingService;
	
	@Mock
	private PaymentService paymentServiceMock;
	@Mock
	private RoomService roomServiceMock;
	@Spy
	private BookingDAO bookingDAOMock;
	@Mock
	private MailSender mailSenderMock;
	@Captor
	private ArgumentCaptor<Double> doubleCaptor;
	
	@BeforeEach
	void setup() {

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
