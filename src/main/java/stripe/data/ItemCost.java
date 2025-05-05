package stripe.data;

import java.util.Arrays;
import java.util.List;

import lombok.Data;
import stripe.calculate.CostCalculationStrategyService;
import stripe.calculate.StoreCost;

@Data
public class ItemCost {
  public String product;
  public List<CostRange> costRanges;

  public ItemCost(String product, List<CostRange> costRanges) {
    this.product = product;
    this.costRanges = costRanges;
  }

  public int calculatePrice(int quantity) throws Exception {
    int res = 0;

//    AtomicInteger quantityForCal = new AtomicInteger(quantity);

    for (CostRange costRange1 : costRanges) {
      if (quantity <= 0)
        break;

      CostCalculationStrategyService costCalculationStrategyService = Arrays.stream(PriceType.values())
          .filter(priceType1 -> priceType1.equals(costRange1.getPriceType()))
          .findFirst()
          .get()
          .getCostCalculationStrategyService();

//      costCalculationStrategyService.setCostRange(costRange1);

      StoreCost cost = new StoreCost();
      cost.setQuantity(quantity);
      res += costCalculationStrategyService.calculateCost(costRange1, cost);
      quantity=cost.getQuantity();
    }

    return res;
  }
}
