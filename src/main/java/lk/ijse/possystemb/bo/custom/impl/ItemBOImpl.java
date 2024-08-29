package lk.ijse.possystemb.bo.custom.impl;

import lk.ijse.possystemb.bo.custom.ItemBO;
import lk.ijse.possystemb.dao.DAOFactory;
import lk.ijse.possystemb.dao.custom.ItemDAO;
import lk.ijse.possystemb.dto.CustomerDTO;
import lk.ijse.possystemb.dto.ItemDTO;
import lk.ijse.possystemb.entity.Item;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ItemBOImpl implements ItemBO {

    private ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);

    @Override
    public List<ItemDTO> getAll() throws SQLException, ClassNotFoundException {
        return itemDAO.get().stream()
                .map(i -> new ItemDTO(i.getId(), i.getItemName(), i.getCategory(), i.getPrice(), i.getQty(), i.getImg()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean saveItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        return itemDAO.save(new Item(dto.getId(), dto.getItemName(), dto.getCategory(), dto.getPrice(), dto.getQty(), dto.getImg()));
    }

    @Override
    public boolean updateItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        return itemDAO.update(new Item(dto.getId(), dto.getItemName(), dto.getCategory(), dto.getPrice(), dto.getQty(), dto.getImg()));
    }

    @Override
    public boolean deleteItem(String id) throws SQLException, ClassNotFoundException {
        return itemDAO.delete(id);
    }
}
