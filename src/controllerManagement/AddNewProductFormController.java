package controllerManagement;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
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

public class AddNewProductFormController {
    public AnchorPane addNewProductContext;
    public Label lblProductCode;
    public TextField txtName;
    public TextField txtDescription;
    public TextField txtListPrice;
    public TextField txtDiscount;
    public JFXButton saveProductBtn;

    LinkedHashMap<TextField, Pattern> map=new LinkedHashMap<>();
    Pattern namePattern = Pattern.compile("^[A-z ]+[-][1-9 0-9]*[A-z]{1,2}$");
    Pattern descPattern = Pattern.compile("^[A-z0-9/ ]*$");
    Pattern discountPattern = Pattern.compile("[1-90-9]{1,2}");
    Pattern pricePattern = Pattern.compile("^[1-9][0-9]*([.][0-9]{1,2})?$");

    public void initialize(){
        setProductCode();

        saveProductBtn.setDisable(true);
        storeValidations();
    }


    void setProductCode(){
        try {
            lblProductCode.setText(new ProductController().getProductCode());
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }

    public void saveDataOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Product p1 = new Product(
                lblProductCode.getText(),txtName.getText(),txtDescription.getText(),Integer.parseInt(txtDiscount.getText()),
                 Double.parseDouble(txtListPrice.getText())
        );

        txtName.clear();txtDescription.clear();txtDiscount.clear();txtListPrice.clear();


        if(new ProductController().saveProduct(p1)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Successfully Saved..!").show();
            setProductCode();
        }else {
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
        }
    }

    private void storeValidations() {
        map.put(txtName, namePattern);
        map.put(txtDescription, descPattern);
        map.put(txtListPrice, pricePattern);
        map.put(txtDiscount, discountPattern);
    }

    public void textFields_Key_Released(KeyEvent keyEvent) {
        Object response = ValidationUtil.validate(map,saveProductBtn);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField) {
                TextField errorText = (TextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {

            }
        }
    }
}
