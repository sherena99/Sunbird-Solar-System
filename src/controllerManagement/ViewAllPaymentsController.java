package controllerManagement;

import controllerReceptionist.PaymentController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.Payment;
import model.Product;
import view.tm.PaymentTm;
import view.tm.ProductTm;

import java.sql.SQLException;
import java.util.ArrayList;

public class ViewAllPaymentsController {
    public AnchorPane allPaymentContext;
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
}
