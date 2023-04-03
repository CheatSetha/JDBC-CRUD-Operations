package JDBC;

import javax.sql.DataSource;
import org.postgresql.ds.PGSimpleDataSource;
public class JdbcImp {
    public DataSource dataSource (){
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setUser("postgres");
        dataSource.setPassword("setha");
        dataSource.setDatabaseName("Demo");
        return dataSource;

    }
}
