package lk.ijse.possystemb.dao.custom;

import lk.ijse.possystemb.dao.CrudDAO;
import lk.ijse.possystemb.dao.SuperDAO;
import lk.ijse.possystemb.dto.CustomerDTO;
import lk.ijse.possystemb.entity.Customer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface CustomerDAO extends CrudDAO<Customer> {

}
