package helper;

public class Message {
    /* Header of the message
     * handles groupID/if message is private/authorization
     */
    Header header;
    /*
     * Body of the Message
     *  handles what should be displayed/processed
     */
    String body;

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
}
