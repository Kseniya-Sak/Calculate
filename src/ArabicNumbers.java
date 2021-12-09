public class ArabicNumbers {
    private int arabicNum1;
    private int arabicNum2;
    private String operator;
    private int result;

    ArabicNumbers(int arabicNum1, String operator, int arabicNum2 ) {
        this.arabicNum1 = arabicNum1;
        this.arabicNum2 = arabicNum2;
        this.operator = operator;
    }

    ArabicNumbers(String arabicNum1, String operator, String arabicNum2) {
        this.arabicNum1 = strToNum(arabicNum1);
        this.arabicNum2 = strToNum(arabicNum2);
        this.operator = operator;
    }

    public static boolean isArabicNumber(String str) throws NumberFormatException {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private int strToNum(String str) {
        return Integer.parseInt(str);
    }

    public int calculateResult() {
        switch (operator) {
            case "+": addition();
                break;
            case "-": subtraction();
                break;
            case "*": multiplication();
                break;
            case "/": division();
                break;
            default:
                System.out.println("Используется неверный оператор - %");
                result = -1000;
                break;
        }
        if (checkBorders())
            return result;
        else
            return -1000;
    }


    private void addition() {
        result = arabicNum1 + arabicNum2;
    }

    private void subtraction() {
        result = arabicNum1 - arabicNum2;
    }

    private void multiplication() {
        result = arabicNum1 * arabicNum2;
    }

    private void division() throws ArithmeticException {
        try {
            result = arabicNum1 / arabicNum2;
        }
        catch (ArithmeticException e) {
        }
    }

    private boolean checkBorders() {
        try {
            if ((arabicNum1 <= 10 && arabicNum1 >= 1) && (arabicNum2 <= 10 && arabicNum2 >= 1)) {
                return true;
            }
            else
                throw new Exception("Принимаются только числа от 1 до 10 влючительно");
        } catch  (Exception e) {
            System.out.println(e);
            return false;
        }
    }
}
