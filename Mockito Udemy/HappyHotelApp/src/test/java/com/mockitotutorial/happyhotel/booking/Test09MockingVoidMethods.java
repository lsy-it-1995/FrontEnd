package com.mockitotutorial.happyhotel.booking;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;

class Test09MockingVoidMethods {

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
	void ThrowExceptionWhenMailReady() {
		BookingRequest bookingRequest = new BookingRequest("1", LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 5), 2, false);
		doThrow(new BusinessException()).when(mailSenderMock).sendBookingConfirmation(any());
		Executable executable = () -> bookingService.makeBooking(bookingRequest);
		assertThrows(BusinessException.class, executable);
	}
	@Test
	void ThrowExceptionWhenMailNotReady() {
		BookingRequest bookingRequest = new BookingRequest("1", LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 5), 2, false);
		doNothing().when(mailSenderMock).sendBookingConfirmation(any());
		bookingService.makeBooking(bookingRequest);
	}
}
