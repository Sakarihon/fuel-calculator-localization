package com.sakari.fuelcalculator;

public class FuelCalculatorLogic {

    public static double calculateFuel(double distance, double consumption) {
        return (consumption / 100.0) * distance;
    }

    public static double calculateCost(double fuel, double price) {
        return fuel * price;
    }

    public static double[] calculateBoth(double distance, double consumption, double price) {
        double fuel = calculateFuel(distance, consumption);
        double cost = calculateCost(fuel, price);
        return new double[]{fuel, cost};
    }
}