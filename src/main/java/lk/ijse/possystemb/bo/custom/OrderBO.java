package lk.ijse.possystemb.bo.custom;

import lk.ijse.possystemb.bo.SuperBO;
import lk.ijse.possystemb.dto.CustomDTO;
import lk.ijse.possystemb.dto.OrderDTO;
import lk.ijse.possystemb.dto.OrderDetailDTO;

import java.sql.SQLException;
import java.util.List;

public interface OrderBO extends SuperBO {
    boolean saveOrder(List<CustomDTO> customDTOS, OrderDTO order, List<OrderDetailDTO> orderDetails) throws SQLException, ClassNotFoundException;
}
