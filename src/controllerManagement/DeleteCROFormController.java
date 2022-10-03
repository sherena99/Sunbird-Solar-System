package controllerManagement;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.CRO;

import java.sql.SQLException;

public class DeleteCROFormController {
    public AnchorPane deleteCroFormContext;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtMobileNum;
    public TextField txtSalary;
    public TextField txtCroId;
    public TextField txtCode;


    public void deleteCroOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (new CROController().deleteCRO(txtCroId.getText())) {

            clearTextField();

            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }


    public void searhCroOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id = txtCroId.getText();

        CRO i1 = new CROController().searchCRO(id);
        if (i1 == null) {
            new Alert(Alert.AlertType.WARNING, "There is not any CRO as : "+txtCroId.getText()).show();
            txtCroId.clear();
        } else {
            setData(i1);
        }
    }

    void setData(CRO c) {
        txtCroId.setText(c.getId());
        txtName.setText(c.getName());
        txtAddress.setText(c.getAddress());
        txtCode.setText(c.getCode());
        txtMobileNum.setText(c.getMobile());
        txtSalary.setText(String.valueOf(c.getSalary()));
    }

    public void clearTextField(){
        txtCroId.clear();txtName.clear();txtCode.clear();txtSalary.clear();txtAddress.clear();txtMobileNum.clear();
    }

}



