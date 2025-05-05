package stripe.calculate;

import stripe.data.CostRange;

public interface CostCalculationStrategyService {
  int calculateCost(CostRange costRange, StoreCost quantity) throws Exception;

}
