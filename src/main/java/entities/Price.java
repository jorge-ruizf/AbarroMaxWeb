package entities;

public class Price {

    private int stock;
    private float price;

    public Price(int stock, float price) {
        this.stock = stock;
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
