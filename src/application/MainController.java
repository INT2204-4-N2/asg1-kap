package application;


import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class MainController implements Initializable {

    @FXML
    private ListView<Word> searchResult;

    @FXML
    private TextField searchField;

    @FXML
    private Button findButton;

    @FXML
    private Button insertButton;

    @FXML
    private Button editButton;

    @FXML
    private Button deleteButton;

    Dictionary dictionary;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SQLiteJDBCDriverConnection sqLiteJDBCDriverConnection = new SQLiteJDBCDriverConnection();
        sqLiteJDBCDriverConnection.connect();
        dictionary = new Dictionary(sqLiteJDBCDriverConnection.initialDictionary());
        initSearchResult();

    }

    public void initSearchResult(){
        ObservableList<Word> items = FXCollections.observableArrayList(dictionary.iterate());
        searchResult.setItems(items);
    }

    public void onSearchFieldChange(KeyEvent e) {
        if (e.getSource() == searchField) {
            String wordTarget = searchField.getText();
            find(wordTarget);
        }
    }

    public void find(String wordTarget) {
        ObservableList<Word> items = FXCollections.observableArrayList(dictionary.lockUp(wordTarget));
        searchResult.setItems(items);
    }




}

