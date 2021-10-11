package com.epam.brest.calc;

import java.math.BigDecimal;

public class CalcImpl implements Calc{
    @Override
    public BigDecimal handle(BigDecimal weight, BigDecimal pricePerKG, BigDecimal length, BigDecimal pricePerKm) {
        BigDecimal result = weight.multiply(pricePerKG).add(length.multiply(pricePerKm));
        return result;
    }
}
