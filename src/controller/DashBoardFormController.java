package controller;

import controllerReceptionist.InquiryController;
import controllerReceptionist.PaymentController;
import db.DbConnection;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Payment;
import view.tm.PaymentTm;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

public class DashBoardFormController {
    public AnchorPane dashBoardFormContext;
    public AnchorPane dashBoardInsideContext;
    public Label lblDate;
    public Label lblTime;
    public Label lblPendingInquiries;
    public Label lblSuccessInquiries;
    public Label lblAllInquiries;
    public TableView tblPayment;
    public TableColumn colDate;
    public TableColumn colTime;
    public TableColumn colPaymentId;
    public TableColumn colInquiry;
    public TableColumn colCustomer;
    public TableColumn colCroCode;
    public TableColumn colPaymentMethod;
    public TableColumn ColDiscount;
    public TableColumn colTotal;

    public void initialize(){
        loadDateAndTime();

        try {

            loadPendingInquiry();
            loadSuccessInquiry();
            loadAllInquiry();

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        ///////////////////////////////////////////////////////////

        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colPaymentId.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
        colInquiry.setCellValueFactory(new PropertyValueFactory<>("inquiryId"));
        colCustomer.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        colCroCode.setCellValueFactory(new PropertyValueFactory<>("croCode"));
        colPaymentMethod.setCellValueFactory(new PropertyValueFactory<>("paymentMethod"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        ColDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));

        try {

            setPaymentsDetailsToTable(new PaymentController().loadAllPayment());

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }

    public void setPaymentsDetailsToTable(ArrayList<Payment> payments) {
        ObservableList<PaymentTm> obList = FXCollections.observableArrayList();
        payments.forEach(e -> {
            obList.add(
                    new PaymentTm(e.getPaymentId(), e.getDate(), e.getTime(), e.getInquiryId(), e.getCustomerName(), e.getCroCode(),
                            e.getPaymentMethod(),e.getTotal(),e.getDiscount()));
        });
        tblPayment.setItems(obList);

    }

    public void loadPendingInquiry() throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        String query = "SELECT COUNT(inquiryCode) AS pendingStatus FROM Inquiry WHERE status= 'Pending'";
        ResultSet rst = con.prepareStatement(query).executeQuery();
        if (rst.next()){
            int i=rst.getInt("pendingStatus");

            lblPendingInquiries.setText(String.valueOf(i));
        }
    }

    public void loadSuccessInquiry() throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        String query = "SELECT COUNT(inquiryCode) AS successStatus FROM Inquiry WHERE status= 'Success'";
        ResultSet rst = con.prepareStatement(query).executeQuery();
        if (rst.next()){
            int i=rst.getInt("successStatus");

            lblSuccessInquiries.setText(String.valueOf(i));
        }
    }

    public void loadAllInquiry() throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        String query = "SELECT COUNT(inquiryCode) AS allInquiries FROM Inquiry ";
        ResultSet rst = con.prepareStatement(query).executeQuery();
        if (rst.next()){
            int i=rst.getInt("allInquiries");

            lblAllInquiries.setText(String.valueOf(i));
        }
    }

    public void dashboardOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/DashBoardForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) dashBoardFormContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void customerManagementOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../viewReceptionist/ManageCustomerForm.fxml");
        Parent load = FXMLLoader.load(resource);
        dashBoardInsideContext.getChildren().clear();
        dashBoardInsideContext.getChildren().add(load);
    }

    public void inquiriesOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../viewReceptionist/ManageInquiriesForm.fxml");
        Parent load = FXMLLoader.load(resource);
        dashBoardInsideContext.getChildren().clear();
        dashBoardInsideContext.getChildren().add(load);

    }

    public void customerComplainOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../viewReceptionist/CustomerComplainForm.fxml");
        Parent load = FXMLLoader.load(resource);
        dashBoardInsideContext.getChildren().clear();
        dashBoardInsideContext.getChildren().add(load);
    }

    public void addNewAdminOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/RSignInForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) dashBoardFormContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    private void loadDateAndTime () {
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

    public void cancelButtonOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/FirstForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) dashBoardFormContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }


    public void siteDetailsBtnOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../viewReceptionist/SiteDetailsForm.fxml");
        Parent load = FXMLLoader.load(resource);
        dashBoardInsideContext.getChildren().clear();
        dashBoardInsideContext.getChildren().add(load);
    }

    public void makePaymentBtnOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../viewReceptionist/PaymentForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) dashBoardFormContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }
}
