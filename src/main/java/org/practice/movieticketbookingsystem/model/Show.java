package org.practice.movieticketbookingsystem.model;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Show {

  private String showId;
  private String movieName;
  private LocalDateTime startTime;
  private Integer duration;
  private Screen screen;

}
