package stripe.calculate;

import stripe.data.CostRange;

public class IncrementalCostCalculationStrategyService extends CostCalculationStrategyServiceAbstract {
  public IncrementalCostCalculationStrategyService() {
  }

  //  private final CostRange costRange;
  @Override
  public int calculatePrice(CostRange costRange, int quantity) {
    int res = 0;

    if (this.noOfItems > quantity) {
      System.out.println("qunatity : " + quantity + " " + this);

      res += quantity * costRange.getCost();
    } else {
      System.out.println("qunatity : " + noOfItems + " " + this);

      res += noOfItems * costRange.getCost();
    }
    return res;
  }
}
