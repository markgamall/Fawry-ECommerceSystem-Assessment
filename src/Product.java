public abstract class Product {
    private String name;
    private double price;
    private int quantity;

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName(){
        return name;
    }
    public double getPrice(){
        return price;
    }
    public int getQuantity() {
        return quantity;
    }

    public void reduceQuantity(int amount) {
        if (amount > quantity) {
            throw new IllegalArgumentException("Not enough amount for " + name);
        }
        quantity -= amount;
    }

    public boolean isExpirable() { return false; }
    public boolean isShippable() { return false; }
    public boolean isExpired() { return false; }
    public double getWeight() { return 0; }
}
