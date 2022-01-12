package GUI.Controllers.ModifyControllers;

import DAO.UserDAO;
import GUI.Controllers.DashboardController;
import Models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ModifyUserController implements ModifyController, Initializable {

    @FXML
    private TextField TxtUsername;

    @FXML
    private TextField TxtPassword;

    @FXML
    private Button BtnModify;

    @FXML
    private Button BtnClose;

    @FXML
    private Label modifyLabel;

    private Object o;

    @Override
    public void setObject(Object o) {
        this.o = o;
    }

    //tmp
    public ModifyUserController(){}

    private DashboardController d;

    public ModifyUserController(DashboardController d){
        this.d = d;
    }

    //tmp

    @FXML
    public void BtnModifyAction(ActionEvent evt){
        if (!TxtPassword.getText().isEmpty()){
            UserDAO dao = new UserDAO();
            boolean b = dao.update(new User(TxtUsername.getText(),TxtPassword.getText()));
            d.reloadAll();
        }else{
            modifyLabel.setText("Please fill in all the fields!");
        }
    }

    @FXML
    public void BtnCloseAction(ActionEvent evt){
        Stage stage = (Stage)BtnClose.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TxtUsername.setText(((User)o).getUsername());
        TxtPassword.setText(((User)o).getPassword());
        TxtUsername.setDisable(true);
    }
}
