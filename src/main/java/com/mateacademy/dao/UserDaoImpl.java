package com.mateacademy.dao;

import com.mateacademy.model.User;
import com.mateacademy.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private static UserDaoImpl userDao = null;
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public void saveUser(User user) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

    @Override
    public void updateUser(User user) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(user);
        transaction.commit();
        session.close();
    }

    @Override
    public void deleteUser(Long id) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        User user = session.get(User.class, id);
        session.delete(user);
        transaction.commit();
        session.close();
    }

    @Override
    public User findUserById(Long id) {
        Session session = this.sessionFactory.openSession();
        User user = session.get(User.class, id);
        session.close();
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        Session session = this.sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        criteria.from(User.class);
        List<User> users = session.createQuery(criteria).getResultList();
        session.close();
        return users;
    }

    public static UserDao getInstance() {
        if (userDao == null) {
            synchronized (UserDaoImpl.class) {
                if (userDao == null) {
                    userDao = new UserDaoImpl();
                }
            }
        }
        return userDao;
    }
}