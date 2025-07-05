import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> items = new ArrayList<>();

    public void add(Product product, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        if (product.getPrice() < 0) {
            throw new IllegalArgumentException("Product price must be positive");
        }
        if (product instanceof Shippable) {
            Shippable shippable = (Shippable) product;

            if (shippable.getWeight() < 0) {
                throw new IllegalArgumentException("Product weight must be positive");
            }
        }

        if (product instanceof ExpirableProduct) {
            ExpirableProduct exp = (ExpirableProduct) product;
            if (exp.isExpired()) {
                throw new IllegalArgumentException("Cannot add expired product: " + product.getName());
            }
        }
        if (quantity > product.getQuantity()) {
            throw new IllegalArgumentException("Not enough stock for " + product.getName());
        }

        for (CartItem item : items) {
            if (item.getProduct().equals(product)) {
                int newQuantity = item.getQuantity() + quantity;
                if (newQuantity > product.getQuantity()) {
                    throw new IllegalArgumentException("Not enough stock for " + product.getName());
                }
                items.remove(item);
                items.add(new CartItem(product, newQuantity));
                return;
            }
        }
        items.add(new CartItem(product, quantity));
    }

    public List<CartItem> getItems() {
         return items;
    }

    public boolean isEmpty() {
         return items.isEmpty();
    }
}
