package org.practice.movieticketbookingsystem.factory;

import java.util.List;

import org.practice.movieticketbookingsystem.model.Show;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TheatreInfo {
  String theatreName;
  List<String> screenNames;
  //Assuming all screens has same no. of seats, same seatPrice (Can modify it later)
  List<Show> shows;
  Integer rowCount;
  Integer colCount;
  Integer seatPrice;
}
