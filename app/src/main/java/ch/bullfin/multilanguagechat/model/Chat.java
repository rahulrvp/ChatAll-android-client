package ch.bullfin.multilanguagechat.model;

import java.io.Serializable;

/**
 * Created by root on 11/10/14.
 */
public class Chat implements Serializable {
    private long id;
    private Message[] message_texts;

    private Participant[] participants;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Message[] getMessage_texts() {
        return message_texts;
    }

    public void setMessage_texts(Message[] message_texts) {
        this.message_texts = message_texts;
    }

    public Participant[] getParticipants() {
        return participants;
    }

    public void setParticipants(Participant[] participants) {
        this.participants = participants;
    }
}
