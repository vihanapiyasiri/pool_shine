package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.model.Customer;
import lk.ijse.model.Payment;
import lk.ijse.model.Tm.CustomerTm;
import lk.ijse.model.Tm.PaymentTm;
import lk.ijse.repository.CustomerRepo;
import lk.ijse.repository.PaymentRepo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static lk.ijse.controller.LoginFormController.credintial;

public class PaymentFormController {
    public TextField txtAmount;
    public TextField txtMethod;
    public TableView<?> tblItem;
    public TableColumn<?, ?> colCode;
    public TableColumn<?, ?> colDescription;
    public TableColumn<?, ?> colUnitPrice;
    public TableColumn<?, ?> colQtyOnHand;
    public TextField txtPaymentId;


        @FXML
        private TableColumn<?, ?> colAddress;

        @FXML
        private TableColumn<?, ?> colId;

        @FXML
        private TableColumn<?, ?> colName;

        @FXML
        private TableColumn<?, ?> colTel;

        @FXML
        private AnchorPane root;

        @FXML
        private TableView<CustomerTm> tblCustomer;

        @FXML
        private TextField txtAddress;

        @FXML
        private TextField txtId;

        @FXML
        private TextField txtName;

        @FXML
        private TextField txtTel;

        public void initialize() {
            setCellValueFactory();
            loadAllPayments();
        }

        private void loadAllPayments() {
            ObservableList<PaymentTm> obList = FXCollections.observableArrayList();

            try {
                List<Payment> paymentList = PaymentRepo.getAll();
                for (Payment payment : paymentList) {
                    PaymentTm tm = new CustomerTm(
                            payment.getPaymentId(),
                            payment.getAmount(),
                            payment.getMethod(),
                            payment.getDate()
                    );

                    obList.add(tm);
                }

                tblPayment.setItems(obList);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        private void setCellValueFactory() {
            colCode.setCellValueFactory(new PropertyValueFactory<>("Id"));
            colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
            colAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
            txtDate.setCellValueFactory(new PropertyValueFactory<>("Contact"));
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
            txtDate.setText("");
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
            String date = txtDate.getText();
            String userId = credintial[0];

            Customer customer = new Customer(id, amount, address, tel, userId);


            try {
                boolean isSaved = CustomerRepo.save(customer);
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
            String name = txtAmount.getText();
            String address = txtMethod.getText();
            String tel = txtDate.getText();

           Payment payment = new Payment(PaymentId, name, address, tel, credintial[0]);

            try {
                boolean isUpdated = PaymentrRepo.update(payment);
                if (isUpdated) {
                    initialize();
                    new Alert(Alert.AlertType.CONFIRMATION, "payment updated!").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }

        @FXML
        void txtSearchOnAction(ActionEvent event) throws SQLException {
            String id = txtId.getText();

            Customer customer =PaymentRepo.searchById(id);
            if (customer != null) {
                txtPaymentId.setText(payment.getId());
                txtAmount.setText(payment.getName());
                txtMethod.setText(payment.getAddress());
                txtDate.setText(payment.getTel());
            } else {
                new Alert(Alert.AlertType.INFORMATION, "payment not found!").show();
            }
        }

    public void codeSearchOnAction(ActionEvent actionEvent) {
    }
}

