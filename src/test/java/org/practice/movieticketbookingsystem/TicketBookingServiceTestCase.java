package org.practice.movieticketbookingsystem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.practice.movieticketbookingsystem.datastore.InMemoryData;
import org.practice.movieticketbookingsystem.enums.BookingStatus;
import org.practice.movieticketbookingsystem.factory.TheatreFactory;
import org.practice.movieticketbookingsystem.factory.TheatreInfo;
import org.practice.movieticketbookingsystem.model.Booking;
import org.practice.movieticketbookingsystem.model.Screen;
import org.practice.movieticketbookingsystem.model.Seat;
import org.practice.movieticketbookingsystem.model.Show;
import org.practice.movieticketbookingsystem.model.Theatre;
import org.practice.movieticketbookingsystem.model.User;
import org.practice.movieticketbookingsystem.service.TicketBookingService;
import org.practice.movieticketbookingsystem.service.TicketBookingServiceImpl;

public class TicketBookingServiceTestCase {

  private static final String TheatreName = "Imax";
  private static  final String userId = "userId";
  private static  final String userTwo = "userTwo";
  private static final List<String> screenNames = List.of("Audi-1");

  private TicketBookingService ticketBookingService = null;

  static UUID uuid = UUID.randomUUID();
  private  static  String showId = uuid.toString();

  static List<User> users = new ArrayList<>();

  @BeforeAll
  public static void setup() {

    Show show = Show.builder()
        .showId(showId)
        .movieName("kabali")
        .startTime(LocalDateTime.now())
        .duration(140)
        .build();

    TheatreInfo theatreInfo = TheatreInfo.builder()
        .theatreName(TheatreName)
        .rowCount(5)
        .colCount(5)
        .screenNames(screenNames)
        .seatPrice(200)
        .shows(null)
        .build();

    users.add(User.builder()
        .userId(userId)
        .mobileNumber("8185886131")
        .walletAmount(5000)
        .build());

    users.add(User.builder()
        .userId(userTwo)
        .mobileNumber("8185886131")
        .walletAmount(5000)
        .build());

    Theatre theatre = TheatreFactory.getTheatre(theatreInfo);

    InMemoryData.getInstance().setTheatres(List.of(theatre));
    InMemoryData.getInstance().setUsers(users);
    List<Screen> screens = theatre.getScreens();
    screens.get(0).setShows(List.of(show));
    show.setScreen(screens.get(0));

  }

  @Test
  public void testGetAvailableTickets() {
    ticketBookingService = new TicketBookingServiceImpl();
    List<Seat> seats = ticketBookingService.getAvailableSeats(showId);

    Assertions.assertEquals(seats.size(), 16);

  }

  @Test
  public void testBookTickets() {
    ticketBookingService = new TicketBookingServiceImpl();
    List<Seat> seats = ticketBookingService.getAvailableSeats(showId);

    List<Booking> booking = users.parallelStream().map(
        user -> ticketBookingService.bookTickets(user.getUserId(), showId, seats)
    ).collect(Collectors.toList());

//    Booking booking = ticketBookingService.bookTickets(userId, showId, seats );

    Assertions.assertEquals(booking.get(0).getBookingStatus(), BookingStatus.FAILURE);

  }



}
