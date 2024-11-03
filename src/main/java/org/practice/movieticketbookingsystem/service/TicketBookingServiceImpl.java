package org.practice.movieticketbookingsystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.locks.ReentrantLock;

import org.practice.movieticketbookingsystem.datastore.InMemoryData;
import org.practice.movieticketbookingsystem.enums.BookingStatus;
import org.practice.movieticketbookingsystem.model.Booking;
import org.practice.movieticketbookingsystem.model.Seat;
import org.practice.movieticketbookingsystem.model.Show;
import org.practice.movieticketbookingsystem.model.User;

public class TicketBookingServiceImpl implements TicketBookingService {

  private final BookingService bookingService = new BookingService();
  private final ShowService showService = new ShowService();
  private final PaymentService paymentService = new PaymentService();
  private final UserService userService = new UserService();

  private final ReentrantLock lock = new ReentrantLock();

  @Override
  public List<Seat> getAvailableSeats(String showId) {
    List<Seat> availableSeats;

    List<Seat> reservedSeats = new ArrayList<>();

    List<Booking> bookings = InMemoryData.getInstance().getBookings();
    bookings.stream()
        .filter(booking -> !booking.getBookingStatus().equals(BookingStatus.FAILURE))
        .forEach(booking -> reservedSeats.addAll(booking.getSeat()));

    Show currentShow = showService.getShowById(showId);

    if (currentShow != null) {
      availableSeats = new ArrayList<>(currentShow.getScreen().getSeats());
      availableSeats.removeAll(reservedSeats);
    } else {
      throw new RuntimeException("Invalid Show Id");
    }
    return availableSeats;
  }

  @Override
  public  Booking bookTickets(String userId, String showId, List<Seat> seats) {

    Booking booking = null;
    lock.lock();
    try {
      if (!bookingService.isSeatsAvailable(showId, seats)) {
        throw new RuntimeException("Some of the selected seats unavailable at this point");
      }

      UUID uuid = UUID.randomUUID();
      String bookingId = uuid.toString();
      Show show = showService.getShowById(showId);
      booking = Booking.builder()
          .seat(seats)
          .bookingId(bookingId)
          .bookingStatus(BookingStatus.STARTED)
          .show(show)
          .startTime(show.getStartTime())
          .build();

      InMemoryData.getInstance().getBookings().add(booking);
    } finally {
      lock.unlock();
    }

    User user = userService.getUserById(userId);

    paymentService.deductPayment(user, booking);

    return booking;
  }
}
