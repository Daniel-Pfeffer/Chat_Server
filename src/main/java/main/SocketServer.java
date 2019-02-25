package main;

/**
 *
 * @author H. Lackinger
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
 
import org.glassfish.tyrus.server.Server;
 
public class SocketServer {
 
    public static void main(String[] args) {
        runServer();
    }
 
    private static void runServer() {
        org.glassfish.tyrus.server.Server server = new org.glassfish.tyrus.server.Server("localhost",
                                   8025, 
                                   "/websockets", 
                                   ChatEndpoint.class);
 
        try {
            server.start();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(System.in));
            System.out.print("Please press a key to stop the chat-server.\n");
            reader.readLine();
        } catch (Exception me) {
            //if u can
            throw new RuntimeException(me);
        } finally {
            server.stop();
        }
    }
}
