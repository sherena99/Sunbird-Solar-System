package controllerManagement;

import com.jfoenix.controls.JFXButton;
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
import util.ValidationUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class AddNewCROFormController {
    public AnchorPane addNewCustomerFormContext;
    public Label lblCroId;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtMobileNum;
    public TextField txtSalary;
    public TextField txtCity;
    public JFXButton saveCroBtn;
    public TextField txtCode;


    LinkedHashMap<TextField, Pattern> map=new LinkedHashMap<>();
    Pattern namePattern = Pattern.compile("^[A-z ]{3,20}$");
    Pattern addressPattern = Pattern.compile("^[A-z0-9/ ]{6,30}$");
    Pattern mobilePattern = Pattern.compile("^[0-9]{10}$");
    Pattern salaryPattern = Pattern.compile("^[1-9][0-9]*([.][0-9]{1,2})?$");
    Pattern codePattern = Pattern.compile("^[A-z ]*(\\([A-z]+\\))$");

    public void initialize(){

            setCROId();

           saveCroBtn.setDisable(true);
           storeValidations();
    }

    void setCROId(){
        try {
            lblCroId.setText(new CROController().getCROId());
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }

    public void saveDataOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        CRO c1 = new CRO(
                lblCroId.getText(),txtName.getText(),
                txtAddress.getText(),txtCode.getText(), txtMobileNum.getText(), Double.parseDouble(txtSalary.getText())
        );

        clearTextField();


        if(new CROController().saveCRO(c1)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Successfully Saved..!").show();
            setCROId();
        }else {
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
        }

    }

    public void clearTextField(){
        txtName.clear();txtCode.clear();txtAddress.clear();txtMobileNum.clear();txtSalary.clear();
    }


   private void storeValidations() {
        map.put(txtName, namePattern);
        map.put(txtAddress, addressPattern);
        map.put(txtMobileNum, mobilePattern);
        map.put(txtSalary, salaryPattern);
        map.put(txtCode, codePattern);
    }

    public void textFields_Key_Released(KeyEvent keyEvent) {
        Object response = ValidationUtil.validate(map,saveCroBtn);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField) {
                TextField errorText = (TextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {
            }
        }
    }
}


