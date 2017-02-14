package ru.vaspoz.relo.numbeo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.vaspoz.relo.numbeo.model.OverallRates;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class NumbeoAPI {

    private NumbeoAPI() {

    }

    public static NumbeoAPI getAPI() {
        return new NumbeoAPI();
    }

    public OverallRates getRatesBetweenCities(String baseCountry,
                                              String baseCity,
                                              String compareToCountry,
                                              String compareToCity) {
        Document doc;
        try{
            doc = Jsoup.connect(getLink(baseCountry, baseCity, compareToCountry, compareToCity)).get();
            String salaryDif = doc.select("tr:contains(Average Monthly Disposable Salary)>td")
                    .last()         //returns columns. Percentage locates in last column
                    .children()     //returns list of tags in last column
                    .last()         //desired tag
                    .childNode(0).toString().replace("%", "");      //get innerText of view " + 24.5% ". And make it = " + 24.5% "

            // Get all percentages except salary. It will produce array of expenses
            Elements columnElements = doc.select("tr:not(:contains(Average Monthly Disposable Salary))>td>span");
            List<String> expensesDif = Arrays.stream(columnElements.toArray())
                    .map(elt -> ((Element)elt).text())
                    .filter(elt -> elt.contains("%"))
                    .map(elt -> elt.replace("%", "")).collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private String getLink(String baseCountry, String baseCity, String compareToCountry, String compareToCity) {
        return "https://www.numbeo.com/cost-of-living/compare_cities.jsp?" +
                "country1=" + baseCountry.replaceAll(" ", "+") +
                "&country2=" + compareToCountry.replaceAll(" ", "+") +
                "&city1=" + baseCity.replaceAll(" ", "+") +
                "&city2=" + compareToCity.replaceAll(" ", "+") +
                "&tracking=getDispatchComparison";
    }
}
