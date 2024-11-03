package org.practice.movieticketbookingsystem.service;

import org.practice.movieticketbookingsystem.enums.BookingStatus;
import org.practice.movieticketbookingsystem.model.Booking;
import org.practice.movieticketbookingsystem.model.User;

public class PaymentService {

  public void deductPayment(User user, Booking booking) {

    Integer totalSeatsPrice = booking.bookingPrice();

    if (totalSeatsPrice < user.getWalletAmount()) {
      user.setWalletAmount(user.getWalletAmount() - totalSeatsPrice);
      booking.setBookingStatus(BookingStatus.SUCCESS);
    } else {
      booking.setBookingStatus(BookingStatus.FAILURE);
//      throw new RuntimeException("User doesn't have sufficient balance");
    }

  }

}
