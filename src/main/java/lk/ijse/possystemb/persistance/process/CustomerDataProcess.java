package lk.ijse.possystemb.persistance.process;

import lk.ijse.possystemb.dto.CustomerDTO;
import lk.ijse.possystemb.persistance.CustomerData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDataProcess implements CustomerData {

    static String SAVE_CUSTOMER = "INSERT INTO Customer (id, name, gender, gmail, phoneNo) VALUES(?, ?, ?, ?, ?)";
    static String GET_ALL_CUSTOMERS = "SELECT * FROM Csutomer"

    @Override
    public List<CustomerDTO> getAll(Connection connection) throws SQLException {

        var customer= connection.prepareStatement(GET_ALL_CUSTOMERS);
        ResultSet resultSet = customer.executeQuery();
        List<CustomerDTO> customerList = new ArrayList<>();

        while (resultSet.next()) {
            CustomerDTO dto = new CustomerDTO();
            dto.setId(resultSet.getString("id"));
            dto.setName(resultSet.getString("id"));
            dto.setGender(resultSet.getString("id"));
            dto.setGmail(resultSet.getString("id"));
            dto.setPhoneNo(resultSet.getInt("id"));
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
    public boolean update(CustomerDTO dto, Connection connection) {
        return false;
    }

    @Override
    public boolean delete(String id, Connection connection) {
        return false;
    }
}
