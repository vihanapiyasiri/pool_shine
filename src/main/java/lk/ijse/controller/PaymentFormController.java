package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.db.DbConnection;
import lk.ijse.model.Customer;
import lk.ijse.model.Payment;
import lk.ijse.model.Tm.CustomerTm;
import lk.ijse.model.Tm.PaymentTm;
import lk.ijse.repository.CustomerRepo;
import lk.ijse.repository.PaymentRepo;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static lk.ijse.controller.LoginFormController.credintial;

public class PaymentFormController {
    public TextField txtAmount;
    public TextField txtMethod;
    public TableView<?> tblItem;
    public TableColumn<?, ?> colCode;
    public TextField txtPaymentId;
    public DatePicker dpDate;
    public TableColumn<?, ?> colDate;
    public TableColumn<?,?> colAmount;
    public TableColumn <?,?>colMethod;


    @FXML
        private TableColumn<?, ?> colAddress;

        @FXML
        private TableColumn<?, ?> colId;

        @FXML
        private TableColumn<?, ?> colName;

        @FXML
        private TableColumn<?, ?> colTel;
//
        @FXML
        private AnchorPane root;

        @FXML
        private TableView<PaymentTm> tblPayment;

        @FXML
        private TextField txtAddress;

        @FXML
        private TextField txtId;

        @FXML
        private TextField txtName;

        @FXML
        private TextField txtTel;
    private DbConnection DBConnection;

    public void initialize() {
            setCellValueFactory();
            loadAllPayments();
        }

        private void loadAllPayments() {
            ObservableList<PaymentTm> obList = FXCollections.observableArrayList();

            try {
                List<Payment> paymentList = PaymentRepo.getAll();
                for (Payment payment : paymentList) {
                    PaymentTm tm = new PaymentTm(
                            payment.getId(),
                            payment.getDate(),
                            payment.getAmount(),
                            payment.getMethod());

                    obList.add(tm);
                }

                tblPayment.setItems(obList);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        private void setCellValueFactory() {
            colCode.setCellValueFactory(new PropertyValueFactory<>("Id"));
            colAmount.setCellValueFactory(new PropertyValueFactory<>("Amount"));
            colMethod.setCellValueFactory(new PropertyValueFactory<>("Method"));
            colDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
        }

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
            clearFields();
        }

        private void clearFields() {
            txtPaymentId.setText("");
            txtAmount.setText("");
            txtMethod.setText("");
           dpDate.setValue(null);
        }

        @FXML
        void btnDeleteOnAction(ActionEvent event) {
            String id = txtPaymentId.getText();

            try {
                boolean isDeleted = PaymentRepo.delete(id);
                if (isDeleted) {
                    initialize();
                    new Alert(Alert.AlertType.CONFIRMATION, "payment deleted!").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }

        @FXML
        void btnSaveOnAction(ActionEvent event) {
            String id = txtPaymentId.getText();
            String amount = txtAmount.getText();
            String method = txtMethod.getText();
           String date = String.valueOf(dpDate.getValue());
            String userId = credintial[0];

            Payment payment = new Payment(id, amount, method,date);


            try {
                boolean isSaved = PaymentRepo.save(payment);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "payment saved!").show();
                    loadAllPayments();
                    clearFields();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        @FXML
        void btnUpdateOnAction(ActionEvent event) {
            String id = txtPaymentId.getText();
            String amount = txtAmount.getText();
            String method = txtMethod.getText();
           String date = String.valueOf(dpDate.getValue());

           Payment payment = new Payment(id, amount, method,date);

            try {
                boolean isUpdated = PaymentRepo.update(payment);
                if (isUpdated) {
                    initialize();
                    new Alert(Alert.AlertType.CONFIRMATION, "payment updated!").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }

        @FXML
        void codeSearchOnAction(ActionEvent event) throws SQLException {
            String id = txtId.getText();

            Payment payment =PaymentRepo.searchById(id);
            if (payment != null) {
                txtPaymentId.setText(payment.getId());
                txtAmount.setText(payment.getAmount());
                txtMethod.setText(payment.getMethod());
               // dpDate.setValue(payment.getDate());
            } else {
                new Alert(Alert.AlertType.INFORMATION, "payment not found!").show();
            }
        }

    @FXML
    void btnPrintBillOnAction(ActionEvent event) throws JRException, SQLException {
        JasperDesign jasperDesign = JRXmlLoader.load("src/main/resources/Report/PoolShineReport.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null, DbConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint);
    }


    public void txtSearchOnAction(ActionEvent actionEvent) {

    }
}





