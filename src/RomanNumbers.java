enum RomanNumeral {

    I(1), IV(4), V(5), IX(9), X(10),
    XL(40), L(50), XC(90), C(100),
    CD(400), D(500), CM(900), M(1000);

    private int value;

    RomanNumeral(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}

public class RomanNumbers {
    private String romanNum1;
    private String romanNum2;
    private String operator;

    RomanNumbers(String romanNum1, String operator, String romanNum2){
        this.romanNum1 = romanNum1;
        this.romanNum2 = romanNum2;
        this.operator = operator;
    }

    public static boolean isRomanNumber(String str) {
        boolean resultOfCheck = false;
        for (int i = 0; i < str.length(); i++) {
            resultOfCheck = false;
            String temp = "" + str.charAt(i);

            for(RomanNumeral numeral: RomanNumeral.values()) {
                if (temp.equals(numeral.toString())) {
                    resultOfCheck = true;
                    break;
                }
            }
            if (!resultOfCheck)
                return resultOfCheck;
        }
        return resultOfCheck;
    }

    private int romanToArabic(String strRomanNum) {
        int arabicNum = 0;
        for (int i = 0; i < strRomanNum.length(); i++) {
            String temp1 = "" + strRomanNum.charAt(i);
            boolean check = true;

            if (i < strRomanNum.length() - 1) {
                String temp2 = temp1 + strRomanNum.charAt(i + 1);

                for(RomanNumeral numeral: RomanNumeral.values()) {
                    if (temp2.equalsIgnoreCase(numeral.toString())) {
                        arabicNum += RomanNumeral.valueOf(temp2).getValue();
                        check = false;
                        i++;
                        break;
                    }
                }
            }
            if (check)
                arabicNum += RomanNumeral.valueOf(temp1).getValue();
        }
        return arabicNum;
    }

    private int calculateArabicNumbers() {
        ArabicNumbers temp = new ArabicNumbers(romanToArabic(romanNum1), operator, romanToArabic(romanNum2));
        return temp.calculateResult();
    }

    public String arabicToRoman() {
        String romanNum = "";
        try {
            int arabicNum = calculateArabicNumbers();
            if (arabicNum == -1000)
                return "";
            else if (arabicNum == 0) throw new Exception ("В римской системе нет 0");
            else if (arabicNum < 0) throw new Exception ("В римской системе нет отцирательных чисел");

            int digitOfArabicNum = calculateDigitOfArabicNum(arabicNum);

            while (arabicNum > 0) {
                int temp = (int) Math.pow(10, digitOfArabicNum - 1);
                int temp2 = arabicNum / temp;

                if (temp2 % 5 > 0 && temp2 % 5 < 4) {
                    if (temp2 > 5) {
                        for(RomanNumeral level: RomanNumeral.values()) {
                            if ((temp2 - temp2 % 5) * temp  == level.getValue()) {
                                romanNum += level.toString();
                                temp2 -= 5;
                            }

                        }
                    }

                    for(RomanNumeral level: RomanNumeral.values()) {
                        if (temp  == level.getValue()) {
                            while (temp2 > 0) {
                                romanNum += level.toString();
                                temp2--;
                            }
                        }
                    }
                }
                else {
                    for(RomanNumeral level: RomanNumeral.values()) {
                        if (temp2 * temp  == level.getValue()) {
                            romanNum += level.toString();
                        }
                    }
                }
                arabicNum -= arabicNum / temp * temp;
                digitOfArabicNum--;
            }
            return romanNum;
        } catch (Exception e) {
            romanNum += e;
            return romanNum;
        }

    }

    private int calculateDigitOfArabicNum(int arabicNum) {
        int digitOfArabicNum = 0;
        int temp = arabicNum;
        while (temp > 0) {
            temp /= 10;
            digitOfArabicNum++;
        }
        return digitOfArabicNum;
    }
}

