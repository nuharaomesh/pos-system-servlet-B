package lk.ijse.possystemb.bo.custom.impl;

import lk.ijse.possystemb.bo.custom.CustomerBO;
import lk.ijse.possystemb.dto.CustomerDTO;

import java.sql.Connection;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {

    @Override
    public List<CustomerDTO> getAll() {
        return List.of();
    }

    @Override
    public boolean saveCustomer(CustomerDTO dto) {
        return false;
    }

    @Override
    public boolean updateCustomer(CustomerDTO dto) {
        return false;
    }

    @Override
    public boolean deleteCustomer(String id) {
        return false;
    }
}
