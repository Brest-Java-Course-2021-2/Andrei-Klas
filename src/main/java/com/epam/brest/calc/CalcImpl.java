package com.epam.brest.calc;

import java.math.BigDecimal;

public class CalcImpl implements Calc{
    @Override
    public BigDecimal handle(BigDecimal weight, BigDecimal pricePerKG, BigDecimal length, BigDecimal pricePerKm) {
        BigDecimal result = weight.multiply(pricePerKG).add(length.multiply(pricePerKm));
        return result;
    }

    public static BigDecimal handleFromFile(BigDecimal weight, BigDecimal length) {
        BigDecimal pricePerKG = null;
        BigDecimal pricePerKm = null;

        try {

            pricePerKG = new Price().getPriceDependsOnValue(PricePerSmthEnum.WEIGHT, weight);
            pricePerKm = new Price().getPriceDependsOnValue(PricePerSmthEnum.LENGTH, length);

        } catch (Exception e) {
            e.printStackTrace();
        }


        BigDecimal result = weight.multiply(pricePerKG).add(length.multiply(pricePerKm));


        return result;
    }
}
