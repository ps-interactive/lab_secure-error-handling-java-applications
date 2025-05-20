import java.util.Scanner;
import java.util.logging.Logger;
import java.util.logging.Level;

public class Calculator {
    private static final Logger LOGGER = Logger.getLogger(Calculator.class.getName());
    private Scanner scanner;

    public Calculator() {
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Calculator Application");
        System.out.println("Enter 'exit' to quit");
        
        while (true) {
            try {
                System.out.print("Enter calculation (e.g., 5 + 3): ");
                String input = scanner.nextLine().trim();
                
                if (input.equalsIgnoreCase("exit")) {
                    break;
                }

                if (input.isEmpty()) {
                    // User-friendly message
                    System.out.println("Please enter a calculation");
                    // Developer logging
                    LOGGER.log(Level.WARNING, "Empty input received");
                    continue;
                }

                String[] parts = input.split("\\s+");
                if (parts.length != 3) {
                    // User-friendly message
                    System.out.println("Please enter a calculation in the format: number operator number");
                    System.out.println("Example: 5 + 3");
                    // Developer logging
                    LOGGER.log(Level.WARNING, "Invalid input format: " + input);
                    continue;
                }

                double num1 = Double.parseDouble(parts[0]);
                String operator = parts[1];
                double num2 = Double.parseDouble(parts[2]);

                double result = calculate(num1, operator, num2);
                System.out.println("Result: " + result);

            } catch (NumberFormatException e) {
                // User-friendly message
                System.out.println("Please enter valid numbers");
                // Developer logging
                LOGGER.log(Level.SEVERE, "Failed to parse number: " + e.getMessage(), e);
            } catch (IllegalArgumentException e) {
                // User-friendly message
                System.out.println("Invalid operator. Please use +, -, *, or /");
                // Developer logging
                LOGGER.log(Level.WARNING, "Invalid operator: " + e.getMessage(), e);
            } catch (ArithmeticException e) {
                // User-friendly message
                System.out.println("Cannot divide by zero");
                // Developer logging
                LOGGER.log(Level.SEVERE, "Division by zero attempted", e);
            } catch (Exception e) {
                // User-friendly message
                System.out.println("An error occurred. Please try again.");
                // Developer logging
                LOGGER.log(Level.SEVERE, "Unexpected error: " + e.getClass().getName(), e);
            }
        }
    }

    private double calculate(double num1, String operator, double num2) {
        switch (operator) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                if (num2 == 0) {
                    // User-friendly message is handled in the catch block
                    throw new ArithmeticException("Division by zero");
                }
                return num1 / num2;
            default:
                // User-friendly message is handled in the catch block
                throw new IllegalArgumentException("Unsupported operator: " + operator);
        }
    }

    public void close() {
        scanner.close();
    }
} 