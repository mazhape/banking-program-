import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        BigDecimal balance = BigDecimal.ZERO;
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("""
                    ******************
                    BANKING PROGRAM
                    ******************
                    1. Show Balance
                    2. Deposit
                    3. Withdraw
                    4. Exit
                    ******************
                    """);
            System.out.print("Enter your choice (1-4): ");

            if (!scanner.hasNextInt()) {  // Prevents infinite loop if invalid input is entered
                System.out.println("Invalid input. Please enter a number between 1-4.");
                scanner.next(); // Clear the invalid input
                continue;
            }

            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> showBalance(balance);
                case 2 -> balance = balance.add(deposit());
                case 3 -> balance = balance.subtract(withdraw(balance));
                case 4 -> isRunning = false;
                default -> System.out.println("Invalid choice. Please enter 1-4.");
            }
        }

        System.out.println("\n***************************");
        System.out.println("Thank you! Have a nice day!");
        System.out.println("***************************");
        scanner.close();
    }

    static void showBalance(BigDecimal balance) {
        System.out.printf("Current Balance: $%.2f\n", balance);
    }

    static BigDecimal deposit() {
        System.out.print("Enter amount to deposit: ");
        BigDecimal amount = getValidAmount();

        if (amount.compareTo(BigDecimal.ZERO) > 0) {
            System.out.println("Deposit successful!");
            return amount;
        } else {
            System.out.println("Deposit amount must be greater than zero.");
            return BigDecimal.ZERO;
        }
    }

    static BigDecimal withdraw(BigDecimal balance) {
        System.out.print("Enter amount to withdraw: ");
        BigDecimal amount = getValidAmount();

        if (amount.compareTo(balance) > 0) {
            System.out.println("INSUFFICIENT FUNDS. You cannot withdraw more than your balance.");
            return BigDecimal.ZERO;
        } else if (amount.compareTo(BigDecimal.ZERO) > 0) {
            System.out.println("Withdrawal successful!");
            return amount;
        } else {
            System.out.println("Withdrawal amount must be greater than zero.");
            return BigDecimal.ZERO;
        }
    }

    static BigDecimal getValidAmount() {
        while (!scanner.hasNextBigDecimal()) {
            System.out.print("Invalid input. Enter a valid amount: ");
            scanner.next();  // Discard invalid input
        }
        return scanner.nextBigDecimal();
    }
}
