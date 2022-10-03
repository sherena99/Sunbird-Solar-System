package controllerReceptionist;

import controllerManagement.ProductController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Customer;
import model.Inquiry;
import view.tm.CustomerTm;
import view.tm.InquiryTm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

public class ManageInquiriesFormController {
    public AnchorPane manageInquiresFormContext;
    public TableView <InquiryTm> tblInquiry;
    public TableColumn colInquiryId;
    public TableColumn colDate;
    public TableColumn colTime;
    public TableColumn colCustId;
    public TableColumn colCustName;
    public TableColumn colAddress;
    public TableColumn colCroId;
    public TableColumn colCroCode;
    public TableColumn colInqFrom;
    public TableColumn colRemarks;
    public TableColumn colTotal;
    public TableColumn colStatus;

    public void initialize(){
        colInquiryId.setCellValueFactory(new PropertyValueFactory<>("inquiryCode"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colCustId.setCellValueFactory(new PropertyValueFactory<>("custId"));
        colCustName.setCellValueFactory(new PropertyValueFactory<>("custName"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("siteAddress"));
        colCroId.setCellValueFactory(new PropertyValueFactory<>("croId"));
        colCroCode.setCellValueFactory(new PropertyValueFactory<>("croCode"));
        colInqFrom.setCellValueFactory(new PropertyValueFactory<>("inquiryFrom"));
        colRemarks.setCellValueFactory(new PropertyValueFactory<>("remarks"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        try {
            setInquiryDetailsToTable(new InquiryController().getAllInquiries());
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

///////////////////////////////////////////////////////////////////////////////////////////
        //Load Ui When Click The Table Raw
        tblInquiry.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {

                loadDetailUi(newValue.getInquiryCode());

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }


    private void loadDetailUi(String inquiryCode) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../viewReceptionist/viewInquiryDetailsForm.fxml"));
        Parent load = fxmlLoader.load();
        ViewInquiryDetailsFormController controller = fxmlLoader.getController();
        controller.loadAllDataToTable(inquiryCode);
        Stage stage=new Stage();
        stage.setScene(new Scene(load));
        stage.show();

    }


    public void setInquiryDetailsToTable(ArrayList<Inquiry> inquiry){
        ObservableList<InquiryTm> obList = FXCollections.observableArrayList();
        inquiry.forEach(e->{
            obList.add(
                    new InquiryTm(e.getInquiryCode(),e.getTime(),e.getDate(),e.getCustId(),e.getCustName(),e.getSiteAddress(),e.getCroId(),e.getCroCode(),e.getInquiryFrom(),e.getRemarks(),e.getStatus()));
        });
        tblInquiry.setItems(obList);

    }

    public void newInquiryOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../viewReceptionist/AddNewInquiry.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) manageInquiresFormContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }
}
