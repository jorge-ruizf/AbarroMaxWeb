package entities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Sales {

    private ArrayList<Sale> sales;

    public Sales() {
        this.sales = new ArrayList<>();
    }

    public Sales(ArrayList<Sale> sales) {
        this.sales = sales;
    }

    public ArrayList<Sale> getSales() {
        return sales;
    }

    public void setSales(ArrayList<Sale> sales) {
        this.sales = sales;
    }

    public void addSale(Sale sale) {
        this.sales.add(sale);
    }

    // Imprime el recibo de la venta más reciente
    public void printLastesReceipt() {
        if (!sales.isEmpty()) {
            Sale lastSale = sales.get(sales.size() - 1);
            //lastSale.printReceipt();
        } else {
            System.out.println("No hay ventas registradas.");
        }
    }

    // Imprime el recibo de la venta en el índice indicado
    public void printReceipt(int index) {
        if (index >= 0 && index < sales.size()) {
            Sale sale = sales.get(index);
            //sale.printReceipt();
        } else {
            System.out.println("Índice de venta no válido.");
        }
    }

    public float getEarningsForNumberOfDays(int days) {
        float total = 0f;

        if (sales == null) {
            return total;
        }

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, -days);
        Date limitDate = cal.getTime();

        for (Sale sale : sales) {
            if (!sale.getDate().before(limitDate)) {
                total += sale.calculateTotalCost();
            }
        }

        return total;
    }

    public int getSalesCountForNumberOfDays(int days) {
        int count = 0;

        if (sales == null) {
            return count;
        }

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, -days);
        Date limitDate = cal.getTime();

        for (Sale sale : sales) {
            if (!sale.getDate().before(limitDate)) {
                count++;
            }
        }

        return count;
    }

    public String printReportForDayWeekMonth() {
        String text = "";
        float earningsForProductsDay = 0, earningsForProductsWeek = 0, earningsForProductsMonth = 0;
        
        if(getSalesCountForNumberOfDays(1) == 0){
            earningsForProductsDay = getEarningsForNumberOfDays(1) / 1;
        }
        if(getSalesCountForNumberOfDays(7) == 0){
            earningsForProductsWeek = getEarningsForNumberOfDays(7) / 7;
        }
        if(getSalesCountForNumberOfDays(30) == 0){
            earningsForProductsMonth = getEarningsForNumberOfDays(30) / 30;
        }
        float mediumProductEarnings = (earningsForProductsDay + earningsForProductsWeek + earningsForProductsMonth) / 3;

        float percentEarningsForProductsDay = earningsForProductsDay * 100 / mediumProductEarnings;
        float percentEarningsForProductsWeek = earningsForProductsWeek * 100 / mediumProductEarnings;
        float percentEarningsForProductsMonth = earningsForProductsMonth * 100 / mediumProductEarnings;

        text = text.concat("Earnings in this day: " + earningsForProductsDay + "\n");
        text = text.concat("Earnings in this week: " + earningsForProductsWeek + "\n");
        text = text.concat("Earnings in this month: " + earningsForProductsMonth + "\n");
        text = text.concat("Medium earnings: " + mediumProductEarnings + "\n\n");
        text = text.concat("=========== Percent earnings =========\n");
        text = text.concat("Day: " + String.format("%.0f", percentEarningsForProductsDay) + "%\n");
        text = text.concat("Week: " + String.format("%.0f", percentEarningsForProductsWeek) + "%\n");
        text = text.concat("Month: " + String.format("%.0f", percentEarningsForProductsMonth) + "%\n");

        return text;
    }

    public Sale removeSaleById(int index) {
        return sales.remove(index);
    }
}
