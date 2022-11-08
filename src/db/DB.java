package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DB {

    private static Connection conn = null;

    public static Connection getConnection() {
        if(conn == null) {
            try {
                //pegando propriedades do banco de dados
                Properties props = loadProperties();
                //url do banco de dados definida no arquivo db.properties
                String url = props.getProperty("dburl");
                //fazendo conexão com banco de dados
                conn = DriverManager.getConnection(url, props);
            }
            catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
        return conn;
    }

    public static void closeConnection() {
        if(conn != null) {
            try {
                conn.close();
            }
            catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

    public static void closeStatement(Statement st) {
        if(st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

    public static void closeResultSet(ResultSet rs) {
        if(rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

    private static Properties loadProperties() {
        try(FileInputStream fs = new FileInputStream("db.properties")) {
            Properties props = new Properties();
            //faz a leitura do properties apontado pelo inputStream fs e guarda os dados dentro do objeto props
            props.load(fs);
            return props;
        }
        catch (IOException e) {
            throw new DbException(e.getMessage());
        }
    }
}
