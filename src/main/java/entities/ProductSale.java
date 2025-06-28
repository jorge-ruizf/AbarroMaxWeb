package entities;

public class ProductSale {

    private int productId;
    private int offer;
    private int stock;

    public ProductSale(int productId, int offer, int stock) {
        this.productId = productId;
        this.offer = offer;
        this.stock = stock;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getOffer() {
        return offer;
    }

    public void setOffer(int offer) {
        this.offer = offer;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    // Cálculo del costo total del producto con descuento aplicado
    public float calculateProductCost(float unitPrice, float discountPercent) {
        float discountAmount = (unitPrice * discountPercent) / 100;
        float finalUnitPrice = unitPrice - discountAmount;
        return finalUnitPrice * stock;
    }

}
