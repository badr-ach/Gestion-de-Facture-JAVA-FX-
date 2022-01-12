package GUI.Controllers.ModifyControllers;

import DAO.*;
import GUI.Controllers.AddControllers.AddToFactureController;
import GUI.Controllers.DashboardController;
import GUI.Main.Main;
import Models.Client;
import Models.FactureAchat;
import Models.FactureVente;
import Models.Fournisseur;
import Utils.Facture;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ModifyFactureController implements ModifyController, Initializable {

    @FXML
    private Button BtnClose;

    @FXML
    private ComboBox ComboType;

    @FXML
    private ComboBox ComboNom;

    private String idFacture;
    private List<String> fournisseurs;
    private List<String> clients;
    private String type = "";
    private Object selectedObject;
    private DashboardController d;

    public ModifyFactureController(){}

    public ModifyFactureController(DashboardController d){
        this.d = d;
    }

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

    @Override
    public void setObject(Object o) {
        this.selectedObject = o;
        idFacture = ((Facture)o).getNumfact();
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
            FactureDAO dao = new FactureDAO();
            Client client = new ClientDAO().getById(id);
            client.setId(id);
            FactureAchat f = new FactureAchat(idFacture,client, LocalDate.now());
            System.out.println("ID OF ACHAT " + id);
            f.setRecevant(id);
            f.setType("achat");
            boolean b = dao.update(f);
            System.out.println("type update  ? " + b);
            // NEXT STAGE :ad
            d.reloadAll();
            nextStage(((Facture)this.selectedObject).getNumfact(),d);

        }else if(type.equals("Fournisseur")){
            FactureDAO dao = new FactureDAO();
            Fournisseur fournisseur = new FournisseurDAO().getById(id);
            System.out.println(fournisseur.getId());
            FactureVente f = new FactureVente(idFacture,fournisseur,LocalDate.now());
            System.out.println(id + " ID OF VENTE");
            f.setRecevant(id);
            f.setType("vente");
            dao.update(f);
            // NEXT STAGE :
            nextStage(((Facture)this.selectedObject).getNumfact(),d);
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
                stage.setTitle("Modify Facture");
                stage.setScene(scene);
                stage.show();
            }
        };
        app.start(new Stage());
    }
}
