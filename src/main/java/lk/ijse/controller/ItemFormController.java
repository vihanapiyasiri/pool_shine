package lk.ijse.controller ;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.db.DbConnection;
import lk.ijse.model.Tm.ItemTm;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemFormController {


    public TableView<ItemTm> tblItem;
    public TableColumn<?,?> colCode;
    public TableColumn<?,?> colDescription;
    public TextField txtItemId;
    public TextField txtDescription;
    public TextField txtUnitPrice;
    public TableColumn<?,?> colUnitPrice;
    public TableColumn<?,?> colQtyOnHand;
    public TextField txtName;

    @FXML
    private AnchorPane root;

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id = txtItemId.getText();
        String name = txtName.getText();
        String desc = txtDescription.getText();
        String price = txtUnitPrice.getText();
        String sql = "INSERT INTO item VALUES(?, ?, ?, ?)";

        try {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setObject(1, id);
            pstm.setObject(2, name);
            pstm.setObject(3, desc);
            pstm.setObject(4, price);
            boolean isSaved = pstm.executeUpdate() > 0;
            if (isSaved) {
                (new Alert(Alert.AlertType.CONFIRMATION, "Item saved!", new ButtonType[0])).show();
                this.clearFields();
            }
        } catch (SQLException var10) {
            (new Alert(Alert.AlertType.ERROR, var10.getMessage(), new ButtonType[0])).show();
        }

    }

    private void clearFields() {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = this.txtItemId.getText();
        String name = this.txtName.getText();
        String desc = this.txtDescription.getText();
        String price = this.txtUnitPrice.getText();
        String sql = "UPDATE item SET name = ?, address = ?, tel = ? WHERE id = ?";

        try {
            PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
            pstm.setObject(1, id);
            pstm.setObject(2, name);
            pstm.setObject(3, desc);
            pstm.setObject(4, price);
            if (pstm.executeUpdate() > 0) {
                (new Alert(Alert.AlertType.CONFIRMATION, "Item updated!", new ButtonType[0])).show();
                this.clearFields();
            }
        } catch (SQLException var8) {
            (new Alert(Alert.AlertType.ERROR, var8.getMessage(), new ButtonType[0])).show();
        }

    }

  @FXML
    void btnSearchOnAction(ActionEvent event) {
        String id = this.txtItemId.getText();
        String sql = "SELECT * FROM customers WHERE id = ?";

        try {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setObject(1, id);
            ResultSet resultSet = pstm.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString(2);
                String desc = resultSet.getString(3);
                String price = resultSet.getString(4);
                this.txtName.setText(name);
                this.txtDescription.setText(desc);
                this.txtUnitPrice.setText(price);
            } else {
                (new Alert(Alert.AlertType.INFORMATION, "customer id can't be find!", new ButtonType[0])).show();
            }

        } catch (SQLException var10) {
            throw new RuntimeException(var10);
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = this.txtItemId.getText();
        String sql = "DELETE FROM customers WHERE id = ?";

        try {
            PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
            pstm.setObject(1, id);
            if (pstm.executeUpdate() > 0) {
                (new Alert(Alert.AlertType.CONFIRMATION, "Item deleted!", new ButtonType[0])).show();
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

    public void codeSearchOnAction(ActionEvent actionEvent) {
    }

    //public void btnUpdateOnAction(ActionEvent actionEvent) {
    }

   // public void btnDeleteOnAction(ActionEvent actionEvent) {

