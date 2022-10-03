package controller;

import db.DbConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MSignInFormController {
    public AnchorPane MsignInFormContext;
    public TextField txtUserName;
    public TextField txtUserPassword;

    public void cancelButtonOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/MDashBoardForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) MsignInFormContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void RegisterButtonOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String userName = txtUserName.getText();
        String password = txtUserPassword.getText();

        Connection con = DbConnection.getInstance().getConnection();
        String query = "INSERT INTO UserManagement VALUES(?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setString(1, userName);
        stm.setString(2, password);

        txtUserName.clear();txtUserPassword.clear();

        if (stm.executeUpdate() > 0)
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();

        else
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();

    }

}

