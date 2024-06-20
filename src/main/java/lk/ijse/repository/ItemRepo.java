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
//        In here you can now save your item
        String sql = "INSERT INTO Item VALUES(?, ?, ?, ?)" ;
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, item.getItemId());
        pstm.setObject(2, item.getName());
        pstm.setObject(3, item.getDesc());
        pstm.setObject(4, item.getPrice());

        return pstm.executeUpdate() > 0;
    }




    public static boolean update(Item item) throws SQLException {
        String sql = "UPDATE item SET ID = ?,Name  = ?, Description = ? Price = ? WHERE Item_ID = ?";

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
            String Item_ID = resultSet.getString(1);
            String Name= resultSet.getString(2);
            String Desc = resultSet.getString(3);
            String price = resultSet.getString(4);

            customer = new Item(Item_ID,Name,Desc,price);
        }
        return customer;
    }

    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM Item WHERE Item_ID = ?";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;
    }

    public static List<Item> getAll() throws SQLException {
        String sql = "SELECT * FROM Item";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Item> itemList = new ArrayList<>();
        while (resultSet.next()) {
            String Item_ID = resultSet.getString(1);
            String Name= resultSet.getString(2);
            String Description = resultSet.getString(3);
            String Price = resultSet.getString(4);

            Item item = new Item(Item_ID,Name,Description,Price);
            itemList.add(item);
        }
        return itemList;
    }

    public static List<String> getIds() throws SQLException {
        String sql = "SELECT Item_ID FROM Item";

        Connection connection = DbConnection.getInstance().getConnection();
        ResultSet resultSet = connection.prepareStatement(sql).executeQuery();

        List<String> idList = new ArrayList<>();

        while (resultSet.next()) {
            idList.add(resultSet.getString(1));
        }
        return idList;
    }
}

