package lk.ijse.possystemb.bo.custom.impl;

import lk.ijse.possystemb.bo.custom.OrderBO;
import lk.ijse.possystemb.dao.DAOFactory;
import lk.ijse.possystemb.dao.custom.ItemDAO;
import lk.ijse.possystemb.dao.custom.OrderDAO;
import lk.ijse.possystemb.dao.custom.OrderDetailsDAO;
import lk.ijse.possystemb.db.DbConnection;
import lk.ijse.possystemb.dto.CustomDTO;
import lk.ijse.possystemb.dto.OrderDTO;
import lk.ijse.possystemb.dto.OrderDetailDTO;
import lk.ijse.possystemb.entity.Custom;
import lk.ijse.possystemb.entity.Order;
import lk.ijse.possystemb.entity.OrderDetail;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderBOImpl implements OrderBO {


    private ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    private OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    private OrderDetailsDAO orderDetailsDAO = (OrderDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER_DETAILS);

    @Override
    public boolean saveOrder(List<CustomDTO> customDTOS, OrderDTO dto, List<OrderDetailDTO> orderDetails) throws SQLException, ClassNotFoundException {

        System.out.println("Awa1");
        Connection connection = DbConnection.getDbConnection().getConnection();
        connection.setAutoCommit(false);

        System.out.println("Awa2");

        if (!itemDAO.updateItemQty(getItemEntityList(customDTOS))) {
            System.out.println("Awa3");
            connection.rollback();
            connection.setAutoCommit(true);
        }

        if (!orderDAO.save(new Order(dto.getId(), dto.getPrice(), dto.getTime(), dto.getQty(), dto.getCusID()))) {
            System.out.println("Awa4");
            connection.rollback();
            connection.setAutoCommit(true);
        }

        if (!orderDetailsDAO.saveOrderDetails(getOdDetailsEntities(orderDetails))) {
            System.out.println("Awa5");
            connection.rollback();
            connection.setAutoCommit(true);
        }

        System.out.println("Awa6");
        connection.commit();
        connection.setAutoCommit(true);
        return true;
    }

    private List<OrderDetail> getOdDetailsEntities(List<OrderDetailDTO> orderDetails) {
        List<OrderDetail> entityList = new ArrayList<>();

        for (OrderDetailDTO dto : orderDetails) {
            OrderDetail orderDetail = new OrderDetail();

            orderDetail.setOrderID(dto.getOrderID());
            orderDetail.setItemID(dto.getItemID());
            orderDetail.setPrice(dto.getPrice());
            orderDetail.setCount(dto.getCount());

            entityList.add(orderDetail);
        }

        return entityList;
    }

    private List<Custom> getItemEntityList(List<CustomDTO> customDTOS) {
        List<Custom> entityList = new ArrayList<>();

        for (CustomDTO dto : customDTOS) {

            Custom custom = new Custom();
            custom.setId(dto.getId());
            custom.setQty(dto.getQty());

            entityList.add(custom);
        }
        return entityList;
    }
}
