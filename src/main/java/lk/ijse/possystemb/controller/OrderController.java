package lk.ijse.possystemb.controller;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.possystemb.dto.CustomDTO;
import lk.ijse.possystemb.dto.OrderDTO;
import lk.ijse.possystemb.dto.OrderDetailDTO;
import lk.ijse.possystemb.dao.custom.ItemDAO;
import lk.ijse.possystemb.dao.custom.OrderDAO;
import lk.ijse.possystemb.dao.custom.OrderDetailsDAO;
import lk.ijse.possystemb.dao.custom.impl.ItemDAOImpl;
import lk.ijse.possystemb.dao.custom.impl.OrderDAOImpl;
import lk.ijse.possystemb.dao.custom.impl.OrderDetailsDAOImpl;
import lk.ijse.possystemb.util.UtilProcess;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/order")
public class OrderController extends HttpServlet {

    private Connection connection;
    private ItemDAO itemProcess = new ItemDAOImpl();
    private OrderDAO orderProcess = new OrderDAOImpl();
    private OrderDetailsDAO orderDetailProcess = new OrderDetailsDAOImpl();
    private UtilProcess utilProcess;
    static Logger log = LoggerFactory.getLogger(CustomerController.class);
    private OrderDTO orderDTO = new OrderDTO();
    private OrderDetailDTO orderDetailDTO = new OrderDetailDTO();

    @Override
    public void init(ServletConfig config) throws ServletException {

        try {

            var ctx = new InitialContext();
            DataSource pool = (DataSource) ctx.lookup("java:comp/env/jdbc/posSys");
            this.connection = pool.getConnection();
            log.info("Connection Successfully created!");
        } catch (Exception e) {

            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (!req.getContentType().toLowerCase().startsWith("application/json") || req.getContentType() == null) {
            resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
        }

        StringBuilder json = new StringBuilder();
        String line;
        while ((line = req.getReader().readLine()) != null) {
            json.append(line);
        }

        Jsonb jsonb = JsonbBuilder.create();

        // Convert the JSON string to List<Object>
        List<Object> orderData = jsonb.fromJson(json.toString(), new ArrayList<Object>(){}.getClass().getGenericSuperclass());

        // Convert the first part to List<CustomDTO>
        String itemsJson = jsonb.toJson(orderData.get(0));
        List<CustomDTO> customDTOS = jsonb.fromJson(itemsJson, new ArrayList<CustomDTO>() {}.getClass().getGenericSuperclass());
        customDTOS.forEach(System.out::println);

        // Convert the second part to OrderDTO
        String orderJson = jsonb.toJson(orderData.get(1));
        OrderDTO order = jsonb.fromJson(orderJson, OrderDTO.class);
        System.out.println(order);

        // Convert the third part to List<OrderDetail>
        String orderDetailsJson = jsonb.toJson(orderData.get(2));
        List<OrderDetailDTO> orderDetails = jsonb.fromJson(orderDetailsJson, new ArrayList<OrderDetailDTO>(){}.getClass().getGenericSuperclass());
        orderDetails.forEach(System.out::println);

        try {

            connection.setAutoCommit(false);

            if (!itemProcess.updateItemQty(customDTOS, connection)) {
                System.out.println("1");
                connection.rollback();
                connection.setAutoCommit(true);
                return;
            }

            if(!orderProcess.save(order, connection)) {
                System.out.println("2");
                connection.rollback();
                connection.setAutoCommit(true);
                return;
            }

            if(!orderDetailProcess.save(orderDetails, connection)) {
                System.out.println("3");
                connection.rollback();
                connection.setAutoCommit(true);
                return;
            }

            connection.commit();
            System.out.println("Wade hari");
            connection.setAutoCommit(true);

        } catch (SQLException e) {
            connection.setAutoCommit(true);
            e.printStackTrace();
        }

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
