package ru.vaspoz.relo.model;

import java.util.List;
import java.util.Objects;

public class CountryRateResponseGET {
    private String country;
    private List<RelativeCityRate> relativeCityRates;

    public CountryRateResponseGET() {
    }

    public CountryRateResponseGET(String country, List<RelativeCityRate> relativeCityRates) {
        this.country = country;
        this.relativeCityRates = relativeCityRates;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void addRelativeCityRate(RelativeCityRate relativeCityRate) {
        this.relativeCityRates.add(relativeCityRate);
    }

    public List<RelativeCityRate> getRelativeCityRates() {
        return this.relativeCityRates;
    }

    public void setRelativeCityRates(List<RelativeCityRate> relativeCityRates) {
        this.relativeCityRates = relativeCityRates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountryRateResponseGET that = (CountryRateResponseGET) o;
        return Objects.equals(country, that.country) &&
                Objects.equals(relativeCityRates, that.relativeCityRates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, relativeCityRates);
    }

    @Override
    public String toString() {
        return "CountryRateResponseGET{" +
                "country='" + country + '\'' +
                ", relativeCityRates=" + relativeCityRates +
                '}';
    }
}
