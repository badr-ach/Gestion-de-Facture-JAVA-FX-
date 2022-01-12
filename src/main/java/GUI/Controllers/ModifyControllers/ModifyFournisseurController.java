package GUI.Controllers.ModifyControllers;

import DAO.FournisseurDAO;
import GUI.Controllers.DashboardController;
import Models.Fournisseur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ModifyFournisseurController implements ModifyController, Initializable {
    @FXML
    private TextField TxtNomFournisseur;

    @FXML
    private TextField TxtAdresseFournisseur;

    @FXML
    private TextField TxtContactFournisseur;

    @FXML
    private Button BtnModify;

    @FXML
    private Button BtnClose;

    @FXML
    private Label modifyLabel;

    private Object o;
    private String id;

    @Override
    public void setObject(Object o) {
        this.o = o;
    }

    private DashboardController d;
    public ModifyFournisseurController(){}
    public ModifyFournisseurController(DashboardController d){
        this.d =d ;
    }

    @FXML
    public void BtnModifyAction(ActionEvent evt){
        if (!TxtNomFournisseur.getText().isEmpty() && !TxtAdresseFournisseur.getText().isEmpty() && !TxtContactFournisseur.getText().isEmpty()){
            FournisseurDAO dao = new FournisseurDAO();
            boolean b = dao.update(new Fournisseur(id,TxtNomFournisseur.getText(),TxtAdresseFournisseur.getText(),TxtContactFournisseur.getText()));
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
        id = ((Fournisseur)o).getId();
        TxtNomFournisseur.setText(((Fournisseur)o).getNomfournisseur());
        TxtAdresseFournisseur.setText(((Fournisseur)o).getAdressefournisseur());
        TxtContactFournisseur.setText(((Fournisseur)o).getContactfournisseur());
    }
}
