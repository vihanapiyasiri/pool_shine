package lk.ijse.repository;

import lk.ijse.db.DbConnection;
import lk.ijse.model.Customer;
import lk.ijse.model.Quotation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class QuotationRepo {
    public static boolean save(Quotation quotation) throws SQLException {
//        In here you can now save your customer
        String sql = "INSERT INTO Quotation VALUES(?, ?, ?, ?)";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, quotation.getId());
        pstm.setObject(2, quotation.getDate());
        pstm.setObject(3, quotation.getAmount());
        pstm.setObject(4, quotation.getOrderId());

        return pstm.executeUpdate() > 0;

    }

    public static boolean update(Quotation quotation) throws SQLException {
        String sql = "UPDATE Quotation SET Date  = ?, Amount = ?, Order_ID =? WHERE Quotation_ID = ?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, quotation.getDate());
        pstm.setObject(2, quotation.getAmount());
        pstm.setObject(3, quotation.getOrderId());
        pstm.setObject(4, quotation.getId());

        return pstm.executeUpdate() > 0;
    }

    public static Quotation searchById(String id) throws SQLException {
        String sql = "SELECT * FROM Quotation WHERE Quotation_ID = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, id);

        ResultSet resultSet = pstm.executeQuery();

      //  Quotation quotation = null;

        if (resultSet.next()) {
            String quotation_id = resultSet.getString(1);
            String date = resultSet.getString(2);
            String amount = resultSet.getString(3);
            String o_id = resultSet.getString(4);

            Quotation quotation = new Quotation(quotation_id, date, amount, o_id);

            return quotation;
        }
        return null;
       // return new Customer();
    }

    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM Quotation WHERE Quotation_ID = ?";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;
    }

    public static List<Quotation> getAll() throws SQLException {
        String sql = "SELECT * FROM Quotation";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Quotation> quotationList = new ArrayList<>();
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String date= resultSet.getString(2);
            String amount = resultSet.getString(3);
            String o_id = resultSet.getString(4);

            Quotation quotation = new Quotation(id, date, amount, o_id);
            quotationList.add(quotation);
       }
        return quotationList;

    }

    public static List<String> getIds() throws SQLException {
        String sql = "SELECT Quotation_ID FROM Quotation";

        Connection connection = DbConnection.getInstance().getConnection();
        ResultSet resultSet = connection.prepareStatement(sql).executeQuery();

        List<String> idList = new ArrayList<>();

        while (resultSet.next()) {
            idList.add(resultSet.getString(1));
        }
        return idList;
    }
}

