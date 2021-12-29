package com.mockitotutorial.happyhotel.booking;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.*;

import java.time.LocalDate;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;

class Test06Matchers {

	private BookingService bookingService;
	private PaymentService paymentServiceMock;
	private RoomService roomServiceMock;
	private BookingDAO bookingDAOMock;
	private MailSender mailSenderMock;
	
	@BeforeEach
	void setup() {
		paymentServiceMock = mock(PaymentService.class);
		roomServiceMock = mock(RoomService.class);
		bookingDAOMock = mock(BookingDAO.class);
		mailSenderMock = mock(MailSender.class);
		
		this.bookingService = new BookingService(paymentServiceMock, roomServiceMock, bookingDAOMock, mailSenderMock);
	}
	
	@Test
	void NotCompletedWhenPriceTooHigh() {
		BookingRequest bookingRequest = new BookingRequest("1", LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 5), 2, true);
		when(this.paymentServiceMock.pay(any(), eq(400.0)))
			.thenThrow(BusinessException.class);
		Executable executable = () -> bookingService.makeBooking(bookingRequest);
		
		assertThrows(BusinessException.class, executable);
	}
	
}
