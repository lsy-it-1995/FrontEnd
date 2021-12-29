package com.mockitotutorial.happyhotel.booking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.jupiter.api.*;

class Test03ReturningCustomValues {

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
	void CountAvailablePlaceOneRoom() {
		when(this.roomServiceMock.getAvailableRooms()).thenReturn(Collections.singletonList(new Room("1", 5)));
		
		int actual = bookingService.getAvailablePlaceCount();
		int expect = 5;
		assertEquals(actual, expect);
	}
	@Test
	void CountAvailablePlaceMultiRoom() {
		List<Room> rooms = Arrays.asList(new Room("1", 2), new Room("2", 5));
		
		when(this.roomServiceMock.getAvailableRooms()).thenReturn(rooms);
		int actual = bookingService.getAvailablePlaceCount();
		int expect = 7;
		assertEquals(actual, expect);
	}
}
