package com.sakari.fuelcalculator;

import java.util.Locale;

public class FuelCalculatorUtils {

    public static double calculateFuel(double distance, double consumption) {
        if (distance < 0 || consumption < 0) {
            throw new IllegalArgumentException("Values must be non-negative");
        }
        return (consumption / 100.0) * distance;
    }

    public static double calculateCost(double fuel, double price) {
        if (fuel < 0 || price < 0) {
            throw new IllegalArgumentException("Values must be non-negative");
        }
        return fuel * price;
    }

    public static double averageFuelConsumption(double[] trips) {
        if (trips == null || trips.length == 0) return 0.0;
        double sum = 0.0;
        for (double trip : trips) sum += trip;
        return sum / trips.length;
    }

    public static double convertPricePerLiterToGallon(double pricePerLiter) {
        return pricePerLiter * 3.78541;
    }

    public static double convertDistanceKmToMiles(double km) {
        return km * 0.621371;
    }

    public static double convertConsumptionLper100kmToMpg(double lPer100km) {
        if (lPer100km <= 0) return 0.0;
        return 235.214583 / lPer100km;
    }

    public static boolean isFuelEfficient(double consumption) {
        return consumption < 6.0;
    }

    public static String getEfficiencyRating(double consumption) {
        if (consumption < 4.0) return "Excellent";
        else if (consumption < 6.0) return "Good";
        else if (consumption < 8.0) return "Average";
        else if (consumption < 10.0) return "Poor";
        else return "Very Poor";
    }

    public static double roundToTwoDecimals(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    public static double calculateTripCost(double distance, double consumption, double price) {
        return calculateCost(calculateFuel(distance, consumption), price);
    }

    public static double findMax(double[] values) {
        if (values == null || values.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }
        double max = values[0];
        for (double v : values) if (v > max) max = v;
        return max;
    }

    public static double findMin(double[] values) {
        if (values == null || values.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }
        double min = values[0];
        for (double v : values) if (v < min) min = v;
        return min;
    }

    public static double sum(double[] values) {
        if (values == null) return 0.0;
        double sum = 0.0;
        for (double v : values) sum += v;
        return sum;
    }

    public static String formatCurrency(double amount, String currency) {
        if (currency == null) currency = "EUR";
        return String.format(Locale.US, "%.2f %s", amount, currency);
    }

    public static String formatDistance(double km) {
        return String.format(Locale.US, "%.1f km", km);
    }

    public static double applyDiscount(double price, int discountPercent) {
        if (discountPercent < 0 || discountPercent > 100) {
            throw new IllegalArgumentException("Discount must be between 0 and 100");
        }
        return price * (1 - discountPercent / 100.0);
    }

    public static double calculateTax(double amount, double taxRate) {
        return amount * (taxRate / 100.0);
    }

    public static String safeToString(Object obj) {
        return obj != null ? obj.toString() : "null";
    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    public static double gallonsToLiters(double gallons) {
        return gallons * 3.78541;
    }

    public static double milesToKm(double miles) {
        return miles / 0.621371;
    }

    public static double mpgToLper100km(double mpg) {
        if (mpg <= 0) return 0.0;
        return 235.214583 / mpg;
    }

    public static int calculateTripCount(double totalDistance, double averageTripLength) {
        if (totalDistance < 0 || averageTripLength <= 0) return 0;
        return (int) Math.ceil(totalDistance / averageTripLength);
    }

    public static boolean isLongTrip(double distance) {
        return distance > 500.0;
    }

    public static String getTripCategory(double distance) {
        if (distance < 50) return "Short";
        else if (distance < 200) return "Medium";
        else if (distance < 500) return "Long";
        else return "Very Long";
    }

    // ---------- UUDET METODIT (viimeiset 5 %) ----------
    public static double litersPer100kmToKml(double lPer100km) {
        if (lPer100km <= 0) return 0.0;
        return 100.0 / lPer100km;
    }

    public static double kmlToLitersPer100km(double kml) {
        if (kml <= 0) return 0.0;
        return 100.0 / kml;
    }

    public static double calculateFuelCostForTrip(double distance, double consumption, double price) {
        return calculateTripCost(distance, consumption, price);
    }

    public static String createTripSummary(double distance, double consumption, double price) {
        double fuel = calculateFuel(distance, consumption);
        double cost = calculateCost(fuel, price);
        return String.format(Locale.US, "Trip: %.1f km, Fuel: %.2f L, Cost: %.2f EUR", distance, fuel, cost);
    }

    public static boolean isValidFuelConsumption(double consumption) {
        return consumption > 0 && consumption < 100;
    }

    public static boolean isValidDistance(double distance) {
        return distance >= 0;
    }

    public static boolean isValidPrice(double price) {
        return price > 0;
    }
}