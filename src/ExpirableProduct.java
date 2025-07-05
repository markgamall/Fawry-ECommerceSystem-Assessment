import java.time.LocalDate;

public abstract class ExpirableProduct extends Product {
    private LocalDate expiryDate;

    public ExpirableProduct(String name, double price, int quantity, LocalDate expiryDate) {
        super(name, price, quantity);
        this.expiryDate = expiryDate;
    }

    @Override
    public boolean isExpirable() {
        return true;
    }

    @Override
    public boolean isExpired() {
        if (LocalDate.now().isAfter(expiryDate)) {
            return true;
        } else {
            return false;
        }
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }
}
