package GUI.Controllers.ModifyControllers;

import DAO.ArticleDAO;
import GUI.Controllers.DashboardController;
import Models.Article;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ModifyArticleController implements ModifyController, Initializable {
    @FXML
    private TextField TxtNumArt;

    @FXML
    private TextField TxtLibelle;

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

    private DashboardController d;
    public ModifyArticleController(){}
    public ModifyArticleController(DashboardController d){
        this.d=d;
    }

    @FXML
    public void BtnModifyAction(ActionEvent evt){
        if (!TxtLibelle.getText().isEmpty()){
            ArticleDAO dao = new ArticleDAO();
            dao.update(new Article(TxtNumArt.getText(),TxtLibelle.getText()));
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
        TxtNumArt.setText(((Article)o).getNumart());
        TxtLibelle.setText(((Article)o).getLibelle());
        TxtNumArt.setDisable(true);
    }
}
