import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

    public class Calculator {
        private static final Map<Character, Integer> romanNumerals = new HashMap<>();
        static {
            romanNumerals.put('I', 1);
            romanNumerals.put('V', 5);
            romanNumerals.put('X', 10);
        }

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите выражение например: (V+II или 2-1): ");
            String input = scanner.nextLine().toUpperCase();

            Pattern pattern = Pattern.compile("([IVXLCDM]+|[0-9]+)([+\\-*/])([IVXLCDM]+|[0-9]+)");
            Matcher matcher = pattern.matcher(input);

            if (!matcher.matches()) {
                System.out.println("Ошибка!");
                return;
            }

            String number1 = matcher.group(1);
            String number2 = matcher.group(3);
            char operator = matcher.group(2).charAt(0);

            boolean isRoman = number1.matches("[IVXLCDM]+") && number2.matches("[IVXLCDM]+");
            int arabicNumber1 = isRoman ? romanToArabic(number1) : Integer.parseInt(number1);
            int arabicNumber2 = isRoman ? romanToArabic(number2) : Integer.parseInt(number2);
            if ((arabicNumber1 < 1 || arabicNumber1 > 10) || (arabicNumber2 < 1 || arabicNumber2 > 10)) {
                System.out.println("Введите числа от 1 до 10");
                return;
            }

            int result = calculateResult(arabicNumber1, arabicNumber2, operator);
            if (result == -1) return; // Ошибка была выведена в методе calculateResult

            if (isRoman) {
                System.out.println("Результат (римскими цифрами): " + arabicToRoman(result));
            } else {
                System.out.println("Результат (арабскими цифрами): " + result);
            }
        }

        private static int calculateResult(int number1, int number2, char operator) {
            switch (operator) {
                case '+':
                    return number1 + number2;
                case '-':
                    return number1 - number2;
                case '*':
                    return number1 * number2;
                case '/':
                    if (number2 == 0) {
                        System.out.println("Ошибка: Деление на ноль!");
                        return -1;
                    }
                    return number1 / number2;
                default:
                    System.out.println("Ошибка: Неверный оператор!");
                    return -1;
            }
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
            return "N/A"; //
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
        }
        return result.toString();
    }
}
