package com.sakari.fuelcalculator;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.util.Locale;

public class Controller {

    @FXML private Label lblDistance, lblConsumption, lblPrice, lblResult;
    @FXML private TextField txtDistance, txtConsumption, txtPrice;
    @FXML private Button btnCalculate;

    private final LocalizationService localizationService = new LocalizationService();
    private final CalculationService calculationService = new CalculationService();

    private String currentLanguage = "en";


    @FXML
    public void initialize() {
        setLanguage("en");
    }

    private void setLanguage(String lang) {
        try {
            currentLanguage = lang;

            localizationService.loadStrings(lang);

            lblDistance.setText(localizationService.getString("distance.label"));
            lblConsumption.setText(localizationService.getString("consumption.label"));
            lblPrice.setText(localizationService.getString("price.label"));
            btnCalculate.setText(localizationService.getString("calculate.button"));

            lblResult.setText("");

        } catch (Exception e) {
            lblResult.setText("Missing language data in database!");
        }
    }


    @FXML
    private void handleCalculate() {
        try {
            double distance = Double.parseDouble(txtDistance.getText());
            double consumption = Double.parseDouble(txtConsumption.getText());
            double price = Double.parseDouble(txtPrice.getText());

            double totalFuel = (consumption / 100) * distance;
            double totalCost = totalFuel * price;

            String result = localizationService.getString("result.label")
                    .replace("{0}", String.format("%.2f", totalFuel))
                    .replace("{1}", String.format("%.2f", totalCost));

            lblResult.setText(result);

            calculationService.saveCalculation(
                    distance,
                    consumption,
                    price,
                    totalFuel,
                    totalCost,
                    currentLanguage
            );

        } catch (Exception e) {
            lblResult.setText(localizationService.getString("invalid.input"));
        }
    }

    @FXML private void setEnglish() { setLanguage("en"); }
    @FXML private void setFrench() { setLanguage("fr"); }
    @FXML private void setJapanese() { setLanguage("ja"); }
    @FXML private void setPersian() { setLanguage("fa"); }
}