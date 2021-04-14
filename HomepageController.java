package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.function.Predicate;


public class HomepageController implements Initializable {

    @FXML
    private TextField tfheightinfeet;

    @FXML
    private TextField tfheightininches;

    @FXML
    private TextField tfweight;

    @FXML
    private Button btncalculate;

    @FXML
    private Label lblgetBMI;

    @FXML
    private TextField tfSearch;

    @FXML
    private TableView<FoodList> tableview;

    @FXML
    private TableColumn<FoodList, String> Categories;

    @FXML
    private TableColumn<FoodList, String> NamesofFood;

    @FXML
    private TableColumn<FoodList, String> Calories;

    @FXML
    private TableColumn<FoodList, String> Servingsize;

    ObservableList<FoodList> data= FXCollections.observableArrayList();

    @FXML
    private Button btnAddLog;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextField tfDescription;

    @FXML
    private ListView<LocalEvent> eventLog;
    @FXML
    private Label descriptionmessage;

    ObservableList<LocalEvent> list = FXCollections.observableArrayList();

    private PreparedStatement pst;
    private Connection connection;
    private DBHandler handler;
    private ResultSet rs;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tfheightinfeet.setStyle("-fx-text-inner-color: #000000;");
        tfheightininches.setStyle("-fx-text-inner-color: #000000;");
        tfweight.setStyle("-fx-text-inner-color: #000000;");
        tfSearch.setStyle("-fx-text-inner-color: #000000;");

        datePicker.setValue(LocalDate.now());

        Categories.setCellValueFactory(new PropertyValueFactory<>("categories"));
        NamesofFood.setCellValueFactory(new PropertyValueFactory<>("foodnames"));
        Calories.setCellValueFactory(new PropertyValueFactory<>("calories"));
        Servingsize.setCellValueFactory(new PropertyValueFactory<>("servingsize"));

    }

    public void getBMIcalculator(ActionEvent event) {

        if(tfheightinfeet.getText().isBlank() == false && tfheightininches.getText().isBlank() == false&& tfweight.getText().isBlank() == false) {
            double totalheight;
            int feet = Integer.parseInt(tfheightinfeet.getText());
            int inches = Integer.parseInt(tfheightininches.getText());
            totalheight = (feet * 12) + inches;
            int weight = Integer.parseInt(tfweight.getText());
            double BMI = (703 * weight) / (totalheight * totalheight);

            if (BMI > 18.5) {
                lblgetBMI.setText(String.valueOf(BMI));
            }
            if (18.5 <= BMI && BMI >= 24.9) {
                lblgetBMI.setText(String.valueOf(BMI));
            }
            if (25 <= BMI && BMI >= 29.9) {
                lblgetBMI.setText(String.valueOf(BMI));
            } else {
                lblgetBMI.setText(String.valueOf(BMI));
            }
        }else{
            lblgetBMI.setText("");
        }
    }
    @FXML
    public void SearchFood(KeyEvent event) throws SQLException, ClassNotFoundException {
        DBHandler connectNow = new DBHandler();
        Connection connectionDB = connectNow.getConnection();
        if(tfSearch.getText().equals("")){
            data.clear();
            loadFoodDatabase();
        }else {
            data.clear();
            rs = connectionDB.createStatement().executeQuery("select * from foods where categories like '" + tfSearch.getText() + "'" + "union select * from foods where names like '" + tfSearch.getText() + "'" + "union select * from foods where calories like '" + tfSearch.getText() + "'" + "union select * from foods where servingsize like '" + tfSearch.getText() + "'");
            while (rs.next()) {
                data.add(new FoodList(rs.getString("categories"), rs.getString("names"), rs.getString("calories"), rs.getString("servingsize")));
            }
            tableview.setItems(data);
        }
    }
    private void loadFoodDatabase() throws SQLException, ClassNotFoundException {
        DBHandler connectNow = new DBHandler();
        Connection connectionDB = connectNow.getConnection();
        rs = connectionDB.createStatement().executeQuery("select * from foods");
        while (rs.next()) {
            data.add(new FoodList(rs.getString("categories"), rs.getString("names"), rs.getString("calories"), rs.getString("servingsize")));
        }
        tableview.setItems(data);
    }

    @FXML
    private void addLog(ActionEvent event) throws NullPointerException{
        if(tfDescription.getText().isBlank()) {
            descriptionmessage.setText("Log Failed. Please try again.");
//            list.add(new LocalEvent(datePicker.getValue(), tfDescription.getText()));
        }else{
            descriptionmessage.setText("");
            list.add(new LocalEvent(datePicker.getValue(), tfDescription.getText()));
            eventLog.setItems(list);
//            list.clear();
        }
    }
}
