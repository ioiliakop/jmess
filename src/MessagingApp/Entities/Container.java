package MessagingApp.Entities;

public class Container {

    private long   id;
    private String containerName;

    public Container() {
    }

    public Container(String containerName) {
        this.containerName = containerName;
    }

    public Container(long id, String containerName) {
        this.id = id;
        this.containerName = containerName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContainerName() {
        return containerName;
    }

    public void setContainerName(String roleName) {
        this.containerName = roleName;
    }

    @Override
    public String toString() {
        return "\nID: " + id + " - " + containerName;
    }
}
