import java.time.LocalDate;

public class Biscuits extends ExpirableProduct implements Shippable {
    private double weight;

    public Biscuits(String name, double price, int quantity, LocalDate expiryDate, double weight) {
        super(name, price, quantity, expiryDate);
        this.weight = weight;
    }

    @Override
    public double getWeight() { return weight; }
}
