package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.model.Quotation;
import lk.ijse.model.Quotation;
import lk.ijse.repository.QuotationRepo;
import lk.ijse.repository.QuotationRepo;

import java.io.IOException;
import java.sql.SQLException;

//import static jdk.internal.misc.OSEnvironment.initialize;
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
        txtQuotationId.setText("");
        txtDate.setText("");
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtQuotationId.getText();

        try {
            boolean isDeleted = QuotationRepo.delete(id);
            if (isDeleted) {
               // initialize();
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
        String userId = credintial[0];

        Quotation quotation = new Quotation(id, date, amount, userId);


        try {
            boolean isSaved = QuotationRepo.save(quotation);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Quotation saved!").show();
                loadAllQuotation();
                clearFields();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        String date = txtDate.getText();
        String amount = txtAmount.getText();

       Quotation quotation = new Quotation(id, date, amount, credintial[0]);

        try {
            boolean isUpdated = QuotationRepo.update(quotation);
            if (isUpdated) {
                initialize();
                new Alert(Alert.AlertType.CONFIRMATION, "quotation updated!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void codeSearchOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();

        Quotation quotation = QuotationRepo.searchById(id);
        if (quotation != null) {
            txtQuotationId.setText(quotation.getId());
            txtDate.setText(quotation.getName());
            txtAmount.setText(quotation.getAddress());
        } else {
            new Alert(Alert.AlertType.INFORMATION, "quotation not found!").show();
        }

    }
}
