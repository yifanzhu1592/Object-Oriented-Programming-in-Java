// The FractionCalculator class is a class that will allow the user to enter in fractions and operations, calculating and displaying the result.
// It will run until the user tells it to quit.

import java.util.Scanner;
import static java.lang.Math.pow;

public class FractionCalculator {
    public static void main(String args[]) {
        System.out.println("This program is a fraction calculator");
        System.out.println("It will add, subtract, multiply and divide fractions until you type Q to quit.");
        System.out.println("Please enter your fractions in the form a/b, where a and b are integers.");
        while (true) {
            System.out.println("------------------------------------------------------------------------------");
            Scanner input = new Scanner(System.in);

            System.out.print("Please enter an operation (+, -, /, *, = or Q to quit): ");
            String operation = getOperation(input);

            if (operation.equals("Q") || operation.equals("q")){
                System.exit(0);
            }

            System.out.print("Please enter a fraction (a/b) or integer (a): ");
            Fraction numerator = getFraction(input);

            System.out.print("Please enter a fraction (a/b) or integer (a): ");
            Fraction denominator = getFraction(input);

            if (numerator.getDenominator() == 1 || numerator.getNumerator() == 0) {
                System.out.print(numerator.getNumerator());
            } else {
                System.out.print(numerator.toString());
            }

            System.out.print(' ' + operation + ' ');

            if (denominator.getDenominator() == 1 || denominator.getNumerator() == 0) {
                System.out.print(denominator.getNumerator());
            } else {
                System.out.print(denominator.toString());
            }

            if (operation.equals("=")){
                if (numerator.equals(denominator)){
                    System.out.println(" is true");
                } else{
                    System.out.println(" is false");
                }
            }
            else if (operation.equals("+")){
                System.out.println(" = " + numerator.add(denominator).toString());
            }
            else if (operation.equals("-")){
                System.out.println(" = " + numerator.subtract(denominator).toString());
            }
            else if (operation.equals("*")){
                System.out.println(" = " + numerator.multiply(denominator).toString());
            }
            else if (denominator.getDenominator() == 0 || denominator.toDouble() == 0){
                System.out.println(" = Undefined");
            }
            else{
                System.out.println(" = " + numerator.divide(denominator).toString());
            }
        }
    }

    // Asks the user to enter in a valid mathematical operation. If the user enters anything except "+", "-
    // ", "/", "*", "=", "q", or "Q" it should re-prompt them until there is valid input.
    public static String getOperation(Scanner input){
        String operation = input.nextLine();
        while (!(operation.equals("+") || operation.equals("-") || operation.equals("*") || operation.equals("/") || operation.equals("=") || operation.equals("Q") || operation.equals("q"))) {
            System.out.print("Invalid input (+, -, /, *, = or Q to quit): ");
            operation = input.nextLine();
        }
        return operation;
    }

    // returns true if the parameter is in the form "a/b" where a is any int and b is any positive int
    public static boolean validFraction(String input){
        if (!(input.charAt(0) == '-' || Character.isDigit(input.charAt(0)))){
            return false;
        }
        for (int i = 1; i < input.length(); i++){
            if (input.charAt(i) == '/'){
                for (int j = 0; j < i; j++){
                    if (!(Character.isDigit(input.charAt(j)))){
                        return false;
                    }
                }
                if (input.charAt(i + 1) == '0'){
                    return false;
                }
                for (int k = i + 1; k < input.length(); k++){
                    if (!(Character.isDigit(input.charAt(k)))){
                        return false;
                    }
                }
            }
            else if (!(Character.isDigit(input.charAt(i)))){
                return false;
            }
        }
        return true;
    }

    // It prompts the user for a String that is a validFraction. If they enter any thing that is not a
    // valid Fraction, it should re-prompt them until it is valid
    public static Fraction getFraction(Scanner input){
        String fraction = input.nextLine();
        while (!validFraction(fraction)){
            System.out.print("Invalid fraction. Please enter (a/b) or (a), where a and b are integers and b is not zero: ");
            fraction = input.nextLine();
        }
        int numerator = 0;
        int denominator = 0;
        for (int i = 0; i < fraction.length(); i++){
            if (fraction.charAt(i) == '/'){
                if (fraction.charAt(0) == '-'){
                    for (int j = i - 1, k = 0; j >= 1; j--, k++){
                        numerator += pow(10, k) * (fraction.charAt(j) - '0');
                    }
                    numerator = -numerator;
                }
                else{
                    for (int j = i - 1, k = 0; j >= 0; j--, k++) {
                        numerator += pow(10, k) * (fraction.charAt(j) - '0');
                    }
                }
                if (fraction.charAt(i + 1) == '-'){
                    for (int m = fraction.length() - 1, n = 0; m > i + 1; m--, n++) {
                        denominator += pow(10, n) * (fraction.charAt(m) - '0');
                    }
                    denominator = -denominator;
                }
                else{
                    for (int m = fraction.length() - 1, n = 0; m > i; m--, n++) {
                        denominator += pow(10, n) * (fraction.charAt(m) - '0');
                    }
                }
                return new Fraction(numerator, denominator);
            }
        }
        if (fraction.charAt(0) == 0){
            return new Fraction();
        }
        else if (fraction.charAt(0) == '-'){
            for (int x = fraction.length() - 1, y = 0; x >= 1; x--, y++){
                numerator += pow(10, y) * (fraction.charAt(x) - '0');
            }
            numerator = -numerator;

            return new Fraction(numerator);
        }
        else{
            for (int x = fraction.length() - 1, y = 0; x >= 0; x--, y++){
                numerator += pow(10, y) * (fraction.charAt(x) - '0');
            }
            return new Fraction(numerator);
        }
    }
}
