package DAO;

import Entity.UserEntity;

import java.util.List;

/**
 * Created by Gennadiy on 20.11.2017.
 */
public interface UserDAO {
    int addUser(UserEntity user);
    int updateUser(UserEntity user);
    List<UserEntity> getListUser();
    UserEntity getUser(int value);
    int getUser(String login, String password);
    void deleteUser(UserEntity user);
}
