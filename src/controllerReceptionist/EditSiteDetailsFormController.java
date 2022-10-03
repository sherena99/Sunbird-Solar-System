package controllerReceptionist;

import com.jfoenix.controls.JFXButton;
import controllerManagement.ProductController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Product;
import model.Site;
import util.ValidationUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class EditSiteDetailsFormController {
    public AnchorPane editSiteFormContext;
    public TextField txtCharges;
    public TextArea txtNotes;
    public JFXButton btnSaveSiteOnAction;
    public TextField txtAddress;
    public TextField txtInquiryCode;
    public TextField txtRoof;
    public TextField txtSiteType;
    public TextField txtDirection;
    public TextField txtExtraWork;
    public Label lblDate;
    public Label lblTime;

    LinkedHashMap<TextField, Pattern> map=new LinkedHashMap<>();
    Pattern inquiryCode = Pattern.compile("^(I-)[0-91-9]{3}$");
    Pattern siteType = Pattern.compile("^(House|Flat|Office|Villa|Government Site|Library|Church|Temple|Institute|Class Hole|Apartment|Else)$");
    Pattern roof = Pattern.compile("^(Lanka Ulu|Roof tiles|Roofing Sheets|Asbestos Roof|Metal Roof|Asphalt shingle|Else)$");
    Pattern direction = Pattern.compile("^(North|South|West|East)$");
    Pattern extraWork = Pattern.compile("^(Plumbing|Remove tanks and setting|Extra PVC|Add new part|Else| - )$");
    Pattern charges = Pattern.compile("^[1-9][0-9]*([.][0-9]{1,2})?$");

    public void initialize() {

        btnSaveSiteOnAction.setDisable(true);
        storeValidations();
    }


        public void updateSiteBtnOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Site s1 = new Site(
                lblDate.getText(),lblTime.getText(),txtInquiryCode.getText(),txtAddress.getText(),txtSiteType.getText(),txtAddress.getText(),
                txtDirection.getText(),txtExtraWork.getText(), Double.parseDouble(txtCharges.getText()),txtNotes.getText()
        );

        clearTextFeild();


        if (new SiteController().updateSiteDetails(s1)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Updated..").show();
            txtInquiryCode.setDisable(false);

        }else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
            txtInquiryCode.setDisable(false);
        }
    }

    public void SearchInquiry(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String code = txtInquiryCode.getText();
        //txtInquiryCode.setDisable(true);

        Site s1 = new SiteController().searchSiteDetails(code);
        if (s1 == null) {
            new Alert(Alert.AlertType.WARNING, "There is not any Product code as : "+txtInquiryCode.getText()).show();
            txtInquiryCode.setDisable(false);
        } else {
            setData(s1);
        }
    }

    void setData(Site s) {
        lblDate.setText(s.getDate());
        lblTime.setText(s.getTime());
        txtAddress.setText(s.getAddress());
        txtSiteType.setText(s.getSiteType());
        txtRoof.setText(s.getRoof());
        txtDirection.setText(s.getDirection());
        txtExtraWork.setText(s.getExtraWork());
        txtCharges.setText(String.valueOf(s.getCharges()));
        txtNotes.setText(s.getNote());

    }

    public void cancelButtonOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/DashBoardForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) editSiteFormContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void clearTextFeild(){
        txtInquiryCode.clear();txtCharges.clear();txtNotes.clear();txtRoof.clear();txtAddress.clear();txtSiteType.clear();txtExtraWork.clear();
        txtDirection.clear();
    }

    private void storeValidations() {
        map.put(txtInquiryCode, inquiryCode);
        map.put(txtSiteType, siteType);
        map.put(txtRoof, roof);
        map.put(txtDirection, direction);
        map.put(txtExtraWork, extraWork);
        map.put(txtCharges, charges);
    }

    public void textFields_Key_Released(KeyEvent keyEvent) {
        Object response = ValidationUtil.validate(map,btnSaveSiteOnAction);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField) {
                TextField errorText = (TextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {
            }
        }
    }
}
