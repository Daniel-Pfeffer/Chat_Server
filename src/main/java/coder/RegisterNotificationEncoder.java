package coder;

import notifications.Register;

import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class RegisterNotificationEncoder implements Encoder.Text<Register> {
    @Override
    public String encode(Register register) {
        return "{\"header\":{\"token\":\"" + register.getUser().getJwt() + "\",isPrivate: true}, \"body\":\"" + register.getMessage() + "\"}";
    }

    @Override
    public void init(EndpointConfig endpointConfig) {
        //NOOP
    }

    @Override
    public void destroy() {
        //NOOP
    }
}
