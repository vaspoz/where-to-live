package ru.vaspoz.relo.model;

import java.util.List;
import java.util.Objects;

public class CountryRateResponseGET {
    private String country;
    private List<CityRate> cityRates;

    public CountryRateResponseGET() {
    }

    public CountryRateResponseGET(String country, List<CityRate> cityRates) {
        this.country = country;
        this.cityRates = cityRates;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<CityRate> getCityRates() {
        return cityRates;
    }

    public void setCityRates(List<CityRate> cityRates) {
        this.cityRates = cityRates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountryRateResponseGET that = (CountryRateResponseGET) o;
        return Objects.equals(country, that.country) &&
                Objects.equals(cityRates, that.cityRates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, cityRates);
    }

    @Override
    public String toString() {
        return "CountryRateResponseGET{" +
                "country='" + country + '\'' +
                ", cityRates=" + cityRates +
                '}';
    }
}
