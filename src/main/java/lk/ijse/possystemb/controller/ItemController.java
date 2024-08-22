package lk.ijse.possystemb.controller;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.possystemb.dto.ItemDTO;
import lk.ijse.possystemb.persistance.ItemData;
import lk.ijse.possystemb.persistance.process.ItemDataProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/item")
public class ItemController extends HttpServlet {

    private Connection connection;
    private ItemData dataProcess = new ItemDataProcess();
    static Logger log = LoggerFactory.getLogger(ItemController.class);

    @Override
    public void init() throws ServletException {

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

        try(var writer = resp.getWriter()) {
            List<ItemDTO> dtoList = dataProcess.getAll(connection);

            Jsonb jsonb = JsonbBuilder.create();
            String items = jsonb.toJson(dtoList);

            writer.write(items);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
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
