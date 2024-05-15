package lk.ijse.repository;
import com.lowagie.text.pdf.AcroFields;
import lk.ijse.db.DbConnection;
import lk.ijse.model.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ItemRepo {
    public static boolean save(Item item) throws SQLException {
//        In here you can now save your customer
        String sql = "INSERT INTO Item VALUES(?, ?, ?, ?)";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, item.getItemId());
        pstm.setObject(2, item.getName());
        pstm.setObject(3, item.getDesc());
        pstm.setObject(4, item.getPrice());

        return pstm.executeUpdate() > 0;

    }
    public static boolean update(Item item) throws SQLException {
        String sql = "UPDATE Customer SET Name = ?, Address  = ?, Contact = ? WHERE Customer_ID = ?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, item.getItemId());
        pstm.setObject(2, item.getName());
        pstm.setObject(3, item.getDesc());
        pstm.setObject(4, item.getPrice());

        return pstm.executeUpdate() > 0;
    }

    public static Item searchById(String id) throws SQLException {
        String sql = "SELECT * FROM Customer WHERE Customer_ID = ?";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, id);
        ResultSet resultSet = pstm.executeQuery();

        Item customer = null;

        if (resultSet.next()) {
            String Customer_ID = resultSet.getString(1);
            String Name= resultSet.getString(2);
            String Address = resultSet.getString(3);
            double price = resultSet.getDouble(4);

            customer = new Item(Customer_ID,Name,Address,price);
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

    public static List<Item> getAll() throws SQLException {
        String sql = "SELECT * FROM Customer";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Item> customersList = new ArrayList<>();
        while (resultSet.next()) {
            String Customer_ID = resultSet.getString(1);
            String Name= resultSet.getString(2);
            String Address = resultSet.getString(3);
            double Contact = resultSet.getDouble(4);

            Item customer = new Item(Customer_ID,Name,Address,Contact);
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

