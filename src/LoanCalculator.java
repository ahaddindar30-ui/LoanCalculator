import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * LoanCalculator class to compute loan payments.
 * This program calculates the monthly payment, total payment, and total interest paid
 * based on the loan amount, annual interest rate, and repayment period.
 */
public class LoanCalculator {

    // Constant for number of months in a year
    private static final int MONTHS_IN_YEAR = 12;

    /**
     * Main method to run the application.
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        try {
            double loanAmount = getLoanAmount(input); // Retrieve loan amount
            double annualInterestRate = getAnnualInterestRate(input); // Retrieve interest rate
            int repaymentPeriod = getRepaymentPeriod(input); // Retrieve repayment period

            calculateAndDisplayLoanDetails(loanAmount, annualInterestRate, repaymentPeriod); // Calculate and display results
        } catch (IllegalArgumentException | InputMismatchException e) {
            System.out.println("Error: " + e.getMessage()); // Handle input errors
        } finally {
            input.close(); // Close the scanner
        }
    }

    /**
     * Gets the loan amount from user input.
     *
     * @param input Scanner object to read input
     * @return the amount of the loan
     */
    private static double getLoanAmount(Scanner input) {
        System.out.print("Enter the loan amount: ");
        double amount = input.nextDouble();
        if (amount <= 0) {
            throw new IllegalArgumentException("Loan amount must be greater than 0.");
        }
        return amount;
    }

    /**
     * Gets the annual interest rate from user input.
     *
     * @param input Scanner object to read input
     * @return the annual interest rate in decimal
     */
    private static double getAnnualInterestRate(Scanner input) {
        System.out.print("Enter the annual interest rate (in percentage): ");
        double rate = input.nextDouble();
        if (rate < 0) {
            throw new IllegalArgumentException("Interest rate cannot be negative.");
        }
        return rate / 100;  // Convert percentage to decimal
    }

    /**
     * Gets the repayment period from user input.
     *
     * @param input Scanner object to read input
     * @return the repayment period in years
     */
    private static int getRepaymentPeriod(Scanner input) {
        System.out.print("Enter the repayment period (in years): ");
        int years = input.nextInt();
        if (years <= 0) {
            throw new IllegalArgumentException("Repayment period must be greater than 0.");
        }
        return years;
    }

    /**
     * Calculates and displays loan payment details based on input values.
     *
     * @param loanAmount          the amount of the loan
     * @param annualInterestRate  the annual interest rate in decimal
     * @param repaymentPeriod     the repayment period in years
     */
    private static void calculateAndDisplayLoanDetails(double loanAmount, double annualInterestRate, int repaymentPeriod) {
        int totalPayments = repaymentPeriod * MONTHS_IN_YEAR;
        double monthlyInterestRate = annualInterestRate / MONTHS_IN_YEAR;
        double monthlyPayment = (loanAmount * monthlyInterestRate) /
                (1 - Math.pow(1 + monthlyInterestRate, -totalPayments));

        DecimalFormat df = new DecimalFormat("0.00");
        System.out.println("Monthly Payment: " + df.format(monthlyPayment));
        System.out.println("Total Payment: " + df.format(monthlyPayment * totalPayments));
        System.out.println("Total Interest Paid: " + df.format((monthlyPayment * totalPayments) - loanAmount));
    }
}
