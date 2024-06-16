import java.util.Scanner;

public class Calculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите первое число:");
        int num1 = Integer.parseInt(scanner.nextLine());
        if (num1 < 1 || num1 > 10) {
            throw new IllegalArgumentException("Число должно быть от 1 до 10");
        }

        System.out.println("Введите второе число:");
        int num2 = Integer.parseInt(scanner.nextLine());
        if (num2 < 1 || num2 > 10) {
            throw new IllegalArgumentException("Число должно быть от 1 до 10");
        }

        System.out.println("Выберите операцию (+, -, *, /):");
        String operation = scanner.nextLine();
        if (!operation.equals("+") && !operation.equals("-") && !operation.equals("*") && !operation.equals("/")) {
            throw new IllegalArgumentException("Недопустимая операция");
        }

        int result = 0;
        switch (operation) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                result = num1 / num2;
                break;
        }

        System.out.println("Результат: " + result);
    }
}