package weather.app;

import backend.apicon;
import java.io.IOException;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;

public class WeatherApp extends Application{
    
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("view-sunny"));
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(WeatherApp.class.getResource("/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }
   

    public static void main(String[] args){
        launch();
        
//        try {
//            String country = "Cambodia";
//            double temp = apicon.currentTemp(country);
//            System.out.println("Current temperature in " + country + "= " + temp);
//            
//            JSONArray forecastHourly = apicon.getHourlyForecast(country); 
//            System.out.println("Today's Temperature in " + country + " at 10PM: " + forecastHourly.get(19) );
//            
//            JSONArray forecastDaily = apicon.getDailyForecast(country); 
//            System.out.println("Temperature in 5 days from today in " + country + " : " + forecastDaily.get(4) );
//            
//            // throw exception when country not found
//            temp = apicon.currentTemp("england");
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        

    }

}
