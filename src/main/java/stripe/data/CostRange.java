package stripe.data;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CostRange {
  public Integer max;
  public Integer min;
  public Integer cost;
  public PriceType priceType;

  public CostRange(Integer min, Integer max, Integer cost) {
    this.min = min;
    this.max = max;
    this.cost = cost;
    this.priceType = PriceType.INCREMENTAL;
  }
}
