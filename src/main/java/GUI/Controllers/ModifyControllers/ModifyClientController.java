package GUI.Controllers.ModifyControllers;

import DAO.ClientDAO;
import GUI.Controllers.DashboardController;
import Models.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ModifyClientController implements ModifyController, Initializable {

    @FXML
    private TextField TxtNomClient;

    @FXML
    private TextField TxtAdresseClient;

    @FXML
    private TextField TxtContactClient;

    @FXML
    private Button BtnModify;

    @FXML
    private Button BtnClose;

    @FXML
    private Label modifyLabel;

    private String id;
    private Object o;

    @Override
    public void setObject(Object o) {
        this.o = o;
    }

    private DashboardController d;
    public ModifyClientController(){}
    public ModifyClientController(DashboardController d){
        this.d =d ;
    }

    @FXML
    public void BtnModifyAction(ActionEvent evt){
        if (!TxtNomClient.getText().isEmpty() && !TxtAdresseClient.getText().isEmpty() && !TxtContactClient.getText().isEmpty()){
            ClientDAO dao = new ClientDAO();
            boolean b = dao.update(new Client(id,TxtNomClient.getText(),TxtAdresseClient.getText(),TxtContactClient.getText()));
            System.out.println(b);
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
        id = ((Client)o).getId();
        TxtNomClient.setText(((Client)o).getNomclient());
        TxtAdresseClient.setText(((Client)o).getAdresseclient());
        TxtContactClient.setText(((Client)o).getContactclient());
    }

}
