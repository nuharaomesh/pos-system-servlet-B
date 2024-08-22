package lk.ijse.possystemb.persistance;

import lk.ijse.possystemb.dto.CustomerDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface CustomerData {

    List<CustomerDTO> getAll(Connection connection);
    boolean save(CustomerDTO dto, Connection connection) throws SQLException;
    boolean update(CustomerDTO dto, Connection connection);
    boolean delete(String id, Connection connection);
}
