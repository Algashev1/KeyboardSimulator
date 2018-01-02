package server;

import DAO.*;
import DAO.Impl.*;
import Entity.*;
import org.hibernate.stat.Statistics;

import java.util.*;

/**
 * Created by Gennadiy on 03.12.2017.
 */
public class WorkWithObjects {

    public static LevelEntity getLevel (int id) {
        LevelDAO levelDAO = new LevelDAOImpl();
        return levelDAO.getLevel(id);
    }

    public static void editLevel (int id, int min, int max, int error, int time, int zoneId) {
        LevelDAO levelDAO = new LevelDAOImpl();
        LevelEntity level = levelDAO.getLevel(id);
        level.setLMinlength(min);
        level.setLMaxlength(max);
        level.setLMaxerrors(error);
        level.setLTime(time);

        ZoneDAO zoneDAO = new ZoneDAOImpl();
        ZoneEntity zone = zoneDAO.getZone(zoneId);
        level.setZId(zone);

        levelDAO.updateLevel(level);
    }

    public static int addUser (String login, String password, int type, int levelId) {
        UserDAO userDAO = new UserDAOImpl();
        UserEntity user = new UserEntity();
        user.setULogin(login);
        user.setUPassword(password);
        LevelEntity level = new LevelDAOImpl().getLevel(levelId);
        user.setLId(level);
        user.setURole(type);
        int result = userDAO.addUser(user);
        return result;
    }

    public static UserEntity getUser (int id) {
        UserDAO userDAO = new UserDAOImpl();
        return userDAO.getUser(id);
    }

    public static int editUser (int id, String login, String password, int levelId, int roleId) {
        UserDAO userDAO = new UserDAOImpl();
        UserEntity user = userDAO.getUser(id);
        user.setULogin(login);
        user.setUPassword(password);
        user.setURole(roleId);

        LevelDAO levelDAO = new LevelDAOImpl();
        LevelEntity level = levelDAO.getLevel(levelId);
        user.setLId(level);

        return userDAO.updateUser(user);
    }

    public static List<UserEntity> getListUsers () {
        UserDAO userDAO = new UserDAOImpl();
        return userDAO.getListUser();
    }

    public static void deleteUser (int id) {
        UserDAO userDAO = new UserDAOImpl();
        UserEntity user = userDAO.getUser(id);
        StatisticsDAO statisticsDAO = new StatisticsDAOImpl();
        List<StatisticsEntity> list = statisticsDAO.getStatisticsByUser(user.getUId());
        for (StatisticsEntity element: list) {
            statisticsDAO.deleteStatistics(element);
        }
        userDAO.deleteUser(user);
    }

    public static List<ExerciseEntity> getListExercise () {
        ExerciseDAO exerciseDAO = new ExerciseDAOImpl();
        return exerciseDAO.getListExercise();
    }

    public static List<ExerciseEntity> getListExerciseCompleted (int userId) {

        StatisticsDAO statisticsDAO = new StatisticsDAOImpl();
        List<StatisticsEntity> listStatistics = (List<StatisticsEntity>) statisticsDAO.getStatisticsByUser(userId);
        List<ExerciseEntity> listExercise = new ArrayList<>();
        for (StatisticsEntity element: listStatistics) {
            listExercise.add(element.getEId());
        }
        return listExercise;
    }

    public static ExerciseEntity getExercise (int id) {
        ExerciseDAO exerciseDAO = new ExerciseDAOImpl();
        return exerciseDAO.getExercise(id);
    }

    public static void addStatistics (int exerciseId, int userId, int maxTime, int minTime, int fullTime, int error, int speed) {
        StatisticsDAO statisticsDAO = new StatisticsDAOImpl();
        StatisticsEntity statisticsEntity = new StatisticsEntity();
        statisticsEntity.setSData(new Date());
        statisticsEntity.setEId(new ExerciseDAOImpl().getExercise(exerciseId));
        statisticsEntity.setSErrors(error);
        statisticsEntity.setSMax(maxTime);
        statisticsEntity.setSMin(minTime);
        statisticsEntity.setSSpeed(speed);
        statisticsEntity.setSTimefull(fullTime);
        statisticsEntity.setUId(new UserDAOImpl().getUser(userId));
        statisticsDAO.addStatistics(statisticsEntity);
    }
}
