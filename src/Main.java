import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            while(true) {
                Scanner scanner = new Scanner(System.in);
                String input = scanner.nextLine();
                System.out.println(calc(input));
            }
        } catch(ScannerException e) {
            System.out.println("Неверный формат выражения! Используйте следующий формат: " + "\n" +
                               "5 + 3" + "\n" +
                               "8 * 3" + "\n" +
                               "X - II" + "\n" +
                               "VI / II");
        }
    }

    public static String calc(String input) throws ScannerException {
        Calculator calculator;
        int num1;
        int num2;
        String[] expression = input.split("\\s+");

        if (!checkExpression(expression)) {
            throw new ScannerException();
        }

        String operand1 = expression[0];
        Operation operation = getOperation(expression[1]);
        String operand2 = expression[2];


        if(isArabicNumber(operand1)) {
            num1 = Integer.parseInt(operand1);
            num2 = Integer.parseInt(operand2);
            calculator = new Calculator(num1, num2, NumbersType.ARABIC);
        } else {
            num1 = Converter.romanToArabic(operand1);
            num2 = Converter.romanToArabic(operand2);
            calculator = new Calculator(num1, num2, NumbersType.ROMAN);
        }

        return calculator.calculate(operation);
    }

    private static boolean checkExpression(String[] expression) {
        int operand1 = 0;
        int operand2 = 0;

        if(expression.length != 3) {
            return false;
        }

        if(isArabicNumber(expression[0]) && isArabicNumber(expression[2])) {
            operand1 = Integer.parseInt(expression[0]);
            operand2 = Integer.parseInt(expression[2]);
        } else if(isRomanNumber(expression[0]) && isRomanNumber(expression[2])) {
            operand1 = Converter.romanToArabic(expression[0]);
            operand2 = Converter.romanToArabic(expression[2]);
        }

        return checkValueNumber(operand1) && checkValueNumber(operand2);
    }

    private static boolean checkValueNumber(int value) {
        return value > 0 && value <= 10;
    }

    public static boolean isArabicNumber(String number) {
        return number.matches("\\d+");
    }

    public static boolean isRomanNumber(String number) {
        return number.matches("(X|IX|V?I{0,3}|IV)");
    }

    public static Operation getOperation(String operation) throws ScannerException {
        return switch(operation) {
            case "+" -> Operation.ADD;
            case "-" -> Operation.SUBTRACT;
            case "*" -> Operation.MULTIPLY;
            case "/" -> Operation.DIVISION;
            default -> throw new ScannerException();
        };
    }
}
