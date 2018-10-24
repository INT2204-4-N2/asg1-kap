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
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

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

    @FXML
    private WebView meaningField;

    Dictionary dictionary;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SQLiteJDBCDriverConnection sqLiteJDBCDriverConnection = new SQLiteJDBCDriverConnection();
        sqLiteJDBCDriverConnection.connect();
        dictionary = new Dictionary(sqLiteJDBCDriverConnection.initialDictionary());
        searchResult.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        initSearchResult();

    }

    public void initSearchResult() {
        ObservableList<Word> items = FXCollections.observableArrayList(dictionary.iterate());
        searchResult.setItems(items);
    }

    public void onSearchFieldChange(KeyEvent e) {
        if (e.getSource() == searchField) {
            String wordTarget = searchField.getText();
            lockUp(wordTarget);
        }
    }

    public void lockUp(String wordTarget) {
        ObservableList<Word> items = FXCollections.observableArrayList(dictionary.lockUp(wordTarget));
        searchResult.setItems(items);
    }

    public void onClickedResulteField(MouseEvent e){
        Word w = searchResult.getSelectionModel().getSelectedItem();
        if(w!= null){
            displayMeaning(w.getWordMeaning());
        }
    }

    public void displayMeaning(String html){
        WebEngine webEngine = meaningField.getEngine();
        webEngine.loadContent(html,"text/html");;
    }
}

