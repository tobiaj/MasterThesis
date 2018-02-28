package database;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;

public class DatabaseStorage {
    private static EntityManagerFactory emFactory;

    public void storeKeys(){

        Date date = new Date();

        KeyHolder keyHolder = new KeyHolder();
        keyHolder.setSNO(1);
        keyHolder.setBO_SNO(1);
        keyHolder.setFOLDER_SNO(1);
        keyHolder.setNAME("A");
        keyHolder.setCREATIONDATE(date);
        keyHolder.setENCRYPTIONKEY("123");
        keyHolder.setUNIQUE_ID("123");

        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        em.getTransaction()
                .begin();
        em.persist(keyHolder);
        em.getTransaction()
                .commit();
        em.close();
        PersistenceManager.INSTANCE.close();

    }

    public enum PersistenceManager {
        INSTANCE;
        private PersistenceManager() {
            // "jpa-example" was the value of the name attribute of the
            // persistence-unit element.
            emFactory = Persistence.createEntityManagerFactory("fileservice");
        }
        public EntityManager getEntityManager() {
            return emFactory.createEntityManager();
        }
        public void close() {
            emFactory.close();
        }
    }
}
