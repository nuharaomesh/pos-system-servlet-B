package lk.ijse.possystemb.dao.custom;

import lk.ijse.possystemb.dao.CrudDAO;
import lk.ijse.possystemb.dao.SuperDAO;
import lk.ijse.possystemb.dto.OrderDTO;
import lk.ijse.possystemb.entity.Customer;
import lk.ijse.possystemb.entity.Order;

import java.sql.Connection;
import java.sql.SQLException;

public interface OrderDAO extends CrudDAO<Order> {
}
