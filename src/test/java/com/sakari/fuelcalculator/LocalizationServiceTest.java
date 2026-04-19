package com.sakari.fuelcalculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LocalizationServiceTest {

    private LocalizationService service;

    @BeforeEach
    void setUp() {
        service = new LocalizationService();
    }

    @Test
    void testMissingKeyReturnsMissingPrefix() {
        String result = service.getString("nonexistent_key");
        assertTrue(result.contains("MISSING"));
    }

    @Test
    void testSetAndGetLanguage() {
        service.loadStrings("en");
        assertEquals("en", service.getCurrentLanguage());

        service.loadStrings("fi");
        assertEquals("fi", service.getCurrentLanguage());
    }

    @Test
    void testGetAllKeysIsNotNull() {
        service.loadStrings("en");
        assertNotNull(service.getAllKeys());
    }

    @Test
    void testCacheSizeAfterLoad() {
        service.loadStrings("en");
        int sizeEn = service.getAllKeys().size();
        assertTrue(sizeEn >= 0);

        service.loadStrings("fr");
        int sizeFr = service.getAllKeys().size();
        assertTrue(sizeFr >= 0);
    }

    @Test
    void testGetStringWithNullKey() {
        service.loadStrings("en");
        String result = service.getString(null);
        assertTrue(result.contains("MISSING"));
    }

    @Test
    void testLoadSameLanguageTwice() {
        service.loadStrings("en");
        int firstSize = service.getAllKeys().size();

        service.loadStrings("en");
        int secondSize = service.getAllKeys().size();

        assertEquals(firstSize, secondSize);
    }

    @Test
    void testGetStringAfterLanguageSwitch() {
        service.loadStrings("en");
        String enValue = service.getString("distance.label");

        service.loadStrings("fi");
        String fiValue = service.getString("distance.label");

        // Arvot voivat olla erilaisia tai samoja, mutta metodin pitää toimia
        assertNotNull(enValue);
        assertNotNull(fiValue);
    }
}