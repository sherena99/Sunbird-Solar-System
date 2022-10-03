package controllerReceptionist;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.InquiryDetails;
import view.tm.InquiryDetailsTm;

import java.sql.SQLException;

public class ViewInquiryDetailsFormController {
    public AnchorPane viewInquiryDetailsContext;
    public Label lblInquiryCode;
    public Label lblDate;
    public Label lblTime;
    public Label lblTotal;
    public Label lblTotalDiscount;
    public TableView tblInquiryDetails;
    public TableColumn colProductid;
    public TableColumn colQty;
    public TableColumn colDiscount;
    public TableColumn colPrice;

    public void initialize(){
        colProductid.setCellValueFactory(new PropertyValueFactory<>("productCode"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));

    }

    public void loadAllDataToTable(String inqId){

        lblInquiryCode.setText(inqId);
        double discount=0;
        double total=0;
        try {

            ObservableList<InquiryDetailsTm> tmList = FXCollections.observableArrayList();
            for (InquiryDetails tempD:new InquiryController().getInquiryDetails(inqId)
                 ) {

                discount=+tempD.getDiscount()+ discount;
                total=+tempD.getUnitPrice()+ total;

                tmList.add(new InquiryDetailsTm(tempD.getInquiryCode(),tempD.getProductCode(),tempD.getQty(),tempD.getDiscount(),tempD.getUnitPrice()));
            }
            tblInquiryDetails.setItems(tmList);
            lblTotalDiscount.setText(String.valueOf(discount));
            lblTotal.setText(String.valueOf(total));

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }


    public void cancelButtonOnAction(ActionEvent actionEvent) {
    }
}
