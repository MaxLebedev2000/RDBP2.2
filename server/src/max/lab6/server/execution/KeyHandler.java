package max.lab6.server.execution;

import java.nio.channels.SelectionKey;

public interface KeyHandler {
    void handle(SelectionKey var1) throws Exception;
}
