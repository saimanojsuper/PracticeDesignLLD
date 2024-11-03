package org.practice.movieticketbookingsystem.service;

import org.practice.movieticketbookingsystem.datastore.InMemoryData;
import org.practice.movieticketbookingsystem.factory.TheatreFactory;
import org.practice.movieticketbookingsystem.factory.TheatreInfo;
import org.practice.movieticketbookingsystem.model.Theatre;

public class TheatreService {
  public void createTheatre(TheatreInfo theatreInfo){
    Theatre theatre =  TheatreFactory.getTheatre(theatreInfo);
    InMemoryData inMemoryData = InMemoryData.getInstance();
    inMemoryData.addTheatre(theatre);
  }
}
