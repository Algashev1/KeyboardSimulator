package DAO.Impl;

import DAO.StatisticsDAO;
import Entity.StatisticsEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import util.HibernateUtil;

import java.util.List;

/**
 * Created by Gennadiy on 03.12.2017.
 */
public class StatisticsDAOImpl implements StatisticsDAO {

    public void addStatistics(StatisticsEntity statistics) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(statistics);
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println("Statistics was not added: " + ex);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void updateStatistics(StatisticsEntity statistics) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(statistics);
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println("Failed to update statistics: " + ex);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public List<StatisticsEntity> getListStatistics() {
        Session session = null;
        List<StatisticsEntity> result = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            result = session.createQuery("from StatisticsEntity order by sId").list();
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println("Could not get a list of statistics: " + ex);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
            return result;
        }
    }

    public StatisticsEntity getStatistics(int value) {
        Session session = null;
        StatisticsEntity result = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("from StatisticsEntity where sId = :id");
            query.setParameter("id", value);
            result = (StatisticsEntity) query.uniqueResult();
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println("Failed to get the statistics: " + ex);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
            return result;
        }
    }

    public List<StatisticsEntity> getStatisticsByUser(int userId) {
        Session session = null;
        List<StatisticsEntity> result = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("from StatisticsEntity where user.id = :id");
            query.setParameter("id", userId);
            result = (List<StatisticsEntity>)query.list();
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println("Failed to get the statistics: " + ex);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
            return result;
        }
    }

    public void deleteStatistics(StatisticsEntity statistics) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(statistics);
            session.flush();
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println("Failed to delete statistics: " + ex);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
