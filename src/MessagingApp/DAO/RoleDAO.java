package MessagingApp.DAO;

import MessagingApp.Entities.Role;

import java.util.List;

public interface RoleDAO {

    Role getRole(long id);

    Role getRole(String roleName);

    List<Role> getAllRoles();

    long insertRole(String roleName);

    int updateRole(String roleName, long id);

    int deleteRole(long id);
}
