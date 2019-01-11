package MessagingApp.Entities;

import java.util.Date;
import java.util.Objects;

public class Message {

    private long   id;
    private String messageSubject;
    private String messageBody;
    private Date   messageDateTime;
    private long   senderId;
    private long   receiverId;

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

    public Date getMessageDateTime() {
        return messageDateTime;
    }

    public void setMessageDateTime(Date messageDateTime) {
        this.messageDateTime = messageDateTime;
    }

    public long getSenderId() {
        return senderId;
    }

    public void setSenderId(long senderId) {
        this.senderId = senderId;
    }

    public long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(long receiverId) {
        this.receiverId = receiverId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return id == message.id &&
                senderId == message.senderId &&
                receiverId == message.receiverId &&
                Objects.equals(messageSubject, message.messageSubject) &&
                Objects.equals(messageBody, message.messageBody) &&
                Objects.equals(messageDateTime, message.messageDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, messageSubject, messageBody, messageDateTime, senderId, receiverId);
    }

    @Override
    public String toString() {
        return "\nMsgID: " + id + "\tsenderId: " + senderId + "\treceiverId: " + receiverId +
                "\tDateTime: " + messageDateTime + "\n\tSubject: " + messageSubject +
                "\n\tMessage: " + messageBody;
    }
}
