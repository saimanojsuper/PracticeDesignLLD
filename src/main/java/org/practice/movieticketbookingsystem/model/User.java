package org.practice.movieticketbookingsystem.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
  private String userId;
  private String name;
  private String mobileNumber;
  private String emailId;
  private Integer walletAmount;
}
