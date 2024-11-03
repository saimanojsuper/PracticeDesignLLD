package org.practice.movieticketbookingsystem.service;

import java.util.List;

import org.practice.movieticketbookingsystem.model.Booking;
import org.practice.movieticketbookingsystem.model.Seat;

public interface TicketBookingService {
  List<Seat> getAvailableSeats(String showId);
  Booking bookTickets(String userId, String showId, List<Seat> seats);
}
