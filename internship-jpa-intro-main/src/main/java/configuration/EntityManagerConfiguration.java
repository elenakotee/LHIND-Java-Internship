package configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerConfiguration {

    private static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("jpa-intro");
    private static final EntityManager ENTITY_MANAGER = FACTORY.createEntityManager();

    private EntityManagerConfiguration() {}

    public static EntityManager getEntityManager() {
        return ENTITY_MANAGER;
    }

}