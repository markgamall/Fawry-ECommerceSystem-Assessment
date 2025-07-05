import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Customer customer = new Customer("Mark", 1000);

        Product cheese = new Cheese("Cheese", 100, 5, LocalDate.now().plusDays(5), 0.2);
        Product biscuits = new Biscuits("Biscuits", 150, 2, LocalDate.now().plusDays(3), 0.35);
        Product tv = new TV("TV", 500, 3, 5.0);
        Product scratchCard = new MobileScratchCard("Mobile Scratch Card", 50, 10);

        Cart cart = new Cart();
        cart.add(cheese, 2);
        cart.add(biscuits, 1);
        cart.add(tv, 1);
        cart.add(scratchCard, 1);

        ECommerceSystem.checkout(customer, cart);
    }
}
