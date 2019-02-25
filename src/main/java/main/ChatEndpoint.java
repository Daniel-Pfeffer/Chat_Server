package main;

import coder.GreeterNotificationEncoder;
import coder.MessageDecoder;
import helper.Message;
import notifications.Greeter;
import repository.Repository;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;


/**
 * @author H. Lackinger
 */
@ServerEndpoint(
        value = "/chat",
        decoders = {
                MessageDecoder.class
        },
        encoders = {
                GreeterNotificationEncoder.class
        })
public class ChatEndpoint {
    private static Set<Session> sessions = Collections.synchronizedSet(new HashSet<Session>());
    private Repository repo = new Repository();

    @OnOpen
    public void onOpen(Session session) throws IOException {
        sessions.add(session);
    }

    @OnMessage
    public void distribute(Message message, Session session) {
        if (message.getHeader().getPrivate()) {
            if (message.getBody().startsWith("[login]")) {
                repo.login(message.getBody().replace("[login]", ""), session);
            } else if (message.getBody().startsWith("[register]")) {
                repo.register(message.getBody().replace("[login]", ""), session);
            }
        } else {
            if (repo.checkSign(message.getHeader())) {
                //TODO: implement can send
            }
        }
    }

    @OnError
    public void onError(Throwable t) {
        t.printStackTrace();
    }

    @OnClose
    public void onClose(Session session) {
        sessions.remove(session);
    }
}