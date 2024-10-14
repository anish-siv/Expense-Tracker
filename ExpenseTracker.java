import java.util.*;
import java.sql.*;
import java.text.SimpleDateFormat;

public class ExpenseTracker {
    private List<Expense> expenses;

    public ExpenseTracker() {
        expenses = new ArrayList<>();
    }

    // Method to add expenses
    public void addExpense(String category, double amount, Date date, String description) {
        expenses.add(new Expense(category, amount, date, description));
    }

    // Method to view expenses
    public void viewExpenses() {
        System.out.println("Expense List:");
        for (Expense expense : expenses) {
            System.out.println("Category: " + expense.getCategory() + ", Amount: $" + expense.getAmount());
        }
    }

    // Exit method
    public void exit() {
        System.out.println("Goodbye!");
        System.exit(0);
    }

    public static void main(String[] args) {

        String CATEGORY;
        double AMOUNT;
        Date DATE;
        String DESCRIPTION;
       

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:8889/expenseTracker","MYSQL USERNAME","MYSQL PASSWORD");
            System.out.println("Connected to the database successfully.");
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO expenses (category, amount, date, description) VALUES (?, ?, ?, ?)");

            Scanner scanner = new Scanner(System.in);
            ExpenseTracker expenseTracker = new ExpenseTracker();
            SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD");

            while (true) {
                System.out.println("--------------------");
                System.out.println("Expense Tracker    |");
                System.out.println("1. Add Expense     |");
                System.out.println("2. View Expenses   |");
                System.out.println("3. Exit            |");
                System.out.println("--------------------");
                //System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print("Enter category: ");
                        CATEGORY = scanner.next();
                        System.out.print("Enter amount: ");
                        AMOUNT = scanner.nextDouble();
                        System.out.print("Enter date (YYYY-MM-DD): ");
                        DATE = dateFormat.parse(scanner.next());
                        scanner.nextLine();
                        System.out.print("Enter description: ");
                        DESCRIPTION = scanner.nextLine();
                        
                        expenseTracker.addExpense(CATEGORY, AMOUNT, DATE, DESCRIPTION);

                        // Setting values for the PreparedStatement
                        preparedStatement.setString(1, CATEGORY);
                        preparedStatement.setDouble(2, AMOUNT);
                        preparedStatement.setDate(3, new java.sql.Date(DATE.getTime()));
                        preparedStatement.setString(4, DESCRIPTION);

                        // Executing the insert statement
                        int rowsAffected = preparedStatement.executeUpdate();
                        if (rowsAffected > 0) {
                            System.out.println("Expense added successfully.");
                        } else {
                            System.out.println("Failed to add expense.");
                        }
                        break;
                    case 2:
                        //expenseTracker.viewExpenses();
                        expenseTracker.exit();
                        break;
                    case 3:
                        System.out.println("Exiting...");
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice");
                }
            }
        }
        catch(Exception e) {
            System.out.println("Error while connecting to the database: " + e.getMessage());
        }
    }
}
