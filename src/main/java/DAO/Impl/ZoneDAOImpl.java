package DAO.Impl;

import DAO.ZoneDAO;
import Entity.ZoneEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import util.HibernateUtil;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Gennadiy on 20.11.2017.
 */
public class ZoneDAOImpl implements ZoneDAO {
    public void addZone(ZoneEntity zone) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(zone);
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println("Zone was not added: " + ex);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void updateZone(ZoneEntity zone) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(zone);
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println("Failed to update zone: " + ex);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public ZoneEntity getZone(int value) {
        Session session = null;
        ZoneEntity result = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("from ZoneEntity where zId = :id");
            query.setParameter("id", value);
            result = (ZoneEntity) query.uniqueResult();
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println("Failed to get the zone: " + ex);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
            return result;
        }
    }

    public List<ZoneEntity> getListZone() {
        Session session = null;
        List<ZoneEntity> result = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            result = session.createQuery("from ZoneEntity order by zId").list();
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

    public void deleteZone(ZoneEntity user) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(user);
            session.flush();
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println("Failed to delete zone: " + ex);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

    }
}
