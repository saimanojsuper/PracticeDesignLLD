package org.practice.movieticketbookingsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Seat {
  private Integer rowNo;
  private Integer colNo;
  private Integer price;

}
