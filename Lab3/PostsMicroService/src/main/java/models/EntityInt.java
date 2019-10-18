package models;

import javax.persistence.EntityManager;
import java.io.Serializable;

public interface  EntityInt extends Serializable {
    /**
     * Returns the entity identifier
     * @return id
     */
    long getId();
    int getVersion();
    void update(EntityManager em, EntityInt source);
    void delete(EntityManager em);
}
