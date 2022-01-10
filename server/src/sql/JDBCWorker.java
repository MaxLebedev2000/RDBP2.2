package sql;

import sql.tables.TasksTable;
import sql.tables.UserTable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCWorker {

    private final String DB_URL = "jdbc:postgresql://localhost:5434/postgres";
    private final String USER = "postgres";
    private final String PASS = "123";
    private static JDBCWorker instance;

    public static JDBCWorker instance(){
        if (instance == null){
            instance = new JDBCWorker();
        }
        return instance;
    }

    private Connection connection;
    private TasksTable tasksTable;
    private UserTable users;
    private JDBCWorker() {
        try {
            Class.forName("org.postgresql.Driver");
            Properties prop = new Properties();
            prop.put("user", USER);
            prop.put("password", PASS);
            prop.put("stringtype", "unspecified");

            connection = DriverManager.getConnection(DB_URL, prop);
            this.tasksTable = new TasksTable(connection);
            this.users = new UserTable(connection);
            System.out.println("The database connection was successful");
        } catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public TasksTable getTasksTable(){
        return tasksTable;
    }

    public UserTable getUsers(){
        return users;
    }

}
