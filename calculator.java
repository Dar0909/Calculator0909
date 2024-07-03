import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

public class сalculator {
    private static final Map<Character, Integer> romanNumerals = new HashMap<>();
    static {
        romanNumerals.put('I', 1);
        romanNumerals.put('V', 5);
        romanNumerals.put('X', 10);
      
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String number1, number2;
        int arabicNumber1, arabicNumber2, result;
        char operator;
        System.out.println("Введите операцию (+, -, *, /): ");
        operator = scanner.next().charAt(0);

        number1 = getInput(scanner, "Введите первое число (1-10, арабское или римское): ");
        number2 = getInput(scanner, "Введите второе число (1-10, арабское или римское): ");

        arabicNumber1 = romanToArabic(number1);
        arabicNumber2 = romanToArabic(number2);

        if (arabicNumber1 == -1 || arabicNumber2 == -1) {
            System.out.println("Ошибка: Введено число вне диапазона 1-10!");
            return;
        }

        switch (operator) {
            case '+':
                result = arabicNumber1 + arabicNumber2;
                break;
            case '-':
                result = arabicNumber1 - arabicNumber2;
                break;
            case '*':
                result = arabicNumber1 * arabicNumber2;
                break;
            case '/':
                if (arabicNumber2 != 0) {
                    result = arabicNumber1 / arabicNumber2;
                } else {
                    System.out.println("Ошибка: Деление на ноль!");
                    return;
                }
                break;
            default:
                System.out.println("Ошибка: Неверный оператор!");
                return;
        }

        System.out.println("Результат (арабскими цифрами): " + result);
        if (result >= 1 && result <= 10) {
            System.out.println("Результат (римскими цифрами): " + arabicToRoman(result));
        } else {
            System.out.println("Результат в римских чисел невозможен, число вне диапазона 1-10");
        }
    }

    private static String getInput(Scanner scanner, String prompt) {
        String input;
        do {
            System.out.println(prompt);
            input = scanner.next().toUpperCase();
            if (!input.matches("[IVXLCDM]+") && !(input.matches("\\d+") && Integer.parseInt(input) >= 1 && Integer.parseInt(input) <= 10)) {
                System.out.println("Ошибка. Введите число от 1 до 10.");
                input = null;
            }
        } while (input == null);
        return input;
    }

    private static int romanToArabic(String roman) {
        if (!roman.matches("[IVX]+")) {
            return Integer.parseInt(roman);
        }
        int result = 0;
        for (int i = 0; i < roman.length(); i++) {
            if (i > 0 && romanNumerals.get(roman.charAt(i)) > romanNumerals.get(roman.charAt(i - 1))) {
                result += romanNumerals.get(roman.charAt(i)) - 2 * romanNumerals.get(roman.charAt(i - 1));
            } else {
                result += romanNumerals.get(roman.charAt(i));
            }
        }
        return result >= 1 && result <= 10 ? result : -1;
    }

    private static String arabicToRoman(int number) {
        if (number < 1 || number > 10) {
            return "N/A"; 
        }
        StringBuilder result = new StringBuilder();
        int[] values = {10, 9, 5, 4, 1};
        String[] symbols = {"X", "IX", "V", "IV", "I"};
        for (int i = 0; i < values.length; i++) {
            while (number >= values[i]) {
                number -= values[i];
                result.append(symbols[i]);
            }
        }
        return result.toString();
    }
}
