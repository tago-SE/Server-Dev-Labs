package models;

import javax.persistence.EntityManager;
import java.io.Serializable;

public interface  EntityInt extends Serializable {
    /**
     * Returns the entity identifier
     * @return id
     */
    long getId();
    long getVersion();
}
