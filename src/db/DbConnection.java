package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
        /*1st Rule*/
        private static db.DbConnection dbConnection=null;
        private Connection connection;

        /*2nd Rule*/
        private DbConnection() throws ClassNotFoundException, SQLException, SQLException {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/SunBird",
                    "root",
                    "1234");
        }

        /*3rd Rule*/
        public static db.DbConnection getInstance() throws ClassNotFoundException, SQLException {
            if (dbConnection==null){
                dbConnection= new db.DbConnection();
            }
            return dbConnection;

            /* return (dbConnection==null)?(dbConnection= new DbConnection()):(dbConnection);*/
        }

        public Connection getConnection(){
            return connection;
        }
    }
