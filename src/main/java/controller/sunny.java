package controller;

import backend.apicon;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class sunny {
    @FXML
    Button searchCountry;
    @FXML
    TextField tfCountry;
    
    @FXML
    Text currentTemp;
    
    public void searchClicked() {
        try {
            System.out.println("Hello WORLD");
            String countryName = tfCountry.getText();
            double curTemp = apicon.currentTemp(countryName);
            currentTemp.setText(curTemp + "°");
            System.out.println(countryName + " " + curTemp);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    
}
