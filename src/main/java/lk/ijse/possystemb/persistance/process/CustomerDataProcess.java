package lk.ijse.possystemb.persistance.process;

import lk.ijse.possystemb.dto.CustomerDTO;
import lk.ijse.possystemb.persistance.CustomerData;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CustomerDataProcess implements CustomerData {

    static String SAVE_CUSTOMER = "INSERT INTO Customer (id, name, gender, gmail, phoneNo) VALUES(?, ?, ?, ?, ?)";

    @Override
    public List<CustomerDTO> getAll(Connection connection) {
        return List.of();
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
