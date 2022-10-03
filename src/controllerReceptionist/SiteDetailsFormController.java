package controllerReceptionist;

import com.jfoenix.controls.JFXButton;
import controllerManagement.ProductController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Product;
import model.Site;
import util.ValidationUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Pattern;

public class SiteDetailsFormController {
    public AnchorPane siteDetailsFormContext;
    public Label lblSiteId;
    public ComboBox<String> cmbInquiryCode;
    public TextField txtSiteType;
    public TextField txtRoof;
    public TextField txtDirection;
    public TextField txtExtraWork;
    public TextField txtCharges;
    public TextArea txtNotes;
    public JFXButton btnSaveSiteOnAction;
    public Label lblDate;
    public Label lblTime;
    public TextField txtAddress;
    public TextField txtInquiryCode;
    public ComboBox<String> cmbSiteType;
    public ComboBox<String> cmbRoof;
    public ComboBox<String> cmbDirection;
    public ComboBox<String> cmbExtraWork;
    public TextField txtStatus;

    LinkedHashMap<TextField, Pattern> map=new LinkedHashMap<>();
    Pattern inquiryCode = Pattern.compile("^(I-)[0-91-9]{3}$");
    Pattern charges = Pattern.compile("^[1-9][0-9]*([.][0-9]{1,2})?$");
    Pattern status = Pattern.compile("^Success$");


    public void initialize() {

        loadDateAndTime();

        btnSaveSiteOnAction.setDisable(true);
        storeValidations();

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        cmbSiteType.getItems().addAll(
                "House", "Flat", "Office", "Villa", "Government Site", "Library", "Church", "Temple", "Institute", "Class Hole", "Apartment","Else"
        );

        cmbRoof.getItems().addAll(
                "Lanka Ulu", "Roof tiles", "Roofing Sheets", "Asbestos Roof", "Metal Roof", "Asphalt shingle","Else"
        );

        cmbDirection.getItems().addAll(
                "North", "South", "West", "East"
        );

        cmbExtraWork.getItems().addAll(
                "Plumbing", "Remove tanks and setting", "Extra PVC", "Add new part", "Else", " - "
        );


    }


    public void btnSaveSiteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        Site s1 = new Site(
                lblDate.getText(), lblTime.getText(), txtInquiryCode.getText(), txtAddress.getText(),
                cmbSiteType.getValue(), cmbRoof.getValue(), cmbDirection.getValue(), cmbExtraWork.getValue(),
                Double.parseDouble(txtCharges.getText()), txtNotes.getText()
        );


        //clearTextField();
        String updateStatus = txtStatus.getText();

        SiteController sc = new SiteController();
        if (sc.saveSite(s1,updateStatus)) {

                new Alert(Alert.AlertType.CONFIRMATION, "Successfully Saved..!").show();
                URL resource = getClass().getResource("../view/DashBoardForm.fxml");
                Parent load = FXMLLoader.load(resource);
                Stage window = (Stage) siteDetailsFormContext.getScene().getWindow();
                window.setScene(new Scene(load));


        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
            txtInquiryCode.setDisable(false);
        }
    }



    private void loadDateAndTime() {
        // load Date
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(f.format(date));

        // load Time
        Timeline time = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            lblTime.setText(
                    currentTime.getHour() + " : " + currentTime.getMinute() +
                            " : " + currentTime.getSecond()
            );
        }),
                new KeyFrame(Duration.seconds(1))
        );
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }

    public void clearTextField() {
        txtInquiryCode.clear();
        txtAddress.clear();
        txtCharges.clear();
        txtNotes.clear();
        txtExtraWork.clear();
    }

    public void SearchInquiry(ActionEvent actionEvent) {
        String inquiryId = txtInquiryCode.getText();
        txtInquiryCode.setDisable(true);


        try {
            String addressByCode = SiteController.getSiteAddressByInquiryCode(inquiryId);
            String statusByCode = SiteController.getStatusByInquiryCode(inquiryId);
            if (addressByCode == null) {
                new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
                txtInquiryCode.setDisable(false);
            } else {
                txtAddress.setText(addressByCode);
                txtStatus.setText(statusByCode);
                txtInquiryCode.setDisable(false);
            }

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public void btnEditSiteDetails(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../viewReceptionist/EditSiteDetailsForm.fxml");
        Parent load = FXMLLoader.load(resource);
        siteDetailsFormContext.getChildren().clear();
        siteDetailsFormContext.getChildren().add(load);
    }

    public void btnViewAllSites(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../viewReceptionist/ViewSiteDetailsForm.fxml");
        Parent load = FXMLLoader.load(resource);
        siteDetailsFormContext.getChildren().clear();
        siteDetailsFormContext.getChildren().add(load);
    }

    private void storeValidations() {
        map.put(txtInquiryCode, inquiryCode);
        map.put(txtCharges, charges);
        map.put(txtStatus, status);
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
