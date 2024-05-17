package lk.ijse.controller;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.db.DbConnection;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DashboardFormController {
    public AnchorPane rootNode;
    public Label lblCustomerCount;
    private int customerCount;

    @FXML
    private AnchorPane bodyPane;

    public void initialize() {
    }

    private void setCustomerCount(int customerCount) {
        lblCustomerCount.setText(String.valueOf(customerCount));
    }

    private int getCustomerCount() throws SQLException {
        String sql = "SELECT COUNT(*) AS customer_count FROM customer";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        int customerCount = 0;
        if(resultSet.next()) {
            customerCount = resultSet.getInt("customer_count");
        }
        return customerCount;
    }

    @FXML
    void btnCustomerOnAction(ActionEvent event) throws IOException {
        /*AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/customer_form.fxml"));
        Stage stage = (Stage) rootNode.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Customer Form");
        stage.centerOnScreen();*/
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/customer_form.fxml"));
            Parent root = loader.load();
            bodyPane.getChildren().clear();
            bodyPane.getChildren().add(root);
            FadeTransition fadeIn = new FadeTransition(Duration.millis(1500), bodyPane);
            fadeIn.setFromValue(0.0);
            fadeIn.setToValue(1.0);
            fadeIn.play();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnItemOnAction(ActionEvent event) throws IOException {
        /*AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/item_form.fxml"));
        Stage stage = (Stage) rootNode.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Item Form");
        stage.centerOnScreen();*/

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/item_form.fxml"));
            Parent root = loader.load();
            bodyPane.getChildren().clear();
            bodyPane.getChildren().add(root);
            FadeTransition fadeIn = new FadeTransition(Duration.millis(1500), bodyPane);
            fadeIn.setFromValue(0.0);
            fadeIn.setToValue(1.0);
            fadeIn.play();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) throws IOException {
        /*AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/order_form.fxml"));
        Stage stage = (Stage) rootNode.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Order Form");
        stage.centerOnScreen();
*/
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/order_form.fxml"));
            Parent root = loader.load();
            bodyPane.getChildren().clear();
            bodyPane.getChildren().add(root);
            FadeTransition fadeIn = new FadeTransition(Duration.millis(1500), bodyPane);
            fadeIn.setFromValue(0.0);
            fadeIn.setToValue(1.0);
            fadeIn.play();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void btnExitOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/login_form.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Login Form");
    }


    public void btnSupplier(ActionEvent actionEvent) throws IOException {
        /*AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/supplier_form.fxml"));
        Stage stage = (Stage) rootNode.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Supplier Form");
        stage.centerOnScreen();*/

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/supplier_form.fxml"));
            Parent root = loader.load();
            bodyPane.getChildren().clear();
            bodyPane.getChildren().add(root);
            FadeTransition fadeIn = new FadeTransition(Duration.millis(1500), bodyPane);
            fadeIn.setFromValue(0.0);
            fadeIn.setToValue(1.0);
            fadeIn.play();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnContractOrder(ActionEvent actionEvent) throws IOException {
       /* AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/contract_order.fxml"));
        Stage stage = (Stage) rootNode.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Supplier Form");
        stage.centerOnScreen();
*/
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/contract_order.fxml"));
            Parent root = loader.load();
            bodyPane.getChildren().clear();
            bodyPane.getChildren().add(root);
            FadeTransition fadeIn = new FadeTransition(Duration.millis(1500), bodyPane);
            fadeIn.setFromValue(0.0);
            fadeIn.setToValue(1.0);
            fadeIn.play();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnPayment(ActionEvent actionEvent) throws IOException {
        /*AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/payment_form.fxml"));
        Stage stage = (Stage) rootNode.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Supplier Form");
        stage.centerOnScreen();*/

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/payment_form.fxml"));
            Parent root = loader.load();
            bodyPane.getChildren().clear();
            bodyPane.getChildren().add(root);
            FadeTransition fadeIn = new FadeTransition(Duration.millis(1500), bodyPane);
            fadeIn.setFromValue(0.0);
            fadeIn.setToValue(1.0);
            fadeIn.play();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnQuotation(ActionEvent actionEvent) throws IOException {
       /* AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/quotation_form.fxml"));
        Stage stage = (Stage) rootNode.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Supplier Form");
        stage.centerOnScreen();*/

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/quotation_form.fxml"));
            Parent root = loader.load();
            bodyPane.getChildren().clear();
            bodyPane.getChildren().add(root);
            FadeTransition fadeIn = new FadeTransition(Duration.millis(1500), bodyPane);
            fadeIn.setFromValue(0.0);
            fadeIn.setToValue(1.0);
            fadeIn.play();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
