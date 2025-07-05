import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class ShippingService {

    public static void ship(List<CartItem> items) {
        if (items.isEmpty()) return;

        System.out.println("** Shipment notice **");

        Map<String, Double> productWeightMap = new HashMap<>();
        Map<String, Integer> productCountMap = new HashMap<>();
        double totalWeight = 0;

        for (CartItem item : items) {
            Product product = item.getProduct();
            if (product instanceof Shippable) {
                Shippable shippable = (Shippable) product;
                String name = shippable.getName();
                double weightPerItem = shippable.getWeight();
                int quantity = item.getQuantity();

                productCountMap.put(name, productCountMap.getOrDefault(name, 0) + quantity);
                productWeightMap.put(name, weightPerItem);
                totalWeight += weightPerItem * quantity;
            }
        }

        for (String name : productCountMap.keySet()) {
            double weightInGrams = productWeightMap.get(name) * 1000;
            System.out.println(productCountMap.get(name) + "x " + name + " " + (int)weightInGrams + "g");
        }

        System.out.println("Total package weight " + String.format("%.1f", totalWeight) + "kg");
    }
}
