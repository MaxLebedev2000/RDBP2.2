package sql.tables;



import max.lab6.server.Task;
import org.json.JSONObject;

import java.sql.*;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class TasksTable extends JDBCTable<Task> {

    private static final String NAME = "tasks";

    public TasksTable(Connection connection) {
        super(connection);
    }

    @Override
    public boolean put(Task task) throws SQLException {

        if (this.exists(task)){
            return false;
        }

        try (PreparedStatement statement = connection().
                prepareStatement("INSERT INTO tasks (title, description, deadline, tags, owner) " +
                        "values (?, ?, ?, ?, ?)")) {

            statement.setString(1, task.getTitle());
            statement.setString(2, task.getDescription());
            statement.setString(3, task.getDeadline());
            statement.setString(4, task.getTags());
            statement.setString(5, task.getOwner());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean remove(Task task) {
        try (PreparedStatement statement = connection().
                prepareStatement("DELETE FROM tasks WHERE " + condition(task))) {
            statement.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }


    }

    @Override
    public Set<Task> set() {
        Set<Task> result = ConcurrentHashMap.newKeySet();
        try (PreparedStatement statement = connection().prepareStatement("SELECT * FROM " + NAME)) {
            ResultSet rs = statement.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            while(rs.next()) {
                int numColumns = rsmd.getColumnCount();
                JSONObject obj = new JSONObject();
                for (int i=1; i<=numColumns; i++) {
                    String column_name = rsmd.getColumnName(i);

                    obj.put(column_name, rs.getObject(column_name));
                }
                result.add(new Task(obj));
            }
            return result;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public boolean exists(Task task) {
        try (PreparedStatement statement = connection().
                prepareStatement("SELECT FROM tasks WHERE " + condition(task))) {
            ResultSet set = statement.executeQuery();
            return set.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

@Override


    public boolean addAll(Set<Task> set) {
        for (Task task: set){
            if (!this.exists(task)) {
                try {
                    this.put(task);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }





    private static String condition(Task task){
        return "title = \'" + task.getTitle() + "\'" +
                " AND description = \'"+task.getDescription()+ "\'"+
                " AND deadline = \'" +  task.getDeadline()+ "\'"+
                " AND tags = \'"+ task.getTags()+ "\'"+
                " AND owner = \'"+ task.getOwner()+ "\'" + ";";
    }
}
