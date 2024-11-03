package org.practice.movieticketbookingsystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.practice.movieticketbookingsystem.datastore.InMemoryData;
import org.practice.movieticketbookingsystem.model.Screen;
import org.practice.movieticketbookingsystem.model.Show;
import org.practice.movieticketbookingsystem.model.Theatre;

public class ShowService {

  public Show getShowById(String showId) {

    List<Theatre> theatres = InMemoryData.getInstance().getTheatres();
    List<Screen> screens = new ArrayList<>();

    theatres.forEach(theatre -> screens.addAll(theatre.getScreens()));

    List<Show> shows = new ArrayList<>();
    screens.forEach(screen -> shows.addAll(screen.getShows()));

    Optional<Show> currentShow = shows.stream().filter(show -> show.getShowId().equals(showId)).findFirst();

    if (currentShow.isPresent()) {
      return currentShow.get();
    } else {
      throw new RuntimeException("Invalid Show Id");
    }

  }
}
