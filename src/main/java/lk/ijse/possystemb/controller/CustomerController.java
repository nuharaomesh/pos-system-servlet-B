package lk.ijse.possystemb.controller;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.possystemb.bo.BOFactory;
import lk.ijse.possystemb.bo.custom.CustomerBO;
import lk.ijse.possystemb.dto.CustomerDTO;
import lk.ijse.possystemb.dao.custom.CustomerDAO;
import lk.ijse.possystemb.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.possystemb.util.UtilProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = "/customer")
public class CustomerController extends HttpServlet {

    private UtilProcess utilProcess;
    private CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);
    static Logger log = LoggerFactory.getLogger(CustomerController.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (!req.getContentType().toLowerCase().startsWith("application/json") || req.getContentType() == null) {
            resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
        }

        Jsonb jsonb = JsonbBuilder.create();
        CustomerDTO dto = jsonb.fromJson(req.getReader(), CustomerDTO.class);

        dto.setId(utilProcess.generateID());

        try (var writer = resp.getWriter()){

            if (customerBO.saveCustomer(dto)) {
                log.info("Customer Successfully Saved!");
                writer.write("Customer Saved!");
                resp.setStatus(HttpServletResponse.SC_CREATED);
            } else {
                log.error("Customer Not Saved!");
                writer.write("Customer Not Saved!");
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } catch (SQLException | ClassNotFoundException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (!req.getContentType().toLowerCase().startsWith("application/json") || req.getContentType() == null) {
            resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
        }

        Jsonb jsonb = JsonbBuilder.create();
        CustomerDTO dto = jsonb.fromJson(req.getReader(), CustomerDTO.class);

        try(var writer = resp.getWriter()) {

            if (customerBO.updateCustomer(dto)) {

                log.info("Customer Successfully Updated!");
                writer.write("Customer Updated!");
                resp.setStatus(HttpServletResponse.SC_CREATED);
            } else {
                log.error("Customer Not Updated!");
                writer.write("Customer Not Updated!");
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } catch (SQLException | ClassNotFoundException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try(var writer = resp.getWriter()) {
            if (customerBO.deleteCustomer(req.getParameter("id"))) {

                log.info("Customer Successfully Deleted!");
                writer.write("Customer Deleted!");
                resp.setStatus(HttpServletResponse.SC_OK);
            } else {
                log.error("Customer Not Deleted!");
                writer.write("Customer Not Deleted!");
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } catch (SQLException | ClassNotFoundException e) {
            log.error(e.getMessage());
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try(var writer = resp.getWriter()) {

            List<CustomerDTO> dtoList = customerBO.getAll();

            Jsonb jsonb = JsonbBuilder.create();
            String customers = jsonb.toJson(dtoList);

            writer.write(customers);
            log.info("Rendering All Customers");
        } catch (SQLException | ClassNotFoundException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
