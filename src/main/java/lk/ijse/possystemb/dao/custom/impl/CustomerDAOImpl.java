package lk.ijse.possystemb.dao.custom.impl;

import lk.ijse.possystemb.dto.CustomerDTO;
import lk.ijse.possystemb.dao.custom.CustomerDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {

    static String GET_ALL_CUSTOMERS = "SELECT id,name,gender,gmail,phoneNo FROM Customer";
    static String SAVE_CUSTOMER = "INSERT INTO Customer (id, name, gender, gmail, phoneNo) VALUES(?, ?, ?, ?, ?)";
    static String UPDATE_CUSTOMER = "UPDATE Customer SET name = ?, gender = ?, gmail = ?, phoneNo = ? WHERE id = ?";
    static String DELETE_CUSTOMER = "DELETE FROM Customer WHERE id = ?";

    @Override
    public List<CustomerDTO> getAll(Connection connection) throws SQLException {

        var customer= connection.prepareStatement(GET_ALL_CUSTOMERS);
        ResultSet resultSet = customer.executeQuery();
        List<CustomerDTO> customerList = new ArrayList<>();

        while (resultSet.next()) {
            CustomerDTO dto = new CustomerDTO();
            dto.setId(resultSet.getString("id"));
            dto.setName(resultSet.getString("name"));
            dto.setGender(resultSet.getString("gender"));
            dto.setGmail(resultSet.getString("gmail"));
            dto.setPhoneNo(resultSet.getInt("phoneNo"));

            customerList.add(dto);
        }

        return customerList;
    }

    @Override
    public boolean save(CustomerDTO dto, Connection connection) throws SQLException {

        var ps = connection.prepareStatement(SAVE_CUSTOMER);

        ps.setString(1, dto.getId());
        ps.setString(2, dto.getName());
        ps.setString(3, dto.getGender());
        ps.setString(4, dto.getGmail());
        ps.setInt(5, dto.getPhoneNo());

        return ps.executeUpdate() > 0;
    }

    @Override
    public boolean update(CustomerDTO dto, Connection connection) throws SQLException {

        var ps = connection.prepareStatement(UPDATE_CUSTOMER);

        ps.setString(1, dto.getName());
        ps.setString(2, dto.getGender());
        ps.setString(3, dto.getGmail());
        ps.setInt(4, dto.getPhoneNo());
        ps.setString(5, dto.getId());

        return ps.executeUpdate() > 0;
    }

    @Override
    public boolean delete(String id, Connection connection) throws SQLException {
        var ps = connection.prepareStatement(DELETE_CUSTOMER);
        ps.setString(1, id);
        return ps.executeUpdate() > 0;
    }
}
