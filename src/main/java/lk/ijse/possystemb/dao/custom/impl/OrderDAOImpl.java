package lk.ijse.possystemb.dao.custom.impl;

import lk.ijse.possystemb.dao.SQLUtil;
import lk.ijse.possystemb.dto.OrderDTO;
import lk.ijse.possystemb.dao.custom.OrderDAO;
import lk.ijse.possystemb.entity.Order;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {

    @Override
    public List<Order> get() {
        return List.of();
    }

    @Override
    public boolean save(Order entity) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Orders (id, price, time, qty, cusID) VALUES (?, ?, ?, ?, ?)";
        return SQLUtil.execute(sql, entity.getId(), entity.getPrice(), entity.getTime(), entity.getQty(), entity.getCusID());
    }

    @Override
    public boolean update(Order entity) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }
}