package lk.ijse.possystemb.persistance;

import lk.ijse.possystemb.dto.ItemDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ItemData {
    List<ItemDTO> getAll(Connection connection) throws SQLException;
    boolean save(ItemDTO dto, Connection connection) throws SQLException;
    boolean update(ItemDTO dto, Connection connection) throws SQLException;
    boolean delete(String id, Connection connection);
}
