package org.practice.movieticketbookingsystem.datastore;

import java.util.ArrayList;
import java.util.List;

import org.practice.movieticketbookingsystem.model.Booking;
import org.practice.movieticketbookingsystem.model.Theatre;
import org.practice.movieticketbookingsystem.model.User;

import lombok.Data;

@Data
public class InMemoryData {
  private static InMemoryData instance;

  private InMemoryData() {
    System.out.println("Singleton is Instantiated.");
  }

  public static InMemoryData getInstance() {
    if (instance == null)
      instance = new InMemoryData();
    return instance;
  }

  List<Theatre> theatres = new ArrayList<>();
  List<Booking> bookings = new ArrayList<>();
  List<User> users = new ArrayList<>();

  public void addTheatre(Theatre theatre) {
    this.theatres.add(theatre);
  }

  public void addBooking(Booking booking) {
    this.bookings.add(booking);
  }

  public void addUsers(User user){
    this.users.add(user);
  }

}
