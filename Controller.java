package sample;

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

import com.jfoenix.controls.*;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    @FXML
    private TextField tfUsername;

    @FXML
    private PasswordField pfPassword;

    @FXML
    private Button btnSignUp;

    @FXML
    private Button btnLogin;

    @FXML
    private Label loginmessage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tfUsername.setStyle("-fx-text-inner-color: #000000;");
        pfPassword.setStyle("-fx-text-inner-color: #000000;");

    }

    @FXML
    public void LoginAction(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {


       if(tfUsername.getText().isBlank() == false && pfPassword.getText().isBlank() == false){
           validateLogin();

       }else{
           loginmessage.setText("Please enter Username and Password.");
       }
        PauseTransition pt = new PauseTransition();
        pt.setDuration(Duration.seconds(2));
        pt.setOnFinished(ev -> {
            System.out.print("Login Success");
            btnLogin.getScene().getWindow().hide();

            Stage home = new Stage();

            try {
                Parent root = FXMLLoader.load(getClass().getResource("Homepage.fxml"));
                Scene scene = new Scene(root);
                home.setScene(scene);
                home.show();
                home.setResizable(false);
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        pt.play();

    }
    public void validateLogin() throws SQLException, ClassNotFoundException {
        DBHandler connectNow = new DBHandler();
        Connection connectionDB = connectNow.getConnection();

        String verifylogin = "SELECT count(1) FROM users where username = '" + tfUsername.getText() + "' AND password = '" + pfPassword.getText() +"'";

        try{
            Statement statement = connectionDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifylogin);

            while(queryResult.next()){
                if(queryResult.getInt(1) == 1){
                    loginmessage.setText("Congratulations!");

                }else{
                    loginmessage.setText("Invaid login. Please try again.");
                }
            }

        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    @FXML
    public void SignUpAction(ActionEvent event) throws IOException {

        btnLogin.getScene().getWindow().hide();

        Stage btnSignUp = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
        Scene scene = new Scene(root);
        btnSignUp.setScene(scene);
        btnSignUp.show();
        btnSignUp.setResizable(false);
    }

}
