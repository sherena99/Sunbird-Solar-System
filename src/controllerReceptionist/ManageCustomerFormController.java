package controllerReceptionist;

import com.jfoenix.controls.JFXButton;
import controllerManagement.CROController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.CRO;
import model.Customer;
import util.ValidationUtil;
import view.tm.CROTm;
import view.tm.CustomerTm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class ManageCustomerFormController {
    public AnchorPane manageCustomerContext;
    public TextField txtTitle;
    public TextField txtAddress;
    public TextField txtNic;
    public TextField txtName;
    public TextField txtMobile;
    public TextField txtEmail;
    public TextField textId;
    public TableView <CustomerTm> tblCustomer;
    public TableColumn colId;
    public TableColumn colTitle;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colNic;
    public TableColumn colMobile;
    public TableColumn colEmail;
    public JFXButton updateCustomerBtn;

    LinkedHashMap<TextField, Pattern> map=new LinkedHashMap<>();
    Pattern idPattern = Pattern.compile("^(C-)[0-91-9]{3}$");
    Pattern titlePattern = Pattern.compile("^(Ms.|Mrs.|Mr.|Miss.|miss.|ms.|mrs.|mr.)$");
    Pattern namePattern = Pattern.compile("^[A-z ]{3,20}$");
    Pattern addressPattern = Pattern.compile("^[A-z0-9/ ]{6,30}$");
    Pattern nicPattern = Pattern.compile("^[1-9][0-9]{8,15}(V)?$");
    Pattern mobilePattern = Pattern.compile("^[0-9]{10}$");
    Pattern emailPattern = Pattern.compile("^[a-z1-9]+[@][a-z]+(.com|.lk|.yahoo)$");


    public void initialize(){
        {
            updateCustomerBtn.setDisable(true);
            storeValidations();
        }


        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colNic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colMobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        try {
            setCustomerDetailsToTable(new CustomerController().getAllCustomers());
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }


    public void updateCustomerOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Customer c1 = new Customer(
                textId.getText(),txtTitle.getText(),txtName.getText(),txtAddress.getText(),
                txtNic.getText(), txtMobile.getText(),txtEmail.getText()
        );

        clearTextField();

        if (new CustomerController().updateCustomer(c1)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Updated..").show();
            textId.setDisable(false);
        }else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
            textId.setDisable(false);
        }

    }

    public void searchCustomerOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id = textId.getText();
        textId.setDisable(true);

        Customer c1 = new CustomerController().searchCustomer(id);
        if (c1 == null) {
            new Alert(Alert.AlertType.WARNING, "There is not any customer as : "+textId.getText()).show();
            textId.setDisable(false);
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

    public void setCustomerDetailsToTable(ArrayList<Customer> customer){
        ObservableList<CustomerTm> obList = FXCollections.observableArrayList();
        customer.forEach(e->{
            obList.add(
                    new CustomerTm(e.getId(),e.getTitle(),e.getName(),e.getAddress(),e.getNic(),e.getMobile(),e.getEmail()));
        });
        tblCustomer.setItems(obList);

    }

    public void cancelButtonOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/DashBoardForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) manageCustomerContext.getScene().getWindow();
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
        Object response = ValidationUtil.validate(map,updateCustomerBtn);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField) {
                TextField errorText = (TextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {
            }
        }
    }
}
