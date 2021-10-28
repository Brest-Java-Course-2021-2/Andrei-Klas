package com.epam.brest;

import com.epam.brest.files.JSONFileReader;
import com.epam.brest.calc.CalcImpl;
import com.epam.brest.model.ReadDataState;
import com.epam.brest.model.Status;
import com.epam.brest.model.StatusType;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        BigDecimal weight, length;

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-config.xml");
        JSONFileReader JSONfReader = (JSONFileReader) applicationContext.getBean("fileReader");

        Map<Integer, BigDecimal> pricePerKgMap = JSONfReader.readData("/PriceForKg.json");
        Map<Integer, BigDecimal> pricePerKmMap = JSONfReader.readData("/PriceForKm.json");

        try (Scanner scanner = new Scanner(System.in)) {
            Status currentStatus = new ReadDataState(scanner, pricePerKgMap, pricePerKmMap);
            while (currentStatus.getType() != StatusType.EXIT) {
                System.out.println("Current system status: " + currentStatus.getType());
                currentStatus = currentStatus.handle();
            }
        }
    }

}
