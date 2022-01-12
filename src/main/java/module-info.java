module com.example.javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;

    opens GUI to javafx.fxml;
    exports GUI.Main;
    opens GUI.Main to javafx.fxml;
    exports GUI.Controllers;
    opens GUI.Controllers to javafx.fxml;

    opens Models to javafx.fxml;
    exports Models;
    exports GUI.Controllers.AddControllers;
    opens GUI.Controllers.AddControllers to javafx.fxml;
    exports GUI.Controllers.ModifyControllers;
    opens GUI.Controllers.ModifyControllers to javafx.fxml;
    opens Utils to javafx.fxml;
    exports Utils;
}