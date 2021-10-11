package com.epam.brest;

import com.epam.brest.calc.Calc;
import com.epam.brest.calc.CalcImpl;

import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        BigDecimal weight, pricePerKG, length, pricePerKm;

        try (Scanner scanner = new Scanner(System.in)){
            do {
                weight = getValueFromCon(scanner, "Enter m:");
                length = getValueFromCon(scanner, "Enter length:");
                System.out.println("Result = " + new CalcImpl().handleFromFile(weight, length));
                System.out.println("Enter 'q' to exit or 'c' for continue");
            }while(!scanner.hasNext("q"));
        }
    }

    private static BigDecimal getValueFromCon(Scanner scanner, String outputMessage) {
        BigDecimal enteredValue;
        System.out.print(outputMessage);
        enteredValue = scanner.nextBigDecimal();
        return enteredValue;
    }


}
