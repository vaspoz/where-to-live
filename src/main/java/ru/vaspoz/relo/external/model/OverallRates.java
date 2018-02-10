package ru.vaspoz.relo.external.model;

public class OverallRates {

    private Double expenses;
    private Double salary;
    private Double overall;

    public OverallRates(Double expenses, Double salary, Double overall) {
        this.expenses = expenses;
        this.salary = salary;
        this.overall = overall;
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
}
