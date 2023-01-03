package models;

public class Items {
    private final String description;
    private final double price;

    public Items(String description, double price) {
        this.description = description;
        this.price = price;
    }

    public Items() {
        this.description = "description";
        this.price = 0.00;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString(){
        return "Items{"
                + getDescription()
                + ", "
                + getPrice()
                + "}";
    }
}
