package entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class InventoryMovement {

    private HashMap<Integer, Integer> inventory;
    private Date date;

    public InventoryMovement(HashMap<Integer, Integer> inventory, Date date) {
        this.inventory = inventory;
        this.date = date;
    }

    // Constructor alternativo: si no se pasa fecha, se toma la fecha actual
    public InventoryMovement(HashMap<Integer, Integer> inventory) {
        this.inventory = inventory;
        this.date = new Date(); // Se registra la fecha actual automáticamente
    }

    public HashMap<Integer, Integer> getInventory() {
        return inventory;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setInventory(HashMap<Integer, Integer> inventory) {
        this.inventory = inventory;
    }

    // Método para mostrar el estado del movimiento
    public String printStatus() {
        String text = new String();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        text = text.concat("Movement Date: " + formatter.format(date) + "\n");
        text = text.concat("Movement Detail:\n");
        for (Map.Entry<Integer, Integer> entry : inventory.entrySet()) {
            text = text.concat("ID: " + entry.getKey() + " | Stock: " + entry.getValue() + "\n");
        }
        return text;
    }
}
