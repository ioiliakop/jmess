package MessagingApp.Entities;

import java.util.Objects;

public class User {

    private long id;
    private String username;
    private String password;
    private long roleId;

    public User() {
    }

    public User(String username, String password, long roleId) {
        this.username = username;
        this.password = password;
        this.roleId = roleId;
    }

    public User(long id, String username, String password, long roleId) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roleId = roleId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "\nid: " + id + "\tusername: " + username +
                "\tpassword: " + password + "\troleId: " + roleId;
    }


    /* If 2 users have the same id, we consider them equal in our application. No need to check the other fields */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
