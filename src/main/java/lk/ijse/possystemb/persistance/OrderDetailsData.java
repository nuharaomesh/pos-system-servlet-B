package lk.ijse.possystemb.persistance;

import lk.ijse.possystemb.dto.OrderDetailDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface OrderDetailsData {
    boolean save(List<OrderDetailDTO> dto, Connection connection) throws SQLException;
}
