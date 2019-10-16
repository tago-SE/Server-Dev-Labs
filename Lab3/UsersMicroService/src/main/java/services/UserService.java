package services;

import models.User;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    public UserService() {

    }

    public User login(String username, String password) {
        EntityManagerFactory factory = HibernateUtil.getEntityManagerFactory();
        EntityManager em = factory.createEntityManager();


        return null;
    }

    public User register(String username, String password) {
        EntityManagerFactory factory = HibernateUtil.getEntityManagerFactory();
        EntityManager em = factory.createEntityManager();
        User user = null;
        try {
            em.getTransaction().begin();
            user = new User(username, password);
            em.persist(user);
            em.getTransaction().commit();
            return user;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return null;
        }
    }

    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        User u = new User();
        u.setUsername("AAA");
        users.add(u);
        u = new User();
        u.setUsername("BBB");
        users.add(u);
        return users;
    }

    public User getUserByName(String usernam) {
        return null;
    }

    public User getUserById(long id) {
        User u = new User();
        u.setId(id);
        u.setUsername("AAA");
        u.setPassword("123123");
        return u;
    }



    private void hidePassword(User u) {
        u.setPassword(" ");
    }

    private void hidePasswords(List<User> users) {
        if (users != null)
            for (User u : users)
                hidePassword(u);
    }

}
