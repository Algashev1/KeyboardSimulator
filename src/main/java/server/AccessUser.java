package server;

import DAO.Impl.LevelDAOImpl;
import DAO.Impl.UserDAOImpl;
import DAO.UserDAO;
import Entity.LevelEntity;
import Entity.UserEntity;

/**
 * Created by Gennadiy on 03.12.2017.
 */
public class AccessUser {

    public static int registrationUser (String login, String password1, String password2) {
        int result = -1;
        if (password1.equals(password2)) {
            UserDAO userDAO = new UserDAOImpl();
            UserEntity user = new UserEntity();
            user.setULogin(login);
            user.setUPassword(password1);
            LevelEntity level = new LevelDAOImpl().getLevel(1);
            user.setLId(level);
            user.setURole(2);
            result = userDAO.addUser(user);
        }
        return result;
    }

    public static int authorizationUser (String login, String password) {
        UserDAO userDAO = new UserDAOImpl();
        return userDAO.getUser(login, password);
    }


}
