package GUI.Controllers.AddControllers;

import DAO.UserDAO;
import GUI.Controllers.DashboardController;
import Models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddUserController implements AddController{

    @FXML
    private Button BtnAdd;

    @FXML
    private Button BtnClose;

    @FXML
    private TextField TxtUsername;

    @FXML
    private TextField TxtPassword;

    @FXML
    private Label addLabel;

    public AddUserController(){}

    private DashboardController d;

    public AddUserController(DashboardController d){
        this.d =d;
    }

    @FXML
    public void BtnAddAction(ActionEvent evt){
        if (!TxtPassword.getText().isEmpty() && !TxtUsername.getText().isEmpty()){
            UserDAO dao = new UserDAO();
            dao.add(new User(TxtUsername.getText(),TxtPassword.getText()));
            d.reloadAll();
        }else{
            addLabel.setText("Please fill in all the fields!");
        }
    }

    @FXML
    public void BtnCloseAction(ActionEvent evt){
        Stage stage = (Stage)BtnClose.getScene().getWindow();
        stage.close();
    }
}
