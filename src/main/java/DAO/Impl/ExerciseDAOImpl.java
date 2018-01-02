package DAO.Impl;

import DAO.ExerciseDAO;
import Entity.ExerciseEntity;
import Entity.LevelEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import util.HibernateUtil;

import java.util.List;

/**
 * Created by Gennadiy on 03.12.2017.
 */
public class ExerciseDAOImpl implements ExerciseDAO {

    public int addExercise(ExerciseEntity exercise) {
        Session session = null;
        int result = -1;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(exercise);
            session.getTransaction().commit();
            result = 0;
        } catch (Exception ex) {
            System.out.println("Exercise was not added: " + ex);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
            return result;
        }
    }

    public void updateUser(ExerciseEntity exercise) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(exercise);
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println("Failed to update exercise: " + ex);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public List<ExerciseEntity> getListExercise() {
        Session session = null;
        List<ExerciseEntity> result = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            result = session.createQuery("from ExerciseEntity order by level.id").list();
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println("Could not get a list of exercise: " + ex);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
            return result;
        }
    }

    public List<ExerciseEntity> getListLevelExercise(LevelEntity level) {
        Session session = null;
        List<ExerciseEntity> result = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("from ExerciseEntity where level =:lev");
            query.setParameter("lev", level);
            result = query.list();
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println("Could not get a list of exercise: " + ex);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
            return result;
        }
    }

    public ExerciseEntity getExercise(int value) {
        Session session = null;
        ExerciseEntity result = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("from ExerciseEntity where eId = :id");
            query.setParameter("id", value);
            result = (ExerciseEntity) query.uniqueResult();
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println("Failed to get the exercise: " + ex);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
            return result;
        }
    }

    public void deleteExercise(ExerciseEntity exercise) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(exercise);
            session.flush();
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println("Failed to delete exercise: " + ex);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
