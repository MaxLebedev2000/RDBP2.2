package max.lab6.server.execution.handlers;

import max.lab6.server.execution.KeyHandler;
import max.lab6.server.mail.MailWorker;
import max.lab6.server.mail.PasswordMaker;
import sql.JDBCWorker;
import sql.tables.UserTable;
import users.Connection;
import users.Type;
import users.User;

import java.nio.channels.SelectionKey;
public class UnknownKeyHandler extends TCPHandler implements KeyHandler {
    private MailWorker mail = new MailWorker();
    private UserTable users = JDBCWorker.instance().getUsers();

    public UnknownKeyHandler() {
    }

    public void handle(SelectionKey key) throws Exception {
        String come = super.receive(key);
        String[] array = come.split(";");
        if (array.length != 3) {
            super.send(key, "incorrect");
        } else {
            String respond;
            String login;
            String password;
            if (array[0].trim().equals("signup")) {
                login = array[1];
                password = array[2];
                if (this.users.exists(new User(login, password, ""))) {
                    respond = "userExists";
                } else {
                    String pass = PasswordMaker.getRandomString();
                     password = PasswordMaker.getHexDigest(pass);

                    try {
                        this.mail.send("Регистрация нового пользователя", "Пароль: " + pass + ";\nЛогин: " + login + ";", password);
                        this.users.put(new User(login, password, password));
                        respond = "regdone";
                        ((Connection)key.attachment()).setLogin(login);
                        ((Connection)key.attachment()).setType(Type.COMMANDS);
                    } catch (Throwable var10) {
                        respond = "mailerror";
                    }
                }

                super.send(key, respond);
            } else if (array[0].trim().equals("signin")) {
                login = array[1];
                password = array[2];
                if (this.users.exists(new User(login, "", ""))) {
                    if (this.users.passwordCorrect(login, PasswordMaker.getHexDigest(password))) {
                        ((Connection)key.attachment()).setType(Type.COMMANDS);
                        ((Connection)key.attachment()).setLogin(login);
                        respond = "logdone";
                    } else {
                        respond = "incorrectPassword";
                    }
                } else {
                    respond = "noSuchUser";
                }

                super.send(key, respond);
            } else {
                respond = "incorrect";
                super.send(key, respond);
            }

        }
    }
}
