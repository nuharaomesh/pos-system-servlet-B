package lk.ijse.possystemb.dao.custom.impl;

import lk.ijse.possystemb.dao.SQLUtil;
import lk.ijse.possystemb.dto.OrderDetailDTO;
import lk.ijse.possystemb.dao.custom.OrderDetailsDAO;
import lk.ijse.possystemb.entity.OrderDetail;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OrderDetailsDAOImpl implements OrderDetailsDAO {

    @Override
    public List<OrderDetail> get() {
        return List.of();
    }

    @Override
    public boolean save(OrderDetail entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(OrderDetail entity) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public boolean saveOrderDetails(List<OrderDetail> entityList) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO OrderDetails (orderID, itemID, price, counts) VALUES (?, ?, ?, ?)";

        boolean isSuccess = true;

        for (OrderDetail entity : entityList) {

            isSuccess = SQLUtil.execute(sql, entity.getOrderID(), entity.getItemID(), entity.getPrice(), entity.getCount());
        }
        return isSuccess;
    }
}