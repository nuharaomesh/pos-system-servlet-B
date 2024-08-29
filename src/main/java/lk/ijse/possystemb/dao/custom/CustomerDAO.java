package lk.ijse.possystemb.dao.custom;

import lk.ijse.possystemb.dao.SuperDAO;
import lk.ijse.possystemb.dto.CustomerDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface CustomerDAO extends SuperDAO {

    List<CustomerDTO> getAll(Connection connection) throws SQLException;
    boolean save(CustomerDTO dto, Connection connection) throws SQLException;
    boolean update(CustomerDTO dto, Connection connection) throws SQLException;
    boolean delete(String id, Connection connection) throws SQLException;
}
