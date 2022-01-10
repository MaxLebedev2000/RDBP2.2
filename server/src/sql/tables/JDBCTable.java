package sql.tables;

import java.sql.Connection;

public abstract class JDBCTable<T> implements Table<T>{

    private Connection connection;

    protected JDBCTable(Connection connection){
        this.connection = connection;
    }

    protected Connection connection(){
        return connection;
    }

}
