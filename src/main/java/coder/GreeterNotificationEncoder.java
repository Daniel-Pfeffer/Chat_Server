package coder;

import notifications.Greeter;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class GreeterNotificationEncoder implements Encoder.Text<Greeter> {
    @Override
    public String encode(Greeter greeter) throws EncodeException {
        return greeter.getMessage();
    }

    @Override
    public void init(EndpointConfig endpointConfig) {
        //does nothing
    }

    @Override
    public void destroy() {
        //does nothing
    }
}
