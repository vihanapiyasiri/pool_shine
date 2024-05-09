package lk.ijse.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.db.DbConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderFormController {
    @FXML
    private TableView<?> tblContractOrder;
    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private AnchorPane root;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtDescription;


    public OrderFormController() {
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id = this.txtId.getText();
        String Date = this.txtDate.getText();
        String address = this.txtAmount.getText();
        String sql = "INSERT INTO contract_order VALUES(?, ?, ?)";

        try {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setObject(1, id);
            pstm.setObject(2, Date);
            pstm.setObject(3, Amount);
            boolean isSaved = pstm.executeUpdate() > 0;
            if (isSaved) {
                (new Alert(Alert.AlertType.CONFIRMATION, "customer saved!", new ButtonType[0])).show();
                this.clearFields();
            }
        } catch (SQLException var10) {
            (new Alert(Alert.AlertType.ERROR, var10.getMessage(), new ButtonType[0])).show();
        }

    }

    private void clearFields() {
        this.txtId.setText("");
        this.txtDate.setText("");
        this.txtAmount.setText("");
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = this.txtId.getText();
        String Date = this.txtDate.getText();
        String address = this.txtAmount.getText();
        String sql = "UPDATE contract_order SET Id = ?, Date = ?, Amount = ? WHERE id = ?";

        try {
            PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
            pstm.setObject(1, id);
            pstm.setObject(2, address);
            pstm.setObject(3, tel);
            if (pstm.executeUpdate() > 0) {
                (new Alert(Alert.AlertType.CONFIRMATION, "customer updated!", new ButtonType[0])).show();
                this.clearFields();
            }
        } catch (SQLException var8) {
            (new Alert(Alert.AlertType.ERROR, var8.getMessage(), new ButtonType[0])).show();
        }

    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {
        String id = this.txtId.getText();
        String sql = "SELECT * FROM customers WHERE id = ?";

        try {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setObject(1, id);
            ResultSet resultSet = pstm.executeQuery();
            if (resultSet.next()) {
                String id = resultSet.getString(2);
                String address = resultSet.getString(3);
                String tel = resultSet.getString(4);
                this.txtDate.setText(id);
                this.txtAmount.setText(address);
            } else {
                (new Alert(Alert.AlertType.INFORMATION, "customer id can't be find!", new ButtonType[0])).show();
            }

        } catch (SQLException var10) {
            throw new RuntimeException(var10);
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = this.txtId.getText();
        String sql = "DELETE FROM customers WHERE id = ?";

        try {
            PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
            pstm.setObject(1, id);
            if (pstm.executeUpdate() > 0) {
                (new Alert(Alert.AlertType.CONFIRMATION, "customer deleted!", new ButtonType[0])).show();
                this.clearFields();
            }
        } catch (SQLException var5) {
            (new Alert(Alert.AlertType.ERROR, var5.getMessage(), new ButtonType[0])).show();
        }

    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        this.clearFields();
    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Dashboard Form");
        stage.centerOnScreen();
    }
}