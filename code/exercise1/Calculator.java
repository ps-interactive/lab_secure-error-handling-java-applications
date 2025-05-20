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
                    // Developer message
                    throw new IllegalArgumentException("Input string is empty");
                }

                String[] parts = input.split("\\s+");
                if (parts.length != 3) {
                    // Developer message
                    throw new IllegalArgumentException("Invalid input format. Expected: number operator number");
                }

                double num1 = Double.parseDouble(parts[0]);
                String operator = parts[1];
                double num2 = Double.parseDouble(parts[2]);

                double result = calculate(num1, operator, num2);
                System.out.println("Result: " + result);

            } catch (NumberFormatException e) {
                // Developer message
                System.out.println("NumberFormatException: " + e.getMessage());
                LOGGER.log(Level.SEVERE, "Failed to parse number", e);
            } catch (IllegalArgumentException e) {
                // Developer message
                System.out.println("IllegalArgumentException: " + e.getMessage());
                LOGGER.log(Level.WARNING, "Invalid input", e);
            } catch (ArithmeticException e) {
                // Developer message
                System.out.println("ArithmeticException: " + e.getMessage());
                LOGGER.log(Level.SEVERE, "Arithmetic error", e);
            } catch (Exception e) {
                // Developer message
                System.out.println("Unexpected error: " + e.getClass().getName() + ": " + e.getMessage());
                LOGGER.log(Level.SEVERE, "Unexpected error", e);
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
                    // Developer message
                    throw new ArithmeticException("Division by zero");
                }
                return num1 / num2;
            default:
                // Developer message
                throw new IllegalArgumentException("Unsupported operator: " + operator);
        }
    }

    public void close() {
        scanner.close();
    }
} 