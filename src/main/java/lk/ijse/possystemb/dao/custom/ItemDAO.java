package lk.ijse.possystemb.dao.custom;

import lk.ijse.possystemb.dao.SuperDAO;
import lk.ijse.possystemb.dto.CustomDTO;
import lk.ijse.possystemb.dto.ItemDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ItemDAO extends SuperDAO {
    List<ItemDTO> getAll(Connection connection) throws SQLException;
    boolean save(ItemDTO dto, Connection connection) throws SQLException;
    boolean update(ItemDTO dto, Connection connection) throws SQLException;
    boolean delete(String id, Connection connection) throws SQLException;
    boolean updateItemQty(List<CustomDTO> dtoList, Connection connection) throws SQLException;
}
