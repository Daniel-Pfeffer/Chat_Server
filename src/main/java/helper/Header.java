package helper;


public class Header {
    /*
     * If private means that these are authentication processes like login/register/group creation/etc.
     */
    private Boolean isPrivate;
    /*
     * JWT Token
     */
    private String token;
    /*
     * groupId from which group message was sent and which group the msg should receive
     */
    private Integer groupID;

    public Boolean getPrivate() {
        return isPrivate;
    }

    public void setPrivate(Boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getGroupID() {
        return groupID;
    }

    public void setGroupID(Integer groupID) {
        this.groupID = groupID;
    }

    public Header(Boolean isPrivate, String token, Integer groupID) {
        this.isPrivate = isPrivate;
        this.token = token;
        this.groupID = groupID;
    }

    public Header() {
    }

    @Override
    public String toString() {
        return "Header{" +
                "isPrivate=" + isPrivate +
                ", token='" + token + '\'' +
                ", groupID=" + groupID +
                '}';
    }
}
