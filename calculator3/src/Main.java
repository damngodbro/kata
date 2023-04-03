import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите выражение в арабской или римской СС");
        String original = scanner.nextLine();
        System.out.println(calc(original));
    }

    public static String calc(String original) throws Exception{
        int number;
        int number1;
        String veryFinalResult;
        boolean roman;
        int result;
        String[] cutOriginal = original.split("[+\\-*/]");
        for (int i = 0; i < cutOriginal.length; i++) {
            cutOriginal[i] = cutOriginal[i].trim();
        }
        if (cutOriginal.length == 1) throw new Exception("т.к. строка не является математической операцией");
        if (cutOriginal.length != 2) throw new Exception("Формат математической операции не удовлетворяет" +
                " заданию - два операнда и один оператор (+, -, /, *)");
        String sign = checkSign(original);
        if (sign.equals("null")) throw new Exception("Поддерживаются только: + - / *");

        if (!FullRoman.itRomanOrNot(cutOriginal[0]) && !FullRoman.itRomanOrNot(cutOriginal[1])) {
            number = Integer.parseInt(cutOriginal[0]);
            number1 = Integer.parseInt(cutOriginal[1]);
            roman = false;
        } else if (FullRoman.itRomanOrNot(cutOriginal[0]) && FullRoman.itRomanOrNot(cutOriginal[1])) {
            number = FullRoman.convertToArabian(cutOriginal[0]);
            number1 = FullRoman.convertToArabian(cutOriginal[1]);
            roman = true;
        } else {
            throw new Exception("т.к используются одновременно разные системы счисления");
        }

        if ((number <= 0 || number > 10) && (number1 <= 0 || number1 > 10)) {
            throw new Exception("Числа должны быть от 1 до 10");
        }
        int finalRez = finalCount(number, number1, sign);
        if (roman){
            if (finalRez <= 0){
                throw new Exception("т.к в римской системе нет отрицательных чисел");
            }
            veryFinalResult = FullRoman.ROMAN_ARRAY[finalRez];
        } else {
            veryFinalResult = String.valueOf(finalRez);
        }
        return veryFinalResult;
    }
    public static String checkSign(String original) {
        if (original.contains("+")) {
            return "+";
        } else if (original.contains("-")) {
            return "-";
        } else if (original.contains("*")) {
            return "*";
        } else if (original.contains("/")) {
            return "/";
        } else {
            return "null";
        }
    }

    public static Boolean checkNumbers(String num1) {
        int num = Integer.parseInt(num1);
        return (num > 0) && (num <= 10);
    }

    public static int finalCount(int int1, int int2, String sign) {
        switch (sign) {
            case "+":
                return int1 + int2;
            case "-":
                return int1 - int2;
            case "*":
                return int1 * int2;
            case "/":
                return int1 / int2;
        }
        return 0;  // никогда не произойдёт, поступает уже проверенный знак
    }
}
