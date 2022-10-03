package controllerReceptionist;

import controllerManagement.CROController;
import controllerManagement.ProductController;
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
import model.Inquiry;
import model.Product;
import model.ProductDetails;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import view.tm.InquiryProductTm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class
AddNewInquiryController {
    public AnchorPane inquiryFormContext;
    public ComboBox <String>cmbCustId;
    public TextField txtInquiryFrom;
    public TextField txtSiteAddress;
    public TextField txtDescription;
    public ComboBox <String>cmbCode;
    public TextField txtListPrice;
    public TextField txtDiscount;
    public TextField txtQty;
    public ComboBox <String>cmbCroId;
    public Label lblInquiryCode;
    public Label lblDate;
    public Label lblTime;
    public TextArea txtRemarks;
    public TextField txtCustName;
    public TextField txtCroCode;
    public TableView<InquiryProductTm> tblProduct;
    public TableColumn colCode;
    public TableColumn colName;
    public TableColumn colDescription;
    public TableColumn colQty;
    public TableColumn colListPrice;
    public TableColumn colDiscountPercentage;
    public TableColumn colGrossTotal;
    public TableColumn colDiscount;
    public TableColumn colTotal;
    public TextField txtName;
    public Label lblTotal;
    public Label lblTotalDiscount;
    public ComboBox <String> cmbInquiryFrom;
    public TextField txtStatus;
    public ComboBox <String> cmbInquiryStatus;

    int cartSelectedRowForRemove = -1;

    public void initialize(){
        setOrderId();
        loadDateAndTime();


        try {
            loadCustomerIds();
            loadCROIds();
            loadProductCodes();

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        /////////////////////////////////////////////////////////////////////////

        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colListPrice.setCellValueFactory(new PropertyValueFactory<>("listPrice"));
        colDiscountPercentage.setCellValueFactory(new PropertyValueFactory<>("discount"));
        colGrossTotal.setCellValueFactory(new PropertyValueFactory<>("grossTotal"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("totalDiscount"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));


        /////////////////////////////////////////////////////////////////////////
        cmbCustId.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    getCustomerName(newValue);
                    getCustomerAddress(newValue);

                });
        cmbCroId.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    getCROCode(newValue);

                });

        cmbCode.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    getProductDetails(newValue);

                });

        cmbInquiryFrom.getItems().addAll(
                "Hotline",
                "Office Visit",
                "CRO Personal",
                "Managers Personal",
                "Boss Personal",
                "Exhibition",
                "Other"

        );

        cmbInquiryStatus.getItems().addAll("Pending");

        ///////////////////////////////////////////////////////////////////////////////
        tblProduct.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            cartSelectedRowForRemove = (int) newValue;
        });
    }

    public void getCustomerName(String id){
        try {
            String nameById = InquiryController.getCustomerNameById(id);
            txtCustName.setText(nameById);

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
    public void getCustomerAddress(String id){
        try {
            String nameById = InquiryController.getCustomerAddressById(id);
            txtSiteAddress.setText(nameById);

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public void getCROCode(String id){

        try {
            String croNameById = InquiryController.getCROCodeById(id);
            txtCroCode.setText(croNameById);

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
    public void getProductDetails(String id){
        try {
            Product productDetailsById = InquiryController.getProductDetails(id);
            txtName.setText(productDetailsById.getName());
            txtDescription.setText(productDetailsById.getDescription());
            txtListPrice.setText(String.valueOf(productDetailsById.getPrice()));
            txtDiscount.setText(String.valueOf(productDetailsById.getDiscount()));

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    private void setOrderId() {
        try {
            lblInquiryCode.setText(new InquiryController().getInquiryCode());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
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
    private void loadCustomerIds() throws SQLException, ClassNotFoundException {
        List<String> customerIds = new InquiryController().getAllCustomerIds();
        cmbCustId.getItems().addAll(customerIds);
    }
    private void loadCROIds() throws SQLException, ClassNotFoundException {
        List<String> croIds = new InquiryController().getAllCROIds();
        cmbCroId.getItems().addAll(croIds);
    }
    private void loadProductCodes() throws SQLException, ClassNotFoundException {
        List<String> productCode = new InquiryController().getAllProductCode();
        cmbCode.getItems().addAll(productCode);
    }

    ObservableList<InquiryProductTm> obList= FXCollections.observableArrayList();
    public void addToListOnAction(ActionEvent actionEvent) {
        String name = txtName.getText();
        String description = txtDescription.getText();
        int qtyForCustomer = Integer.parseInt(txtQty.getText());
        double listPrice = Double.parseDouble(txtListPrice.getText());
        int discountPercentage = Integer.parseInt(txtDiscount.getText());
        double grossTotal = qtyForCustomer * listPrice;
        double discount= grossTotal * discountPercentage/100;
        double total= grossTotal - discount;
        double totalDiscount = Double.parseDouble(lblTotalDiscount.getText());

        InquiryProductTm tm = new InquiryProductTm(
                cmbCode.getValue(),
                name,
                description,
                qtyForCustomer,
                listPrice,
                discountPercentage,
                grossTotal,
                discount,
                total

        );

        int rowNumber = isExists(tm);

        if (rowNumber == -1) {// new Add
            obList.add(tm);
        } else {
            // update
            InquiryProductTm temp = obList.get(rowNumber);
            InquiryProductTm newTm = new InquiryProductTm(
                    temp.getCode(),
                    temp.getName(),
                    temp.getDescription(),
                    temp.getQty()+qtyForCustomer,
                    temp.getListPrice(),
                    temp.getDiscount(),
                    temp.getGrossTotal()+grossTotal,
                    temp.getTotalDiscount()+discount,
                    temp.getTotal()+total

            );

            obList.remove(rowNumber);
            obList.add(newTm);
        }
        tblProduct.setItems(obList);
        calculateTotalCosts();
        calculateTotalDiscount();


    }

    private int isExists(InquiryProductTm tm) {
        for (int i = 0; i < obList.size(); i++) {
            if (tm.getCode().equals(obList.get(i).getCode())){
                return i;
            }
        }
        return -1;
    }
    private void calculateTotalCosts() {
        double ttl=0;
        for (InquiryProductTm tm:obList
        ) {
            ttl+=tm.getTotal();
        }
        lblTotal.setText(ttl+"");
    }
    private void calculateTotalDiscount(){
        double discount=0;
        for (InquiryProductTm tm:obList
        ) {
            discount+=tm.getTotalDiscount();
        }
        lblTotalDiscount.setText(discount+" ");
    }

    public void clearOnAction(ActionEvent actionEvent) {
        if (cartSelectedRowForRemove==-1){
            new Alert(Alert.AlertType.WARNING, "Please Select a row").show();
        }else{
            obList.remove(cartSelectedRowForRemove);
            calculateTotalCosts();
            calculateTotalDiscount();

            tblProduct.refresh();
        }

    }

    public void addNewCustomerOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../viewReceptionist/AddNewCustomerForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) inquiryFormContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }
    public void cancelButtonOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/DashBoardForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) inquiryFormContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void saveDataOnAction(ActionEvent actionEvent) throws IOException, SQLException {
        ArrayList <ProductDetails> products = new ArrayList<>();
        double total = 0;
        double totalDiscount =0;
        for (InquiryProductTm tempTm:obList
             ) {
            total+= tempTm.getTotal();
            totalDiscount+= tempTm.getTotalDiscount();
            products.add(new ProductDetails(tempTm.getCode(), tempTm.getQty(), tempTm.getTotal(), tempTm.getTotalDiscount() ));
        }

        Inquiry inquiry = new Inquiry(
                lblInquiryCode.getText(),
                lblTime.getText(),
                lblDate.getText(),
                cmbCustId.getValue(),
                txtCustName.getText(),
                txtSiteAddress.getText(),
                cmbCroId.getValue(),
                txtCroCode.getText(),
                cmbInquiryFrom.getValue(),
                txtRemarks.getText(),
                total,
                totalDiscount,
                cmbInquiryStatus.getValue(),
                products

        );
        if (new InquiryController().saveInquiry(inquiry)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Success").show();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }

    public void printInquiryFormOnAction(ActionEvent actionEvent) {
        try {
            JasperDesign design = JRXmlLoader.load(this.getClass().getResourceAsStream("../view/reports/InquiryReport.jrxml"));
            JasperReport compileReport = JasperCompileManager.compileReport(design);

            //get all values from table
            ObservableList<InquiryProductTm> items = tblProduct.getItems();

            //setting values for parameters
            String inquiryCode = lblInquiryCode.getText();
            String date = lblDate.getText();
            String time=lblTime.getText();
            String customerId = cmbCustId.getValue();
            String customerName = txtCustName.getText();
            String siteAddress = txtSiteAddress.getText();
            String croId = cmbCroId.getValue();
            String croCode = txtCroCode.getText();
            String inquiryFrom = cmbInquiryFrom.getValue();
            String status = cmbInquiryStatus.getValue();
            String remarks = txtRemarks.getText();
            double totalDiscount =Double.parseDouble(lblTotalDiscount.getText());
            double totalCost =Double.parseDouble(lblTotal.getText());

            //Setting Parameter Values
            HashMap map = new HashMap();
            map.put("inquiryId",inquiryCode);
            map.put("date", date);
            map.put("time", time);
            map.put("custId",customerId);
            map.put("custName", customerName);
            map.put("siteAddress", siteAddress);
            map.put("croId",croId);
            map.put("croCode", croCode);
            map.put("inqFrom", inquiryFrom);
            map.put("inqStatus",status);
            map.put("remarks", remarks);
            map.put("ttlDiscount", totalDiscount);
            map.put("ttlPrice",totalCost);


            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, map, new JRBeanArrayDataSource(items.toArray()));
            JasperViewer.viewReport(jasperPrint,false);


        } catch (JRException e) {
            e.printStackTrace();
        }

    }
}
