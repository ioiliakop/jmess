package MessagingApp.Entities;

import java.util.Date;

public class Message {

    private long   id;
    private String messageSubject;
    private String messageBody;
    private Date   messageDateTime;
    private long   authorId;

    public Message() {
    }

    public Message(String messageSubject, String messageBody, long authorId) {
        this.messageSubject = messageSubject;
        this.messageBody = messageBody;
        this.authorId = authorId;
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

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }


    @Override
    public String toString() {
        return "\nMsgID: " + id + "\tauthorId: " + authorId +
                "\tDateTime: " + messageDateTime + "\tSubject: " + messageSubject +
                "\n\tMessage: " + messageBody;
    }
}
