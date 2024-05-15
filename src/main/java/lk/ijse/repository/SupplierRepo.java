package lk.ijse.repository;

import lk.ijse.db.DbConnection;
import lk.ijse.model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;


public class SupplierRepo {
    public static boolean save(Supplier supplier) throws SQLException {
//        In here you can now save your customer
        String sql = "INSERT INTO Supplier VALUES(?, ?, ?, ? ,?)";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, supplier.getSupplierId());
        pstm.setObject(2, supplier.getName());
        pstm.setObject(3, supplier.getAddress());
        pstm.setObject(4, supplier.getContact());
        pstm.setObject(5, supplier.getPayment_terms());

        return pstm.executeUpdate() > 0;

    }

    public static boolean update(Supplier supplier) throws SQLException {
        String sql = "UPDATE Customer SET Name = ?, Address  = ?, Contact = ? WHERE Customer_ID = ?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, supplier.getName());
        pstm.setObject(2, supplier.getAddress());
        pstm.setObject(3, supplier.getTel());
        pstm.setObject(4, supplier.getId());

        return pstm.executeUpdate() > 0;
    }

    public static Supplier searchById(String id) throws SQLException {
        String sql = "SELECT * FROM Customer WHERE Customer_ID = ?";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, id);
        ResultSet resultSet = pstm.executeQuery();

        Customer customer = null;

        if (resultSet.next()) {
            String Customer_ID = resultSet.getString(1);
            String Name= resultSet.getString(2);
            String Address = resultSet.getString(3);
            String Contact = resultSet.getString(4);

            customer = new Customer(Customer_ID,Name,Address,Contact);
        }
        return customer;
    }

    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM Customer WHERE Customer_ID = ?";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;
    }

    public static List<lk.ijse.model.Supplier> getAll() throws SQLException {
        String sql = "SELECT * FROM Customer";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Customer> customersList = new ArrayList<>();
        while (resultSet.next()) {
            String Customer_ID = resultSet.getString(1);
            String Name= resultSet.getString(2);
            String Address = resultSet.getString(3);
            String Contact = resultSet.getString(4);

            Customer customer = new Customer(Customer_ID,Name,Address,Contact);
            customersList.add(customer);
        }
        return customersList;
    }

    public static List<String> getIds() throws SQLException {
        String sql = "SELECT Customer_ID FROM Customer";

        Connection connection = DbConnection.getInstance().getConnection();
        ResultSet resultSet = connection.prepareStatement(sql).executeQuery();

        List<String> idList = new ArrayList<>();

        while (resultSet.next()) {
            idList.add(resultSet.getString(1));
        }
        return idList;
    }
}

