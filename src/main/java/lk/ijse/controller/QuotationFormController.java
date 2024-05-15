package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.model.Customer;
import lk.ijse.repository.CustomerRepo;

import java.io.IOException;
import java.sql.SQLException;

import static jdk.internal.misc.OSEnvironment.initialize;
import static lk.ijse.controller.LoginFormController.credintial;

public class QuotationFormController {

    public AnchorPane root;
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
    private DatePicker txtDate;

    @FXML
    private TextField txtQuotationId;

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/Dashboard_form.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Dashboard Form");
        stage.centerOnScreen();

    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearField();

    }

    private void clearField() {
        txtAmount.setText("");
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtQuotationId.getText();

        try {
            boolean isDeleted = CustomerRepo.delete(id);
            if (isDeleted) {
                initialize();
                new Alert(Alert.AlertType.CONFIRMATION, "Quotation deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id = txtQuotationId.getText();
        String date = txtDate.getText();
        String amount = txtAmount.getText();
       // String tel = txtTel.getText();
        String userId = credintial[0];

        Customer customer = new Customer(id, name,  address, tel,userId);


        try {
            boolean isSaved = CustomerRepo.save(customer);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Quotation saved!").show();
                loadAllCustomers();
                clearFields();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtQuotationId.getText();
        String date = txtDate.getText();
        String amount = txtAmount.getText();
       // String tel = txtTel.getText();

        Customer customer = new Customer(id, name, address,credintial[0]);

        try {
            boolean isUpdated = CustomerRepo.update(customer);
            if (isUpdated) {
                initialize();
                new Alert(Alert.AlertType.CONFIRMATION, "Quotation updated!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
    @FXML
    void txtSearchOnAction(ActionEvent event) throws SQLException {
        String id = txtQuotationId.getText();

        Customer customer = CustomerRepo.searchById(id);
        if (customer != null) {
            txtQuotationId.setText(customer.getId());
            txtDate.setText(customer.getName());
            txtAmount.setText(customer.getAddress());
            //txtTel.setText(customer.getTel());
        } else {
            new Alert(Alert.AlertType.INFORMATION, "Quotation not found!").show();
        }
    }

}
