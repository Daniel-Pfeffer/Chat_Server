package helper;

public class Message {
    /* Header of the message
     * handles groupID/if message is private/authorization
     */
    private Header header;
    /*
     * Body of the Message
     *  handles what should be displayed/processed
     */
    private String body;

    public Message(Header header, String body) {
        this.body = body;
        this.header = header;
    }

    public Message() {
    }

    @Override
    public String toString() {
        return "Message{" +
                "header=" + header +
                ", body='" + body + '\'' +
                '}';
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
