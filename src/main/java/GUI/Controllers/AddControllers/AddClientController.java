package GUI.Controllers.AddControllers;

import DAO.ClientDAO;
import GUI.Controllers.DashboardController;
import Models.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddClientController implements AddController{
    @FXML
    private Button BtnAdd;

    @FXML
    private Button BtnClose;

    @FXML
    private TextField TxtNomClient;

    @FXML
    private TextField TxtAdresseClient;

    @FXML
    private TextField TxtContactClient;

    @FXML
    private Label addLabel;

    private DashboardController d;
    public AddClientController(){}
    public AddClientController(DashboardController d){
        this.d =d;
    }

    @FXML
    public void BtnAddAction(ActionEvent evt){
        if (!TxtNomClient.getText().isEmpty() && !TxtAdresseClient.getText().isEmpty() && !TxtContactClient.getText().isEmpty()){
            ClientDAO dao = new ClientDAO();
            dao.add(new Client(TxtNomClient.getText(),TxtAdresseClient.getText(),TxtContactClient.getText()));
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
