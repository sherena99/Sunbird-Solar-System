package controllerReceptionist;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Customer;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

public class DeleteCustomerFormController {
    public AnchorPane deleteCustomerFormContext;
    public TextField txtTitle;
    public TextField txtAddress;
    public TextField txtNic;
    public TextField txtName;
    public TextField txtMobile;
    public TextField txtEmail;
    public TextField textId;

    public void deleteCustomerOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (new CustomerController().deleteCustomer(textId.getText())) {

            clearTextField();

            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }

    public void searchCustomerOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id = textId.getText();

        Customer c1 = new CustomerController().searchCustomer(id);
        if (c1 == null) {
            new Alert(Alert.AlertType.WARNING, "There is not any customer as : "+textId.getText()).show();
            textId.clear();
        } else {
            setData(c1);
        }
    }

    void setData(Customer c) {
        textId.setText(c.getId());
        txtTitle.setText(c.getTitle());
        txtName.setText(c.getName());
        txtAddress.setText(c.getAddress());
        txtNic.setText(c.getNic());
        txtMobile.setText(c.getMobile());
        txtEmail.setText(c.getEmail());


    }

    public void clearTextField(){
        textId.clear();txtTitle.clear();txtName.clear();txtAddress.clear();txtNic.clear();txtMobile.clear();txtEmail.clear();
    }

    public void cancelButtonOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/MDashBoardForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) deleteCustomerFormContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }
}
