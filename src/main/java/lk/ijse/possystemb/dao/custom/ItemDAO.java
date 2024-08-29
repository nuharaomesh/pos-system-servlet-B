package lk.ijse.possystemb.dao.custom;

import lk.ijse.possystemb.dao.CrudDAO;
import lk.ijse.possystemb.dao.SuperDAO;
import lk.ijse.possystemb.dto.CustomDTO;
import lk.ijse.possystemb.dto.ItemDTO;
import lk.ijse.possystemb.entity.Customer;
import lk.ijse.possystemb.entity.Item;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ItemDAO extends CrudDAO<Item> {
    boolean updateItemQty(List<Item> dtoList, Connection connection) throws SQLException, ClassNotFoundException;
}
