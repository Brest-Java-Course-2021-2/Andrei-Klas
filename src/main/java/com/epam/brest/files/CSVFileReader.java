package com.epam.brest.files;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeMap;

public class CSVFileReader implements FileReader {

    @Override
    public Map<Integer, BigDecimal> readData(PriceType priceType) throws IOException {
        Map<Integer, BigDecimal> resultMap = new TreeMap<>();
        String filePath = "some value, It doesnt works";
        InputStream inputStream = getClass().getResourceAsStream("/" + filePath.toString()); //it doesn't work for, I just testing injection from prop file to JSONFileReader

        try (InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
            String line;
            String[] values;
            while ((line = bufferedReader.readLine()) != null) {
                values = line.split(",");
                resultMap.put(Integer.valueOf(values[0]), new BigDecimal(values[1]));
            }
        }
        return resultMap;
    }
}
