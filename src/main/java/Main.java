import DAO.Impl.ExerciseDAOImpl;
import DAO.Impl.StatisticsDAOImpl;
import DAO.Impl.UserDAOImpl;
import DAO.StatisticsDAO;
import DAO.UserDAO;
import Entity.ExerciseEntity;
import Entity.StatisticsEntity;
import Entity.UserEntity;
import server.CreatingExercises;

import java.util.Date;
import java.util.List;

/**
 * Created by Gennadiy on 19.11.2017.
 */
public class Main {

    public static void main(final String[] args) throws Exception {

       StatisticsDAO statisticsDAO = new StatisticsDAOImpl();
       StatisticsEntity statisticsEntity = statisticsDAO.getStatistics(2);
       //List<StatisticsEntity> list = statisticsDAO.getStatistics();
       int a = 0;
    }
}