package coder;

import notifications.Information;

import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class InformationNotificationEncoder implements Encoder.Text<Information> {
    @Override
    public String encode(Information information) {
        return information.getMessage();
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
