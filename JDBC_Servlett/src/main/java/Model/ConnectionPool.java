package Model;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {
    private static final String URL = "jdbc:mysql://localhost:3306/clothes";
    private static final String USER = "root";
    private static final String PASSWORD = "huydat201";

    private static BasicDataSource dataSource = new BasicDataSource();

    static {
      
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver"); 
        dataSource.setUrl(URL);
        dataSource.setUsername(USER);
        dataSource.setPassword(PASSWORD);

        dataSource.setInitialSize(10); 
        dataSource.setMaxTotal(50);
        dataSource.setMinIdle(10);    
        dataSource.setMaxIdle(20);     
        dataSource.setMaxWaitMillis(10000); 
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static void close() throws SQLException {
        dataSource.close();
    }
}
