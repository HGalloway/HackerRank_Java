package Introduction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Currency;
import java.util.Locale;
import java.util.Scanner;

public class Currency_Formatter {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String Payment = "12345678.10456"; //scanner.nextLine();
        scanner.close();

        String[] PaymentSplit = Payment.split("\\.");

        String FormattedDollarAmount = FormatDollars(PaymentSplit[0].toString());
        String FormattedChangeAmount = FormatChange(PaymentSplit[1].toString());

        String FullFormattedAmount = FormattedDollarAmount + "." + FormattedChangeAmount;

        System.out.println("US: $" + FullFormattedAmount);
        System.out.println("India: Rs." + FullFormattedAmount);
        
        Currency Yuan = Currency.getInstance(Locale.CHINA);
        System.out.println("China: " + Yuan.getSymbol().replaceAll("CN", "") + FullFormattedAmount);

        Currency Euro = Currency.getInstance(Locale.FRANCE);
        System.out.println("France: " + FullFormattedAmount.replaceFirst(",", " ").replace(".", ",") + Euro.getSymbol());
    }

    private static String FormatDollars(String Dollars) {
        char[] DollarsCharByChar = Dollars.toCharArray();

        //Group into groups of three
        ArrayList<ArrayList<Character>> NumSeparated = new ArrayList<ArrayList<Character>>(); //Numbers separated into groups of three
        int Rotation = 0;
        ArrayList<Character> Group = new ArrayList<Character>();
        
        for (int i = DollarsCharByChar.length - 1; i > -1 ; i--) {
            if (Rotation == 3) {
                Rotation = 0;
                NumSeparated.add(Group);
                Group = new ArrayList<Character>();
            }
            if (i == 0) {
                Group.add(DollarsCharByChar[i]);
                NumSeparated.add(Group);
                break;
            }
            if (Rotation != 3) {
                Group.add(DollarsCharByChar[i]);
                Rotation++;
            }
        }
        
        //Reverse Groups
        String FinalDollarAmount = "";
        for (int i = NumSeparated.size() - 1; i > -1; i--) {
            Collections.reverse(NumSeparated.get(i));
            if (FinalDollarAmount == "") {
                FinalDollarAmount = RemoveSpacesAndBrackets(NumSeparated.get(i).toString());
            }
            else {
                FinalDollarAmount = FinalDollarAmount + "," + RemoveSpacesAndBrackets(NumSeparated.get(i).toString());
            }
        }
        return FinalDollarAmount;
    }
    
    private static String RemoveSpacesAndBrackets(String WorkingString) {
        return WorkingString.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll(",", "").replaceAll(" ", "");
    }

    private static String FormatChange(String Change) {
        if (Change.length() > 2) {
            return Change.substring(0, 2);
        }
        else {
            return Change;
        }
    }

}