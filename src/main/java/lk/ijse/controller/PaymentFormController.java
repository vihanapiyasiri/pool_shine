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
import lk.ijse.model.Tm.CustomerTm;
import lk.ijse.repository.CustomerRepo;

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
            loadAllCustomers();
        }

        private void loadAllCustomers() {
            ObservableList<CustomerTm> obList = FXCollections.observableArrayList();

            try {
                List<Customer> customerList = CustomerRepo.getAll();
                for (Customer customer : customerList) {
                    CustomerTm tm = new CustomerTm(
                            customer.getId(),
                            customer.getName(),
                            customer.getAddress(),
                            customer.getTel()
                    );

                    obList.add(tm);
                }

                tblCustomer.setItems(obList);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        private void setCellValueFactory() {
            colId.setCellValueFactory(new PropertyValueFactory<>("Id"));
            colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
            colAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
            colTel.setCellValueFactory(new PropertyValueFactory<>("Contact"));
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
            txtId.setText("");
            txtName.setText("");
            txtAddress.setText("");
            txtTel.setText("");
        }

        @FXML
        void btnDeleteOnAction(ActionEvent event) {
            String id = txtId.getText();

            try {
                boolean isDeleted = CustomerRepo.delete(id);
                if (isDeleted) {
                    initialize();
                    new Alert(Alert.AlertType.CONFIRMATION, "customer deleted!").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }

        @FXML
        void btnSaveOnAction(ActionEvent event) {
            String id = txtId.getText();
            String name = txtName.getText();
            String address = txtAddress.getText();
            String tel = txtTel.getText();
            String userId = credintial[0];

            Customer customer = new Customer(id, name, address, tel, userId);


            try {
                boolean isSaved = CustomerRepo.save(customer);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "customer saved!").show();
                    loadAllCustomers();
                    clearFields();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        @FXML
        void btnUpdateOnAction(ActionEvent event) {
            String id = txtId.getText();
            String name = txtName.getText();
            String address = txtAddress.getText();
            String tel = txtTel.getText();

            Customer customer = new Customer(id, name, address, tel, credintial[0]);

            try {
                boolean isUpdated = CustomerRepo.update(customer);
                if (isUpdated) {
                    initialize();
                    new Alert(Alert.AlertType.CONFIRMATION, "customer updated!").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }

        @FXML
        void txtSearchOnAction(ActionEvent event) throws SQLException {
            String id = txtId.getText();

            Customer customer = CustomerRepo.searchById(id);
            if (customer != null) {
                txtId.setText(customer.getId());
                txtName.setText(customer.getName());
                txtAddress.setText(customer.getAddress());
                txtTel.setText(customer.getTel());
            } else {
                new Alert(Alert.AlertType.INFORMATION, "customer not found!").show();
            }
        }

    }

