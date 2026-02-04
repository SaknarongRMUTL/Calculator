package MainProgram;
import java.util.Scanner;

public class ProjectCalculater {

    static abstract class CalculatorBase {
        private double x;
        private double y;

        CalculatorBase(double x, double y) { 
            this.x = x; 
            this.y = y; 
        }

        public double getX() { return x; }
        public void setX(double x) { this.x = x; }
        
        public double getY() { return y; }
        public void setY(double y) { this.y = y; }

        abstract double calculate();
    }

    static class Addition extends CalculatorBase {
        Addition(double x, double y) { super(x, y); }
        double calculate() { return getX() + getY(); }
    }
    static class Subtraction extends CalculatorBase {
        Subtraction(double x, double y) { super(x, y); }
        double calculate() { return getX() - getY(); }
    }
    static class Multiplication extends CalculatorBase {
        Multiplication(double x, double y) { super(x, y); }
        double calculate() { return getX() * getY(); }
    }
    static class Division extends CalculatorBase {
        Division(double x, double y) { super(x, y); }
        double calculate() { return getY() == 0 ? 0 : getX() / getY(); }
    }
    static class Modulo extends CalculatorBase {
        Modulo(double x, double y) { super(x, y); }
        double calculate() { return getX() % getY(); }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("==========================================");
        System.out.println("   (Getter/Setter)    ");
        System.out.println("==========================================");
        System.out.println("number 0-9");
        System.out.println("operter +  -  * /  %");
        System.out.println("enter your 'exit' for end progame");
        System.out.println("------------------------------------------");

        while (true) {
            System.out.print("\nenter your number and operter : ");
            String input = scan.nextLine().replace(" ", "");

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("end program.");
                break;
            }

            String[] parts = input.split("(?<=[-+*/%])|(?=[-+*/%])");

            if (parts.length < 3 || parts.length % 2 == 0) {
                System.out.println("Error: you not operter is number");
                continue; 
            }

            boolean isNumberValid = true;
            for (int k = 0; k < parts.length; k += 2) {
                if (!parts[k].matches("[0-9]+(\\.[0-9]+)?")) { 
                    System.out.println("Error: is not number (" + parts[k] + ")");
                    isNumberValid = false;
                    break;
                }
            }

            if (!isNumberValid) {
                continue;
            }

            double currentResult = Double.parseDouble(parts[0]);
            String processText = parts[0]; 

            for (int i = 1; i < parts.length; i += 2) {
                String op = parts[i];                 
                double nextNum = Double.parseDouble(parts[i+1]); 

                CalculatorBase calc = null;
                if (op.equals("+"))      calc = new Addition(currentResult, nextNum);
                else if (op.equals("-")) calc = new Subtraction(currentResult, nextNum);
                else if (op.equals("*")) calc = new Multiplication(currentResult, nextNum);
                else if (op.equals("/")) calc = new Division(currentResult, nextNum);
                else if (op.equals("%")) calc = new Modulo(currentResult, nextNum);

                if (calc != null) {     //ขั้นตอนการลงมือคำนวณและบันทึกผล
                    currentResult = calc.calculate();
                    processText += " " + op + " " + nextNum;
                }
            }

            System.out.println("------------------------------------------");
            System.out.println("Process: " + processText);
            System.out.println("= " + currentResult);
            System.out.println("------------------------------------------");
        }
        scan.close();
    }
}