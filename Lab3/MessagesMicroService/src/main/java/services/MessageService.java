package services;

import models.Message;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {

    public MessageService() {

    }

    public Message insert(Message toInsert) {
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

    public Message delete(Message toDelete) {
        EntityManagerFactory factory = HibernateUtil.getEntityManagerFactory();
        EntityManager em = factory.createEntityManager();
        try {
            toDelete = em.find(Message.class, toDelete.getId());
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

    public Message delete(long id) {
        return delete(new Message(id));
    }

    public Message update(Message source) {
        EntityManagerFactory factory = HibernateUtil.getEntityManagerFactory();
        EntityManager em = factory.createEntityManager();
        try {
            Message persistent = em.find(Message.class, source.getId());
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

    public List<Message> getAll() {
        EntityManagerFactory factory = HibernateUtil.getEntityManagerFactory();
        EntityManager em = factory.createEntityManager();
        try {
            return Message.createGetAllQuery(em).getResultList();
        } catch (Exception e) {
            if (e instanceof NoResultException)
                return new ArrayList<>();
            else
                throw e;
        } finally {
            em.close();
        }
    }

    public List<Message> getUserInbox(String username) {
        EntityManagerFactory factory = HibernateUtil.getEntityManagerFactory();
        EntityManager em = factory.createEntityManager();
        try {
            return Message.createInboxQuery(em, username).getResultList();
        } catch (Exception e) {
            if (e instanceof NoResultException)
                return new ArrayList<>();
            else
                throw e;
        } finally {
            em.close();
        }
    }

    public Message getMessageById(long id) {
        EntityManagerFactory factory = HibernateUtil.getEntityManagerFactory();
        EntityManager em = factory.createEntityManager();
        try {
            return em.find(Message.class, id);
        } finally {
            em.close();
        }
    }

}
