package ru.vaspoz.relo.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vaspoz.relo.exceptions.CountryNotFoundException;
import ru.vaspoz.relo.model.*;
import ru.vaspoz.relo.repository.CitiesRepository;
import ru.vaspoz.relo.repository.CityRatesRepository;
import ru.vaspoz.relo.repository.CountriesRepository;
import ru.vaspoz.relo.repository.CountryRatesRepository;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CityRatesService {

    @Autowired
    private CityRatesRepository cityRatesRepository;

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

        List<CityRateFull> cityRateList = cityRatesRepository.findByCountryAndCity(country, city);
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


}
