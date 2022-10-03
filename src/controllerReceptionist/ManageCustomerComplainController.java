package controllerReceptionist;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.Complain;
import model.Customer;
import view.tm.ComplainTm;
import view.tm.CustomerTm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class ManageCustomerComplainController {
    public AnchorPane complainContext;
    public TableView<ComplainTm> tblComplain;
    public TableColumn colComplainNumber;
    public TableColumn colDate;
    public TableColumn colTime;
    public TableColumn colInqNum;
    public TableColumn colCustomer;
    public TableColumn ColCro;
    public TableColumn colAbout;
    public TableColumn colDetails;
    public TableColumn colOfficer;


    public void initialize(){

        colComplainNumber.setCellValueFactory(new PropertyValueFactory<>("complainNum"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colInqNum.setCellValueFactory(new PropertyValueFactory<>("inquiryCode"));
        colCustomer.setCellValueFactory(new PropertyValueFactory<>("custName"));
        ColCro.setCellValueFactory(new PropertyValueFactory<>("croCode"));
        colAbout.setCellValueFactory(new PropertyValueFactory<>("comAbout"));
        colDetails.setCellValueFactory(new PropertyValueFactory<>("comDetails"));
        colOfficer.setCellValueFactory(new PropertyValueFactory<>("officer"));

        try {
            setCOmplainDetailsToTable(new ComplainController().loadAllComplains());
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }

    public void viewComplainBtnOnAction(ActionEvent actionEvent) {
    }

    public void setCOmplainDetailsToTable(ArrayList<Complain> complain){
        ObservableList<ComplainTm> obList = FXCollections.observableArrayList();
        complain.forEach(e->{
            obList.add(
                    new ComplainTm(e.getComplainNum(),e.getTime(),e.getDate(),e.getInquiryCode(),e.getCustName(),e.getCroCode(),
                            e.getComAbout(), e.getComDetails(),e.getOfficer()));
        });
        tblComplain.setItems(obList);


    }
}
