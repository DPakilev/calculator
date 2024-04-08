import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception{
        Scanner input = new Scanner(System.in);
        System.out.println(calc(input.nextLine()));
    }

    public static String calc(String input) throws Exception{
        String[] arithmeticOperation = {"+", "-", "*", "/"};
        String[] regexForSplit = {"\\+", "-", "\\*", "/"};
        String[] verifyOperands = input.split("\\+|-|\\*|/");
        int result;
        int firstOperand;
        int secondOperand;
        boolean romanNumbers;
        int indexArithmeticOperation = -1;


        if (verifyOperands.length>2) {
            throw new Exception("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");

        } else if (verifyOperands.length==1) {
            throw new Exception("Строка не является математической операцией");

        } else {
            for (int count=0; count<=arithmeticOperation.length-1; count++) {
                if (input.contains(arithmeticOperation[count])) {
                    indexArithmeticOperation = count;
                    break;
                }
            }
        }

        verifyOperands = null;
        String[] arithmeticOperands = input.split(regexForSplit[indexArithmeticOperation]);

        try {
            firstOperand = Integer.parseInt(arithmeticOperands[0].trim());
            secondOperand = Integer.parseInt(arithmeticOperands[1].trim());

            romanNumbers = false;
        } catch (NumberFormatException e) {
            firstOperand = RomanToInt(arithmeticOperands[0].trim());
            secondOperand = RomanToInt(arithmeticOperands[1].trim());
            if ((firstOperand == 0)|(secondOperand == 0)) {
                throw new Exception("Используются одновременно разные системы счисления");
            }
            romanNumbers = true;
        }

        if ((firstOperand > 10)|(secondOperand > 10)){
            throw new Exception("Калькулятор принимает на вход числа от 1 до 10 включительно");
        }

        switch (arithmeticOperation[indexArithmeticOperation]){
            case "+":
                result = firstOperand + secondOperand;
                if (romanNumbers){
                    return IntToRoman(result);
                } else {
                    return Integer.toString(result);
                }
            case "-":
                result = firstOperand - secondOperand;
                if (romanNumbers){
                    if (result>0){
                        return IntToRoman(result);
                    } else {
                        throw new Exception("В римской системе нет отрицательных чисел");
                    }
                } else {
                    return Integer.toString(result);
                }
            case "*":
                result = firstOperand * secondOperand;
                if (romanNumbers){
                    return IntToRoman(result);
                } else {
                    return Integer.toString(result);
                }
            case "/":
                result = firstOperand / secondOperand;
                if (romanNumbers){
                    if (result>0){
                        return IntToRoman(result);
                    } else {
                        throw new Exception("В римской системе нет отрицательных чисел");
                    }
                } else {
                    return Integer.toString(result);
                }
        }
        throw new Exception("Можно использовать лишь 4 оператора: +, -, *, /");
    }

    public  static int RomanToInt(String romanNumbers){
        String upRomanNumbers = romanNumbers.toUpperCase();
        char [] arrChar = upRomanNumbers.toCharArray();
        int last = 0;
        int result = 0;
        RomanNumerals num;
        try {
            num = RomanNumerals.valueOf(String.valueOf(arrChar[0]));
        } catch (IllegalArgumentException e) {
            return 0;
        }

        for (int count=arrChar.length-1; count>=0; count--) {
            num = RomanNumerals.valueOf(String.valueOf(arrChar[count]));
            if (last > num.getValue()){
                result -= num.getValue();
                last = num.getValue();
            } else {
                result += num.getValue();
                last = num.getValue();
            }
        }
        return result;
    }

    public static String IntToRoman(int numbers) {
        String result = "";
        while (numbers > 0){
            if (numbers>=100){
                numbers -= 100;
                result += "C";
            }
            else if (numbers>=90){
                numbers -= 90;
                result += "XC";
            }
            else if (numbers>=50){
                numbers -= 50;
                result += "L";
            }
            else if (numbers>=40){
                numbers -= 40;
                result += "XL";
            }
            else if (numbers>=10){
                numbers -= 10;
                result += "X";
            }
            else if (numbers >= 9){
                numbers -= 9;
                result += "IX";
            }
            else if (numbers>=5){
                numbers -= 5;
                result += "V";
            }
            else if (numbers>=4){
                numbers -= 4;
                result += "IV";
            }
            else if (numbers>=1){
                numbers -= 1;
                result += "I";
            }
        }
        return result;
    }
}

