package ru.vaspoz.relo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vaspoz.relo.external.model.OverallRates;
import ru.vaspoz.relo.model.*;
import ru.vaspoz.relo.repository.CityRatesFullRepository;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class CityRatesFullService {

    @Autowired
    private CityRatesFullRepository cityRatesFullRepository;

    public Double getCityOverallSalary(String country, String city) {
        return getCityOverallValue(country, city, "salary");
    }

    public Double getCityOverallExpenses(String country, String city) {
        return getCityOverallValue(country, city, "expenses");
    }

    private Double getCityOverallValue(String country, String city, String type) {

        int indBegin, indEnd;
        if (type.equals("expenses")) {
            indBegin = 1;
            indEnd = 53;
        } else {
            indBegin = 54;
            indEnd = 54;
        }

        List<CityRateFull> cityRateList = cityRatesFullRepository.findByCountryAndCity(country, city);
        if (cityRateList.size() != 1) {
            return 0.0;
        }

        CityRateFull cityRate = cityRateList.get(0);

        double overalMedianValue = 0.0;
        List<Double> doubles = new ArrayList<>();

        for (int i = indBegin; i <= indEnd; i++) {
            try {
                Method getValue = CityRateFull.class.getDeclaredMethod("getValue_" + (i < 10 ? "0" + i : i));
                Double value = (Double) getValue.invoke(cityRate);
                if (value == null || Double.compare(value, 0.0) == 0) {
                    continue;
                }
                doubles.add(value);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        normalizeValuesList(doubles);
        overalMedianValue = Arrays.stream(doubles.toArray(new Double[]{}))
                .reduce(Double::sum).get() / doubles.size();

        return overalMedianValue;

    }

    private void normalizeValuesList(List<Double> doubleValues) {
        // For more correct calculations, remove the biggest expense and the lowest expense
        Collections.sort(doubleValues);

        if (doubleValues.size() < 3) return;
        doubleValues.remove(0);
        doubleValues.remove(doubleValues.size() - 1);
    }


    public OverallRates getRatesBetweenCities(String baseCountry, String baseCity, String country, String city) {

        CityRateFull baseCityRates = cityRatesFullRepository.findByCountryAndCity(baseCountry, baseCity).get(0);
        CityRateFull comparedCityRates = cityRatesFullRepository.findByCountryAndCity(country, city).get(0);

        List<Double> expensesRelativeValueList = new ArrayList<>();
        Double salaryRelativeValue = 0.0;

        for (int i = 1; i <= 54; i++) {
            try {
                Method getValueMethod = CityRateFull.class.getDeclaredMethod("getValue_" + (i < 10 ? "0" + i : i));

                Double baseValue = (Double) getValueMethod.invoke(baseCityRates);
                Double comparedValue = (Double) getValueMethod.invoke(comparedCityRates);

                if (isNullOrZero(baseValue) || isNullOrZero(comparedValue)) {
                    continue;
                }

                if (i < 54) {
                    expensesRelativeValueList.add(getRelativePercentageValue(baseValue, comparedValue));
                } else {
                    salaryRelativeValue = getRelativePercentageValue(baseValue, comparedValue);
                }
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
                continue;
            }
        }

        Double expensesRelativeValue = Arrays.stream(expensesRelativeValueList.toArray(new Double[]{}))
                .reduce(Double::sum).get() / expensesRelativeValueList.size();

        return new OverallRates(
                make2digits(expensesRelativeValue),
                make2digits(salaryRelativeValue),
                make2digits(Double.sum(salaryRelativeValue, -expensesRelativeValue))
        );


    }

    private double getRelativePercentageValue(Double baseValue, Double comparedValue) {
        return 100 * (comparedValue - baseValue) / baseValue;
    }

    private Double make2digits(Double d) {
        return Math.floor(d * 100) / 100;
    }


    private boolean isNullOrZero(Double value) {
        return value == null || (Double.compare(value, 0.0) == 0);
    }
}