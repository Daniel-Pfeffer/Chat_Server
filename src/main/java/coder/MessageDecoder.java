package coder;

import helper.Header;
import helper.Message;
import org.json.JSONObject;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public class MessageDecoder implements Decoder.Text<Message> {

    @Override
    public Message decode(String s) throws DecodeException {
        JSONObject head = new JSONObject(s).getJSONObject("header");
        String body = new JSONObject(s).optString("body");
        Header header;
        if (head.has("groupID")) {
            header = new Header(head.getBoolean("isPrivate"),
                    head.getString("token"),
                    head.getInt("groupID"));
        } else {
            header = new Header(head.getBoolean("isPrivate"), head.getString("token"));
        }

        return new Message(header, body);
    }

    @Override
    public boolean willDecode(String s) {
        return true;
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
