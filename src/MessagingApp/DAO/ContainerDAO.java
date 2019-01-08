package MessagingApp.DAO;

import MessagingApp.Entities.Container;

import java.util.List;

public interface ContainerDAO {

    Container getContainer(long id);

    Container getContainer(String containerName);

    List<Container> getAllContainers();

    long insertContainer(String containerName);

    int updateContainer(String containerName, long id);

    int deleteContainer(long id);

}