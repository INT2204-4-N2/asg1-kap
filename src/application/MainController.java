package application;


import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
 
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
 
public class MainController implements Initializable {
 
   @FXML
   private Button myButton;
  
   @FXML
   private TextField myTextField;
  
   @Override
   public void initialize(URL location, ResourceBundle resources) {
 
      
   }
 
   public void showDateTime(ActionEvent event) {
       System.out.println("Button Clicked!");
      
       Date now= new Date();
      
       DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS");
      
         // Dữ liệu Model
        String dateTimeString = df.format(now);
  
        // Hiển thị lên VIEW.
        myTextField.setText(dateTimeString);
      
   }
  
}

