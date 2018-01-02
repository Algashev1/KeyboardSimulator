package DAO;

import Entity.LevelEntity;

import java.util.List;

/**
 * Created by Gennadiy on 20.11.2017.
 */
public interface LevelDAO {
    void addLevel(LevelEntity level);
    void updateLevel(LevelEntity level);
    List<LevelEntity> getListLevel();
    LevelEntity getLevel(int value);
    void deleteLevel(LevelEntity level);
}
