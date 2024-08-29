package lk.ijse.possystemb.dao.custom;

import lk.ijse.possystemb.dao.SuperDAO;
import lk.ijse.possystemb.dto.OrderDetailDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface OrderDetailsDAO extends SuperDAO {
    boolean save(List<OrderDetailDTO> dto, Connection connection) throws SQLException;
}
