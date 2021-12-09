import java.util.Scanner;
public class Calculate {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        String str = scan.nextLine();
        calculate(str);
        scan.close();
    }

    public static void calculate(String str) {
        try {
            str = str.toUpperCase();
            String[] strArray = str.split(" ");
            if (strArray.length < 3) throw new Exception(mistakes(1));
            if (strArray.length > 3) throw new Exception(mistakes(2));

            boolean checkArabicNum1 = ArabicNumbers.isArabicNumber(strArray[0]);
            boolean checkRomanNum1 = RomanNumbers.isRomanNumber(strArray[0]);

            if (checkArabicNum1 && ArabicNumbers.isArabicNumber(strArray[2])) {
                ArabicNumbers temp = new ArabicNumbers(strArray[0], strArray[1], strArray[2]);
                int result = temp.calculateResult();
                if (result != -1000)
                    System.out.println(result);
            }
            else if (checkArabicNum1 && RomanNumbers.isRomanNumber(strArray[2]))
                throw new Exception(mistakes(3));
            else if (checkRomanNum1 && RomanNumbers.isRomanNumber(strArray[2])) {
                RomanNumbers temp = new RomanNumbers(strArray[0], strArray[1], strArray[2]);
                System.out.println(temp.arabicToRoman());
            }
            else if (checkRomanNum1 && ArabicNumbers.isArabicNumber(strArray[2]))
                throw new Exception(mistakes(3));
            else
                throw new Exception(mistakes(4));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static String mistakes(int num) {
        String mistake = "";
        switch (num) {
            case 1: mistake = "Cтрока не является математической операцией";
                break;
            case 2: mistake = "Формат математической операции не удовлетворяет "
                    + "заданию - два операнда и один оператор (+, -, /, *)";
                break;
            case 3: mistake = "Используются одновременно разные системы счисления";
                break;
            case 4: mistake = "Принимаются только арабские и римские цифры";
                break;
        }
        return mistake;
    }

}
