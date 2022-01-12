package GUI.Controllers.AddControllers;

import DAO.FournisseurDAO;
import GUI.Controllers.DashboardController;
import Models.Fournisseur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddFournisseurController implements  AddController{
    @FXML
    private Button BtnAdd;

    @FXML
    private Button BtnClose;

    @FXML
    private TextField TxtNomFournisseur;

    @FXML
    private TextField TxtAdresseFournisseur;

    @FXML
    private TextField TxtContactFournisseur;

    @FXML
    private Label addLabel;

    private DashboardController d;
    public AddFournisseurController(){}
    public AddFournisseurController(DashboardController d){
        this.d =d;
    }

    @FXML
    public void BtnAddAction(ActionEvent evt){
        if (!TxtNomFournisseur.getText().isEmpty() && !TxtAdresseFournisseur.getText().isEmpty() && !TxtContactFournisseur.getText().isEmpty()){
            FournisseurDAO dao = new FournisseurDAO();
            dao.add(new Fournisseur(TxtNomFournisseur.getText(),TxtAdresseFournisseur.getText(),TxtAdresseFournisseur.getText()));
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
