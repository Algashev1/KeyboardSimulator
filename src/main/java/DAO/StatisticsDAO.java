package DAO;

import Entity.StatisticsEntity;

import java.util.List;

/**
 * Created by Gennadiy on 20.11.2017.
 */
public interface StatisticsDAO {
    void addStatistics(StatisticsEntity statistics);
    void updateStatistics(StatisticsEntity statistics);
    List<StatisticsEntity> getListStatistics();
    List<StatisticsEntity> getStatisticsByUser(int userId);
    StatisticsEntity getStatistics(int value);
    void deleteStatistics(StatisticsEntity statistics);
}
