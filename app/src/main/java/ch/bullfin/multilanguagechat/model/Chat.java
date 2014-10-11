package ch.bullfin.multilanguagechat.model;

import java.io.Serializable;

/**
 * Created by root on 11/10/14.
 */
public class Chat implements Serializable {
    private long id;
    private User sender;
    private User receiver;
    private Message[] message_texts;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public Message[] getMessage_texts() {
        return message_texts;
    }

    public void setMessage_texts(Message[] message_texts) {
        this.message_texts = message_texts;
    }
}
