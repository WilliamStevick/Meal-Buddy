package sample;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.DBHandler;

public class SignupController implements Initializable {

    @FXML
    private TextField tfName;

    @FXML
    private PasswordField pfPassword;

    @FXML
    protected TextField tfDailyCalorie;

    @FXML
    private Button btnSignUp;

    @FXML
    private Button btnLogin;

    @FXML
    private TextField tfUserName;

    @FXML
    private Label lblregister;

    private Connection connection;
    private DBHandler handler;
    private PreparedStatement pst;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tfName.setStyle("-fx-text-inner-color: #000000;");
        tfUserName.setStyle("-fx-text-inner-color: #000000;");
        pfPassword.setStyle("-fx-text-inner-color: #000000;");
        tfDailyCalorie.setStyle("-fx-text-inner-color: #000000;");



    }
    @FXML
    public void SignUpAction(ActionEvent event) throws SQLException, ClassNotFoundException {

        if(tfName.getText().isBlank() == false && tfUserName.getText().isBlank() == false && pfPassword.getText().isBlank() == false && tfDailyCalorie.getText().isBlank() == false) {
            registerUser();
            PauseTransition pt = new PauseTransition();
            pt.setDuration(Duration.seconds(2));
            pt.setOnFinished(e1 -> {
                System.out.print("SignUp Successful");
            });
            pt.play();

        }else{
            lblregister.setText("Please enter all fields above.");
        }
    }
    public void registerUser() throws SQLException, ClassNotFoundException {

        DBHandler connectNow = new DBHandler();
        Connection connectDB = connectNow.getConnection();

        String name = tfName.getText();
        String username = tfUserName.getText();
        String password = pfPassword.getText();
        String dailycal = tfDailyCalorie.getText();

        String insertparameters = "INSERT INTO users(names, username, password, dailycalories) VALUES ('";
        String insertvalues = name + "','" + username + "','" + password + "','" + dailycal + "')";
        String insertToregister = insertparameters + insertvalues;


        try{
            Statement statement = connectDB.createStatement();
            statement.execute(insertToregister);

            lblregister.setText("User registered successfully!");

        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }


    }
    @FXML
    public void LoginAction(ActionEvent event) throws IOException {

        btnSignUp.getScene().getWindow().hide();

        Stage btnLogin = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("LogIn.fxml"));
        Scene scene = new Scene(root);
        btnLogin.setScene(scene);
        btnLogin.show();
        btnLogin.setResizable(false);


    }
}
