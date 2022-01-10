package max.lab6.server.collection;

import max.lab6.server.Task;

import java.util.Set;

public interface Writer {
    boolean write(Set<Task> collection);
    void reset();
    void close();
}
