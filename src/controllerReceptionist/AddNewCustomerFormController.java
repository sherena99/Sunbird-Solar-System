package controllerReceptionist;

import com.jfoenix.controls.JFXButton;
import controllerManagement.CROController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.CRO;
import model.Customer;
import util.ValidationUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class AddNewCustomerFormController {
    public AnchorPane addNewCustomerContext;
    public TextField txtTitle;
    public TextField txtAddress;
    public TextField txtNic;
    public TextField txtName;
    public TextField txtMobile;
    public TextField txtEmail;
    public Label lblCustomerId;
    public JFXButton saveCustomerBtn;

    LinkedHashMap<TextField, Pattern> map=new LinkedHashMap<>();
    Pattern titlePattern = Pattern.compile("^(Ms.|Mrs.|Mr.|Miss.|miss.|ms.|mrs.|mr.)$");
    Pattern namePattern = Pattern.compile("^[A-z ]{3,20}$");
    Pattern addressPattern = Pattern.compile("^[A-z0-9/ ]{6,30}$");
    Pattern nicPattern = Pattern.compile("^[1-9][0-9]{8,15}(V)?$");
    Pattern mobilePattern = Pattern.compile("^[0-9]{10}$");
    Pattern emailPattern = Pattern.compile("^[a-z1-9]+[@][a-z]+(.com|.lk|.yahoo)$");

    public void initialize(){

        setCustomerId();

        saveCustomerBtn.setDisable(true);
        storeValidations();

    }

    void setCustomerId(){
        try {
            lblCustomerId.setText(new CustomerController().getCustomerId());
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }


    public void saveCustomerOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
            Customer c1 = new Customer(
                    lblCustomerId.getText(),txtTitle.getText(),txtName.getText(),
                    txtAddress.getText(), txtNic.getText(),txtMobile.getText(),txtEmail.getText()
            );

            clearTextField();


            if(new CustomerController().saveCustomer(c1)) {
                new Alert(Alert.AlertType.CONFIRMATION, "Successfully Saved..!").show();

                //setCustomerId();
                URL resource = getClass().getResource("../viewReceptionist/AddNewInquiry.fxml");
                Parent load = FXMLLoader.load(resource);
                Stage window = (Stage) addNewCustomerContext.getScene().getWindow();
                window.setScene(new Scene(load));
            }else {
                new Alert(Alert.AlertType.WARNING, "Try Again..").show();
            }

        }
    public void clearTextField(){
        txtName.clear();txtAddress.clear();txtTitle.clear();txtMobile.clear();txtNic.clear();txtEmail.clear();
    }

    public void cancelButtonOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../viewReceptionist/AddNewInquiry.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) addNewCustomerContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    private void storeValidations() {
        map.put(txtTitle, titlePattern);
        map.put(txtName, namePattern);
        map.put(txtAddress, addressPattern);
        map.put(txtNic, nicPattern);
        map.put(txtMobile, mobilePattern);
        map.put(txtEmail, emailPattern);
    }

    public void textFields_Key_Released(KeyEvent keyEvent) {
        Object response = ValidationUtil.validate(map,saveCustomerBtn);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField) {
                TextField errorText = (TextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {
            }
        }
    }
}
