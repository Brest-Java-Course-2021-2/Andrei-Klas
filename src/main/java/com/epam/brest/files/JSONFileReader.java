package com.epam.brest.files;

import org.codehaus.jackson.map.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeMap;

public class JSONFileReader implements FileReader {
    private FilePath filePath;

    @Override
    public Map<Integer, BigDecimal> readData(PriceType priceType) throws IOException {
        switch (priceType){
            case LENGTH: return prReadData(filePath.getLengthFilePath());
            case WEIGHT: return prReadData(filePath.getWeightFilePath());
        }
        throw new IOException("Some problem with enum PriceType");
    }

    public Map<Integer, BigDecimal> prReadData(String filePath) throws IOException {
        Map<Integer, BigDecimal> resultMap = new TreeMap<>();
        Map<String, BigDecimal> tempMap = new TreeMap<>();
        InputStream inputStream = getClass().getResourceAsStream(filePath);
        ObjectMapper mapper = new ObjectMapper();

        try (InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {

            // convert JSON to Map
            tempMap = mapper.readValue(bufferedReader, Map.class);

            //System.out.println(tempMap);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Map.Entry<String, BigDecimal> entry : tempMap.entrySet()) {
            Integer key = Integer.valueOf(entry.getKey());
            BigDecimal value =  new BigDecimal(String.valueOf(entry.getValue()));
            resultMap.put(key, value);
        }
        //System.out.println(resultMap);
        return resultMap;
    }

    public FilePath getFilePath() {
        return filePath;
    }

    public void setFilePath(FilePath filePath) {
        this.filePath = filePath;
    }
}
