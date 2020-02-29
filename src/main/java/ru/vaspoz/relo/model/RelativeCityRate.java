package ru.vaspoz.relo.model;

import java.util.Objects;

public class RelativeCityRate {
    private String city;
    private Double expenses;
    private Double salary;
    private Double overall;

    public RelativeCityRate() {
    }

    public RelativeCityRate(String city, Double expenses, Double salary, Double overall) {
        this.city = city;
        this.expenses = expenses;
        this.salary = salary;
        this.overall = overall;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Double getExpenses() {
        return expenses;
    }

    public void setExpenses(Double expenses) {
        this.expenses = expenses;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Double getOverall() {
        return overall;
    }

    public void setOverall(Double overall) {
        this.overall = overall;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RelativeCityRate relativeCityRate = (RelativeCityRate) o;
        return Objects.equals(city, relativeCityRate.city) &&
                Objects.equals(expenses, relativeCityRate.expenses) &&
                Objects.equals(salary, relativeCityRate.salary) &&
                Objects.equals(overall, relativeCityRate.overall);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, expenses, salary, overall);
    }

    @Override
    public String toString() {
        return "RelativeCityRate{" +
                "city='" + city + '\'' +
                ",\texpenses=" + expenses +
                ",\tsalary=" + salary +
                ",\toverall=" + overall +
                '}';
    }
}
