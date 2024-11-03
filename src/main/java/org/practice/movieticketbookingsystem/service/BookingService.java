package org.practice.movieticketbookingsystem.service;

import java.util.ArrayList;
import java.util.List;

import org.practice.movieticketbookingsystem.datastore.InMemoryData;
import org.practice.movieticketbookingsystem.model.Booking;
import org.practice.movieticketbookingsystem.model.Seat;

public class BookingService {

  public boolean isSeatsAvailable(String showId, List<Seat> seats) {

    List<Booking> bookings = InMemoryData.getInstance().getBookings();

    List<Seat> unAvailableSeats = new ArrayList<>();

    bookings.stream()
        .filter(booking -> booking.getShow().getShowId().equals(showId))
        .forEach(booking -> unAvailableSeats.addAll(booking.getSeat()));

    return seats.stream().noneMatch(unAvailableSeats::contains);

  }
}
