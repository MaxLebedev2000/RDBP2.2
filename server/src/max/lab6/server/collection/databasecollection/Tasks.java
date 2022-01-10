package max.lab6.server.collection.databasecollection;



import max.lab6.server.Task;
import sql.JDBCWorker;
import sql.tables.TasksTable;

import java.util.Set;

public class Tasks implements CollectionManager {

    private TasksTable table;
    private Set<Task> collection;

    public Tasks(){
        this.table = JDBCWorker.instance().getTasksTable();
        this.read();
    }

    @Override
    public boolean read() {
        this.collection = table.set();
        if (collection == null) return false;
        return true;
    }

    @Override
    public boolean write() {
        this.table.addAll(collection);
        return true;
    }

    @Override
    public void close() {

    }

    @Override
    public Set<Task> getCollection() {
        return collection;
    }
}
