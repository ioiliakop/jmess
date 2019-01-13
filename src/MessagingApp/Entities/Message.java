package MessagingApp.Entities;

import java.util.Date;
import java.util.Objects;

public class Message {

    private long   id;
    private String messageSubject;
    private String messageBody;
    private Date   messageDateCreated;
    private long   senderId;

    public Message() {
    }

    public Message(String messageSubject, String messageBody, long senderId) {
        this.messageSubject = messageSubject;
        this.messageBody = messageBody;
        this.senderId = senderId;
    }

    public Message(String messageSubject, String messageBody) {
        this.messageSubject = messageSubject;
        this.messageBody = messageBody;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMessageSubject() {
        return messageSubject;
    }

    public void setMessageSubject(String messageSubject) {
        this.messageSubject = messageSubject;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public Date getMessageDateCreated() {
        return messageDateCreated;
    }

    public void setMessageDateCreated(Date messageDateCreated) {
        this.messageDateCreated = messageDateCreated;
    }

    public long getSenderId() {
        return senderId;
    }

    public void setSenderId(long senderId) {
        this.senderId = senderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return id == message.id &&
                senderId == message.senderId &&
                Objects.equals(messageSubject, message.messageSubject) &&
                Objects.equals(messageBody, message.messageBody) &&
                Objects.equals(messageDateCreated, message.messageDateCreated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, messageSubject, messageBody, messageDateCreated, senderId);
    }

    @Override
    public String toString() {
        return "\nMsgID: " + id + "\tsenderId: " + senderId + "\tSubject: " + messageSubject +
                "\tDateTime: " + messageDateCreated + "\n\tMessage: " + messageBody;
    }
}
