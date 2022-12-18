package models;

public class Items {
    private final String description;
    private final String price;

    public Items(String description, String price) {
        this.description = description;
        this.price = price;
    }

    public Items() {
        this.description = "description";
        this.price = "price";
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
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
