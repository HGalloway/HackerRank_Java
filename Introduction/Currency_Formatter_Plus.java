package Introduction;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;
import java.util.Scanner;

public class Currency_Formatter_Plus {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String Payment = "12345678.104"; // scanner.nextLine();
        scanner.close();

        FormatPayment(Payment, Locale.US);
        FormatPayment(Payment, new Locale("hi", "IN"));
        FormatPayment(Payment, Locale.CHINA);
        FormatPayment(Payment, Locale.FRANCE);
    }

    private static void FormatPayment(String Payment, Locale locale) {
        NumberFormat NumFormat = NumberFormat.getInstance(locale);
        Currency currency = Currency.getInstance(locale);
        Payment = ReduceCents(Payment);
        // System.out.println("Payment: " + Payment);
        String PaymentFormatted = NumFormat.format(Double.parseDouble(Payment));
        

        if (locale.equals(Locale.FRANCE)) {
            String[] PaymentSplit = Payment.split("\\.");
            if (Integer.parseInt(PaymentSplit[PaymentSplit.length - 1]) % 10 == 0) {
                System.out.println("France: " + PaymentFormatted + "0 " + currency.getSymbol());
            } else {
                System.out.println("France: " + PaymentFormatted + " " +currency.getSymbol());
            }
        }
        else if (currency.getDisplayName() == "Indian Rupee") {
            String[] PaymentSplit = Payment.split("\\.");
            if (Integer.parseInt(PaymentSplit[PaymentSplit.length - 1]) % 10 == 0) {
                System.out.println("India: Rs." + PaymentFormatted + "0");
            } else {
                System.out.println("India: Rs." + PaymentFormatted);
            }
        }
        else if (locale.equals(Locale.US)) {
            String[] PaymentSplit = Payment.split("\\.");
            if (Integer.parseInt(PaymentSplit[PaymentSplit.length - 1])%10 == 0) {
                System.out.println("US: " + currency.getSymbol() + PaymentFormatted + "0");
            }
            else {
                System.out.println("US: " + currency.getSymbol() + PaymentFormatted);
            }
        }
        else if (locale.equals(Locale.CHINA)) {
            String[] PaymentSplit = Payment.split("\\.");
            if (Integer.parseInt(PaymentSplit[PaymentSplit.length - 1]) % 10 == 0) {
                System.out.println("China: " + currency.getSymbol(locale) + " " + PaymentFormatted + "0");
            } else {
                System.out.println("China: " + currency.getSymbol(locale) + " " + PaymentFormatted);
            }
        }
    }

    private static String ReduceCents(String Payment) {
        String[] PaymentSplit = Payment.split("\\.");
        if (PaymentSplit[PaymentSplit.length - 1].length() > 2) {
            PaymentSplit[PaymentSplit.length - 1] = PaymentSplit[PaymentSplit.length - 1].subSequence(0, 2).toString();
        }
        return ReBuildPayment(PaymentSplit);
    }

    private static String ReBuildPayment(String[] PaymentSplit) {
        StringBuilder StringBuilder = new StringBuilder();
        for (int i = 0; i <= PaymentSplit.length - 1; i++) {
            if (i == PaymentSplit.length - 1){
                StringBuilder.append("." + PaymentSplit[i]);
            }
            else{
                StringBuilder.append(PaymentSplit[i]);
            }
        }
        return StringBuilder.toString();
    }
}