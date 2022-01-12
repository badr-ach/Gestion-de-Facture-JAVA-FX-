package GUI.Controllers.AddControllers;

import DAO.ClientDAO;
import DAO.FactureAchatDAO;
import DAO.FactureVenteDAO;
import DAO.FournisseurDAO;
import GUI.Controllers.DashboardController;
import GUI.Main.Main;
import Models.Client;
import Models.FactureAchat;
import Models.FactureVente;
import Models.Fournisseur;
import Utils.ResultsetConvert;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddFactureController implements AddController, Initializable {


    private List<String> fournisseurs;
    private List<String> clients;
    private String type = "";

    @FXML
    private ComboBox ComboType;

    @FXML
    private ComboBox ComboNom;

    @FXML
    private Button BtnClose;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<String> list = new ArrayList<String>();
        list.add("Client");
        list.add("Fournisseur");
        ObservableList obList = FXCollections.observableList(list);
        ComboType.getItems().clear();
        ComboType.setItems(obList);
        ComboNom.getItems().clear();
        //**************************************//
        //init//
        List<Client> clientList = new ResultsetConvert(new ClientDAO().getAll()).toClient();
        clients = new ArrayList<>();

        List<Fournisseur> fournisseurList = new ResultsetConvert(new FournisseurDAO().getAll()).toFournisseur();
        fournisseurs = new ArrayList<>();

        for(int i = 0 ; i< clientList.size();i++){
            clients.add(clientList.get(i).getNomclient());
        }
        for(int i = 0; i< fournisseurList.size() ; i++){
            fournisseurs.add(fournisseurList.get(i).getNomfournisseur());
        }
    }

    @FXML
    public void ComboTypeAction(ActionEvent evt){
        type = ComboType.getValue().toString();

        if(type.equals("Client")){
            ObservableList obList = FXCollections.observableList(clients);
            ComboNom.getItems().clear();
            ComboNom.setItems(obList);
        }else if(type.equals("Fournisseur")){
            ObservableList obList = FXCollections.observableList(fournisseurs);
            ComboNom.getItems().clear();
            ComboNom.setItems(obList);
        }else{
            ComboNom.getItems().clear();
        }
    }

    private String id;
    private DashboardController d;
    public AddFactureController(){}

    public AddFactureController(DashboardController d){this.d = d;}

    @FXML
    public void ComboNomAction(ActionEvent evt){
        String nom = ComboNom.getValue().toString();
        if( type.equals("Client")){
            System.out.println(new ClientDAO().get(nom));
            id = new ClientDAO().get(nom).getId();
            System.out.println(id);
        }else if(type.equals("Fournisseur")){
            Fournisseur fournisseur = new FournisseurDAO().get(nom);
            System.out.println(fournisseur);
            id = fournisseur.getId();
        }
    }

    @FXML
    public void BtnNextAction(ActionEvent evt) throws Exception {
        if( type.equals("Client")){
            FactureAchatDAO dao = new FactureAchatDAO();
            Client client = new ClientDAO().getById(id);
            dao.add(new FactureAchat(client,LocalDate.now()));
            // NEXT STAGE :
            d.reloadAll();
            nextStage(dao.getLastInsertId(),d);

        }else if(type.equals("Fournisseur")){
            FactureVenteDAO dao = new FactureVenteDAO();
            Fournisseur fournisseur = new FournisseurDAO().getById(id);
            dao.add(new FactureVente(fournisseur,LocalDate.now()));
            // NEXT STAGE :
            nextStage(dao.getLastInsertId(),d);
        }else{
            System.out.println("empty !!");
        }
    }

    @FXML
    public void BtnCloseAction(ActionEvent evt){
        Stage stage = (Stage)BtnClose.getScene().getWindow();
        stage.close();
    }

    public void nextStage(String id,DashboardController d) throws Exception {
        Application app = new Application() {
            @Override
            public void start(Stage stage) throws Exception {
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/GUI/AddToFacture.fxml"));
                AddToFactureController controller = new AddToFactureController(id,d);
                fxmlLoader.setController(controller);
                Parent root = fxmlLoader.load();
                Scene scene = new Scene(root);
                stage.setTitle("Add to Facture");
                stage.setScene(scene);
                stage.show();
            }
        };
        app.start(new Stage());
    }
}
