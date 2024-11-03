package org.practice.movieticketbookingsystem.model;

import java.util.List;

import lombok.Data;

@Data
public class Theatre {
  private String theatreId;
  private String theatreName;
  private List<Screen> screens;

}
