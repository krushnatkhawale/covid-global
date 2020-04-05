package com.practice.covid.util;

import com.practice.covid.model.TSDeaths;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static TSDeaths lineToPojo(String[] tokens) {

        String province = tokens[0];
        String country = tokens[1];
        String lat = tokens[2];
        String lng = tokens[3];
        List<Integer> counts = new ArrayList<>(tokens.length - 4);

        for (int i = 4; i < tokens.length; i++) {
            int intValue = Integer.parseInt(tokens[i]);
            counts.add(intValue);
        }

        return new TSDeaths(province, country, lat, lng, counts);
    }
}
