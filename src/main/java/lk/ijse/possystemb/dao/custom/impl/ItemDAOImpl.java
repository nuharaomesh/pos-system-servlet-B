package lk.ijse.possystemb.dao.custom.impl;

import lk.ijse.possystemb.dao.SQLUtil;
import lk.ijse.possystemb.dto.CustomDTO;
import lk.ijse.possystemb.dto.ItemDTO;
import lk.ijse.possystemb.dao.custom.ItemDAO;
import lk.ijse.possystemb.entity.Customer;
import lk.ijse.possystemb.entity.Item;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {

    @Override
    public List<Item> get() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Item";
        ResultSet resultSet = SQLUtil.execute(sql);

        List<Item> entityList = new ArrayList<>();

        while (resultSet.next()) {
            Item entity = new Item();
            entity.setId(resultSet.getString("id"));
            entity.setItemName(resultSet.getString("itemName"));
            entity.setCategory(resultSet.getString("category"));
            entity.setPrice(resultSet.getFloat("price"));
            entity.setQty(resultSet.getInt("qty"));
            entity.setImg(resultSet.getString("img"));

            entityList.add(entity);
        }

        return entityList;
    }

    @Override
    public boolean save(Item entity) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Item (id, itemName, category, price, qty, img) VALUES (?, ?, ?, ?, ?, ?)";
        return SQLUtil.execute(sql, entity.getId(), entity.getItemName(), entity.getCategory(), entity.getPrice(), entity.getQty(), entity.getImg());
    }

    @Override
    public boolean update(Item entity) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE Item SET itemName = ?, category = ?, price = ?, qty = ?, img = ? WHERE id = ?";
        return SQLUtil.execute(sql, entity.getItemName(), entity.getCategory(), entity.getPrice(), entity.getQty(), entity.getImg(), entity.getId());
    }

    @Override
    public boolean updateItemQty(List<Item> dtoList, Connection connection) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE Item SET qty = ? WHERE id = ?";

        boolean isSuccess = true;

        for (Item entity : dtoList) {

            int affectedRows = SQLUtil.execute(sql, entity.getQty(), entity.getId());

            if (affectedRows == 0) {
                isSuccess = false;
                break;
            }
        }
        return isSuccess;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM Item WHERE id = ?";
        return SQLUtil.execute(sql, id);
    }
}
