package lk.ijse.possystemb.bo.custom;

import lk.ijse.possystemb.bo.SuperBO;
import lk.ijse.possystemb.dto.CustomerDTO;

import java.sql.Connection;
import java.util.List;

public interface CustomerBO extends SuperBO {
    List<CustomerDTO> getAll();
    boolean saveCustomer(CustomerDTO dto);
    boolean updateCustomer(CustomerDTO dto);
    boolean deleteCustomer(String id);
}
