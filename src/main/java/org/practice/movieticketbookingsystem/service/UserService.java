package org.practice.movieticketbookingsystem.service;

import java.util.Optional;

import org.practice.movieticketbookingsystem.datastore.InMemoryData;
import org.practice.movieticketbookingsystem.model.User;

public class UserService {
  public User getUserById(String userId) {
    Optional<User> userData = InMemoryData.getInstance()
        .getUsers()
        .stream()
        .filter(user -> user.getUserId().equals(userId))
        .findFirst();

    if (userData.isPresent()) {
      return userData.get();
    } else {
      throw new RuntimeException("Not Valid User");
    }
  }
}
