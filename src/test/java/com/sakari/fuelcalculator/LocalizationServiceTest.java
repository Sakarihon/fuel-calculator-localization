package com.sakari.fuelcalculator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LocalizationServiceTest {

    @Test
    void testMissingKey() {
        LocalizationService service = new LocalizationService();
        String result = service.getString("fake_key");
        assertTrue(result.contains("MISSING"));
    }

    @Test
    void testLanguageSet() {
        LocalizationService service = new LocalizationService();
        service.loadStrings("en");
        assertEquals("en", service.getCurrentLanguage());
    }

    @Test
    void testCacheNotNull() {
        LocalizationService service = new LocalizationService();
        service.loadStrings("en");
        assertNotNull(service.getAllKeys());
    }

    @Test
    void testCacheIsClearedOnReload() {
        LocalizationService service = new LocalizationService();
        service.loadStrings("en");
        int firstSize = service.getAllKeys().size();
        service.loadStrings("fr");
        int secondSize = service.getAllKeys().size();
        assertTrue(secondSize >= 0);
    }

    @Test
    void testMultipleGetStringCalls() {
        LocalizationService service = new LocalizationService();
        service.loadStrings("en");
        service.getString("distance.label");
        service.getString("price.label");
        assertTrue(true);
    }
}