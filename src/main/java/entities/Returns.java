package entities;


import java.util.ArrayList;

public class Returns implements Transaction{
    private ArrayList<Sale> returns;

    public Returns() {
    }

    public Returns(ArrayList<Sale> returns) {
        this.returns = returns;
    }

    public ArrayList<Sale> getReturns() {
        return returns;
    }

    public void setReturns(ArrayList<Sale> returns) {
        this.returns = returns;
    }

    // Agrega una nueva devolución
    public void addReturn(Sale sale) {
        returns.add(sale);
        System.out.println("Devolución registrada correctamente.");
    }

    @Override
    public double calculateAmount() {
        double totalAmount = 0;
        if (returns != null) {
            for (Sale sale : returns) {
                totalAmount += sale.calculateTotalCost();
            }
        }
        return totalAmount;
    }
}
