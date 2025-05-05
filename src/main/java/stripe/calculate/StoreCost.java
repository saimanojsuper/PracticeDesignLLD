package stripe.calculate;

import java.util.concurrent.atomic.AtomicInteger;

import lombok.Data;

@Data
public class StoreCost {
  private int quantity;
  private int result;
}
