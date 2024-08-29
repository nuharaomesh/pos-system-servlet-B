package lk.ijse.possystemb.dao.custom;

import lk.ijse.possystemb.dao.CrudDAO;
import lk.ijse.possystemb.dao.SuperDAO;
import lk.ijse.possystemb.dto.OrderDetailDTO;
import lk.ijse.possystemb.entity.Customer;
import lk.ijse.possystemb.entity.OrderDetail;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface OrderDetailsDAO extends CrudDAO<OrderDetail> {
    boolean saveOrderDetails(List<OrderDetail> entityList) throws SQLException, ClassNotFoundException;
}
