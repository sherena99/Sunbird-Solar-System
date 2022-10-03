package controllerReceptionist;

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
import model.Payment;
import model.Site;
import view.tm.PaymentTm;
import view.tm.SiteTm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

public class ViewSiteDetailsFormController {
    public AnchorPane viewSiteFormContext;
    public TableView tblSite;
    public TableColumn colDate;
    public TableColumn colIqId;
    public TableColumn colAddress;
    public TableColumn colSiteType;
    public TableColumn colRoof;
    public TableColumn colDirection;
    public TableColumn colExtraWork;
    public TableColumn colCharge;
    public TableColumn colNote;

    public void initialize(){
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colIqId.setCellValueFactory(new PropertyValueFactory<>("inquiryCode"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSiteType.setCellValueFactory(new PropertyValueFactory<>("siteType"));
        colRoof.setCellValueFactory(new PropertyValueFactory<>("roof"));
        colDirection.setCellValueFactory(new PropertyValueFactory<>("direction"));
        colExtraWork.setCellValueFactory(new PropertyValueFactory<>("extraWork"));
        colCharge.setCellValueFactory(new PropertyValueFactory<>("charges"));
        colNote.setCellValueFactory(new PropertyValueFactory<>("note"));

        try {

            setSiteDetailsToTable(new SiteController().loadSiteDetails());

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public void setSiteDetailsToTable(ArrayList<Site> sites) {
        ObservableList<SiteTm> obList = FXCollections.observableArrayList();
        sites.forEach(e -> {
            obList.add(
                    new SiteTm(e.getDate(), e.getInquiryCode(), e.getAddress(), e.getSiteType(), e.getRoof(), e.getDirection(),
                            e.getExtraWork(),e.getCharges(),e.getNote()));
        });
        tblSite.setItems(obList);

    }

    public void cancelButtonOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/DashBoardForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) viewSiteFormContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }
}



