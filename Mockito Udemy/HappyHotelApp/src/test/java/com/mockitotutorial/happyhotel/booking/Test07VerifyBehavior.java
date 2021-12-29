package com.mockitotutorial.happyhotel.booking;

import static org.mockito.Mockito.*;

import java.time.LocalDate;

import org.junit.jupiter.api.*;

class Test07VerifyBehavior {

	private BookingService bookingService;
	private PaymentService paymentServiceMock;
	private RoomService roomServiceMock;
	private BookingDAO bookingDAOMock;
	private MailSender mailSenderMock;
	
	@BeforeEach
	void setup() {
		paymentServiceMock = mock(PaymentService.class);
		roomServiceMock = mock(RoomService.class);
		bookingDAOMock = spy(BookingDAO.class);
		mailSenderMock = mock(MailSender.class);
		
		this.bookingService = new BookingService(paymentServiceMock, roomServiceMock, bookingDAOMock, mailSenderMock);
	}
	
	@Test
	void should_InvokePayment_When_Prepaid() {
		// given
		BookingRequest bookingRequest = new BookingRequest("1", LocalDate.of(2020, 01, 01),
				LocalDate.of(2020, 01, 05), 2, true);

		// when
		bookingService.makeBooking(bookingRequest);

		// then
		verify(paymentServiceMock, times(1)).pay(bookingRequest, 400.0);
		verifyNoMoreInteractions(paymentServiceMock);

	}

	@Test
	void should_NotInvokePayment_When_NotPrepaid() {
		// given
		BookingRequest bookingRequest = new BookingRequest("1", LocalDate.of(2020, 01, 01),
				LocalDate.of(2020, 01, 05), 2, false);

		// when
		bookingService.makeBooking(bookingRequest);

		// then
		verify(paymentServiceMock, never()).pay(any(), anyDouble());
	}
}
