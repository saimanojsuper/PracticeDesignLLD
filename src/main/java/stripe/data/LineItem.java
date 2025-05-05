package stripe.data;

import lombok.Data;

@Data
public class LineItem {
  public String product;
  public int quantity;

  public LineItem(String product, int quantity) {
    this.product = product;
    this.quantity = quantity;
  }
}
