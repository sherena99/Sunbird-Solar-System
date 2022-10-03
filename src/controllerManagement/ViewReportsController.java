package controllerManagement;

import db.DbConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

public class ViewReportsController {
    public AnchorPane viewReportContext;

    public void mostMovableProductView(ActionEvent actionEvent) {
        try {
            JasperDesign design = JRXmlLoader.load(this.getClass().getResourceAsStream("../view/reports/MostMovableProductReport.jrxml"));
            JasperReport compileReport = JasperCompileManager.compileReport(design);
            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, null, DbConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint,false);

        } catch (JRException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

    public void leastMovableProductView(ActionEvent actionEvent) {
        try {
            JasperDesign design = JRXmlLoader.load(this.getClass().getResourceAsStream("../view/reports/LeastMovableProductReport.jrxml"));
            JasperReport compileReport = JasperCompileManager.compileReport(design);
            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, null, DbConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint,false);

        } catch (JRException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void cancelButtonOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/MDashBoardForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) viewReportContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }
}
