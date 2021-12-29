package com.mockitotutorial.happyhotel.booking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.jupiter.api.*;

class Test16FinalMethods {

	private BookingService bookingService;
	private PaymentService paymentServiceMock;
	private RoomService roomServiceMock;
	private BookingDAO bookingDAOMock;
	private MailSender mailSenderMock;
	
	@Test
	void CountAvailablePlaceOneRoom() {
		when(this.roomServiceMock.getAvailableRooms()).thenReturn(Collections.singletonList(new Room("1", 5)));
		
		int actual = bookingService.getAvailablePlaceCount();
		int expect = 5;
		assertEquals(actual, expect);
	}
	
}
