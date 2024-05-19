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
import lk.ijse.db.DbConnection;
import lk.ijse.model.Supplier;
import lk.ijse.model.Tm.SupplierTm;
import lk.ijse.repository.SupplierRepo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SupplierFormController {
    public TextField txtSupplierId;
    public TextField txtTel;
    public TextField txtPaymentterms;
    public TableColumn <?,?> colTel;
    public TableColumn <?,?> colTerms;
    public TableView<SupplierTm> tblSupplier;
    public TableColumn  <?,?> colId;
    public TableColumn <?,?> colName;
    public TableColumn <?,?> colAddress;


    @FXML
    private AnchorPane root;


    @FXML
    private TextField txtName;

    @FXML
    private TextField txtAddress;
    public void initialize() {
        setCellValueFactory();
        loadAllSuppliers();
    }
    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        colTerms.setCellValueFactory(new PropertyValueFactory<>("terms"));
    }


    private void loadAllSuppliers() {
        ObservableList<SupplierTm> obList = FXCollections.observableArrayList();

        try {
            List<Supplier> supplierList = SupplierRepo.getAll();
            for (Supplier supplier : supplierList) {
                SupplierTm tm = new SupplierTm(
                        supplier.getId(),
                        supplier.getName(),
                        supplier.getAddress(),
                        supplier.getContact(),
                        supplier.getPaymentTerms()
                );

                obList.add(tm);
            }

            tblSupplier.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id = txtSupplierId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String Contact = txtTel.getText();
        String Payment_terms = txtPaymentterms.getText();

        Supplier s1 = new Supplier(id, name, address, Contact,Payment_terms);

        try {
            boolean isSaved = SupplierRepo.save(s1);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "supplier saved!").show();
                initialize();
                clearFields();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void clearFields() {
        txtSupplierId.setText("");
        txtName.setText("");
        txtAddress.setText("");
      txtTel.setText("");
        txtPaymentterms.setText("");
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtSupplierId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String contact = txtTel.getText();
        String paymentTerms = txtPaymentterms.getText();

        String sql = "UPDATE supplier SET Name = ?, Address = ?, Contact = ?, Terms = ? WHERE Supplier_ID = ?";

        try {
            PreparedStatement pstm = DbConnection.getInstance().getConnection()
                    .prepareStatement(sql);

            pstm.setObject(1, name);
            pstm.setObject(2, address);
            pstm.setObject(3, contact);
            pstm.setObject(4, paymentTerms);
            pstm.setObject(5, id);

            if(pstm.executeUpdate() > 0) {
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier updated!").show();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }


    @FXML
    void txtSearchOnAction(ActionEvent event) {
        String id = txtSupplierId.getText();

        String sql = "SELECT * FROM supplier WHERE Supplier_ID = ?";
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setObject(1, id);

            ResultSet resultSet = pstm.executeQuery();
            if(resultSet.next()) {
                String name = resultSet.getString("Name");
                String address = resultSet.getString("Address");
                String tel = resultSet.getString("Contact");
                String terms = resultSet.getString("Terms");

                txtName.setText(name);
                txtAddress.setText(address);
                txtTel.setText(tel);
                txtPaymentterms.setText(terms);
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Supplier ID not found!").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtSupplierId.getText();

        try {
            boolean isDeleted = SupplierRepo.delete(id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "supplier deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }
    private void clearField() {
        txtPaymentterms.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtTel.setText("");
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
