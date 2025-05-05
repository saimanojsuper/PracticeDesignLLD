package stripe.data;

import java.util.List;

import lombok.Data;

@Data
public class Order {
  public String country;
  public List<LineItem> items;

  public Order(String country, List<LineItem> items) {
    this.country = country;
    this.items = items;
  }
}
