package com.practice.covid.model;

import java.util.List;

public class TSDeaths {
    private String province;
    private String country;
    private String lat;
    private String lng;
    private List<Integer> counts;

    public TSDeaths(String province, String country, String lat, String lng, List<Integer> counts) {
        this.province = province;
        this.country = country;
        this.lat = lat;
        this.lng = lng;
        this.counts = counts;
    }

    public List<Integer> getCounts() {
        return counts;
    }

    public void setCounts(List<Integer> counts) {
        this.counts = counts;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    @Override
    public String toString() {
        return "TSDeaths{" +
                "province='" + province + '\'' +
                ", country='" + country + '\'' +
                ", lat='" + lat + '\'' +
                ", lng='" + lng + '\'' +
                ", counts=" + counts +
                '}';
    }
}
