package server;

import DAO.Impl.StatisticsDAOImpl;
import DAO.Impl.UserDAOImpl;
import DAO.StatisticsDAO;
import DAO.UserDAO;
import Entity.StatisticsEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Gennadiy on 25.12.2017.
 */
public class StatisticsFormation {
    public static List<Integer> pieChart (int userId, int dateId) {
        dateId++;
        List<Integer> result = new ArrayList<>();
        StatisticsDAO statisticsDAO = new StatisticsDAOImpl();
        List<StatisticsEntity> statisticsEntities =  statisticsDAO.getStatisticsByUser(userId);
        Date date = new Date();
        Date dateBefore = new Date(date.getTime() - dateId * 24 * 3600 * 1000l); //Subtract n days
        int max = 0;
        int min = 0;
        int average = 0;
        int count = 0;
        if (statisticsEntities.size() > 0) {
            for (StatisticsEntity element : statisticsEntities) {
                if (element.getSData().after(dateBefore)) {
                    max += element.getSMax();
                    min += element.getSMin();
                    average += element.getSSpeed();
                    count++;
                }
            }
            max = Math.round(max / count);
            min = Math.round(min / count);
            average = Math.round(average /count);
        }
        result.add(max);
        result.add(min);
        result.add(average);
        return result;
    }

    public static List<Integer> chart (int userId, int dateId) {
        dateId++;
        List<Integer> result = new ArrayList<>();
        StatisticsDAO statisticsDAO = new StatisticsDAOImpl();
        List<StatisticsEntity> statisticsEntities =  statisticsDAO.getStatisticsByUser(userId);
        Date date = new Date();
        Date dateBefore = new Date(date.getTime() - dateId * 24 * 3600 * 1000l); //Subtract n days

        if (statisticsEntities.size() > 0) {
            for (StatisticsEntity element : statisticsEntities) {
                if (element.getSData().after(dateBefore)) {
                    result.add(element.getSErrors());
                }
            }
        }
        return result;
    }

    public static List<StatisticsEntity> allStatisticsByUser (int userId, int dateId) {
        dateId++;
        List<StatisticsEntity> result = new ArrayList<>();
        StatisticsDAO statisticsDAO = new StatisticsDAOImpl();
        List<StatisticsEntity> statisticsEntities =  statisticsDAO.getStatisticsByUser(userId);
        Date date = new Date();
        Date dateBefore = new Date(date.getTime() - dateId * 24 * 3600 * 1000l);
        if (statisticsEntities.size() > 0) {
            for (StatisticsEntity element : statisticsEntities) {
                if (element.getSData().after(dateBefore)) {
                    result.add(element);
                }
            }
        }
        return result;
    }
}
