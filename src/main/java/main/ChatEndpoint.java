package main;

import coder.*;
import helper.Commands;
import helper.Header;
import helper.Message;
import notifications.Greeter;
import notifications.Information;
import notifications.Login;
import notifications.Register;
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
                GreeterNotificationEncoder.class,
                LoginNotificationEncoder.class,
                RegisterNotificationEncoder.class,
                MessageEncoder.class,
                InformationNotificationEncoder.class
        })
public class ChatEndpoint {
    private Set<Session> sessions = Collections.synchronizedSet(new HashSet<>());
    private Repository repo = new Repository();

    @OnOpen
    public void onOpen(Session session) {
        sessions.add(session);
    }

    @OnMessage
    public void distribute(Message message, Session session) {
        if (message.getHeader().getPrivate()) {
            if (message.getBody().startsWith(Commands.LOGIN.toString())) {
                session.getAsyncRemote().sendObject(
                        new Login(
                                repo.login(message.getBody().replace(Commands.LOGIN.toString(), ""), session)
                        )
                );
            } else if (message.getBody().startsWith(Commands.REGISTER.toString())) {
                session.getAsyncRemote().sendObject(
                        new Register(
                                repo.register(message.getBody().replace(Commands.REGISTER.toString(), ""), session)
                        )
                );
            } else if (repo.checkSign(message.getHeader())) {
                if (message.getBody().startsWith(Commands.CREATE.toString())) {
                    String groupName = message.getBody().replace(Commands.CREATE.toString(), "");
                    session.getAsyncRemote().sendObject(new Information(Commands.CREATE, repo.createGroup(groupName, repo.getSubject(message.getHeader()))));
                } else if (message.getBody().startsWith(Commands.INVITE.toString())) {
                    String newBody = message.getBody().replace(Commands.INVITE.toString(), "");
                    //TODO: implement invite users to group chat
                } else if (message.getBody().startsWith(Commands.KICK.toString())) {
                    String newBody = message.getBody().replace(Commands.KICK.toString(), "");
                    //TODO: implement kick users from group chats
                } else if (message.getBody().startsWith(Commands.DISBAND.toString())) {
                    String groupName = message.getBody().replace(Commands.DISBAND.toString(), "");
                    session.getAsyncRemote().sendObject(new Information(Commands.DISBAND, repo.disbandGroup(groupName, repo.getSubject(message.getHeader()))));
                }
            }

        } else {
            if (repo.checkSign(message.getHeader())) {
                //TODO: implement can send
            } else {
                session.getAsyncRemote().sendObject(
                        new Message(new Header(true), "Please log in to go on")
                );
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