package controllerManagement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.CRO;
import view.tm.CROTm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

public class ManageCROFormController {
    public AnchorPane manageCroFormContext;
    public AnchorPane manageCroInsideFormContext;
    public TableView tblCro;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colMobile;
    public TableColumn colCode;
    public TableColumn colSalary;

    public void initialize(){

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colMobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));

        try {
            setCRODetailsToTable(new CROController().getAllCRO());
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }


    public void setCRODetailsToTable(ArrayList<CRO> cro){
        ObservableList<CROTm> obList = FXCollections.observableArrayList();
        cro.forEach(e->{
            obList.add(
                    new CROTm(e.getId(),e.getName(),e.getCode(),e.getAddress(),e.getMobile(),e.getSalary()));
        });
        tblCro.setItems(obList);

    }

    public void viewAllCroOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../viewManagement/ViewAllCroForm.fxml");
        Parent load = FXMLLoader.load(resource);
        manageCroInsideFormContext.getChildren().clear();
        manageCroInsideFormContext.getChildren().add(load);
    }

    public void deleteCroOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../viewManagement/DeleteCROForm.fxml");
        Parent load = FXMLLoader.load(resource);
        manageCroInsideFormContext.getChildren().clear();
        manageCroInsideFormContext.getChildren().add(load);
    }

    public void updateCroOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../viewManagement/UpdateCROForm.fxml");
        Parent load = FXMLLoader.load(resource);
        manageCroInsideFormContext.getChildren().clear();
        manageCroInsideFormContext.getChildren().add(load);
    }

    public void addNewCroOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../viewManagement/AddNewCROForm.fxml");
        Parent load = FXMLLoader.load(resource);
        manageCroInsideFormContext.getChildren().clear();
        manageCroInsideFormContext.getChildren().add(load);
    }

    public void cancelButtonOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/MDashBoardForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) manageCroInsideFormContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }
}
