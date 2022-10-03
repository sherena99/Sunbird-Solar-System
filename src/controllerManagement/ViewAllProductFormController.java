package controllerManagement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.CRO;
import model.Product;
import view.tm.CROTm;
import view.tm.ProductTm;

import java.sql.SQLException;
import java.util.ArrayList;

public class ViewAllProductFormController {
    public AnchorPane viewAllProductContext;
    public TableView <ProductTm>tblProduct;
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

}
