//import backend.apicon;
//import java.io.IOException;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.stage.Stage;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//
//public class WeatherAppTest extends Application{
//    
//    private static Scene scene;
//
//    @Override
//    public void start(Stage stage) throws IOException {
//        scene = new Scene(loadFXML("view-sunny"), 640, 480);
//        stage.setScene(scene);
//        stage.show();
//    }
//    
//    private static Parent loadFXML(String fxml) throws IOException {
//        String root = "E:\\java-project\\weather-app\\src\\main\\java\\frontend\\";
//        FXMLLoader fxmlLoader = new FXMLLoader(WeatherAppTest.class.getResource( root + fxml + ".fxml"));
//        return fxmlLoader.load();
//    }
//
//    public static void main(String[] args){
//        
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
//
//    }
//
//
//}
