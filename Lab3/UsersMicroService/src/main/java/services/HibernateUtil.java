package services;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.logging.Level;

public class HibernateUtil {

    private static final String PERSISTENCE_UNIT_NAME = "org.hibernate.lab3.jpa";

    private static EntityManagerFactory factory = null;

    public static EntityManagerFactory getEntityManagerFactory() {
        if (factory == null) { // run-once
            return factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        }
        return factory;
    }
}
