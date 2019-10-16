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
            return hidePassword((User) query.getSingleResult());
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
                return hidePassword(user);
            }
            em.getTransaction().rollback();
            return null;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public User delete(User source) {
        EntityManagerFactory factory = HibernateUtil.getEntityManagerFactory();
        EntityManager em = factory.createEntityManager();
        User persistent = null;
        try {
            persistent = em.find(User.class, source.getId());
            if (persistent != null) {
                em.getTransaction().begin();
                try {
                    persistent.delete(em);
                    em.remove(persistent);
                    em.getTransaction().commit();
                   return hidePassword(persistent);
                } catch (Exception e) {
                    em.getTransaction().rollback();
                    throw e;
                }
            }
            return null;
        }
        finally {
            em.close();
        }
    }

    public User delete(long id) {
        return delete(new User(id));
    }

    public boolean update(User source) {
        EntityManagerFactory factory = HibernateUtil.getEntityManagerFactory();
        EntityManager em = factory.createEntityManager();
        try {
            User persistent = em.find(User.class, source.getId());
            if (persistent != null) {
                em.getTransaction().begin();
                try {
                    persistent.update(em, source);
                    em.getTransaction().commit();
                    return true;
                } catch (Exception e) {
                    em.getTransaction().rollback();
                    throw e;
                }
            }
        }
        finally {
            em.close();
        }
        return false;
    }

    public List<User> getAll() {
        EntityManagerFactory factory = HibernateUtil.getEntityManagerFactory();
        EntityManager em = factory.createEntityManager();
        try {
            return  hidePassword(User.createGetAllQuery(em).getResultList());
        } finally {
            em.close();
        }
    }

    public User getUserByName(String username) {
        EntityManagerFactory factory = HibernateUtil.getEntityManagerFactory();
        EntityManager em = factory.createEntityManager();
        try {
            Query query = User.createFindUserByNameQuery(em, username);
            return hidePassword((User) query.getSingleResult());
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
            return hidePassword(em.find(User.class, id));
        } finally {
            em.close();
        }
    }

    private User hidePassword(User u) {
        if (u != null)
            u.setPassword(" ");
        return u;
    }

    private List<User> hidePassword(List<User> users) {
        if (users != null)
            for (User u : users)
                hidePassword(u);
        return users;
    }


}
