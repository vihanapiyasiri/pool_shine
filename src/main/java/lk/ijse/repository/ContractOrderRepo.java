package lk.ijse.repository;

import lk.ijse.db.DbConnection;
import lk.ijse.model.ContractOrder;
import lk.ijse.model.ContractOrder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ContractOrderRepo {
    public static boolean save(ContractOrder contractorder) throws SQLException {
//        In here you can now save your contractorder
        String sql = "INSERT INTO ContractOrder VALUES(?, ?, ?)";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, contractorder.getId());
        pstm.setObject(2, contractorder.getDate());
        pstm.setObject(3, contractorder.getAmount());

        return pstm.executeUpdate() > 0;

    }

    public static boolean update(ContractOrder contractorder) throws SQLException {
        String sql = "UPDATE ContractOrder SET Id = ?, Name  = ?, Amount = ? WHERE ContractOrder_ID = ?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, contractorder.getId());
        pstm.setObject(2, contractorder.getDate());
        pstm.setObject(3, contractorder.getAmount());
        return pstm.executeUpdate() > 0;
    }

    public static ContractOrder searchById(String id) throws SQLException {
        String sql = "SELECT * FROM ContractOrder WHERE ContractOrder_ID = ?";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, id);
        ResultSet resultSet = pstm.executeQuery();

        ContractOrder contractorder = null;

        if (resultSet.next()) {
            String ID = resultSet.getString(1);
            String Date= resultSet.getString(2);
            String Amount = resultSet.getString(3);
            String Customer_Id = resultSet.getString(4);

            contractorder = new ContractOrder(ID,Date,Amount,Customer_Id);
        }
        return contractorder;
    }

    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM ContractOrder WHERE Contract_ID = ?";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;
    }

    public static List<ContractOrder> getAll() throws SQLException {
        String sql = "SELECT * FROM ContractOrder";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<ContractOrder> contractordersList = new ArrayList<>();
        while (resultSet.next()) {
            String ID = resultSet.getString(1);
            String Date= resultSet.getString(2);
            String Amount = resultSet.getString(3);
            String Customer_ID = resultSet.getString(5);

            ContractOrder contractorder = new ContractOrder(ID,Date,Amount,Customer_ID);
            contractordersList.add(contractorder);
        }
        return contractordersList;
    }

    public static List<String> getIds() throws SQLException {
        String sql = "SELECT ContractOrder_ID FROM Customer";

        Connection connection = DbConnection.getInstance().getConnection();
        ResultSet resultSet = connection.prepareStatement(sql).executeQuery();

        List<String> idList = new ArrayList<>();

        while (resultSet.next()) {
            idList.add(resultSet.getString(1));
        }
        return idList;
    }
}

