import java.util.ArrayList;
import java.util.List;

public class ECommerceSystem {

    public static void checkout(Customer customer, Cart cart) {
        if (cart.isEmpty()) {
            throw new IllegalArgumentException("Cart is empty");
        }

        double subtotal = 0;
        List<CartItem> shippableItems = new ArrayList<>();

        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();

            if (product.isExpirable() && product.isExpired()) {
                throw new IllegalArgumentException(product.getName() + " is expired");
            }
            if (item.getQuantity() > product.getQuantity()) {
                throw new IllegalArgumentException("Not enough stock for " + product.getName());
            }

            subtotal += product.getPrice() * item.getQuantity();

            if (product instanceof Shippable) {
                shippableItems.add(item);
            }
        }

        double totalWeight = 0;
        for (CartItem item : shippableItems) {
            Product product = item.getProduct();
            totalWeight += ((Shippable) product).getWeight() * item.getQuantity();
        }

        double shippingFee = totalWeight * 10;
        double totalAmount = subtotal + shippingFee;

        if (totalAmount > customer.getBalance()) {
            throw new IllegalArgumentException("Not enough balance");
        }

        for (CartItem item : cart.getItems()) {
            item.getProduct().reduceQuantity(item.getQuantity());
        }

        customer.deductBalance(totalAmount);
        ShippingService.ship(shippableItems);

        //print
        System.out.println("\n** Checkout receipt **");
        for (CartItem item : cart.getItems()) {
            double itemTotal = item.getProduct().getPrice() * item.getQuantity();
            System.out.println(item.getQuantity() + "x " + item.getProduct().getName() + " " + (int)itemTotal);
        }
        System.out.println("----------------------");
        System.out.println("Subtotal " + (int)subtotal);
        System.out.println("Shipping " + (int)shippingFee);
        System.out.println("Amount " + (int)totalAmount);
    }
}
