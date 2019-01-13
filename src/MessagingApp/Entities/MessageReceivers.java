package MessagingApp.Entities;

public class MessageReceivers {

    private long messageId;
    private long receiverId;

    public MessageReceivers() {
    }

    public MessageReceivers(long messageId, long receiverId) {
        this.messageId = messageId;
        this.receiverId = receiverId;
    }

    public long getMessageId() {
        return messageId;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }

    public long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(long receiverId) {
        this.receiverId = receiverId;
    }
}