package controllerManagement;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.CRO;
import model.Product;

import java.sql.SQLException;

public class DeleteProductFormController {
    public AnchorPane deleteProductFormContext;
    public TextField txtName;
    public TextField txtDescription;
    public TextField txtListPrice;
    public TextField txtDiscount;
    public TextField txtProductCode;


    public void deleteProductOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (new ProductController().deleteProduct(txtProductCode.getText())) {

            clearTextField();

            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }

    public void searchProductOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String code = txtProductCode.getText();

        Product p1 = new ProductController().searchProduct(code);
        if (p1 == null) {
            new Alert(Alert.AlertType.WARNING, "There is not any Product code as : "+txtProductCode.getText()).show();
            txtProductCode.clear();
        } else {
            setData(p1);
        }
    }

    void setData(Product p) {
        txtProductCode.setText(p.getCode());
        txtName.setText(p.getName());
        txtDescription.setText(p.getDescription());
        txtDiscount.setText(String.valueOf(p.getDiscount()));
        txtListPrice.setText(String.valueOf(p.getPrice()));
    }

    public void clearTextField(){
        txtProductCode.clear(); txtName.clear(); txtDescription.clear(); txtDiscount.clear();txtListPrice.clear();

    }
}
