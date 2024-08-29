package lk.ijse.possystemb.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DbConnection {

    Connection connection;
    private static DbConnection dbConnection;
    static Logger logger = LoggerFactory.getLogger(DbConnection.class);

    private DbConnection() throws ClassNotFoundException, SQLException {

        try {
            var ctx = new InitialContext();
            DataSource pool = (DataSource) ctx.lookup("java:/comp/env/jdbc/posSystem");
            pool.getConnection();

            this.connection = pool.getConnection();
        } catch (Exception e) {
            logger.error("Failed: ",e.getMessage());
            e.printStackTrace();
        }
    }

    public static DbConnection getDbConnection() throws SQLException, ClassNotFoundException {
        return dbConnection == null ? dbConnection= new DbConnection() : dbConnection;
    }

    public Connection getConnection() {
        return connection;
    }
}
