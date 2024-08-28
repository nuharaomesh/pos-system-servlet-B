package lk.ijse.possystemb.controller;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import lk.ijse.possystemb.dto.ItemDTO;
import lk.ijse.possystemb.persistance.ItemData;
import lk.ijse.possystemb.persistance.process.ItemDataProcess;
import lk.ijse.possystemb.util.UtilProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/item")
@MultipartConfig
public class ItemController extends HttpServlet {

    private UtilProcess utilProcess;
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

        if (!req.getContentType().toLowerCase().startsWith("multipart/form-data") || req.getContentType() == null ) {
            resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
        }

        String itemName = req.getParameter("itemName");
        String category = req.getParameter("category");
        float price = Float.parseFloat(req.getParameter("price"));
        int qty = Integer.parseInt(req.getParameter("qty"));
        Part filePart = req.getPart("img");

        String fileName = filePart.getSubmittedFileName();

        String id = utilProcess.generateID();

        ItemDTO dto = new ItemDTO(id, itemName, category, price, qty, fileName);

        try(var writer = resp.getWriter()) {

            if (dataProcess.save(dto, connection)) {
                String fileSavePath = "/home/omesh/vs code/pos-system/assets/img/" + fileName;
                filePart.write(fileSavePath);

                log.info("Item Successfully Saved!");
                writer.write("Item Saved!");
                resp.setStatus(HttpServletResponse.SC_CREATED);
            } else {
                log.error("Item Not Saved!");
                writer.write("Item Not Saved!");
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (!req.getContentType().toLowerCase().startsWith("multipart/form-data") || req.getContentType() == null ) {
            resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
        }

        String id = req.getParameter("id");
        String itemName = req.getParameter("itemName");
        String category = req.getParameter("category");
        float price = Float.parseFloat(req.getParameter("price"));
        int qty = Integer.parseInt(req.getParameter("qty"));
        Part filePart = req.getPart("img");

        String fileName = filePart.getSubmittedFileName();

        ItemDTO dto = new ItemDTO(id, itemName, category, price, qty, fileName);

        try(var writer = resp.getWriter()) {

            if (dataProcess.update(dto, connection)) {
                String fileSavePath = "/home/omesh/vs code/pos-system/assets/img/" + fileName;
                filePart.write(fileSavePath);

                log.info("Item Successfully Updated!");
                writer.write("Item Updated!");
                resp.setStatus(HttpServletResponse.SC_OK);
            } else  {
                log.error("Item Not Updated!");
                writer.write("Item Not Updated!");
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try(var writer = resp.getWriter()) {
            if (dataProcess.delete(req.getParameter("id"), connection)) {

                log.info("Item Successfully Deleted!");
                writer.write("Item Deleted!");
                resp.setStatus(HttpServletResponse.SC_OK);
            } else {
                log.error("Item Not Deleted!");
                writer.write("Item Not Deleted!");
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }
    }
}
