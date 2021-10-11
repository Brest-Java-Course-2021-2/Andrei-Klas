package com.epam.brest.calc;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.math.BigDecimal;


public class Price {
    public BigDecimal getPriceDependsOnValue(PricePerSmthEnum typeOfPrice, BigDecimal value) throws Exception {
        BigDecimal result = null;
        JSONParser parser = new JSONParser();

        String jsonFilePath = "/home/user/dev/Andrei-Klas/src/main/resources/";
        switch (typeOfPrice){
            case WEIGHT: jsonFilePath += "PricesForKg.json"; break;
            case LENGTH: jsonFilePath += "PricesForKm.json"; break;
        }

        String typeOfValue;
        if (value.compareTo(new BigDecimal(10)) < 0)
            typeOfValue = "First";
        else
            if (value.compareTo(new BigDecimal(20)) < 0)
                typeOfValue = "Second";
        else
            typeOfValue = "Max";

        try {
            Object obj = parser.parse(new FileReader(jsonFilePath));
            JSONObject jsonObject = (JSONObject) obj;

            result = new BigDecimal(( jsonObject.get(typeOfValue)).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (result.equals(null))
                throw  new Exception("NULL value of" + typeOfPrice.toString() + " price");

        return result;
    }
}
