package MainProgram;
import java.util.Scanner;

public class ProjectCalculater {

    // คลาสแม่
    static class Calculator {
        public double calculate(double x, double y) {
            return x;
        }
    }

    // คูณ
    static class Multiply extends Calculator {
        public double calculate(double x, double y) {
            return x * y;
        }
    }

    // หาร
    static class Divide extends Calculator {
        public double calculate(double x, double y) {
            if (y == 0) {
                System.out.println("It cannot be divided by 0.");
                return 0;
            }
            return x / y;
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("--- Calculater ---");
        System.out.print("entter for (8*2/4): ");
        String input = scan.nextLine();
        input = input.replace(" ", "");

        double[] numbers = new double[100];
        char[] operators = new char[100];
        int numCount = 0;
        int opCount = 0;

        String tempNum = "";

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            if (c != '*' && c != '/') {
                tempNum += c;
            }

            if (c == '*' || c == '/' || i == input.length() - 1) {
                if (!tempNum.isEmpty()) {
                    numbers[numCount++] = Double.parseDouble(tempNum);
                    tempNum = "";
                }
                if (c == '*' || c == '/') {
                    operators[opCount++] = c;
                }
            }
        }

        double result = numbers[0];

        for (int i = 0; i < opCount; i++) {
            char op = operators[i];
            double next = numbers[i + 1];

            Calculator calc;
            if (op == '*') {
                calc = new Multiply();
            } else {
                calc = new Divide();
            }

            double old = result;
            result = calc.calculate(result, next);

            System.out.println("    Process : " + old + " " + op + " " + next + " = " + result);
        }

        System.out.println("-----------------------------------");
        System.out.println("Output : " + result);

        scan.close();
    }
}