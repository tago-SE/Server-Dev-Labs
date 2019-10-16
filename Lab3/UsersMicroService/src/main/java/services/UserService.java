package services;

import models.User;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

@Service
public class UserService {

    public UserService() {

    }

    public User login(String username, String password) {
        EntityManagerFactory factory = HibernateUtil.getEntityManagerFactory();
        EntityManager em = factory.createEntityManager();
        User user = null;
        try {
            Query query = User.createValidateCredentialsQuery(em, username, password);
            return (User) query.getSingleResult();
        } catch (Exception e) {
            if (e instanceof NoResultException)
                return null;
            else
                throw e;
        } finally {
            em.close();
        }
    }

    public User register(String username, String password) {
        EntityManagerFactory factory = HibernateUtil.getEntityManagerFactory();
        EntityManager em = factory.createEntityManager();
        User user = null;
        try {
            em.getTransaction().begin();
            if (getUserByName(username) == null) {
                user = new User(username, password);
                em.persist(user);
                em.getTransaction().commit();
                return user;
            }
            em.getTransaction().rollback();
            return null;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return null;
        } finally {
            em.close();
        }
    }

    /**
     * You can pass in a dummy user model as long as it has a valid id, it will be deleted.
     * @param source
     * @return the deleted user
     */
    public User delete(User source) {

        return null;
    }

    public boolean update(User source) {

        return true;
    }

    public List<User> getAll() {
        EntityManagerFactory factory = HibernateUtil.getEntityManagerFactory();
        EntityManager em = factory.createEntityManager();
        try {
            return  User.createGetAllQuery(em).getResultList();
        } finally {
            em.close();
        }
    }

    public User getUserByName(String username) {
        EntityManagerFactory factory = HibernateUtil.getEntityManagerFactory();
        EntityManager em = factory.createEntityManager();
        try {
            Query query = User.createFindUserByNameQuery(em, username);
            return (User) query.getSingleResult();
        } catch (Exception e) {
            if (e instanceof NoResultException)
                return null;
            else
                throw e;
        } finally {
            em.close();
        }
    }

    public User getUserById(long id) {
        EntityManagerFactory factory = HibernateUtil.getEntityManagerFactory();
        EntityManager em = factory.createEntityManager();
        try {
            return em.find(User.class, id);
        } finally {
            em.close();
        }
    }


}
