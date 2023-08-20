package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final SessionFactory sessionFactory = Util.getSessionFactory();
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        String query1 = "CREATE TABLE IF NOT EXISTS users(id int auto_increment primary key, name varchar(100), lastname varchar(100), age int)";
        session.createSQLQuery(query1).executeUpdate();
        session.getTransaction().commit();

    }

    @Override
    public void dropUsersTable() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        String query1 = "DROP TABLE IF EXISTS users";
        session.createSQLQuery(query1).executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        User user = new User(name, lastName, age);
        session.save(user);
        session.getTransaction().commit();
    }

    @Override
    public void removeUserById(long id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        User user = session.get(User.class, id);
        session.delete(user);
        session.getTransaction().commit();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<User> users = session.createQuery("FROM User").getResultList();
        session.getTransaction().commit();
        return users;

    }

    @Override
    public void cleanUsersTable() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.createQuery("DELETE FROM User").executeUpdate();
        session.getTransaction().commit();
    }
}
