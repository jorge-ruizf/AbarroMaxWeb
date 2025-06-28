package entities;


import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

public class Sale implements Transaction {
    private ArrayList<ProductSale> productSales;
    private int saleId;
    private String employeeName;
    private int discount;
    private Date date;

    public Sale() {
        this.productSales = new ArrayList<ProductSale>();
        this.saleId = 0;
        this.employeeName = "";
        this.discount = 0;
        this.date = new Date();
    }

    public Sale(ArrayList<ProductSale> productSales, int saleId, String employeeName, int discount, Date date) {
        this.productSales = productSales;
        this.saleId = saleId;
        this.employeeName = employeeName;
        this.discount = discount;
        this.date = date;
    }
    
    public Sale(ArrayList<ProductSale> productSales, int saleId, String employeeName, int discount) {
        this.productSales = productSales;
        this.saleId = saleId;
        this.employeeName = employeeName;
        this.discount = discount;
        this.date = new Date();
    }

    public ArrayList<ProductSale> getProductSales() {
        return productSales;
    }

    public void setProductSales(ArrayList<ProductSale> productSales) {
        this.productSales = productSales;
    }

    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    public int getDiscount() {
        return discount;
    }
    
    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public void addProduct(ProductSale ps) {
        productSales.add(ps);
    }
    
    public void removeProductById(int id) {
        for (ProductSale ps : productSales) {
            if (ps.getProductId() == id) {
                productSales.remove(ps);
                break;
            }
        }
    }

    public float calculateSubtotalCost() {
        float subtotal = 0;
        for (ProductSale ps : productSales) {
            float dummyPrice = 1000f;
            float dummyDiscount = 0f;

            subtotal += ps.calculateProductCost(dummyPrice, dummyDiscount);
        }
        return subtotal;
    }

    public float calculateTotalCost() {
        float subtotal = calculateSubtotalCost();
        float discountAmount = subtotal * (discount / 100f);
        return subtotal - discountAmount;
    }
    
    public String getProductSalesAsString() {
        String text = "";
        for (ProductSale ps : productSales) {
            text = text.concat("Product ID: " + ps.getProductId() + ", Offer: " + ps.getOffer() + ", Stock: " + ps.getStock() + "\n");
        }
        return text;
    }

    public String printReceipt(ArrayList<Product> products, Prices prices) {
        float total = 0;
        float discount = 0;
        String receipt = "=== Receipt ===\n";

        for (ProductSale ps : this.getProductSales()) {
            Product product = products.get(ps.getProductId());  // Obtener el producto
            int quantitySold = ps.getStock();

            float priceTemp = 0;

            HashMap<Integer, Price> productPrices = prices.getPrices().get(ps.getProductId());

            if (productPrices != null) {
                for (Map.Entry<Integer, Price> priceEntry : productPrices.entrySet()) {
                    int quantityPrice = priceEntry.getKey();
                    if (quantitySold <= quantityPrice) {
                        Price priceObj = priceEntry.getValue();
                        priceTemp = priceObj.getPrice();
                    }
                }
            }

            float price = priceTemp;
            float subTotalPrice = price * quantitySold;
            float totalPrice = subTotalPrice - subTotalPrice * ps.getOffer() / 100;
            float discountAmount = subTotalPrice * ps.getOffer() / 100;

            // Mostrar información del producto
            receipt = receipt.concat("Product: " + product.getName() + " | Quantity: " + quantitySold + " | Price: $" + price + " | Subtotal: $" + subTotalPrice + " | Total: $" + totalPrice + "\n");

            total += totalPrice;
            discount += discountAmount;
        }

        receipt = receipt.concat("\nSubtotal: $" + Float.toString(total + discount) + "\nDiscount: -$" + discount + "\nTotal: $" + total + "\n\nThank you for your purchase!");


        return receipt;
    }
    
    
    
    @Override
    public double calculateAmount() {
        return (double) calculateTotalCost();
    }
    
}

