package com.epam.brest.calc;

import java.math.BigDecimal;

public interface Calc {
    BigDecimal handle(BigDecimal weight, BigDecimal pricePerKG, BigDecimal length, BigDecimal pricePerKm);

    static BigDecimal handleFromFile(BigDecimal weight, BigDecimal length) {
        return null;
    }
}
