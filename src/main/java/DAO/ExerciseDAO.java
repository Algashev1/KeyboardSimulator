package DAO;

import Entity.ExerciseEntity;
import Entity.LevelEntity;

import java.util.List;

/**
 * Created by Gennadiy on 20.11.2017.
 */
public interface ExerciseDAO {
    int addExercise(ExerciseEntity exercise);
    void updateUser(ExerciseEntity exercise);
    List<ExerciseEntity> getListExercise();
    List<ExerciseEntity> getListLevelExercise(LevelEntity level);
    ExerciseEntity getExercise(int value);
    void deleteExercise(ExerciseEntity exercise);
}
