package lk.ijse.possystemb.dao.custom.impl;

import lk.ijse.possystemb.dao.SQLUtil;
import lk.ijse.possystemb.dto.CustomerDTO;
import lk.ijse.possystemb.dao.custom.CustomerDAO;
import lk.ijse.possystemb.entity.Customer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public List<Customer> get() throws SQLException, ClassNotFoundException {
        String sql = "SELECT id,name,gender,gmail,phoneNo FROM Customer";

        ResultSet resultSet = SQLUtil.execute(sql);
        List<Customer> entityList = new ArrayList<>();

        while (resultSet.next()) {
            Customer entity = new Customer();
            entity.setId(resultSet.getString("id"));
            entity.setName(resultSet.getString("name"));
            entity.setGender(resultSet.getString("gender"));
            entity.setGmail(resultSet.getString("gmail"));
            entity.setPhoneNo(resultSet.getInt("phoneNo"));

            entityList.add(entity);
        }

        return entityList;
    }

    @Override
    public boolean save(Customer entity) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Customer (id, name, gender, gmail, phoneNo) VALUES (?, ?, ?, ?, ?)";
        return SQLUtil.execute(sql, entity.getId(), entity.getName(), entity.getGender(), entity.getGmail(), entity.getPhoneNo());
    }

    @Override
    public boolean update(Customer entity) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE Customer SET name = ?, gender = ?, gmail = ?, phoneNo = ? WHERE id = ?";
        return SQLUtil.execute(sql, entity.getName(), entity.getGender(), entity.getGmail(), entity.getPhoneNo(), entity.getId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM Customer WHERE id = ?";
        return SQLUtil.execute(sql, id);
    }
}
