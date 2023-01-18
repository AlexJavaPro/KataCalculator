
import java.util.Arrays;


public class Calculator {
    private static final String[] arabianNumbers = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    private static final String[] romanNumbers = new String[]{"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
    private static final String[] romanUnits = new String[] {"","I","II","III","IV","V","VI","VII","VIII","IX"};
    private static final String[] romanDozens = new String[] {"","X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
    private static final String[] romanHundreds = new String[] {"","C"};
    private static String[] strings;
    private static int firstNumber;
    private static int secondNumber;
    private static boolean isArabicFirstNumber;
    private static boolean isArabicSecondNumber;
    private static boolean isRomanFirstNumber;
    private static boolean isRomanSecondNumber;

    public static void calculator(String unput) throws MyException {
        lineDivision(unput);
        numberSystemСheck();
        conversionNumbers();
        System.out.println(stringResult());
    }

    public static void lineDivision(String unput) throws MyException {
        String[] str = unput.split(" ");
        if (str.length > 3) {
            throw new MyException("т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        } else if(str.length < 3) throw new MyException("т.к. строка не является математической операцией");
        strings = str;
    }

    public static boolean arabicNumberCheck(String number) {
        for (int i = 0; i < arabianNumbers.length; i++)
            if (number.equals(arabianNumbers[i])){
                return true;}
        return false;
    }

    public static boolean romanNumberCheck(String number) {
        for (int i = 0; i < romanNumbers.length; i++)
            if (number.equals(romanNumbers[i])){
                return true;}
        return false;
    }

    public static void numberSystemСheck() throws MyException {
        isArabicFirstNumber = arabicNumberCheck(strings[0]);
        isArabicSecondNumber = arabicNumberCheck(strings[2]);
        isRomanFirstNumber = romanNumberCheck(strings[0]);
        isRomanSecondNumber = romanNumberCheck(strings[2]);
        if(!((isArabicFirstNumber && isArabicSecondNumber) ||
                (isRomanFirstNumber && isRomanSecondNumber)))
            throw new MyException("т.к. используются одновременно разные системы счисления");
    }


    public static void conversionNumbers(){
        if (isArabicFirstNumber && isArabicFirstNumber){
            firstNumber = Integer.parseInt(strings[0]);
            secondNumber = Integer.parseInt(strings[2]);
        } else {
            firstNumber = Arrays.asList(romanNumbers).indexOf(strings[0]) + 1;
            secondNumber = Arrays.asList(romanNumbers).indexOf(strings[2]) + 1;
        }
    }
  public static int result() throws MyException {
      switch (strings[1]){
          case "+" :
              return firstNumber + secondNumber;
          case "-" :
              return  firstNumber - secondNumber;
          case "*" :
              return  firstNumber * secondNumber;
          case "/" :
              return  firstNumber / secondNumber;
          default:
              throw new MyException("т.к. строка не является математической операцией");
      }

    }
    public static String stringResult() throws MyException{
        int result = result();
        if (isRomanFirstNumber && isRomanSecondNumber) {
            if (result < 1) throw new MyException("т.к. в римской системе нет отрицательных чисел и отсутствует ноль");
            int units = result % 10;
            int dozens = ((result % 100) - units) / 10;
            int hundreds = ((result % 1000) - dozens - units) / 100;
            return romanHundreds[hundreds] + romanDozens[dozens] + romanUnits[units];}
        else return (Integer.toString(result()));
    }


}
