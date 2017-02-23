package ru.vaspoz.relo.numbeo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.vaspoz.relo.exceptions.ParsingDoublesException;
import ru.vaspoz.relo.numbeo.model.OverallRates;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NumbeoAPI {

    private NumbeoAPI() {

    }

    public static NumbeoAPI getAPI() {
        return new NumbeoAPI();
    }

    public OverallRates getRatesBetweenCities(String baseCountry,
                                              String baseCity,
                                              String compareToCountry,
                                              String compareToCity) throws ParsingDoublesException, IOException {
        Document doc = Jsoup.connect(getLink(baseCountry, baseCity, compareToCountry, compareToCity)).get();

        String salaryDifString = doc.select("tr:contains(Average Monthly Disposable Salary)>td")
                .last()         //returns columns. Percentage locates in last column
                .children()     //returns list of tags in last column
                .last()         //desired tag
                .childNode(0).toString().replace("%", "");      //get innerText of view " + 24.5% ". And make it = " + 24.5% "

        // Get all percentages except salary. It will produce array of expenses
        Elements columnElements = doc.select("tr:not(:contains(Average Monthly Disposable Salary))>td>span");
        Stream<String> expensesDifStream = Arrays.stream(columnElements.toArray())
                .map(elt -> ((Element) elt).text())
                .filter(elt -> elt.contains("%"))
                .map(elt -> elt.replace("%", "").replace(" ", "").replace(",", ""));

        Difference bothDifference = parseDoublesOrThrowException(salaryDifString, expensesDifStream);
        if (bothDifference.getExpences().size() == 0) throw
                new ParsingDoublesException("Exception occurred during Double.parse() values: " +
                        "salaryDif " + salaryDifString + " and " +
                        "expensesDifList " + bothDifference.getExpences());

        normalizeExpencesList(bothDifference.getExpences());

        // Calculate median value
        Double expenses = Arrays.stream(bothDifference.getExpences().toArray())
                .map(elt -> (Double) elt)
                .reduce((elA, elB) -> elA + elB)
                .get() / bothDifference.getExpences().size();

        return new OverallRates(
                make2digits(expenses),
                make2digits(bothDifference.getSalary()),
                0.0);  // todo: Defer overall calculations
    }

    private Double make2digits(Double d) {
        return Math.floor(d * 100) / 100;
    }

    private void normalizeExpencesList(List<Double> expences) {
        // For more correct calculations, remove the biggest expenses and the lowest expenses
        if (expences.size() < 3) return;
        expences.remove(0);
        expences.remove(expences.size() - 1);
    }

    private Difference parseDoublesOrThrowException(String salaryDifString, Stream<String> expensesDifStream) throws ParsingDoublesException {
        Double salaryDif = null;
        List<Double> expensesDifList = null;
        try {
            salaryDif = Double.parseDouble(salaryDifString);
            expensesDifList = expensesDifStream
                    .map(Double::parseDouble)
                    .sorted()
                    .collect(Collectors.toList());
        } catch (NumberFormatException nfe) {
            throw new ParsingDoublesException("Exception occurred during Double.parse() values: " +
                    "salaryDif " + salaryDifString + " and " +
                    "expensesDifList " + expensesDifList);
        }
        return new Difference(salaryDif, expensesDifList);
    }

    private String getLink(String baseCountry, String baseCity, String compareToCountry, String compareToCity) {
        return "https://www.numbeo.com/cost-of-living/compare_cities.jsp?" +
                "country1=" + baseCountry.replaceAll(" ", "+") +
                "&country2=" + compareToCountry.replaceAll(" ", "+") +
                "&city1=" + baseCity.replaceAll(" ", "+") +
                "&city2=" + compareToCity.replaceAll(" ", "+") +
                "&tracking=getDispatchComparison";
    }

    private class Difference {
        private Double salary;
        private List<Double> expences;

        public Difference(Double salary, List<Double> expences) {
            this.salary = salary;
            this.expences = expences;
        }

        public Double getSalary() {
            return salary;
        }

        public void setSalary(Double salary) {
            this.salary = salary;
        }

        public List<Double> getExpences() {
            return expences;
        }

        public void setExpences(List<Double> expences) {
            this.expences = expences;
        }
    }

    public static void main(String[] args) {
        String s = "Philippines\n" +
                "51\tAustralia\n" +
                "52\tVietnam\n" +
                "53\tRussia\n" +
                "54\tFrance\n" +
                "55\tGermany\n" +
                "56\tIsrael\n" +
                "57\tSweden\n" +
                "58\tItaly\n" +
                "59\tNetherlands\n" +
                "60\tGreece\n" +
                "61\tSpain\n" +
                "62\tAustria\n" +
                "63\tUnited Kingdom\n" +
                "64\tBelgium\n" +
                "65\tUnited Arab Emirates\n" +
                "66\tPortugal\n" +
                "67\tSaudi Arabia\n" +
                "68\tDenmark\n" +
                "69\tNorway\n" +
                "70\tMexico\n" +
                "71\tCanada\n" +
                "72\tCyprus\n" +
                "73\tCzech Republic\n" +
                "74\tSwitzerland\n" +
                "75\tFinland\n" +
                "76\tBulgaria\n" +
                "77\tPoland\n" +
                "78\tIreland\n" +
                "79\tNew Zealand\n" +
                "80\tSingapore\n" +
                "81\tBahamas\n" +
                "82\tMorocco\n" +
                "83\tArgentina\n" +
                "84\tMalta\n";
        System.out.println(Arrays.stream(s.split("\n"))
                .map(token -> {
                    String[] sp = token.split("\t");
                    if (sp.length == 2) {
                        return sp[1];
                    } else {
                        return sp[0];
                    }
                })
                .reduce((a, b) -> a + "," + b).toString());

    }
}
