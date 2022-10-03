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
import model.Product;
import view.tm.ProductTm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

public class ManageProductFormController {
    public AnchorPane manageProductFormContext;
    public AnchorPane manageProductInsideFormContext;
    public TableView tblProduct;
    public TableColumn colCode;
    public TableColumn colName;
    public TableColumn colDescription;
    public TableColumn colDiscount;
    public TableColumn colListPrice;

    public void initialize(){
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        colListPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        try {
            setProductDetailsToTable(new ProductController().getAllProduct());
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }

    public void setProductDetailsToTable(ArrayList<Product> product) {
        ObservableList<ProductTm> obList = FXCollections.observableArrayList();
        product.forEach(e -> {
            obList.add(
                    new ProductTm(e.getCode(), e.getName(), e.getDescription(), e.getDiscount(), e.getPrice()));
        });
        tblProduct.setItems(obList);

    }


    public void viewAllProductOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../viewManagement/ViewAllProductForm.fxml");
        Parent load = FXMLLoader.load(resource);
        manageProductInsideFormContext.getChildren().clear();
        manageProductInsideFormContext.getChildren().add(load);
    }

    public void deleteProductOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../viewManagement/DeleteProductForm.fxml");
        Parent load = FXMLLoader.load(resource);
        manageProductInsideFormContext.getChildren().clear();
        manageProductInsideFormContext.getChildren().add(load);
    }

    public void updateProductOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../viewManagement/UpdateProductForm.fxml");
        Parent load = FXMLLoader.load(resource);
        manageProductInsideFormContext.getChildren().clear();
        manageProductInsideFormContext.getChildren().add(load);
    }

    public void addNewProductOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../viewManagement/AddNewProductForm.fxml");
        Parent load = FXMLLoader.load(resource);
        manageProductInsideFormContext.getChildren().clear();
        manageProductInsideFormContext.getChildren().add(load);
    }

    public void cancelButtonOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/MDashBoardForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) manageProductInsideFormContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }
}
