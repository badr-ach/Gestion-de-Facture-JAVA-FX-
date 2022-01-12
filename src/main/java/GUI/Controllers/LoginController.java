package GUI.Controllers;

import GUI.Main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import DAO.UserDAO;
import Models.User;

import java.io.IOException;

public class LoginController {

    @FXML
    private Pane Pane;

    @FXML
    private TextField TxtUsername;

    @FXML
    private TextField TxtPassword;

    @FXML
    private Button BtnQuit;

    @FXML
    private Label loginLabel;

    @FXML
    public void btnQuitAction(ActionEvent evt){
        Stage stage = (Stage) BtnQuit.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void btnLoginAction(ActionEvent evt) throws IOException {
        if (TxtPassword.getText().isEmpty() || TxtUsername.getText().isEmpty()){
            loginLabel.setText("Please enter Username and Password!!");
        }else{
            if (!validate()){
                loginLabel.setText("Invalid Credentials");
            }else{
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/GUI/Dashboard.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 707, 463);
                Stage stage = (Stage)Pane.getScene().getWindow();
                stage.setScene(scene);
            }
        }
    }

    public boolean validate(){
        User user = new User(TxtUsername.getText(),TxtPassword.getText());
        UserDAO userDAO = new UserDAO();
        return userDAO.Login(user);
    }
}
