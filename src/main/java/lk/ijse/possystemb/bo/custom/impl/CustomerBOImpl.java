package lk.ijse.possystemb.bo.custom.impl;

import lk.ijse.possystemb.bo.custom.CustomerBO;
import lk.ijse.possystemb.dao.DAOFactory;
import lk.ijse.possystemb.dao.custom.CustomerDAO;
import lk.ijse.possystemb.dto.CustomerDTO;
import lk.ijse.possystemb.entity.Customer;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerBOImpl implements CustomerBO {

    private CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public List<CustomerDTO> getAll() throws SQLException, ClassNotFoundException {
        return customerDAO.get().stream()
                .map(c -> new CustomerDTO(c.getId(), c.getName(), c.getGender(), c.getGmail(), c.getPhoneNo()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean saveCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return customerDAO.save(new Customer(dto.getId(), dto.getName(), dto.getGender(), dto.getGmail(), dto.getPhoneNo()));
    }

    @Override
    public boolean updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new Customer(dto.getId(), dto.getName(), dto.getGender(), dto.getGmail(), dto.getPhoneNo()));
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(id);
    }
}
