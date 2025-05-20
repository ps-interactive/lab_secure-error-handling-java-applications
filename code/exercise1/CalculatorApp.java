public class CalculatorApp {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        try {
            calculator.start();
        } finally {
            calculator.close();
        }
    }
} 