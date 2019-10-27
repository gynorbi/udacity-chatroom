package edu.udacity.java.nano.chat;

/**
 * WebSocket message model
 */
public class Message {
    private String type;
    private String username;
    private String msg;

    public Message(){ };
    public Message(String type, String username, String msg){
        this.type = type;
        this.username = username;
        this.msg = msg;
    }

    public String getType(){return type;}
    public String getUsername(){return username;}
    public String getMsg(){return msg;}
}
