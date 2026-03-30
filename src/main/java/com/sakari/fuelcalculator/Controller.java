package com.sakari.fuelcalculator;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class Controller {

    @FXML private Label lblDistance, lblConsumption, lblPrice, lblResult;
    @FXML private TextField txtDistance, txtConsumption, txtPrice;
    @FXML private Button btnCalculate;

    private ResourceBundle bundle;
    private Locale currentLocale;

    @FXML
    public void initialize() {
        setLanguage(new Locale("en", "US"));
    }

    private void setLanguage(Locale locale) {
        currentLocale = locale;

        try {
            // ✅ FIXED: proper Java i18n way
            bundle = ResourceBundle.getBundle("messages", locale);

            lblDistance.setText(bundle.getString("distance.label"));
            lblConsumption.setText(bundle.getString("consumption.label"));
            lblPrice.setText(bundle.getString("price.label"));
            btnCalculate.setText(bundle.getString("calculate.button"));

            // ✅ reset result on language change
            lblResult.setText("");

        } catch (Exception e) {
            lblResult.setText("Missing language file!");
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

            String result = MessageFormat.format(
                    bundle.getString("result.label"),
                    String.format("%.2f", totalFuel),
                    String.format("%.2f", totalCost)
            );

            lblResult.setText(result);

        } catch (Exception e) {
            lblResult.setText(bundle.getString("invalid.input"));
        }
    }

    @FXML private void setEnglish() {
        setLanguage(new Locale("en", "US"));
    }

    @FXML private void setFrench() {
        setLanguage(new Locale("fr", "FR"));
    }

    @FXML private void setJapanese() {
        setLanguage(new Locale("ja", "JP"));
    }

    @FXML private void setPersian() {
        setLanguage(new Locale("fa", "IR"));
    }
}