package sample;

import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

import java.net.URL;
import java.sql.*;
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

    @FXML
    private TextField tfCalorieGoal;

    @FXML
    private TextField tfCalorieFood;

    @FXML
    private TextField tfServing;

    @FXML
    private Button btnSubtract;

    @FXML
    private Label lblNewgoal;

    @FXML
    private Button btnRefresh;

    private String holder;

    @FXML
    private Button btnAddition;

    @FXML
    private TextField tfCalExercise;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tfheightinfeet.setStyle("-fx-text-inner-color: #000000;");
        tfheightininches.setStyle("-fx-text-inner-color: #000000;");
        tfweight.setStyle("-fx-text-inner-color: #000000;");
        tfSearch.setStyle("-fx-text-inner-color: #000000;");
        tfCalorieGoal.setStyle("-fx-text-inner-color: #000000;");
        tfCalExercise.setStyle("-fx-text-inner-color: #000000;");

        datePicker.setValue(LocalDate.now());

        Categories.setCellValueFactory(new PropertyValueFactory<>("categories"));
        NamesofFood.setCellValueFactory(new PropertyValueFactory<>("foodnames"));
        Calories.setCellValueFactory(new PropertyValueFactory<>("calories"));
        Servingsize.setCellValueFactory(new PropertyValueFactory<>("servingsize"));


    }

    public void getBMIcalculator(ActionEvent event) {

        if(tfheightinfeet.getText().isBlank() == false && tfheightininches.getText().isBlank() == false && tfweight.getText().isBlank() == false) {
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
        }else{
            descriptionmessage.setText("");
            list.add(new LocalEvent(datePicker.getValue(), tfDescription.getText()));
            eventLog.setItems(list);
        }
    }

    @FXML
    private void subtractcalories(ActionEvent event) {
        if(tfCalorieGoal.getText().isBlank() == false && tfCalorieFood.getText().isBlank() == false && tfServing.getText().isBlank() == false) {

            int thegoal = Integer.parseInt(tfCalorieGoal.getText());
            int calsperfood = Integer.parseInt(tfCalorieFood.getText());
            int servingsz = Integer.parseInt(tfServing.getText());
            int newgoal = thegoal - (calsperfood * servingsz);

            holder = tfCalorieGoal.getText();
            lblNewgoal.setText(String.valueOf(newgoal)+" remaining");
            PauseTransition pt = new PauseTransition();
            pt.setDuration(Duration.seconds(2));

            pt.setOnFinished(ev -> {
                tfCalorieGoal.setText(String.valueOf(newgoal));
                tfCalorieFood.clear();
                tfServing.clear();
                lblNewgoal.setText("");
            });
            pt.play();

        }else{
            lblNewgoal.setText("");
        }

    }
    @FXML
    private void refreshcalories(ActionEvent event) {
        if(tfCalorieGoal.getText().isBlank()){
            tfCalorieGoal.clear();

        }else{

            tfCalorieGoal.setText(holder);
        }
    }

    @FXML
    private void btnAddin(ActionEvent event) {

        if(tfCalorieGoal.getText().isBlank() == false && tfCalExercise.getText().isBlank() == false) {
            int thegoal = Integer.parseInt(tfCalorieGoal.getText());
            int calexercise = Integer.parseInt(tfCalExercise.getText());
            int newgoal = thegoal + calexercise;
            lblNewgoal.setText(String.valueOf(newgoal) + " remaining");

            PauseTransition pt = new PauseTransition();
            pt.setDuration(Duration.seconds(2));
            pt.setOnFinished(ev -> {
                tfCalorieGoal.setText(String.valueOf(newgoal));
                tfCalExercise.clear();
                lblNewgoal.setText("");
            });
            pt.play();

        }else{
            lblNewgoal.setText("");
        }


    }


}
