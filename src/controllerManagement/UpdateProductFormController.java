package controllerManagement;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import model.CRO;
import model.Product;
import util.ValidationUtil;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class UpdateProductFormController {
    public AnchorPane updateProductFormContext;
    public TextField txtName;
    public TextField txtDescription;
    public TextField txtListPrice;
    public TextField txtDiscount;
    public TextField txtProductCode;
    public JFXButton updateProductBtn;

    LinkedHashMap<TextField, Pattern> map=new LinkedHashMap<>();
    Pattern codePattern = Pattern.compile("^(P-)[0-91-9]{3}");
    Pattern namePattern = Pattern.compile("^[A-z ]+[-][1-9 0-9]*[A-z]$");
    Pattern descPattern = Pattern.compile("^[A-z0-9/ ]{6,30}$");
    Pattern discountPattern = Pattern.compile("[1-90-9]{1,2}");
    Pattern pricePattern = Pattern.compile("^[1-9][0-9]*([.][0-9]{1,2})?$");

    public void initialize(){

        updateProductBtn.setDisable(true);
        storeValidations();
    }


    public void updateProductOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Product p1 = new Product(
                txtProductCode.getText(),txtName.getText(),txtDescription.getText(),Integer.parseInt(txtDiscount.getText()),
                 Double.parseDouble(txtListPrice.getText())
        );

        clearTextField();

        if (new ProductController().updateProduct(p1)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Updated..").show();
            txtProductCode.setDisable(false);

        }else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
            txtProductCode.setDisable(false);
        }
    }

    public void searchProductOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String code = txtProductCode.getText();

        Product p1 = new ProductController().searchProduct(code);
        if (p1 == null) {
            new Alert(Alert.AlertType.WARNING, "There is not any Product code as : "+txtProductCode.getText()).show();
            txtProductCode.setDisable(false);
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

    private void storeValidations() {
        map.put(txtProductCode, codePattern);
        map.put(txtName, namePattern);
        map.put(txtDescription, descPattern);
        map.put(txtListPrice, pricePattern);
        map.put(txtDiscount, discountPattern);
    }

    public void textFields_Key_Released(KeyEvent keyEvent) {
        Object response = ValidationUtil.validate(map,updateProductBtn);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField) {
                TextField errorText = (TextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {

            }
        }
    }
}
