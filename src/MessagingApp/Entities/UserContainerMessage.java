package MessagingApp.Entities;

public class UserContainerMessage {

    private long userId;
    private long containerId;
    private long messageId;

    public UserContainerMessage() {
    }

    public UserContainerMessage(long userId, long containerId, long messageId) {
        this.userId = userId;
        this.containerId = containerId;
        this.messageId = messageId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getContainerId() {
        return containerId;
    }

    public void setContainerId(long containerId) {
        this.containerId = containerId;
    }

    public long getMessageId() {
        return messageId;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }
}
