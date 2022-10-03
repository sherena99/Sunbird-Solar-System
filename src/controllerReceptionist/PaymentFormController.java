package controllerReceptionist;

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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.InquiryDetails;
import model.Payment;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import view.tm.InquiryDetailsTm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class PaymentFormController {
    public AnchorPane PaymentFormContext;
    public Label lblPaymentId;
    public Label lblDate;
    public Label lblTime;
    public ComboBox<String> cmbInquiryCode;
    public ComboBox<String> cmbSiteId;
    public Label lblTotal;
    public Label lblTotalCost;
    public TextField txtCustomerPayment;
    public ComboBox<String> cmbPaymentMethod;
    public Label lblCost;
    public Label lblBalance;
    public Label lblCustomerSaving;
    public TableView <InquiryDetailsTm> tblProduct;
    public TableColumn colInquiryCode;
    public TableColumn colProductCode;
    public TableColumn colQty;
    public TableColumn colDiscount;
    public TableColumn colTotal;
    public TextField txtCustomerName;
    public TextField txtCroCode;
    public TextField txtSiteAddress;
    public TextField txtExtraWork;
    public TextField txtCharge;
    public Label lblExtraCharges;

    public void initialize(){
        loadDateAndTime();
        setPaymentId();

        try {

            loadInquiryCodes();
            loadInquiryIds();


        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        /////////////////////////////////////////////////////////////////////////
        cmbInquiryCode.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    getCustomerName(newValue);
                    getCroCode(newValue);
                    getTotalById(newValue);
                    getTotalDiscountById(newValue);
                    calculateTotal();

                    colInquiryCode.setCellValueFactory(new PropertyValueFactory<>("inquiryCode"));
                    colProductCode.setCellValueFactory(new PropertyValueFactory<>("productCode"));
                    colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
                    colDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));
                    colTotal.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));

                    try {
                        setInquiryDetailsToTable(new PaymentController().getAllInquiryDetails(newValue));
                    } catch (SQLException | ClassNotFoundException throwables) {
                        throwables.printStackTrace();
                    }

                });

        cmbSiteId.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    getSiteAddress(newValue);
                    getExtraWork(newValue);
                    getExtraCharges(newValue);
                    setLblExtraCharges(newValue);

                });

        cmbPaymentMethod.getItems().addAll(
                "Credit Card",
                            "Cash",
                             "PayPal",
                            "Online Banking"
        );


    }

    void setPaymentId(){
        try {
            lblPaymentId.setText(new PaymentController().getPaymentId());
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

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

    private void loadInquiryCodes() throws SQLException, ClassNotFoundException {
        List<String> croIds = new PaymentController().getAllInquiryCodes();
        cmbInquiryCode.getItems().addAll(croIds);
    }
    public void getCustomerName(String id){
        try {
            String nameByCode = PaymentController.getCustomerNameById(id);
            txtCustomerName.setText(nameByCode);

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
    public void getCroCode(String id){
        try {
            String croByCode = PaymentController.getCROCodeById(id);
            txtCroCode.setText(croByCode);

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
    public void getTotalById(String id){
        try {
            String totalById = PaymentController.getTotalById(id);
            lblTotal.setText(totalById);

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
    public void getTotalDiscountById(String id){
        try {
            String totalDiscountById = PaymentController.getTotalDiscountById(id);
            lblCustomerSaving.setText(totalDiscountById);

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
    public void calculateTotal(){
        double extraCharges = Double.parseDouble(lblExtraCharges.getText());
        double firstTotal = Double.parseDouble(lblTotal.getText());
        double total = extraCharges+firstTotal;

        lblTotalCost.setText(total+ " ");
        lblCost.setText(""+total);

    }
    public void setInquiryDetailsToTable(ArrayList<InquiryDetails> inquiryDetails){
        ObservableList<InquiryDetailsTm> obList = FXCollections.observableArrayList();
        inquiryDetails.forEach(e->{
            obList.add(
                    new InquiryDetailsTm(e.getInquiryCode(),e.getProductCode(),e.getQty(),e.getDiscount(),e.getUnitPrice()));
        });
        tblProduct.setItems(obList);

    }

    private void loadInquiryIds() throws SQLException, ClassNotFoundException {
        List<String> inquiryId = new PaymentController().getAllInquiryId();
        cmbSiteId.getItems().addAll(inquiryId);
    }
    public void getSiteAddress(String id){
        try {
            String addressById = PaymentController.getSiteAddressById(id);
            txtSiteAddress.setText(addressById);

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
    public void getExtraWork(String id){
        try {
            String extraWorkById = PaymentController.getExtraWorkById(id);
            txtExtraWork.setText(extraWorkById);

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
    public void getExtraCharges(String id){
        try {
            String extraChargesById = PaymentController.getExtraChargesById(id);
            txtCharge.setText(extraChargesById);

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
    public void setLblExtraCharges(String id){
        try {
           String extraChargesById = PaymentController.getExtraChargesById(id);
            lblExtraCharges.setText(extraChargesById);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
    

    public void cancelButtonOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/DashBoardForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) PaymentFormContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void calculateCustomerPayment(ActionEvent actionEvent) {
        double total = Double.parseDouble(lblTotalCost.getText());

        double customerPayment = Double.parseDouble(txtCustomerPayment.getText());
        double finalTotal = customerPayment-total;

        lblBalance.setText(finalTotal+"");
    }

    public void SavePaymentOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Payment p1 = new Payment(
                lblPaymentId.getText(),lblDate.getText(),
                lblTime.getText(),cmbInquiryCode.getValue(), txtCustomerName.getText(),txtCroCode.getText(),
                cmbPaymentMethod.getValue(), Double.parseDouble(lblCost.getText()), Double.parseDouble(lblCustomerSaving.getText())
        );

        if(new PaymentController().savePayment(p1)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Successfully Saved..!").show();
            //setPaymentId();
        }else {
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
        }

    }


    public void printPaymentOnAction(ActionEvent actionEvent) {

        try {
            JasperDesign design = JRXmlLoader.load(this.getClass().getResourceAsStream("../view/reports/PaymentFormReport.jrxml"));
            JasperReport compileReport = JasperCompileManager.compileReport(design);

            String inputId = cmbInquiryCode.getValue();
            String paymentId=lblPaymentId.getText();
            String inquiryId=cmbInquiryCode.getValue();
            String date=lblDate.getText();
            String time=lblTime.getText();
            String customer=txtCustomerName.getText();
            String address=txtSiteAddress.getText();
            String cro=txtCroCode.getText();
            String extraWork=txtExtraWork.getText();
            String payMethod=cmbPaymentMethod.getValue();
            double extraCharge=Double.parseDouble(txtCharge.getText());
            double grossTotal=Double.parseDouble(lblTotal.getText());
            double total=Double.parseDouble(lblTotalCost.getText());
            double cusPayAmount=Double.parseDouble(txtCustomerPayment.getText());
            double cost=Double.parseDouble(lblCost.getText());
            double balance=Double.parseDouble(lblBalance.getText());
            double totalDiscount=Double.parseDouble(lblCustomerSaving.getText());



            HashMap map = new HashMap();
            map.put("selectID",inputId);
            map.put("paymentId",paymentId);
            map.put("inquiryId",inquiryId);
            map.put("date",date);
            map.put("time",time);
            map.put("customer",customer);
            map.put("address",address);
            map.put("cro",cro);
            map.put("extraWork",extraWork);
            map.put("grossTotal",grossTotal);
            map.put("extraCharges",extraCharge);
            map.put("total",total);
            map.put("paymentMethod",payMethod);
            map.put("amount",cusPayAmount);
            map.put("cost",cost);
            map.put("balance",balance);
            map.put("totalDiscount",totalDiscount);

            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, map, DbConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint,false);

        } catch (JRException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }


    }
}
