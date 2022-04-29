// Wordt A.T.M. niet meeer gebruikt.
package Database;

import java.sql.SQLException;
import java.sql.Connection;
import org.apache.commons.dbcp2.BasicDataSource;

public class DBCPDataSource {
    
    private static BasicDataSource ds = new BasicDataSource();
    
    static{
        ds.setUrl("jdbc:mysql://localhost:3306/parkeerapplicatie?useSSL=false");
        ds.setUsername("parkeerapplicatie");
        ds.setPassword("Nijmegen1");
        ds.setMinIdle(5);
        ds.setMaxIdle(10);
        ds.setMaxOpenPreparedStatements(100);

    }
    
    public static Connection getConnection() throws SQLException{
        return ds.getConnection();
    }
    
    private DBCPDataSource(){
        
    }
    
}
