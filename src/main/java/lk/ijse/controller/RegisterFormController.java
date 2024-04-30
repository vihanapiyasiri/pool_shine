package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import lk.ijse.db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterFormController {

    public TextField txtRole;
    public TextField txtPassword;
    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPw;

    @FXML
    private TextField txtUserId;


    private void saveUser(String u_Id, String username,String role, String password) {
        try {
            String sql = "INSERT INTO Users VALUES(?, ?, ?,?)";

            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setObject(1, u_Id);
            pstm.setObject(2, username);
            pstm.setObject(3, role);
            pstm.setObject(4, password);

            if(pstm.executeUpdate() > 0) {
                new Alert(Alert.AlertType.CONFIRMATION, "user saved!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "something happend!").show();
        }
    }

    public void btnRegisterOnAction(ActionEvent actionEvent) {
        String u_Id = txtUserId.getText();
        String username = txtName.getText();
        String password = txtPassword.getText();
        String role = txtRole.getText();

        saveUser(u_Id, username,role, password);
    }
}
