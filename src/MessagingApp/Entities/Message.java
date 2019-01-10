package MessagingApp.Entities;

import java.util.Date;

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
    public String toString() {
        return "\nMsgID: " + id + "\tsenderId: " + senderId +
                "\tDateTime: " + messageDateTime + "\tSubject: " + messageSubject +
                "\n\tMessage: " + messageBody;
    }
}
