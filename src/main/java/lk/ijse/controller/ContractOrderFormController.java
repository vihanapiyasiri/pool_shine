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
import java.time.LocalDate;

public class ContractOrderFormController {
    @FXML
    private Label Amount;

    @FXML
    private TableColumn<?, ?> colCode;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private TableView<?> tblItem;

    @FXML
    private TextField txtAmount;

    @FXML
    private TextField txtContractOrderId;

    @FXML
    private DatePicker txtDate;


    @FXML
    void codeSearchOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id = this.txtContractOrderId.getText();
        String name = String.valueOf(this.txtDate.getValue());
        String address = this.txtAmount.getText();
        String sql = "INSERT INTO contract_order VALUES(?, ?, ?)";

        try {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setObject(1, id);
            pstm.setObject(2, name);
            pstm.setObject(3, address);
            boolean isSaved = pstm.executeUpdate() > 0;
            if (isSaved) {
                (new Alert(Alert.AlertType.CONFIRMATION, "ContractOrder saved!", new ButtonType[0])).show();
                this.clearFields();
            }
        } catch (SQLException var10) {
            (new Alert(Alert.AlertType.ERROR, var10.getMessage(), new ButtonType[0])).show();
        }

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = this.txtContractOrderId.getText();
        String date = String.valueOf(this.txtDate.getValue());
        String address = this.txtAmount.getText();
        String sql = "UPDATE ContractOrder SET Id = ?, Date = ?, Amount = ? WHERE id = ?";

        try {
            PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
            pstm.setObject(1, date);
            pstm.setObject(2, address);
            pstm.setObject(3, 00);
            pstm.setObject(4, id);
            if (pstm.executeUpdate() > 0) {
                (new Alert(Alert.AlertType.CONFIRMATION, "ContractOrder updated!", new ButtonType[0])).show();
                this.clearFields();
            }
        } catch (SQLException var8) {
            (new Alert(Alert.AlertType.ERROR, var8.getMessage(), new ButtonType[0])).show();
        }

    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {
        String id = this.txtContractOrderId.getText();
        String sql = "SELECT * FROM ContractOrders WHERE id = ?";

        try {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setObject(1, id);
            ResultSet resultSet = pstm.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString(2);
                String address = resultSet.getString(3);
                String tel = resultSet.getString(4);
                //this.txtDate.setText(name);
                this.txtAmount.setText(address);
            } else {
                (new Alert(Alert.AlertType.INFORMATION, "ContractOrder id can't be find!", new ButtonType[0])).show();
            }

        } catch (SQLException var10) {
            throw new RuntimeException(var10);
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = this.txtContractOrderId.getText();
        String sql = "DELETE FROM ContractOrders WHERE id = ?";

        try {
            PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
            pstm.setObject(1, id);
            if (pstm.executeUpdate() > 0) {
                (new Alert(Alert.AlertType.CONFIRMATION, "ContractOrder deleted!", new ButtonType[0])).show();
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
    private void clearFields() {
        txtDate.setText("");
        txtContractOrderId.setText("");
        txtAmount.setText("");
    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"));
        Stage stage = (Stage) txtAmount.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Dashboard Form");
        stage.centerOnScreen();
    }
}