//package ExpenseTracker;

import java.util.Date;

public class Expense {
    private String category;
    private double amount;
    private Date date;
    private String description;

    // Expense class parameterized constructor
    public Expense(String category, double amount, Date date, String description) {
        this.category = category;
        this.amount = amount;
        this.date = date;
        this.description = description;
    }

    // Category getter
    public String getCategory() {
        return category;
    }

    // Category setter
    public void setCategory(String category) {
        this.category = category;
    }

    // Amount getter
    public double getAmount() {
        return amount;
    }

    // Amount setter
    public void setAmount(double amount) {
        this.amount = amount;
    }

    // Date getter
    public Date getDate() {
        return date;
    }

    // Date setter
    public void setDate(Date date) {
        this.date = date;
    }

    // Description getter
    public String getDescription() {
        return description;
    }

    // Description setter
    public void setDescription(String description) {
        this.description = description;
    }
}
