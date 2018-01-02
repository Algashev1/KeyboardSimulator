package DAO.Impl;

import DAO.UserDAO;
import Entity.UserEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import util.HibernateUtil;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Gennadiy on 20.11.2017.
 */
public class UserDAOImpl implements UserDAO {

    public int addUser(UserEntity user) {
        Session session = null;
        int result = -1;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            result = 0;
        } catch (Exception ex) {
            System.out.println("User was not added: " + ex);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
            return result;
        }
    }

    public int updateUser(UserEntity user) {
        Session session = null;
        int result = -1;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
            result = 1;
        } catch (Exception ex) {
            System.out.println("Failed to update user: " + ex);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
            return result;
        }
    }

    public List<UserEntity> getListUser() {
        Session session = null;
        List<UserEntity> result = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            result = session.createQuery("from UserEntity order by uId").list();
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println("Could not get a list of users: " + ex);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
            return result;
        }
    }

    public UserEntity getUser(int value) {
        Session session = null;
        UserEntity result = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("from UserEntity where uId = :id");
            query.setParameter("id", value);
            result = (UserEntity) query.uniqueResult();
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println("Failed to get the user: " + ex);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
            return result;
        }
    }

    public int getUser (String login, String password) {
        Session session = null;
        UserEntity user;
        int result = -1;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("from UserEntity where uLogin = :log and uPassword = :pass");
            query.setParameter("log", login);
            query.setParameter("pass", password);
            user = (UserEntity) query.uniqueResult();
            session.getTransaction().commit();
            result = user.getUId();
        } catch (Exception ex) {
            System.out.println("Failed to get the user: " + ex);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
            return result;
        }
    }

    public void deleteUser(UserEntity user) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(user);
            session.flush();
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println("Failed to delete user: " + ex);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
