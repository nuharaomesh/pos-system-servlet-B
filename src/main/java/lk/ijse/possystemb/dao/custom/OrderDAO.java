package lk.ijse.possystemb.dao.custom;

import lk.ijse.possystemb.dao.SuperDAO;
import lk.ijse.possystemb.dto.OrderDTO;

import java.sql.Connection;
import java.sql.SQLException;

public interface OrderDAO extends SuperDAO {
    boolean save(OrderDTO dto, Connection connection) throws SQLException;
}
