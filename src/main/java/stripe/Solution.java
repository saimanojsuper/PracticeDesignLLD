package stripe;// Part 1

// Imagine an online hardware store that sells products of different sizes. The store could sell small items like a mouse or larger items like a laptop. These items cost a different amount to be shipped out. Shipping to different countries can also cost a different amount. Imagine you have an order object that looks like the below.

// Order:
// {
//     "country": "US", // or "CA" for the CA order
//     "items": [
//         {"product": "mouse", "quantity": 20},
//         {"product": "laptop", "quantity": 5}
//     ]
// }

// Note: In your solution you can pass in an in memory object that has the same shape as the json. You don't need to worry about parsing json for this problem.

// The US and CA orders have the same shape, they are just different countries.

// Each country/product has a corresponding shipping cost matrix. The cost is stored in the smallest currency unit.

// Shipping Cost:
// Each product has its own shipping cost
// {
//     "US": [
//         {"product": "mouse", "cost": 550},
//         {"product": "laptop", "cost": 1000}
//     ],
//     "CA": [
//         {"product": "mouse", "cost": 750},
//         {"product": "laptop", "cost": 1100}
//     ]
// }

// Write a function called calculate_shipping_cost that takes in an order and shipping cost matrix and returns the shipping cost.

// Examples:
// calculate_shipping_cost(order_us, shipping_cost) == 16000 // 20 * 550 + 5 * 1000 = 16000
// calculate_shipping_cost(order_ca, shipping_cost) == 20500 // 20 * 750 + 5 * 1100 = 20500

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import stripe.data.CostRange;
import stripe.data.ItemCost;
import stripe.data.LineItem;
import stripe.data.Order;
import stripe.data.PriceType;

//@Data
//class LineItem {
//  public String product;
//  public int quantity;
//
//  public LineItem(String product, int quantity) {
//    this.product = product;
//    this.quantity = quantity;
//  }
//}

//@Data
//class Order {
//  public String country;
//  public List<LineItem> items;
//
//  public Order(String country, List<LineItem> items) {
//    this.country = country;
//    this.items = items;
//  }
//}

//@Data
//class ItemCost {
//  public String product;
//  public List<CostRange> costRanges;
//
//  public ItemCost(String product, List<CostRange> costRanges) {
//    this.product = product;
//    this.costRanges = costRanges;
//  }
//
//  public int calculatePrice(int quantity) throws Exception {
//    int res = 0;
//
//    AtomicInteger quantityForCal = new AtomicInteger(quantity);
//
//    for (CostRange costRange1 : costRanges) {
//      if (quantityForCal.get() <= 0)
//        break;
//
//      CalculateCost calculateCost = Arrays.stream(PriceType.values())
//          .filter(priceType1 -> priceType1.equals(costRange1.getPriceType()))
//          .findFirst()
//          .get()
//          .getCalculateCost();
//
//      calculateCost.setCostRange(costRange1);
//
//      res += calculateCost.calculateCost(quantityForCal);
//
//    }
//
//    return res;
//  }
//}

//@Data
//@ToString
//class CostRange {
//  public Integer max;
//  public Integer min;
//  public Integer cost;
//  public PriceType priceType;
//
//  public CostRange(Integer min, Integer max, Integer cost) {
//    this.min = min;
//    this.max = max;
//    this.cost = cost;
//  }
//}

//interface CalculateCost {
//  int calculateCost(AtomicInteger quantity) throws Exception;
//
//  void setCostRange(CostRange costRange);
//
//}

//@RequiredArgsConstructor
//@Setter
//abstract class CalculateCostAbstract implements CalculateCost {
//  protected CostRange costRange;
//  protected Integer noOfItems;
//
//  public void setCostRange(CostRange costRange) {
//    this.costRange = costRange;
//  }
//
//  public int calculateCost(AtomicInteger quantity) throws Exception {
//    if (this.costRange == null)
//      throw new Exception("costRange is null");
//    int needToADD = (costRange.getMax() == null || costRange.getMin() == 0) ? 0 : 1;
//
//    this.noOfItems = costRange.getMax() == null ?
//        Integer.MAX_VALUE :
//        (costRange.getMax() - costRange.getMin() + needToADD);
//    int res = calculatePrice(quantity);
//    quantity.set(quantity.get() - noOfItems);
//    return res;
//  }
//
//  abstract int calculatePrice(AtomicInteger quantity);
//}
//
//class IncrementalCost extends CalculateCostAbstract {
//  public IncrementalCost() {
//  }
//
//  //  private final CostRange costRange;
//  @Override
//  public int calculatePrice(AtomicInteger quantity) {
//    int res = 0;
//
//    if (this.noOfItems > quantity.get()) {
//      System.out.println("qunatity : " + quantity + " " + this);
//
//      res += quantity.get() * costRange.getCost();
//    } else {
//      System.out.println("qunatity : " + noOfItems + " " + this);
//
//      res += noOfItems * costRange.getCost();
//    }
//    return res;
//  }
//}
//
//@RequiredArgsConstructor
//class FixedCost extends CalculateCostAbstract {
//  @Override
//  public int calculatePrice(AtomicInteger quantity) {
//    return costRange.getCost();
//  }
//}
//
//@RequiredArgsConstructor
//@Getter
//enum PriceType {
//  FIXED(new FixedCost()), INCREMENTAL(new IncrementalCost());
//  private final CalculateCost calculateCost;
//}

public class Solution {
  //    private static int calculateShippingCost(Order order, Map<String, List<ItemCost>> shippingCosts) {
  //
  //        String country = order.getCountry();
  //        Integer res = 0;
  //
  //        List<ItemCost> itemCosts = shippingCosts.getOrDefault(country, null);
  //
  //        if(itemCosts == null){
  //
  //        }
  //
  //        for(LineItem lineItem : order.getItems()){
  //
  //          Integer itemPrice =  itemCosts.stream().filter(itemCost -> itemCost.getProduct().equals(lineItem.getProduct()))
  //              .findFirst().get().getCostRanges();
  //
  //          res += itemPrice * lineItem.getQuantity();
  //
  //        }
  //
  //        return res;
  //    }

  private static int calculateShippingCost(Order order, Map<String,List<ItemCost>> shippingCosts) {

    String country = order.getCountry();
    Integer res = 0;

    List<ItemCost> itemCosts = shippingCosts.getOrDefault(country, null);

    if (itemCosts == null) {

    }

    for (LineItem lineItem : order.getItems()) {

      List<CostRange> costRanges = itemCosts.stream()
          .filter(itemCost -> itemCost.getProduct().equals(lineItem.getProduct()))
          .findFirst()
          .get()
          .getCostRanges();

      Integer quantity = lineItem.getQuantity();

      //      for(CostRange range : costRanges){
      //
      //      }
      int i = 0;
      CostRange costRange = costRanges.get(i);

      while (quantity > 0) {

        costRange = costRanges.get(i);
        PriceType priceType = costRange.getPriceType();

        int needToADD = (costRange.getMax() == null || costRange.getMin() == 0) ? 0 : 1;

        int noOfItems = costRange.getMax() == null ?
            Integer.MAX_VALUE :
            (costRange.getMax() - costRange.getMin() + needToADD);

        if (priceType.equals(PriceType.INCREMENTAL)) {
          if (noOfItems > quantity) {
            System.out.println("qunatity : " + quantity + " " + costRange);

            res += quantity * costRange.getCost();
          } else {
            System.out.println("qunatity : " + noOfItems + " " + costRange);

            res += noOfItems * costRange.getCost();
          }
        } else {

        }
        quantity -= noOfItems;
        i++;

      }

    }

    return res;
  }

  /**
   * CostRange costRangeLaptopUS = new CostRange(0,null, 500);
   *
   * @param args
   */

  public static void main(String[] args) {

    CostRange costRangeMouseUS = new CostRange(0, null, 550);
    List<CostRange> costRangeLaptopUS = List.of(new CostRange(0, 2, 1000), new CostRange(3, 4, 950),
        new CostRange(5, null, 900));
    CostRange costRangeMouseCA = new CostRange(0, null, 750);
    List<CostRange> costRangeLaptopCA = List.of(new CostRange(0, 2, 1100), new CostRange(3, null, 1000));

    var shippingCosts = new HashMap<String,List<ItemCost>>();
    shippingCosts.put("US",
        List.of(new ItemCost("mouse", List.of(costRangeMouseUS)), new ItemCost("laptop", costRangeLaptopUS)));
    shippingCosts.put("CA",
        List.of(new ItemCost("mouse", List.of(costRangeMouseCA)), new ItemCost("laptop", costRangeLaptopCA)));

    Order usOrder = new Order("US", List.of(new LineItem("mouse", 20), new LineItem("laptop", 5)));
    Order caOrder = new Order("CA", List.of(new LineItem("mouse", 20), new LineItem("laptop", 5)));

    System.out.println(calculateShippingCost(usOrder, shippingCosts));
    System.out.println(calculateShippingCost(caOrder, shippingCosts));

  }
}
