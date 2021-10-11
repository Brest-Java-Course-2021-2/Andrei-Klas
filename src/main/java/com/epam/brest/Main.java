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
                pricePerKG = getValueFromCon(scanner, "Enter pricePerKG:");
                length = getValueFromCon(scanner, "Enter length:");
                pricePerKm = getValueFromCon(scanner, "Enter pricePerKm:");
                BigDecimal result = weight.multiply(pricePerKG).add(length.multiply(pricePerKm));
                System.out.println("Result = " + new CalcImpl().handle(weight,pricePerKG, length,pricePerKm));
                System.out.println("Enter 'q' to exit or 'c' for continue");
            }while(!scanner.hasNext("q"));
        }



        //System.out.println("Val " + value);
        //}
    }

    private static BigDecimal getValueFromCon(Scanner scanner, String outputMessage) {
        BigDecimal enteredValue;
        System.out.print(outputMessage);
        enteredValue = scanner.nextBigDecimal();
        return enteredValue;
    }


}
