package lk.ijse.possystemb.persistance.process;

import lk.ijse.possystemb.dto.ItemDTO;
import lk.ijse.possystemb.persistance.ItemData;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDataProcess implements ItemData {

    static String GET_ALL_ITEMS = "SELECT * FROM Item";
    static String SAVE_ITEM = "INSERT INTO Item (id, itemName, category, price, qty, img) VALUES (?, ?, ?, ?, ?, ?)";
    static String UPDATE_ITEM = "UPDATE Item SET itemName = ?, category = ?, price = ?, qty = ?, img = ? WHERE id = ?";

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
    public boolean save(ItemDTO dto, Connection connection) throws SQLException {

        var ps = connection.prepareStatement(SAVE_ITEM);

        ps.setString(1, dto.getId());
        ps.setString(2, dto.getItemName());
        ps.setString(3, dto.getCategory());
        ps.setFloat(4, dto.getPrice());
        ps.setInt(5, dto.getQty());
        ps.setString(6, dto.getImg());

        return ps.executeUpdate() > 0;
    }

    @Override
    public boolean update(ItemDTO dto, Connection connection) throws SQLException {

        var ps = connection.prepareStatement(UPDATE_ITEM);

        ps.setString(1, dto.getItemName());
        ps.setString(2, dto.getCategory());
        ps.setFloat(3, dto.getPrice());
        ps.setInt(4, dto.getQty());
        ps.setString(5, dto.getImg());
        ps.setString(6, dto.getId());

        return ps.executeUpdate() > 0;
    }

    @Override
    public boolean delete(String id, Connection connection) {
        return false;
    }
}
