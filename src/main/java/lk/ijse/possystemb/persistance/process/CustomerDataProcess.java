package lk.ijse.possystemb.persistance.process;

import lk.ijse.possystemb.dto.CustomerDTO;
import lk.ijse.possystemb.persistance.CustomerData;

import java.sql.Connection;
import java.util.List;

public class CustomerDataProcess implements CustomerData {

    @Override
    public List<CustomerDTO> getAll(Connection connection) {
        return List.of();
    }

    @Override
    public boolean save(CustomerDTO dto, Connection connection) {
        return false;
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
