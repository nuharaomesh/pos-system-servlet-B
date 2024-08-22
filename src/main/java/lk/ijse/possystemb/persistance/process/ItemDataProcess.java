package lk.ijse.possystemb.persistance.process;

import lk.ijse.possystemb.dto.ItemDTO;
import lk.ijse.possystemb.persistance.ItemData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDataProcess implements ItemData {

    static String GET_ALL_ITEMS = "SELECT * FROM Item";

    @Override
    public List<ItemDTO> getAll(Connection connection) throws SQLException {

        var item = connection.prepareStatement(GET_ALL_ITEMS);
        ResultSet resultSet = item.executeQuery();
        List<ItemDTO> itemDTOList = new ArrayList<>();

        while (resultSet.next()) {
            ItemDTO dto = new ItemDTO();
            dto.setId(resultSet.getString("id"));
            dto.setItemName(resultSet.getString("itemName"));
            dto.setCategory(resultSet.getString("category"));
            dto.setPrice(resultSet.getFloat("price"));
            dto.setQty(resultSet.getInt("qty"));
            dto.setImg(resultSet.getString("img"));
        }

        return itemDTOList;
    }

    @Override
    public boolean save(ItemDTO dto, Connection connection) {
        return false;
    }

    @Override
    public boolean update(ItemDTO dto, Connection connection) {
        return false;
    }

    @Override
    public boolean delete(String id, Connection connection) {
        return false;
    }
}
