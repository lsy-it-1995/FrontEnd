package com.mockitotutorial.happyhotel.booking;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;

import org.junit.jupiter.api.*;

class Test04MultiThenReturnCalls {

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
	void CountAvailablePlacesWehnCallMultipleReturn() {
		when(this.roomServiceMock.getAvailableRooms())
			.thenReturn(Collections.singletonList(new Room("1", 3)))
			.thenReturn(Collections.emptyList());
		
		int expectFirstCall = 3;
		int expectSecondCall = 0;
		
		int actualFirst = this.bookingService.getAvailablePlaceCount();
		int actualSecond = this.bookingService.getAvailablePlaceCount();
		
		assertAll(
				() -> assertEquals(expectFirstCall,  actualFirst),
				() -> assertEquals(expectSecondCall,  actualSecond));
	}
	
}
