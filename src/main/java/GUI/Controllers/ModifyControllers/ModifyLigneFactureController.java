package GUI.Controllers.ModifyControllers;

import DAO.ArticleDAO;
import DAO.LigneFactureDAO;
import GUI.Controllers.AddControllers.AddToFactureController;
import Models.Article;
import Models.LigneFacture;
import Utils.ResultsetConvert;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ModifyLigneFactureController implements Initializable {
    @FXML
    private ComboBox ComboArticles;

    @FXML
    private TextField TxtPrix;

    @FXML
    private TextField TxtQte;

    @FXML
    private Label modifyLabel;

    @FXML
    private Button BtnClose;

    @FXML
    private Button BtnAdd;

    private String idFacture;
    private AddToFactureController i;
    private Object o;

    public void setObject(Object o){
        this.o = o;
    }

    public ModifyLigneFactureController(){}

    public ModifyLigneFactureController(String idFacture, AddToFactureController i){
        this.idFacture = idFacture;
        this.i = i;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TxtPrix.setText(Double.toString(((LigneFacture)o).getPrix()));
        TxtQte.setText(Integer.toString(((LigneFacture)o).getQte()));

        List<Article> articles = new ResultsetConvert(new ArticleDAO().getAll()) .toArticle();
        List<String> list = new ArrayList<>();
        for(int i =0 ; i< articles.size() ; i++){
            list.add(articles.get(i).getLibelle());
        }
        //HERE I NEED TO SET THE ARTCIESL NAME NOT WHOLE OBJECT
        ObservableList obList = FXCollections.observableList(list);
        ComboArticles.getItems().clear();
        ComboArticles.setItems(obList);
    }

    @FXML
    public void BtnAddAction (ActionEvent evt){
        if(!TxtPrix.getText().isEmpty() && !TxtQte.getText().isEmpty() && ComboArticles.getValue()!=null ){
            LigneFactureDAO dao = new LigneFactureDAO();
            try {
                String idArticle = new ArticleDAO().get(ComboArticles.getValue().toString()).getNumart();
                Double prix = Double.parseDouble(TxtPrix.getText());
                int qte = Integer.parseInt(TxtQte.getText());
                System.out.println(idArticle);
                System.out.println(idFacture);
                dao.update(new LigneFacture(((LigneFacture)o).getId(),idFacture, idArticle, prix, qte));
            }catch(Exception ex){
                System.out.println(ex.getMessage());
                modifyLabel.setText("Format saisit invalide");
            }

        }else{
            modifyLabel.setText("Please fill in all the fields!");
        }
    }

    @FXML
    public void BtnCloseAction(ActionEvent evt){
        Stage stage = (Stage)BtnClose.getScene().getWindow();
        stage.close();
    }

}
