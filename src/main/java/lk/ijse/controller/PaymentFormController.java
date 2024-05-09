package lk.ijse.controller ;

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
import java.util.Date;

public class PaymentFormController {

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colAmount;

    @FXML
    private TableColumn<?, ?> colMethod;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<?> tblPayment;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtAmount;

    @FXML
    private TextField txtMethod;

    @FXML
    private TextField txtDate;

    public PaymentFormController() {
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id = this.txtId.getText();
        String Amount = this.txtAmount.getText();
        String Method = this.txtMethod.getText();
        String Date = this.txtDate.getText();
        String sql = "INSERT INTO customers VALUES(?, ?, ?, ?)";

        try {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setObject(1, id);
            pstm.setObject(2, Amount);
            pstm.setObject(3, Method);
            pstm.setObject(4, Date);
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
        this.txtAmount.setText("");
        this.txtMethod.setText("");
        this.txtDate.setText("");
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = this.txtId.getText();
        String Amount = this.txtAmount.getText();
        String Method = this.txtMethod.getText();
        String tel = this.txtDate.getText();
        String sql = "UPDATE customers SET name = ?, address = ?, tel = ? WHERE id = ?";

        try {
            PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
            pstm.setObject(1, id);
            pstm.setObject(2, Amount);
            pstm.setObject(3, Method);
            pstm.setObject(4, Date);
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
                String name = resultSet.getString(2);
                String address = resultSet.getString(3);
                String tel = resultSet.getString(4);
                this.txtName.setText(name);
                this.txtAddress.setText(address);
                this.txtTel.setText(tel);
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
        AnchorPane anchorPane = (AnchorPane)FXMLLoader.load(this.getClass().getResource("/view/dashboard_form.fxml"));
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Dashboard Form");
        stage.centerOnScreen();
    }
}
