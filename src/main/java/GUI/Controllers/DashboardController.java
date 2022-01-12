package GUI.Controllers;

import DAO.*;
import GUI.Controllers.AddControllers.*;
import GUI.Controllers.ModifyControllers.*;
import GUI.Main.Main;
import Models.Article;
import Models.Client;
import Models.Fournisseur;
import Models.User;
import Utils.Facture;
import Utils.ResultsetConvert;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    private ArrayList<String> columns;
    private String currentResource;
    private IDAO currentDAO;
    private ModifyController currentModifyController;
    private AddController currentAddController;
    private Object selectedObject;
    private String currentLoader;


    @FXML
    private TableView Table;

    @FXML
    private TextField TxtFind;

    @FXML
    private Button BtnArticles;

    @FXML
    private Button BtnFind;

    @FXML
    private Button BtnAdd;

    @FXML
    private Label LblFind;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userView();
        Table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                BtnFind.setText("Modify");
                BtnAdd.setText("Delete");
                selectedObject = Table.getSelectionModel().selectedItemProperty().get();
            }else{
                BtnFind.setText("Find");
                BtnAdd.setText("Add");
            }
        });
    }

    @FXML
    public void BtnFindAction(ActionEvent evt) throws Exception {
        if(BtnFind.getText().equals("Find")) {
            String text = TxtFind.getText();
            if (!text.isEmpty()) {
                reload(currentDAO.find(text));
            } else {
                reload(currentDAO.getAll());
            }
        }else if(BtnFind.getText().equals("Modify")){
            Application app = new Application() {
                @Override
                public void start(Stage stage) throws Exception {
                    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/GUI/Modify"+currentResource+".fxml"));
                    ModifyController controller = currentModifyController;
                    controller.setObject(selectedObject);
                    fxmlLoader.setController(controller);
                    Parent root = fxmlLoader.load();
                    Scene scene = new Scene(root);
                    stage.setTitle("Modify"+currentResource);
                    stage.setScene(scene);
                    stage.show();
                    stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                        @Override
                        public void handle(WindowEvent windowEvent) {
                            reload( currentDAO.getAll());
                        }
                    });
                }
            };
            app.start(new Stage());
        }
    }

    @FXML
    public void BtnAddAction(ActionEvent evt) throws Exception {
        if(BtnAdd.getText().equals("Add")){
            Application app = new Application() {
                @Override
                public void start(Stage stage) throws Exception {
                    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/GUI/Add"+currentResource+".fxml"));
                    AddController controller = currentAddController;
                    fxmlLoader.setController(controller);
                    Parent root = fxmlLoader.load();
                    Scene scene = new Scene(root);
                    stage.setTitle("Add"+currentResource);
                    stage.setScene(scene);
                    stage.show();
                    stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                        @Override
                        public void handle(WindowEvent windowEvent) {
                            reload( currentDAO.getAll());
                        }
                    });
                }
            };
            app.start(new Stage());
        }else if(BtnAdd.getText().equals("Delete")){
            currentDAO.delete(selectedObject);
            reload(currentDAO.getAll());
        }
    }

    // Indepedent

    private void Load(ArrayList data){
        Table.getColumns().clear();
        for(int i = 0 ; i < columns.size() ; i++) {
            TableColumn col = new TableColumn(columns.get(i));
            boolean b = Table.getColumns().add(col);
            System.out.println(b);
            col.setCellValueFactory(new PropertyValueFactory<User, String>(columns.get(i).toLowerCase().toString()));
        }

        ObservableList coldata = FXCollections.observableArrayList(data);
        Table.setItems(coldata);
    }

    private void reload(ResultSet rs){
        Method InstanceMethod = null;
        try {
            InstanceMethod = DashboardController.class.getMethod(currentLoader, ResultSet.class);
            DashboardController Instance = new DashboardController();
            ArrayList data = (ArrayList)InstanceMethod.invoke(Instance, rs);
            Load(data);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    // USERS INTERFACE LOADER AND VIEW

    @FXML
    public void BtnUsersAction(ActionEvent evt){
        userView();
    }

    public ArrayList<User> LoadUserData(ResultSet rs){
        ResultsetConvert rsc = new ResultsetConvert(rs);
        ArrayList<User> users = rsc.toUser();
        columns = rsc.getColumns();
        return users;
    }

    public void userView(){
        currentDAO = new UserDAO();
        currentModifyController = new ModifyUserController(this);
        currentAddController = new AddUserController(this);
        currentResource = "User";
        currentLoader = "LoadUserData";
        LblFind.setText("Username ");
        ArrayList data = LoadUserData(currentDAO.getAll());
        Load(data);
    }

    // Article

    @FXML
    public void BtnArticlesAction(ActionEvent evt){
        articleView();
    }

    public ArrayList<Article> LoadArticleData(ResultSet rs){
        ResultsetConvert rsc = new ResultsetConvert(rs);
        ArrayList<Article> articles = rsc.toArticle();
        columns = rsc.getColumns();
        return articles;
    }

    public void articleView(){
        currentDAO = new ArticleDAO();
        currentModifyController = new ModifyArticleController(this);
        currentAddController = new AddArticleController(this);
        currentResource = "Article";
        currentLoader = "LoadArticleData";
        LblFind.setText("Article ");
        ArrayList data = LoadArticleData(currentDAO.getAll());
        Load(data);
    }

    // Clients

    @FXML
    public void BtnClientsAction(ActionEvent evt){
        clientView();
    }

    public ArrayList<Client> LoadClientData(ResultSet rs){
        ResultsetConvert rsc = new ResultsetConvert(rs);
        ArrayList<Client> clients = rsc.toClient();
        columns = rsc.getColumns();
        return clients;
    }

    public void clientView(){
        currentDAO = new ClientDAO();
        currentModifyController = new ModifyClientController(this);
        currentAddController = new AddClientController(this);
        currentResource = "Client";
        currentLoader = "LoadClientData";
        LblFind.setText("Client");
        ArrayList data = LoadClientData(currentDAO.getAll());
        Load(data);
    }

    // Fournisseurs

    @FXML
    public void BtnFournisseursAction(ActionEvent evt){
        fournisseurView();
    }

    public ArrayList<Fournisseur> LoadFournisseurData(ResultSet rs){
        ResultsetConvert rsc = new ResultsetConvert(rs);
        ArrayList<Fournisseur> fournisseurs = rsc.toFournisseur();
        columns = rsc.getColumns();
        return fournisseurs;
    }

    public void fournisseurView(){
        currentDAO = new FournisseurDAO();
        currentModifyController = new ModifyFournisseurController(this);
        currentAddController = new AddFournisseurController(this);
        currentResource = "Fournisseur";
        currentLoader = "LoadFournisseurData";
        LblFind.setText("Fournisseur");
        ArrayList data = LoadFournisseurData(currentDAO.getAll());
        Load(data);
    }

    //Facture

    @FXML
    public void BtnFacturesAction(ActionEvent evt){
        factureView();
    }

    public ArrayList<Facture> LoadFactureData(ResultSet rs){
        ResultsetConvert rsc = new ResultsetConvert(rs);
        ArrayList<Facture> factures = rsc.toFacture();
        columns = rsc.getColumns();
        return factures;
    }

    public void factureView(){
        currentDAO = new FactureDAO();
        currentModifyController = new ModifyFactureController(this);
        currentAddController = new AddFactureController(this);
        currentResource = "Facture";
        currentLoader = "LoadFactureData";
        LblFind.setText("Facture");
        ArrayList data = LoadFactureData(currentDAO.getAll());
        Load(data);
    }


    // tmp
    public void reloadAll(){
        reload(currentDAO.getAll());
    }
    // tmp
}
