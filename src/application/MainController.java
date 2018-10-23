package application;


import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class MainController implements Initializable {

    @FXML
    private ListView<Word> searchResult;

    @FXML
    private TextField searchField;


    Dictionary dictionary;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SQLiteJDBCDriverConnection sqLiteJDBCDriverConnection = new SQLiteJDBCDriverConnection();
        sqLiteJDBCDriverConnection.connect();
        dictionary = new Dictionary(sqLiteJDBCDriverConnection.initialDictionary());
        sqLiteJDBCDriverConnection.disconnect();
        ArrayList<Word> a = dictionary.lockUp("a");
        for (Word w : a) {
            System.out.println(w.toString());
        }

    }


}

