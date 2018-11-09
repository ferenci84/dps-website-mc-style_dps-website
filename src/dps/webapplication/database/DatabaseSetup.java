package dps.webapplication.database;

import dps.commons.startup.Startup;
import dps.logging.HasLogger;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.sql.DataSource;
import javax.transaction.*;
import java.sql.*;

@Startup
@ApplicationScoped
public class DatabaseSetup implements HasLogger {

    @Resource(lookup = "java:comp/env/jdbc/DefaultDS")
    DataSource dataSource;

    @Resource
    UserTransaction tx;

    @PostConstruct
    void init() {

        try {

            tx.begin();

            try (Connection connection = dataSource.getConnection()) {

                DatabaseMetaData metaData = connection.getMetaData();
                ResultSet tables = metaData.getTables(connection.getCatalog(), connection.getSchema(), null, null);

                System.out.println("getting tables");
                while (tables.next()) {
                    System.out.println(tables.getString("TABLE_NAME"));
                }

                createTableIfNotExists(connection, "applicationuser",
                        "CREATE TABLE APPLICATIONUSER\n" +
                                "(\n" +
                                "  ID           BIGSERIAL PRIMARY KEY,\n" +
                                "  CUSTOMFIELD  VARCHAR(255),\n" +
                                "  PASSWORDHASH VARCHAR(255),\n" +
                                "  USERNAME     VARCHAR(255) NOT NULL\n" +
                                ")");

            }

        } catch (SQLException | NotSupportedException | SystemException e) {
            e.printStackTrace();
            try {
                tx.setRollbackOnly();
            } catch (SystemException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                tx.commit();
            } catch (HeuristicMixedException | SystemException | RollbackException | HeuristicRollbackException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }

    }

    boolean createTableIfNotExists(Connection connection, String tableName, String... sql) throws SQLException
    {
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet tables = metaData.getTables(null, null, null, null);
        boolean exists = false;
        while (tables.next()) {
            if (tables.getString("TABLE_NAME").equalsIgnoreCase(tableName)) {
                exists = true;
                break;
            }
        }
        if (exists) {
            logInfo(tableName+" EXISTS");
            return false;
        } else {
            logInfo(tableName+" NOT EXISTS");
            for (String currentSql: sql) {
                Statement statement = connection.createStatement();
                statement.executeUpdate(currentSql);
            }
            return true;
        }
    }

    boolean createColumnIfNotExists(Connection connection, String tableName, String colName, String... sql) throws SQLException
    {
        return createOrDropColumnIf(connection,tableName,true,colName,sql);
    }

    boolean dropColumnIfExists(Connection connection, String tableName, String colName, String... sql) throws SQLException
    {
        return createOrDropColumnIf(connection,tableName,false,colName,sql);
    }

    boolean createOrDropColumnIf(Connection connection, String tableName, boolean shouldExist, String colName, String... sql) throws SQLException
    {
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet articleTableResultSetLower = metaData.getTables(null, null, tableName.toLowerCase(), null);
        ResultSet articleTableResultSetUpper = metaData.getTables(null, null, tableName.toUpperCase(), null);

        boolean tableFound = false;
        if (articleTableResultSetLower.next()) {
            tableName = tableName.toLowerCase();
            tableFound = true;
        } else if (articleTableResultSetUpper.next()) {
            tableName = tableName.toUpperCase();
            tableFound = true;
        }

        if (tableFound) {
            logInfo(tableName+" EXISTS");

            ResultSet columnResultSetLower = metaData.getColumns(null, null, tableName, colName.toLowerCase());
            ResultSet columnResultSetUpper = metaData.getColumns(null, null, tableName, colName.toUpperCase());
            if ((columnResultSetLower.next() || columnResultSetUpper.next()) == shouldExist) {
                logInfo(tableName+"."+colName+" EXISTS");
                return false;
            } else {
                logInfo(tableName+"."+colName+" NOT EXISTS");
                for (String currentSql: sql) {
                    Statement statement = connection.createStatement();
                    statement.executeUpdate(currentSql);
                }
                return true;
            }

        } else {
            throw new SQLException(tableName+" NOT EXISTS");
        }

    }

}
