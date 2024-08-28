package lk.ijse.possystemb.persistance;

import lk.ijse.possystemb.dto.OrderDTO;

import java.sql.Connection;
import java.sql.SQLException;

public interface OrderData {
    boolean save(OrderDTO dto, Connection connection) throws SQLException;
}
