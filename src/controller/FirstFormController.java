package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class FirstFormController {
    public AnchorPane firstFormContext;
    public JFXButton managementLogInBtn;
    public JFXButton receptionistLogInBtn;

    public void initialize(){
        receptionistLogInBtn.requestFocus();
    }
    public void receptionistLogInBtnOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/RLogInForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) firstFormContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void managementLogInBtnOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/MLogInForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) firstFormContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }
}
