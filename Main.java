import java.util.*;


public class Main  {
    public static void main(String[] args) throws CalculateException {
        String input = new Scanner(System.in).nextLine();
        System.out.println(calc(input));
    }

public static String calc(String input) throws CalculateException{
    String[] expression = input.split("\\s+");
    String numberOne = expression[0], numberTwo = expression[2], operator = expression[1], result = "";
    int decimalNumberOne = 0, decimalNumberTwo = 0, decimalResult = 0;
    boolean isRoman1 = !numberOne.isEmpty() && numberOne.matches("M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})"); // проверка на римским регуляркой
    boolean isRoman2 = !numberTwo.isEmpty() && numberTwo.matches("M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})");
    boolean isAllRoman = isRoman1 && isRoman2;
    boolean isAction = !operator.isEmpty() && operator.matches("[+\\-*/]");
    if(isRoman1 && isRoman2 && isAction ) {                                                                                      // проверка на полное соответствие ввода римских
        decimalNumberOne = romanConverter(numberOne);
        decimalNumberTwo = romanConverter(numberTwo);
    } else if (numberOne.chars().allMatch(Character::isDigit) && numberTwo.chars().allMatch(Character::isDigit) && isAction) {   // проверка на полное соответствие ввода арабских
        decimalNumberOne = Integer.parseInt(numberOne);
        decimalNumberTwo = Integer.parseInt(numberTwo);
    }else throw new CalculateException();                                                                                       // выбрасывает исключение при фелс
    if((decimalNumberOne>0 && decimalNumberOne<=10) && (decimalNumberTwo>0 && decimalNumberTwo<=10) && expression.length==3 ) {
        switch (operator) {
            case "+" -> decimalResult = decimalNumberOne + decimalNumberTwo;
            case "-" -> decimalResult = decimalNumberOne - decimalNumberTwo;
            case "*" -> decimalResult = decimalNumberOne * decimalNumberTwo;
            case "/" -> decimalResult = decimalNumberOne / decimalNumberTwo;
            default -> System.out.println("Все по новой");
        }
    }else throw new CalculateException();
    if (isAllRoman && decimalResult<=0) {
        throw new CalculateException();
    }else result = String.valueOf(decimalResult);

    return  isAllRoman? (reverseConvertDecimalToRoman(decimalResult)).toString() : result;
    }
    public static int romanConverter(String romanIn) {
        int[] decimal = {100, 90, 50, 40, 10, 9, 5, 4, 1};                   //  значения 4-9-40-90  сопадающие значения
        String[] roman = {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int result = 0;
        for (int i = 0; i < decimal.length; i++) {                           // цикл проходит по каждому символу
            while (romanIn.indexOf(roman[i]) == 0) {
                result += decimal[i];
                romanIn = romanIn.substring(roman[i].length());
            }}
        return  result;
    }
    public static StringBuilder reverseConvertDecimalToRoman(int res){
        int[] decimal = {100, 90, 50, 40, 10, 9, 5, 4, 1};                   //  значения 4-9-40-90  сопадающие значения
        String[] roman = {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder resultRoman = new StringBuilder();
        for (int i = 0; i <decimal.length ; i++) {
            while (res>=decimal[i]){
                res -=decimal[i];
                resultRoman.append(roman[i]);
            }}
        return resultRoman;
    }
}









