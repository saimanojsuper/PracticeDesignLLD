package org.practice.movieticketbookingsystem.model;

import java.time.LocalDateTime;
import java.util.List;

import org.practice.movieticketbookingsystem.enums.BookingStatus;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class Booking {
  private String bookingId;
  private Show show;
  private List<Seat> seat;
  private LocalDateTime startTime;
  private BookingStatus bookingStatus;

  public Integer bookingPrice() {
    return seat.stream().map(Seat::getPrice).reduce(0, Integer::sum);
  }

}
