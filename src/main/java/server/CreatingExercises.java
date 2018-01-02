package server;

import DAO.ExerciseDAO;
import DAO.Impl.ExerciseDAOImpl;
import DAO.Impl.LevelDAOImpl;
import DAO.LevelDAO;
import Entity.ExerciseEntity;
import Entity.LevelEntity;
import Entity.ZoneEntity;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Gennadiy on 03.12.2017.
 */
public class CreatingExercises {

    public static String generationExercise (LevelEntity level)
    {
        ZoneEntity zone = level.getZId();
        String str = zone.getZValue();
        List listChar = new ArrayList();
        Collections.addAll(listChar, str.split(","));
        listChar.add(" ");
        listChar.add(" ");
        listChar.add(" ");
        listChar.add(" ");
        listChar.add(" ");
        int count = rnd(level.getLMinlength(), level.getLMaxlength());
        String result = "";
        int number;
        for (int i = 0; i < count; i++)
        {
            number = rnd(0, listChar.size() - 1);
            result += listChar.get(number);
        }
        result = result.replaceAll("\\s+", " ");
        result = result.trim();
        return result;
    }

    public static String loadExercise (String file)
    {
        String result = "";
        try
        {
            List parsFile = new ArrayList();
            Collections.addAll(parsFile, file.split("\\."));
            if (parsFile.get(1).equals("txt")) {
                InputStreamReader reader = new InputStreamReader( new FileInputStream(file) , "UTF8");
                int c;
                String element = "";
                while((c=reader.read())!=-1){
                    element += (char)c;
                    result += element;
                    element = "";
                }
                if (result == null) {
                    return "-2";
                }
            }
            else {
                return "-1";
            }
        }
        catch(IOException ex){
            return "-3";
        }

        return result;
    }

    public static int createExercise(int levelId, String value)
    {
        int result;
        LevelDAO levelDAO = new LevelDAOImpl();
        LevelEntity level = levelDAO.getLevel(levelId);

        ZoneEntity zone = level.getZId();
        String str = zone.getZValue();
        List listChar = new ArrayList();
        Collections.addAll(listChar, str.split(","));
        listChar.add(" ");

        String element = "";
        for (int i = 0; i < value.length(); i++) {
            element += value.charAt(i);
            if (listChar.indexOf(element) == -1) {
                return -4;
            }
            element = "";
        }

        if ((value.length() < level.getLMinlength())) {
            return -2;
        }
        else if ((value.length() > level.getLMaxlength())) {
            return -3;
        }

        ExerciseDAO exerciseDAO = new ExerciseDAOImpl();
        ExerciseEntity exercise = new ExerciseEntity();
        exercise.setLId(level);
        exercise.setEText(value);
        result = exerciseDAO.addExercise(exercise);
        return result;

    }

    private static int rnd(int min, int max)
    {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }
}
