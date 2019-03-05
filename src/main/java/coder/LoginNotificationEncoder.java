package coder;

import notifications.Login;

import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class LoginNotificationEncoder implements Encoder.Text<Login> {
    @Override
    public String encode(Login login) {
        String loginResp = "{\"header\":{\"token\":\"" + login.getUser().getJwt() + "\",isPrivate: true}, \"body\":\"" + login.getMessage() + "\"}";
        return loginResp;
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
