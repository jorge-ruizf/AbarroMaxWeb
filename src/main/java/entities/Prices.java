package entities;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Prices {

    private HashMap<Integer, HashMap<Integer, Price>> prices;

    public Prices(HashMap<Integer, HashMap<Integer, Price>> prices) {
        this.prices = prices;
    }

    public Prices() {
        this.prices = new HashMap<>();
    }

    public HashMap<Integer, HashMap<Integer, Price>> getPrices() {
        return prices;
    }

    public void setPrices(HashMap<Integer, HashMap<Integer, Price>> prices) {
        this.prices = prices;
    }

    // Agregar un precio a un producto
    public void addPrice(int productId, int minQuantity, Price price) {
        if (!prices.containsKey(productId)) {
            prices.put(productId, new HashMap<>());
        }
        prices.get(productId).put(minQuantity, price);
    }
    
    public float getPriceForQuantityOne(int productId) {
        if (prices.containsKey(productId)) {
            HashMap<Integer, Price> priceTiers = prices.get(productId);
            if (priceTiers.containsKey(1)) {
                return priceTiers.get(1).getPrice(); // suponiendo que Price tiene getValue()
            }
        }
        return 0.0f; // o puedes lanzar una excepción o retornar null si prefieres
    }


}
