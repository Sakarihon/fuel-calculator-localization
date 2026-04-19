package com.sakari.fuelcalculator;

import org.junit.jupiter.api.Test;
import java.io.File;
import static org.junit.jupiter.api.Assertions.*;

class DatabaseConnectionFailureTest {

    @Test
    void staticInitialization_MissingProperties_ThrowsRuntimeException() {
        File propsFile = new File("src/main/resources/database.properties");
        File backup = new File("src/main/resources/database.properties.backup");

        assertTrue(propsFile.renameTo(backup));

        try {

        } finally {
            backup.renameTo(propsFile);
        }
    }
}