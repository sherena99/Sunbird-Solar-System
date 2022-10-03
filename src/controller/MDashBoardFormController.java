package controller;

import controllerReceptionist.PaymentController;
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
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

public class MDashBoardFormController {
    public AnchorPane MDashBoardFormContext;
    public Label lblDate;
    public Label lblTime;
    public Label lblRole;
    public AnchorPane MDashBoardInsideFormContext;
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

    public void addNewAdminOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/MSignInForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) MDashBoardFormContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void dashboardOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/MDashBoardForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) MDashBoardInsideFormContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void manageCroOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../viewManagement/ManageCROForm.fxml");
        Parent load = FXMLLoader.load(resource);
        MDashBoardInsideFormContext.getChildren().clear();
        MDashBoardInsideFormContext.getChildren().add(load);
    }

    public void manageProductOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../viewManagement/ManageProductForm.fxml");
        Parent load = FXMLLoader.load(resource);
        MDashBoardInsideFormContext.getChildren().clear();
        MDashBoardInsideFormContext.getChildren().add(load);
    }

    public void systemReportOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../viewManagement/viewReports.fxml");
        Parent load = FXMLLoader.load(resource);
        MDashBoardInsideFormContext.getChildren().clear();
        MDashBoardInsideFormContext.getChildren().add(load);
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
        Stage window = (Stage) MDashBoardInsideFormContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void deleteCustomerOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../viewReceptionist/DeleteCustomerForm.fxml");
        Parent load = FXMLLoader.load(resource);
        MDashBoardInsideFormContext.getChildren().clear();
        MDashBoardInsideFormContext.getChildren().add(load);
    }

    public void customerComplainBtnOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../viewReceptionist/ManageCustomerComplain.fxml");
        Parent load = FXMLLoader.load(resource);
        MDashBoardInsideFormContext.getChildren().clear();
        MDashBoardInsideFormContext.getChildren().add(load);
    }

}
