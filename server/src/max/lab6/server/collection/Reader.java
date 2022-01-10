package max.lab6.server.collection;

import max.lab6.server.Task;

import java.util.Set;

public interface Reader {
    boolean read();
    Set<Task> getCollection();

}
