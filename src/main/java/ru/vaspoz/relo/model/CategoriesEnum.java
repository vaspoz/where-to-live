package ru.vaspoz.relo.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum CategoriesEnum {

    VALUE_01("Meal, Inexpensive Restaurant", "Restaurants"),
    VALUE_02("Meal for 2 People, Mid-range Restaurant, Three-course", "Restaurants"),
    VALUE_03("McMeal at McDonalds (or Equivalent Combo Meal)", "Restaurants"),
    VALUE_04("Domestic Beer (0.5 liter draught)", "Restaurants"),
    VALUE_05("Imported Beer (0.33 liter draught)", "Restaurants"),
    VALUE_06("Cappuccino (regular)", "Restaurants"),
    VALUE_07("Coke/Pepsi (0.33 liter bottle)", "Restaurants"),
    VALUE_08("Water (0.33 liter bottle)", "Restaurants"),
    VALUE_09("Milk (regular), (1 liter)", "Markets"),
    VALUE_10("Loaf of Fresh White Bread (500g)", "Markets"),
    VALUE_11("Rice (white), (1kg)", "Markets"),
    VALUE_12("Eggs (regular) (12)", "Markets"),
    VALUE_13("Local Cheese (1kg)", "Markets"),
    VALUE_14("Chicken Fillets (1kg)", "Markets"),
    VALUE_15("Beef Round (1kg) (or Equivalent Back Leg Red Meat)", "Markets"),
    VALUE_16("Apples (1kg)", "Markets"),
    VALUE_17("Banana (1kg)", "Markets"),
    VALUE_18("Oranges (1kg)", "Markets"),
    VALUE_19("Tomato (1kg)", "Markets"),
    VALUE_20("Potato (1kg)", "Markets"),
    VALUE_21("Onion (1kg)", "Markets"),
    VALUE_22("Lettuce (1 head)", "Markets"),
    VALUE_23("Water (1.5 liter bottle)", "Markets"),
    VALUE_24("Bottle of Wine (Mid-Range)", "Markets"),
    VALUE_25("Domestic Beer (0.5 liter bottle)", "Markets"),
    VALUE_26("Imported Beer (0.33 liter bottle)", "Markets"),
    VALUE_27("Cigarettes 20 Pack (Marlboro)", "Markets"),
    VALUE_28("One-way Ticket (Local Transport)", "Transportation"),
    VALUE_29("Monthly Pass (Regular Price)", "Transportation"),
    VALUE_30("Taxi Start (Normal Tariff)", "Transportation"),
    VALUE_31("Taxi 1km (Normal Tariff)", "Transportation"),
    VALUE_32("Taxi 1hour Waiting (Normal Tariff)", "Transportation"),
    VALUE_33("Gasoline (1 liter)", "Transportation"),
    VALUE_34("Volkswagen Golf 1.4 90 KW Trendline (Or Equivalent New Car)", "Transportation"),
    VALUE_35("Toyota Corolla Sedan 1.6l 97kW Comfort (Or Equivalent New Car)", "Transportation"),
    VALUE_36("Basic (Electricity, Heating, Cooling, Water, Garbage) for 85m2 Apartment", "Utilities"),
    VALUE_37("1 min. of Prepaid Mobile Tariff Local (No Discounts or Plans)", "Utilities"),
    VALUE_38("Internet (60 Mbps or More, Unlimited Data, Cable/ADSL)", "Utilities"),
    VALUE_39("Fitness Club, Monthly Fee for 1 Adult", "Sports And Leisure"),
    VALUE_40("Tennis Court Rent (1 Hour on Weekend)", "Sports And Leisure"),
    VALUE_41("Cinema, International Release, 1 Seat", "Sports And Leisure"),
    VALUE_42("Preschool (or Kindergarten), Full Day, Private, Monthly for 1 Child", "Childcare"),
    VALUE_43("International Primary School, Yearly for 1 Child", "Childcare"),
    VALUE_44("1 Pair of Jeans (Levis 501 Or Similar)", "Clothing And Shoes"),
    VALUE_45("1 Summer Dress in a Chain Store (Zara, H&M, ...)", "Clothing And Shoes"),
    VALUE_46("1 Pair of Nike Running Shoes (Mid-Range)", "Clothing And Shoes"),
    VALUE_47("1 Pair of Men Leather Business Shoes", "Clothing And Shoes"),
    VALUE_48("Apartment (1 bedroom) in City Centre", "Rent Per Month"),
    VALUE_49("Apartment (1 bedroom) Outside of Centre", "Rent Per Month"),
    VALUE_50("Apartment (3 bedrooms) in City Centre", "Rent Per Month"),
    VALUE_51("Apartment (3 bedrooms) Outside of Centre", "Rent Per Month"),
    VALUE_52("Price per Square Meter to Buy Apartment in City Centre", "Buy Apartment Price"),
    VALUE_53("Price per Square Meter to Buy Apartment Outside of Centre", "Buy Apartment Price"),
    VALUE_54("Average Monthly Net Salary (After Tax)", "Salaries And Financing"),
    VALUE_55("Mortgage Interest Rate in Percentages (%), Yearly, for 20 Years Fixed-Rate", "Salaries And Financing");

    private String description;
    private String groupName;

    CategoriesEnum(String description, String groupName) {
        this.description = description;
        this.groupName = groupName;
    }

    public String getDescription() {
        return this.description;
    }

    public static CategoriesEnum getCategoryByDescription(String description) throws NoSuchFieldException {
        List<CategoriesEnum> catList = Arrays.stream(CategoriesEnum.values()).filter(value -> value.getDescription().equals(description)).collect(Collectors.toList());
        if (catList.size() == 0) {
            throw new NoSuchFieldException("Category or Description (" + description + ") hasn't been found in CategoriesEnum");
        } else {
            return catList.get(0);
        }
    }

    public String getIndexString() {
        return this.name().substring(6);
    }
}
