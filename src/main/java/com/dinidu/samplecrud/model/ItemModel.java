package com.dinidu.samplecrud.model;

import com.dinidu.samplecrud.dto.Item;
import com.dinidu.samplecrud.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemModel {
    public ArrayList<Item> getAllItems() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM item";
        PreparedStatement st = connection.prepareStatement(sql);
        ResultSet rs = st.executeQuery();
        ArrayList<Item> items = new ArrayList<>();
        while (rs.next()) {
            Item item = new Item(
                    rs.getString("ItemCode"),
                    rs.getString("Description"),
                    rs.getString("PackSize"),
                    rs.getString("UnitPrice"),
                    rs.getInt("QtyOnHand")
            );
            items.add(item);
        }
        return items;
    }

    public void saveItem(Item item) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO item (ItemCode, Description, PackSize, UnitPrice, QtyOnHand) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement st = connection.prepareStatement(sql);
        st.setString(1, item.getItemCode());
        st.setString(2, item.getDescription());
        st.setString(3, item.getPackSize());
        st.setString(4, item.getUnitPrice());
        st.setInt(5, item.getQtyOnHand());
        st.executeUpdate();
    }

    public void updateItem(Item item) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "UPDATE item SET Description = ?, PackSize = ?, UnitPrice = ?, QtyOnHand = ? WHERE ItemCode = ?";
        PreparedStatement st = connection.prepareStatement(sql);
        st.setString(1, item.getDescription());
        st.setString(2, item.getPackSize());
        st.setString(3, item.getUnitPrice());
        st.setInt(4, item.getQtyOnHand());
        st.setString(5, item.getItemCode());
        st.executeUpdate();
    }

    public void deleteItem(String itemCode) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "DELETE FROM item WHERE ItemCode = ?";
        PreparedStatement st = connection.prepareStatement(sql);
        st.setString(1, itemCode);
        st.executeUpdate();
    }
}