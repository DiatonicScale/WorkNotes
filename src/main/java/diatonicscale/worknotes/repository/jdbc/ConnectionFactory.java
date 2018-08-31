/**
 * User: DiatonicScale
 * Date: 30.08.2018
 */

package diatonicscale.worknotes.repository.jdbc;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.Properties;

@Component
public class ConnectionFactory {
    private String databaseURL;
    private String databaseUser;
    private String databasePassword;

    ConnectionFactory () throws ClassNotFoundException, IOException {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("db/postgresql.properties")) {
            Class.forName("org.postgresql.Driver");
            Properties dbProps = new Properties();
            dbProps.load(is);
            databaseURL = dbProps.getProperty("database.url");
            databaseUser = dbProps.getProperty("database.username");
            databasePassword = dbProps.getProperty("database.password");
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(databaseURL, databaseUser, databasePassword);
    }
}
