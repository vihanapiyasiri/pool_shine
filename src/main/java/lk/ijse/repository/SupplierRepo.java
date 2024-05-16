package lk.ijse.repository;

import lk.ijse.db.DbConnection;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;


public class SupplierRepo {
   /* public static boolean save(Supplier supplier) throws SQLException {
        String sql = "INSERT INTO supplier VALUES(?, ?, ?,?,?)";

       Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, supplier.getSupplierId());
        pstm.setObject(2, supplier.getName());
        pstm.setObject(3, supplier.getPaymentTerms());
        pstm.setObject(4, supplier.getAddress());
        pstm.setObject(5, supplier.getContact());


        return pstm.executeUpdate() > 0;
    }
    public static Supplier searchById(String id) throws SQLException {
        String sql = "SELECT * FROM supplier WHERE Supplier_ID = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String supplierId = resultSet.getString(1);
            String name = resultSet.getString(2);
            String terms = resultSet.getString(3);
            String address = resultSet.getString(4);
            String tel = resultSet.getString(5);


            Supplier supplier = new Supplier(supplierId,name,terms,address,tel);

            return supplier;
        }

        return null;
    }
    public static boolean update(Supplier supplier) throws SQLException {
        String sql = "UPDATE supplier SET Name = ?, Terms = ?,Address=?,Contact=? WHERE Supplier_ID = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, supplier.getName());
        pstm.setObject(2, supplier.getAddress());
        pstm.setObject(3, supplier.getPaymentTerms());
        pstm.setObject(4, supplier.getAddress());
        pstm.setObject(5, supplier.getSupplierId());


        return pstm.executeUpdate() > 0;
    }
    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM supplier WHERE Supplier_ID = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;
    }
    public static List<Supplier> getAll() throws SQLException {
        String sql = "SELECT * FROM supplier";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Supplier> supplierList = new ArrayList<>();

        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String terms = resultSet.getString(3);
            String address = resultSet.getString(4);
            String tel = resultSet.getString(5);


            Supplier supplier = new Supplier(id,name,terms,address,tel);
            supplierList.add(supplier);
        }
        return supplierList;
    }
    public static List<String> getIds() throws SQLException {
        String sql = "SELECT id FROM supplier";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        List<String> idList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            idList.add(id);
        }
        return idList;
    }*/
}

