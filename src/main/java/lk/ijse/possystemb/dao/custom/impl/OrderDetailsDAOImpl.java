package lk.ijse.possystemb.dao.custom.impl;

import lk.ijse.possystemb.dto.OrderDetailDTO;
import lk.ijse.possystemb.dao.custom.OrderDetailsDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OrderDetailsDAOImpl implements OrderDetailsDAO {

    static String SAVE_ORDER_DETAILS = "INSERT INTO OrderDetails (orderID, itemID, price, counts) VALUES (?, ?, ?, ?)";

    @Override
    public boolean save(List<OrderDetailDTO> dtoList, Connection connection) throws SQLException {

        var ps = connection.prepareStatement(SAVE_ORDER_DETAILS);
        boolean isSuccess = true;

        for (OrderDetailDTO dto : dtoList) {

            ps.setString(1, dto.getOrderID());
            ps.setString(2, dto.getItemID());
            ps.setFloat(3, dto.getPrice());
            ps.setInt(4, dto.getCount());

            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                isSuccess = false;
                break;
            }
        }
        return isSuccess;
    }
}
