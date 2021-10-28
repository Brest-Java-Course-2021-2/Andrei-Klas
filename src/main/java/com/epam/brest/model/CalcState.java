package com.epam.brest.model;

import com.epam.brest.calc.CalcImpl;
import com.epam.brest.selector.PriceSelector;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Scanner;

import static com.epam.brest.model.StatusType.CALC;

public class CalcState extends AbstractStatus {

    public CalcState(Scanner scanner, Map<Integer, BigDecimal> pricePerKgMap, Map<Integer, BigDecimal> pricePerKmMap) {
        this.scanner = scanner;
        this.pricePerKgMap = pricePerKgMap;
        this.pricePerKmMap = pricePerKmMap;
    }

    @Override
    public Status handle() {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-config.xml");
        PriceSelector priceSelector = applicationContext.getBean("priceSelector", PriceSelector.class);
        CalcImpl calc = applicationContext.getBean("calcImpl", CalcImpl.class);

        try {
            BigDecimal pricePerKg = priceSelector.selectPriceValue(pricePerKgMap, userData.get(0));
            BigDecimal pricePerKm = priceSelector.selectPriceValue(pricePerKmMap, userData.get(1));
            BigDecimal result = calc.handle(pricePerKg, userData.get(0), pricePerKm, userData.get(1));
            System.out.println("Result: " + result);
        } finally {
            userData.clear();
        }

        return new ReadDataState(scanner, pricePerKgMap, pricePerKmMap);
    }

    @Override
    public StatusType getType() {
        return CALC;
    }
}
