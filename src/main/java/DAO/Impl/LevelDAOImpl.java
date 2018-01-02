package DAO.Impl;

import DAO.LevelDAO;
import Entity.LevelEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import util.HibernateUtil;

import java.util.List;

/**
 * Created by Gennadiy on 03.12.2017.
 */
public class LevelDAOImpl implements LevelDAO {

    public void addLevel(LevelEntity level) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(level);
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println("Level was not added: " + ex);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void updateLevel(LevelEntity level) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(level);
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println("Failed to update level: " + ex);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public List<LevelEntity> getListLevel() {
        Session session = null;
        List<LevelEntity> result = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            result = session.createQuery("from LevelEntity order by lId").list();
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println("Could not get a list of zones: " + ex);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
            return result;
        }
    }

    public LevelEntity getLevel(int value) {
        Session session = null;
        LevelEntity result = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("from LevelEntity where lId = :id");
            query.setParameter("id", value);
            result = (LevelEntity) query.uniqueResult();
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println("Failed to get the level: " + ex);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
            return result;
        }
    }

    public void deleteLevel(LevelEntity level) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(level);
            session.flush();
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println("Failed to delete level: " + ex);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
