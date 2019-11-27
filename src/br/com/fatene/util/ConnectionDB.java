package br.com.fatene.util;

import java.sql.*;

/**
 * @author Kaio Henrique on 11/25/2019
 */
public class ConnectionDB {

    private static final String USER = "root";
    private static final String PASS = "root";
    private static final String DB = "db_daily_tasks";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String STR_CONN = "jdbc:mysql://localhost:3306/";

    public static Connection getConnection() throws SQLException, ClassNotFoundException {

        Connection conn;
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(STR_CONN + DB, USER, PASS);
            return conn;
        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException("Driver SQL não encontrado" + e.getMessage());
        } catch (SQLException e) {
            throw new SQLException("Erro ao tentar se conectar a base de dados " + e.getMessage());
        }

    }

    private static void closeConnection(
            Connection conn
    ) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void closeConnection(
            Connection conn,
            PreparedStatement stmt
    ) {
        try {
            if (conn != null) {
                closeConnection(conn);
            }
            if (stmt != null) {
                stmt.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void closeConnection(
            Connection conn,
            PreparedStatement stmt,
            ResultSet result
    ) {
        try {
            if (conn != null && stmt != null) {
                closeConnection(conn, stmt);
            }
            if (result != null) {
                result.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
