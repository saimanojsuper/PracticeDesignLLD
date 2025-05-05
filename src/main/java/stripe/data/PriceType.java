package stripe.data;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import stripe.calculate.CostCalculationStrategyService;
import stripe.calculate.FixedCostCalculationStrategyService;
import stripe.calculate.IncrementalCostCalculationStrategyService;

@RequiredArgsConstructor
@Getter
public enum PriceType {
  FIXED(new FixedCostCalculationStrategyService()), INCREMENTAL(new IncrementalCostCalculationStrategyService());
  private final CostCalculationStrategyService costCalculationStrategyService;
}
