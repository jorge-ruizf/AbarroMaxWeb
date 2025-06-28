package entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;

public class InventoryMovementHistory {

    private ArrayList<InventoryMovement> inventoryMovementHistory;

    public InventoryMovementHistory() {
        this.inventoryMovementHistory = new ArrayList<>();
    }

    public InventoryMovementHistory(ArrayList<InventoryMovement> inventoryMovementHistory) {
        this.inventoryMovementHistory = inventoryMovementHistory;
    }

    public ArrayList<InventoryMovement> getInventoryMovementHistory() {
        return inventoryMovementHistory;
    }

    public void setInventoryMovementHistory(ArrayList<InventoryMovement> inventoryMovementHistory) {
        this.inventoryMovementHistory = inventoryMovementHistory;
    }

    // Agregar un nuevo movimiento
    public void addInventoryMovement(InventoryMovement movement) {
        this.inventoryMovementHistory.add(movement);
    }

    // Generar un reporte de todos los movimientos
    public String report(Date since, Date until) {
        String text = new String();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        text = text.concat("----- Inventory Movement Reports -----\n");
        int i = 0, nMovements = 0;
        for (InventoryMovement movement : inventoryMovementHistory) {
            if (movement.getDate().after(since) && movement.getDate().before(until)) {
                text = text.concat("ID: " + i + "\n");
                text = text.concat(movement.printStatus());
                text = text.concat("-------------------------------------------\n");
                nMovements++;
            }
            i++;
        }
        if (nMovements == 0) {
            text = text.concat("No movements register");
        }

        return text;
    }

    public String report() {
        String text = new String();

        text = text.concat("----- Inventory Movement Reports -----\n");
        int i = 0;
        for (InventoryMovement movement : inventoryMovementHistory) {
            text = text.concat("ID: " + i + "\n");
            text = text.concat(movement.printStatus());
            text = text.concat("-------------------------------------------\n");
            i++;
        }
        if (i == 0) {
            text = text.concat("No movements register");
        }

        return text;
    }
}
