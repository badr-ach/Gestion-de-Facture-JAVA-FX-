package GUI.Controllers.AddControllers;

import DAO.LigneFactureDAO;
import GUI.Controllers.DashboardController;
import GUI.Controllers.ModifyControllers.ModifyLigneFactureController;
import GUI.Main.Main;
import Models.LigneFacture;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddToFactureController implements Initializable {

    private String idFacture;

    @FXML
    private TableView Table;

    @FXML
    private Button BtnFind;

    @FXML
    private Button BtnAddLigne;

    @FXML
    private Button BtnConfirm;

    @FXML
    private TextField TxtFind;

    private DashboardController d;
    private LigneFacture selectedObject;

    public AddToFactureController(){}

    public AddToFactureController(String idFacture,DashboardController d){
        this.idFacture = idFacture;
        this.d = d;
    }

    public void reload(ResultSet rs){
        ResultsetConvert rsc = new ResultsetConvert(rs);
        ArrayList lignes = rsc.toLigneFacture();
        ObservableList coldata = FXCollections.observableArrayList(lignes);
        Table.setItems(coldata);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //POPULATE TABLE :

        ResultSet rs = new LigneFactureDAO().getAllByFacture(idFacture);
        System.out.println(rs);
        ResultsetConvert rsc = new ResultsetConvert(rs);
        ArrayList lignes = rsc.toLigneFacture();
        List<String> columns = rsc.getColumns();
        Table.getColumns().clear();

        for(int i = 0 ; i < columns.size() ; i++) {
            TableColumn col = new TableColumn(columns.get(i));
            boolean b = Table.getColumns().add(col);
            System.out.println(b);
            col.setCellValueFactory(new PropertyValueFactory<LigneFacture, String>(columns.get(i).toLowerCase().toString()));
        }

        ObservableList coldata = FXCollections.observableArrayList(lignes);
        Table.setItems(coldata);


        // Selection listener

        Table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                BtnFind.setText("Modify");
                BtnAddLigne.setText("Delete");
                selectedObject = ((LigneFacture) Table.getSelectionModel().selectedItemProperty().get());
            }else{
                BtnFind.setText("Find");
                BtnAddLigne.setText("Add");
            }
        });
    }

    @FXML
    public void BtnFindAction(ActionEvent evt) throws Exception {
        if(BtnFind.getText().equals("Find")) {
            String text = TxtFind.getText();
            if (!text.isEmpty()) {
                reload(new LigneFactureDAO().getAllByTwoIds(idFacture,text));
            } else {
                reload(new LigneFactureDAO().getAllByFacture(idFacture));
            }
        }else if(BtnFind.getText().equals("Modify")){
            ModifyLigneFactureController controller = new ModifyLigneFactureController(idFacture,this);
            Application app = new Application() {
                @Override
                public void start(Stage stage) throws Exception {
                    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/GUI/ModifyLigneFacture.fxml"));
                    controller.setObject(selectedObject);
                    fxmlLoader.setController(controller);
                    Parent root = fxmlLoader.load();
                    Scene scene = new Scene(root);
                    stage.setTitle("Modify Ligne Facture");
                    stage.setScene(scene);
                    stage.show();
                    stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                        @Override
                        public void handle(WindowEvent windowEvent) {
                            reload( new LigneFactureDAO().getAllByFacture(idFacture));
                        }
                    });
                }
            };
            app.start(new Stage());
        }
    }

    @FXML
    public void BtnAddAction(ActionEvent evt) throws Exception {
        if(BtnAddLigne.getText().equals("Add")){
            AddLigneFactureController controller = new AddLigneFactureController(idFacture,this);
            Application app = new Application() {
                @Override
                public void start(Stage stage) throws Exception {
                    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/GUI/AddLigneFacture.fxml"));
                    fxmlLoader.setController(controller);
                    Parent root = fxmlLoader.load();
                    Scene scene = new Scene(root);
                    stage.setTitle("Add Ligne Facture");
                    stage.setScene(scene);
                    stage.show();
                    stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                        @Override
                        public void handle(WindowEvent windowEvent) {
                            reload( new LigneFactureDAO().getAllByFacture(idFacture));
                        }
                    });
                }
            };
            app.start(new Stage());
        }else if(BtnAddLigne.getText().equals("Delete")){
            LigneFactureDAO dao = new LigneFactureDAO();
            dao.delete(selectedObject);
            reload(dao.getAllByFacture(idFacture));
        }
    }

    @FXML
    public void BtnConfirmAction(ActionEvent evt){
        Stage stage = (Stage)BtnConfirm.getScene().getWindow();
        d.reloadAll();
        stage.close();
    }
}
