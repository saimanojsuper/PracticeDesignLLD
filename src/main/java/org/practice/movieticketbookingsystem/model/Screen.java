package org.practice.movieticketbookingsystem.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Screen {
  private String screenId;
  private String screenName;
  private Theatre theatre;
  List<Show> shows;
  List<Seat> seats;
}
