package lk.ijse.repository;

import lk.ijse.db.DbConnection;
import lk.ijse.model.Payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class PaymentRepo {
    public static boolean save(Payment payment) throws SQLException {
//        In here you can now save your payment
        String sql = "INSERT INTO payment VALUES(?, ?, ?, ?)";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, payment.getId());
        pstm.setObject(2, payment.getAmount());
        pstm.setObject(3, payment.getMethod());
        pstm.setObject(4, payment.getDate());

        return pstm.executeUpdate() > 0;

    }

    public static boolean update(Payment payment) throws SQLException {
        String sql = "UPDATE Payment SET Date = ?, Amount  = ?, Method = ? WHERE Payment_ID = ?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, payment.getId());
        pstm.setObject(2, payment.getAmount());
        pstm.setObject(3, payment.getMethod());
        pstm.setObject(4, payment.getDate());

        return pstm.executeUpdate() > 0;
    }

    public static Payment searchById(String id) throws SQLException {
        String sql = "SELECT * FROM Payment WHERE Payment_ID = ?";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, id);
        ResultSet resultSet = pstm.executeQuery();

        Payment payment = null;

        if (resultSet.next()) {
            String Payment_ID = resultSet.getString(1);
            String Amount= resultSet.getString(2);
            String Method = resultSet.getString(3);
            String Date = resultSet.getString(4);

           payment = new Payment(Payment_ID,Amount,Method,Date);
        }
        return payment;
    }

    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM Payment WHERE Payment_ID = ?";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;
    }

    public static List<Payment> getAll() throws SQLException {
        String sql = "SELECT * FROM Payment";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Payment> paymentsList = new ArrayList<>();
        while (resultSet.next()) {
            String Payment_ID = resultSet.getString(1);
            String Amount= resultSet.getString(2);
            String Method = resultSet.getString(3);
            String Date = resultSet.getString(4);

           Payment payment = new Payment(Payment_ID,Amount,Method,Date);
            paymentsList.add(payment);
        }
      return paymentsList;
    }

    public static List<String> getIds() throws SQLException {
        String sql = "SELECT Payment_ID FROM Payment";

        Connection connection = DbConnection.getInstance().getConnection();
        ResultSet resultSet = connection.prepareStatement(sql).executeQuery();

        List<String> idList = new ArrayList<>();

        while (resultSet.next()) {
            idList.add(resultSet.getString(1));
        }
        return idList;
    }
}

