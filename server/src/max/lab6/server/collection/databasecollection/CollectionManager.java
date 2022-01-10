package max.lab6.server.collection.databasecollection;



import max.lab6.server.Task;

import java.util.Set;

public interface CollectionManager {
    boolean read();

    boolean write();

    void close();

    Set<Task> getCollection();
}
