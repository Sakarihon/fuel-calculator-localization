package com.sakari.fuelcalculator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import static org.junit.jupiter.api.Assertions.*;

class FuelCalculatorUtilsTest {

    @Test
    void calculateFuel_Valid() {
        assertEquals(5.0, FuelCalculatorUtils.calculateFuel(100, 5), 0.001);
    }

    @Test
    void calculateFuel_NegativeThrows() {
        assertThrows(IllegalArgumentException.class, () -> FuelCalculatorUtils.calculateFuel(-1, 5));
    }

    @Test
    void calculateCost_Valid() {
        assertEquals(10.0, FuelCalculatorUtils.calculateCost(5, 2), 0.001);
    }

    @Test
    void calculateCost_NegativeThrows() {
        assertThrows(IllegalArgumentException.class, () -> FuelCalculatorUtils.calculateCost(5, -1));
    }

    @Test
    void averageFuelConsumption_Normal() {
        double[] trips = {5.0, 6.0, 7.0};
        assertEquals(6.0, FuelCalculatorUtils.averageFuelConsumption(trips), 0.001);
    }

    @Test
    void averageFuelConsumption_EmptyOrNull() {
        assertEquals(0.0, FuelCalculatorUtils.averageFuelConsumption(null));
        assertEquals(0.0, FuelCalculatorUtils.averageFuelConsumption(new double[0]));
    }

    @Test
    void convertPricePerLiterToGallon() {
        assertEquals(1.0 * 3.78541, FuelCalculatorUtils.convertPricePerLiterToGallon(1.0), 0.001);
    }

    @Test
    void convertDistanceKmToMiles() {
        assertEquals(0.621371, FuelCalculatorUtils.convertDistanceKmToMiles(1.0), 0.001);
    }

    @ParameterizedTest
    @CsvSource({"5.0,47.04", "10.0,23.52", "0,0"})
    void convertConsumptionLper100kmToMpg(double l, double expected) {
        assertEquals(expected, FuelCalculatorUtils.convertConsumptionLper100kmToMpg(l), 0.01);
    }

    @Test
    void isFuelEfficient() {
        assertTrue(FuelCalculatorUtils.isFuelEfficient(5.0));
        assertFalse(FuelCalculatorUtils.isFuelEfficient(7.0));
    }

    @ParameterizedTest
    @CsvSource({"3.0,Excellent", "5.0,Good", "7.0,Average", "9.0,Poor", "12.0,Very Poor"})
    void getEfficiencyRating(double cons, String expected) {
        assertEquals(expected, FuelCalculatorUtils.getEfficiencyRating(cons));
    }

    @Test
    void roundToTwoDecimals() {
        assertEquals(3.14, FuelCalculatorUtils.roundToTwoDecimals(3.14159), 0.001);
    }

    @Test
    void calculateTripCost() {
        assertEquals(10.0, FuelCalculatorUtils.calculateTripCost(100, 5, 2), 0.001);
    }

    @Test
    void findMax() {
        double[] vals = {1.0, 5.0, 3.0};
        assertEquals(5.0, FuelCalculatorUtils.findMax(vals));
        assertThrows(IllegalArgumentException.class, () -> FuelCalculatorUtils.findMax(null));
        assertThrows(IllegalArgumentException.class, () -> FuelCalculatorUtils.findMax(new double[0]));
    }

    @Test
    void findMin() {
        double[] vals = {3.0, 1.0, 5.0};
        assertEquals(1.0, FuelCalculatorUtils.findMin(vals));
    }

    @Test
    void sum() {
        double[] vals = {1.0, 2.0, 3.0};
        assertEquals(6.0, FuelCalculatorUtils.sum(vals));
        assertEquals(0.0, FuelCalculatorUtils.sum(null));
    }

    @Test
    void formatCurrency() {
        assertEquals("10.50 EUR", FuelCalculatorUtils.formatCurrency(10.5, "EUR"));
        assertEquals("10.50 USD", FuelCalculatorUtils.formatCurrency(10.5, "USD"));
        assertEquals("10.50 EUR", FuelCalculatorUtils.formatCurrency(10.5, null));
    }

    @Test
    void formatDistance() {
        assertEquals("123.5 km", FuelCalculatorUtils.formatDistance(123.5));
    }

    @Test
    void applyDiscount() {
        assertEquals(90.0, FuelCalculatorUtils.applyDiscount(100.0, 10), 0.001);
        assertThrows(IllegalArgumentException.class, () -> FuelCalculatorUtils.applyDiscount(100, -5));
        assertThrows(IllegalArgumentException.class, () -> FuelCalculatorUtils.applyDiscount(100, 101));
    }

    @Test
    void calculateTax() {
        assertEquals(24.0, FuelCalculatorUtils.calculateTax(100.0, 24.0), 0.001);
    }

    @Test
    void safeToString() {
        assertEquals("hello", FuelCalculatorUtils.safeToString("hello"));
        assertEquals("null", FuelCalculatorUtils.safeToString(null));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void isNullOrEmpty(String input) {
        assertTrue(FuelCalculatorUtils.isNullOrEmpty(input));
    }

    @Test
    void isNullOrEmpty_NonEmpty() {
        assertFalse(FuelCalculatorUtils.isNullOrEmpty("hello"));
    }

    @Test
    void isNullOrEmpty_Whitespace() {
        assertTrue(FuelCalculatorUtils.isNullOrEmpty("   "));
    }

    @Test
    void gallonsToLiters() {
        assertEquals(3.78541, FuelCalculatorUtils.gallonsToLiters(1.0), 0.001);
    }

    @Test
    void milesToKm() {
        assertEquals(1.60934, FuelCalculatorUtils.milesToKm(1.0), 0.01);
    }

    @ParameterizedTest
    @CsvSource({"47.04,5.0", "23.52,10.0", "0,0"})
    void mpgToLper100km(double mpg, double expected) {
        assertEquals(expected, FuelCalculatorUtils.mpgToLper100km(mpg), 0.01);
    }

    @ParameterizedTest
    @CsvSource({"1000,200,5", "500,100,5", "0,10,0", "100,0,0"})
    void calculateTripCount(double total, double avg, int expected) {
        assertEquals(expected, FuelCalculatorUtils.calculateTripCount(total, avg));
    }

    @Test
    void isLongTrip() {
        assertTrue(FuelCalculatorUtils.isLongTrip(600));
        assertFalse(FuelCalculatorUtils.isLongTrip(400));
    }

    @ParameterizedTest
    @CsvSource({"30,Short", "120,Medium", "300,Long", "600,Very Long"})
    void getTripCategory(double distance, String expected) {
        assertEquals(expected, FuelCalculatorUtils.getTripCategory(distance));
    }

    // ---------- UUSIEN METODIEN TESTIT ----------
    @ParameterizedTest
    @CsvSource({"5.0,20.0", "10.0,10.0", "0,0"})
    void litersPer100kmToKml(double l, double expected) {
        assertEquals(expected, FuelCalculatorUtils.litersPer100kmToKml(l), 0.01);
    }

    @ParameterizedTest
    @CsvSource({"20.0,5.0", "10.0,10.0", "0,0"})
    void kmlToLitersPer100km(double kml, double expected) {
        assertEquals(expected, FuelCalculatorUtils.kmlToLitersPer100km(kml), 0.01);
    }

    @Test
    void calculateFuelCostForTrip() {
        assertEquals(10.0, FuelCalculatorUtils.calculateFuelCostForTrip(100, 5, 2), 0.001);
    }

    @Test
    void createTripSummary() {
        String summary = FuelCalculatorUtils.createTripSummary(100, 5, 2);
        assertTrue(summary.contains("Trip: 100.0 km"));
        assertTrue(summary.contains("Fuel: 5.00 L"));
        assertTrue(summary.contains("Cost: 10.00 EUR"));
    }

    @Test
    void isValidFuelConsumption() {
        assertTrue(FuelCalculatorUtils.isValidFuelConsumption(5.0));
        assertFalse(FuelCalculatorUtils.isValidFuelConsumption(0));
        assertFalse(FuelCalculatorUtils.isValidFuelConsumption(150));
        assertFalse(FuelCalculatorUtils.isValidFuelConsumption(-5));
    }

    @Test
    void isValidDistance() {
        assertTrue(FuelCalculatorUtils.isValidDistance(100));
        assertTrue(FuelCalculatorUtils.isValidDistance(0));
        assertFalse(FuelCalculatorUtils.isValidDistance(-10));
    }

    @Test
    void isValidPrice() {
        assertTrue(FuelCalculatorUtils.isValidPrice(2.0));
        assertFalse(FuelCalculatorUtils.isValidPrice(0));
        assertFalse(FuelCalculatorUtils.isValidPrice(-1));
    }
    @ParameterizedTest
    @CsvSource({"3.0,Low", "6.0,Medium", "9.0,High"})
    void getFuelGrade(double cons, String expected) {
        assertEquals(expected, FuelCalculatorUtils.getFuelGrade(cons));
    }

    @Test
    void calculateCO2Emissions() {
        assertEquals(23.1, FuelCalculatorUtils.calculateCO2Emissions(10.0), 0.01);
        assertEquals(0.0, FuelCalculatorUtils.calculateCO2Emissions(0.0), 0.01);
    }
}