package controllerReceptionist;

import com.jfoenix.controls.JFXButton;
import controllerManagement.CROController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import model.Complain;
import model.Customer;
import model.Inquiry;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Pattern;

public class CustomerComplainFormController {
    public AnchorPane CustomerComplainFormContext;
    public TextField txtCustomerName;
    public TextField txtComplainAbout;
    public Label lblComplainNum;
    public TextField txtCroCode;
    public JFXButton btnSaveComplainOnAction;
    public Label lblDate;
    public Label lblTime;
    public TextField txtComplainHandleOfficer;
    public TextArea txtComplainDetails;
    public ComboBox <String> cmbInquiryCode;



    public void initialize() {
        setComplainNumber();
        loadDateAndTime();


        try {
            loadInquiryCodes();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        ///////////////////////////////////////

        cmbInquiryCode.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    getCustomerName(newValue);
                    getCroCode(newValue);

                });

    }


    private void loadInquiryCodes() throws SQLException, ClassNotFoundException {
        List<String> croIds = new InquiryController().getAllInquiryCodes();
        cmbInquiryCode.getItems().addAll(croIds);
    }

    public void getCustomerName(String id){
        try {
            String nameByCode = InquiryController.getCustomerNameByCode(id);
            txtCustomerName.setText(nameByCode);

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public void getCroCode(String id){
        try {
            String croByCode = InquiryController.getCroCodeByCode(id);
            txtCroCode.setText(croByCode);

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }


    void setComplainNumber() {
        try {
            lblComplainNum.setText(new ComplainController().getComplainNumber());
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }

    public void btnSaveComplainOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Complain com1 = new Complain(
                lblComplainNum.getText(),lblTime.getText(),lblDate.getText(), cmbInquiryCode.getValue(),
                txtCustomerName.getText(),txtCroCode.getText(),txtComplainAbout.getText(),txtComplainDetails.getText(), txtComplainHandleOfficer.getText()
        );

        clearTextField();


        if(new ComplainController().saveComplain(com1)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Successfully Saved..!").show();
            setComplainNumber();
        }else {
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
        }

    }

    private void clearTextField() {
        txtCustomerName.clear();
        txtCroCode.clear();
        txtComplainDetails.clear();
        txtComplainAbout.clear();
        txtComplainHandleOfficer.clear();
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


}


