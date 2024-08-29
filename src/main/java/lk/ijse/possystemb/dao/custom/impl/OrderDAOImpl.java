package lk.ijse.possystemb.dao.custom.impl;

import lk.ijse.possystemb.dto.OrderDTO;
import lk.ijse.possystemb.dao.custom.OrderDAO;

import java.sql.Connection;
import java.sql.SQLException;

public class OrderDAOImpl implements OrderDAO {

    static String SAVE_ORDER = "INSERT INTO Orders (id, price, time, qty, cusID) VALUES (?, ?, ?, ?, ?)";

    @Override
    public boolean save(OrderDTO dto, Connection connection) throws SQLException {

        var ps = connection.prepareStatement(SAVE_ORDER);

        ps.setString(1, dto.getId());
        ps.setFloat(2, dto.getPrice());
        ps.setString(3, dto.getTime());
        ps.setInt(4, dto.getQty());
        ps.setString(5, dto.getCusID());

        System.out.println("DTO: "+dto);
        return ps.executeUpdate() > 0;
    }
}
