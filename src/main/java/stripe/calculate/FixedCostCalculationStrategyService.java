package stripe.calculate;

import lombok.RequiredArgsConstructor;
import stripe.data.CostRange;

@RequiredArgsConstructor
public class FixedCostCalculationStrategyService extends CostCalculationStrategyServiceAbstract {
  @Override
  public int calculatePrice(CostRange costRange, int quantity) {
    return costRange.getCost();
  }
}
