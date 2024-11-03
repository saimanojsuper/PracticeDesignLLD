package org.practice.movieticketbookingsystem.factory;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.practice.movieticketbookingsystem.model.Screen;
import org.practice.movieticketbookingsystem.model.Seat;
import org.practice.movieticketbookingsystem.model.Show;
import org.practice.movieticketbookingsystem.model.Theatre;

public class TheatreFactory {
  public static Theatre getTheatre(TheatreInfo theatreInfo) {

    Theatre theatre = new Theatre();
    List<Screen> screens = theatreInfo.getScreenNames().stream().map(screenName -> {

      List<Show> shows = theatreInfo.getShows() != null ? theatreInfo.getShows()
          .stream()
          .filter(show -> show.getScreen().getScreenName().equals(screenName))
          .collect(Collectors.toList()) : null;
      List<Seat> seats = new ArrayList<>();

      for (int i = 1; i < theatreInfo.getRowCount(); i++) {
        for (int j = 1; j < theatreInfo.getColCount(); j++) {
          seats.add(new Seat(i, j, theatreInfo.getSeatPrice()));
        }
      }

      UUID uuid = UUID.randomUUID();
      String uuidAsString = uuid.toString();

      return new Screen(uuidAsString, screenName, theatre, shows, seats);
    }).collect(Collectors.toList());

    theatre.setScreens(screens);

    UUID uuid = UUID.randomUUID();
    String theatreId = uuid.toString();
    theatre.setTheatreId(theatreId);
    theatre.setTheatreName(theatreInfo.getTheatreName());

    return theatre;
  }
}
