package GUI.Controllers.AddControllers;

import DAO.ArticleDAO;
import GUI.Controllers.DashboardController;
import Models.Article;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddArticleController implements AddController{
    @FXML
    private Button BtnAdd;

    @FXML
    private Button BtnClose;

    @FXML
    private TextField TxtLibelle;

    @FXML
    private Label addLabel;

    private DashboardController d;
    public AddArticleController(){}
    public AddArticleController(DashboardController d){
        this.d =d;
    }

    @FXML
    public void BtnAddAction(ActionEvent evt){
        if (!TxtLibelle.getText().isEmpty() ){
            ArticleDAO dao = new ArticleDAO();
            dao.add(new Article(TxtLibelle.getText()));
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
