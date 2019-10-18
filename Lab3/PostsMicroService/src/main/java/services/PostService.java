package services;

import models.Post;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    public PostService() {

    }

    public Post insert(Post toInsert) {
        EntityManagerFactory factory = HibernateUtil.getEntityManagerFactory();
        EntityManager em = factory.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(toInsert);
            em.getTransaction().commit();
            return toInsert;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public Post delete(Post toDelete) {
        EntityManagerFactory factory = HibernateUtil.getEntityManagerFactory();
        EntityManager em = factory.createEntityManager();
        try {
            toDelete = em.find(Post.class, toDelete.getId());
            if (toDelete != null) {
                em.getTransaction().begin();
                try {
                    toDelete.delete(em);
                    em.remove(toDelete);
                    em.getTransaction().commit();
                   return toDelete;
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

    public Post delete(long id) {
        return delete(new Post(id));
    }

    public Post update(Post source) {
        EntityManagerFactory factory = HibernateUtil.getEntityManagerFactory();
        EntityManager em = factory.createEntityManager();
        try {
            Post persistent = em.find(Post.class, source.getId());
            if (persistent != null) {
                em.getTransaction().begin();
                try {
                    persistent.update(em, source);
                    em.getTransaction().commit();
                    return persistent;
                } catch (Exception e) {
                    em.getTransaction().rollback();
                    throw e;
                }
            }
        }
        finally {
            em.close();
        }
        return null;
    }

    public List<Post> getUserPosts(String username) {
        EntityManagerFactory factory = HibernateUtil.getEntityManagerFactory();
        EntityManager em = factory.createEntityManager();
        try {
            return Post.createUserPostQuery(em, username).getResultList();
        } catch (Exception e) {
            if (e instanceof NoResultException)
                return new ArrayList<>();
            else
                throw e;
        } finally {
            em.close();
        }
    }

    public Post getPostById(long id) {
        EntityManagerFactory factory = HibernateUtil.getEntityManagerFactory();
        EntityManager em = factory.createEntityManager();
        try {
            return em.find(Post.class, id);
        } finally {
            em.close();
        }
    }
}
