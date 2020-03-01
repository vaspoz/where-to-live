package ru.vaspoz.relo.model;

import java.util.List;
import java.util.Objects;

public class CountryRateResponseGET {
    private String country;
    private List<RelativeCityRate> relativeCityRateList;

    public CountryRateResponseGET() {
    }

    public CountryRateResponseGET(String country, List<RelativeCityRate> relativeCityRateList) {
        this.country = country;
        this.relativeCityRateList = relativeCityRateList;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void addRelativeCityRate(RelativeCityRate relativeCityRate) {
        this.relativeCityRateList.add(relativeCityRate);
    }

    public List<RelativeCityRate> getRelativeCityRateList() {
        return this.relativeCityRateList;
    }

    public void setRelativeCityRateList(List<RelativeCityRate> relativeCityRateList) {
        this.relativeCityRateList = relativeCityRateList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountryRateResponseGET that = (CountryRateResponseGET) o;
        return Objects.equals(country, that.country) &&
                Objects.equals(relativeCityRateList, that.relativeCityRateList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, relativeCityRateList);
    }

    @Override
    public String toString() {
        return "CountryRateResponseGET{" +
                "country='" + country + '\'' +
                ", relativeCityRateList=" + relativeCityRateList +
                '}';
    }
}
