package max.lab6.server.execution.handlers;



import max.lab6.server.Pair;
import max.lab6.server.collection.databasecollection.CollectionManager;
import max.lab6.server.commands.ComandFactory;
import max.lab6.server.commands.Comandable;
import max.lab6.server.execution.KeyHandler;
import users.Connection;

import java.nio.channels.SelectionKey;

public class CommandKeyHandler extends TCPHandler implements KeyHandler {

    private CollectionManager manager;

    public CommandKeyHandler(CollectionManager manager){
        this.manager = manager;
    }


    @Override
    public void handle(SelectionKey key) throws Exception {

        String command = super.receive(key);
        String clearCommand = command.trim();
        Pair<Comandable, String> pair = ComandFactory.createCommand(clearCommand);
        String respond;
        if (pair.getKey() != null) {
            respond = pair.getKey().run(pair.getValue(), manager, ((Connection) key.attachment()).id());
        } else {
            if (pair.getValue() == null) {
                respond = "Неверная команда!";
            } else {
                respond = pair.getValue();
            }
        }
        super.send(key, respond);

    }
}