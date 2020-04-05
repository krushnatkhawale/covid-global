package com.practice.covid.util;

import com.practice.covid.model.TSDeaths;

import java.util.HashMap;
import java.util.Map;

public class Utils {

    public static TSDeaths lineToPojo(String[] headers, String[] tokens) {

        String province = tokens[0];
        String country = tokens[1];
        String lat = tokens[2];
        String lng = tokens[3];
        Map<String, Integer> counts = new HashMap<>(tokens.length - 4);

        for (int i = 4; i < tokens.length; i++) {
            final int intCount = Integer.parseInt(tokens[i]);
            counts.put(headers[i], intCount);
        }

        return new TSDeaths(province, country, lat, lng, counts);
    }
}