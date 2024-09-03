package com.dinidu.samplecrud.model;

import com.dinidu.samplecrud.db.DBConnection;
import com.dinidu.samplecrud.dto.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerModel {
    public ArrayList<Customer> getAllCustomers() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM customer";
        PreparedStatement st = connection.prepareStatement(sql);
        ResultSet rs = st.executeQuery();
        ArrayList<Customer> customers = new ArrayList<>();
        while (rs.next()) {
            Customer customer = new Customer(
                    rs.getString("CustID"),
                    rs.getString("CustTitle"),
                    rs.getString("CustName"),
                    rs.getString("DOB"),
                    rs.getString("Province"),
                    rs.getString("CustAddress"),
                    rs.getString("City"),
                    rs.getDouble("salary"),
                    rs.getString("PostalCode")
                    );
            customers.add(customer);
        }
        return customers;
    }
}
