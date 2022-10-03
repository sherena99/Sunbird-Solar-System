package controllerManagement;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import model.CRO;
import util.ValidationUtil;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class UpdateCROFormController {
    public AnchorPane updateCROFormContext;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtMobileNum;
    public TextField txtSalary;
    public TextField txtCroId;
    public TextField txtCode;
    public JFXButton updateCroBtn;

    LinkedHashMap<TextField, Pattern> map=new LinkedHashMap<>();
    Pattern idPattern = Pattern.compile("^(CRO-)[0-91-9]{3}$");
    Pattern namePattern = Pattern.compile("^[A-z ]{3,20}$");
    Pattern addressPattern = Pattern.compile("^[A-z0-9/ ]{6,30}$");
    Pattern mobilePattern = Pattern.compile("^[0-9]{10}$");
    Pattern salaryPattern = Pattern.compile("^[1-9][0-9]*([.][0-9]{1,2})?$");
    Pattern codePattern = Pattern.compile("^[A-z ]*(\\([A-z]+\\))$");

    public void initialize(){

        updateCroBtn.setDisable(true);
        storeValidations();
    }


    public void updateCroOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        CRO c1 = new CRO(
                txtCroId.getText(),txtName.getText(),txtAddress.getText(),txtCode.getText(),
                txtMobileNum.getText(), Double.parseDouble(txtSalary.getText())
        );
        clearTextField();

        if (new CROController().updateCRO(c1)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Updated..").show();
            txtCroId.setDisable(false);

        }else{
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
            txtCroId.setDisable(false);
        }
    }

    public void searchCroOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id = txtCroId.getText();
        txtCroId.setDisable(true);

        CRO i1 = new CROController().searchCRO(id);
        if (i1 == null) {
            new Alert(Alert.AlertType.WARNING, "There is not any CRO as : "+txtCroId.getText()).show();
            txtCroId.setDisable(false);
        } else {
            setData(i1);
        }
    }

    void setData(CRO c) {
        txtCroId.setText(c.getId());
        txtName.setText(c.getName());
        txtAddress.setText(c.getAddress());
        txtMobileNum.setText(c.getMobile());
        txtSalary.setText(String.valueOf(c.getSalary()));
        txtCode.setText(c.getCode());

    }

    public void clearTextField(){
        txtCroId.clear();txtName.clear();txtCode.clear();txtSalary.clear();txtAddress.clear();txtMobileNum.clear();
    }



    public void textFields_Key_Released(KeyEvent keyEvent) {
        Object response = ValidationUtil.validate(map, updateCroBtn);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField) {
                TextField errorText = (TextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {
            }
        }
    }
        private void storeValidations () {
            map.put(txtCroId, idPattern);
            map.put(txtName, namePattern);
            map.put(txtAddress, addressPattern);
            map.put(txtMobileNum, mobilePattern);
            map.put(txtSalary, salaryPattern);
            map.put(txtCode, codePattern);
        }
    }

