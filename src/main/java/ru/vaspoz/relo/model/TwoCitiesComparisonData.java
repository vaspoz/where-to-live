package ru.vaspoz.relo.model;

public class TwoCitiesComparisonData {

    private Integer id;
    private String baseCountry;
    private String baseCity;
    private String comparedWithCountry;
    private String comparedWithCity;
    private Double expenses;
    private Double salary;
    private Double overall;

    public TwoCitiesComparisonData() {
    }

    public TwoCitiesComparisonData(String baseCountry, String baseCity, String comparedWithCountry, String comparedWithCity, Double expenses, Double salary, Double overall) {
        this.baseCountry = baseCountry;
        this.baseCity = baseCity;
        this.comparedWithCountry = comparedWithCountry;
        this.comparedWithCity = comparedWithCity;
        this.expenses = expenses;
        this.salary = salary;
        this.overall = overall;
    }

    public String getBaseCountry() {
        return baseCountry;
    }

    public void setBaseCountry(String baseCountry) {
        this.baseCountry = baseCountry;
    }

    public String getBaseCity() {
        return baseCity;
    }

    public void setBaseCity(String baseCity) {
        this.baseCity = baseCity;
    }

    public String getComparedWithCountry() {
        return comparedWithCountry;
    }

    public void setComparedWithCountry(String comparedWithCountry) {
        this.comparedWithCountry = comparedWithCountry;
    }

    public String getComparedWithCity() {
        return comparedWithCity;
    }

    public void setComparedWithCity(String comparedWithCity) {
        this.comparedWithCity = comparedWithCity;
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
    public String toString() {
        return "TwoCitiesComparisonData{" +
                "id=" + id +
                ", baseCountry='" + baseCountry + '\'' +
                ", baseCity='" + baseCity + '\'' +
                ", comparedWithCountry='" + comparedWithCountry + '\'' +
                ", comparedWithCity='" + comparedWithCity + '\'' +
                ", expenses=" + expenses +
                ", salary=" + salary +
                ", overall=" + overall +
                '}';
    }
}
