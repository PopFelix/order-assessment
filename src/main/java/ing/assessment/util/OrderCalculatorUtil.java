package ing.assessment.util;

import ing.assessment.db.order.Order;
import lombok.experimental.UtilityClass;

@UtilityClass
public class OrderCalculatorUtil {

    public static final double TEN_PERCENT_DISCOUNT = 0.9;

    public static void applyDiscount(Order order) {
        if (order.getOrderCost() > 500) {
            order.setDeliveryCost(0);
            if (order.getOrderCost() > 1000) {
                order.setOrderCost(order.getOrderCost() * TEN_PERCENT_DISCOUNT);
            }
        }
    }
}
