package ch.bullfin.multilanguagechat.model;

/**
 * Created by root on 11/10/14.
 */
public class Message {
    private long id;
    private long chat_id;
    private String text;
    private String sender_language;
    private long created_at;
    private User sender;

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getChat_id() {
        return chat_id;
    }

    public void setChat_id(long chat_id) {
        this.chat_id = chat_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSender_language() {
        return sender_language;
    }

    public void setSender_language(String sender_language) {
        this.sender_language = sender_language;
    }

    public long getCreated_at() {
        return created_at;
    }

    public void setCreated_at(long created_at) {
        this.created_at = created_at;
    }
}
