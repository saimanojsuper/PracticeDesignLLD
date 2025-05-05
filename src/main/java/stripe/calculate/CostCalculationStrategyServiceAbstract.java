package stripe.calculate;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import stripe.data.CostRange;

@RequiredArgsConstructor
@Setter
public abstract class CostCalculationStrategyServiceAbstract implements CostCalculationStrategyService {
  protected Integer noOfItems;

  public int calculateCost(CostRange costRange, StoreCost storeCost) throws Exception {
    if (costRange == null || storeCost == null)
       throw new Exception("costRange is null");
    int needToADD = (costRange.getMax() == null || costRange.getMin() == 0) ? 0 : 1;

    this.noOfItems = costRange.getMax() == null ?
        Integer.MAX_VALUE :
        (costRange.getMax() - costRange.getMin() + needToADD);

    int res = calculatePrice(costRange, storeCost.getQuantity());
    storeCost.setQuantity(storeCost.getQuantity() - noOfItems);
    return res;
  }

  abstract int calculatePrice(CostRange costRange, int quantity);
}
