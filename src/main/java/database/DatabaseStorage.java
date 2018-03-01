package database;

import fileservice.FileAttachment;
import fileservice.FileMetadata;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class DatabaseStorage {
    private static EntityManagerFactory emFactory;

    public void storeKeys(FileAttachment fileAttachment){

        KeyHolder keyHolder = new KeyHolder();
        keyHolder.setSNO(fileAttachment.getSNO());
        keyHolder.setBO_SNO(fileAttachment.getBO_SNO());
        keyHolder.setFOLDER_SNO(fileAttachment.getFOLDER_SNO());
        keyHolder.setCREATIONDATE(fileAttachment.getDate());
        keyHolder.setENCRYPTIONKEY(fileAttachment.getEncryptionkey());
        keyHolder.setFILENAME(fileAttachment.getFileName());
        keyHolder.setUNIQUE_ID(UUID.randomUUID().toString());

        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        em.getTransaction()
                .begin();
        em.persist(keyHolder);
        em.getTransaction()
                .commit();
        em.close();
        PersistenceManager.INSTANCE.close();


    }

    public String getKey(FileMetadata fileMetadata){

        java.sql.Date date = java.sql.Date.valueOf(fileMetadata.getDate());
        System.out.println("getting key");
        System.out.println(date);


        int sno = fileMetadata.getSNO();
        int bo_sno = fileMetadata.getBO_SNO();
        int folder_sno = fileMetadata.getFOLDER_SNO();
        String fileName = fileMetadata.getFileName();
        System.out.println(fileName);
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();


        List resultList =  em.createQuery(
                "select ENCRYPTIONKEY from KeyHolder item where item.SNO like :sno " +
                        "and item.BO_SNO like :bo_sno and item.FOLDER_SNO like :folder_sno " +
                        "and item.CREATIONDATE like :date "+
                        "and item.FILENAME like :filename"
        )
                .setParameter("sno", sno)
                .setParameter("bo_sno", bo_sno)
                .setParameter("folder_sno", folder_sno)
                .setParameter("date", date)
                .setParameter("filename", fileName)
                .getResultList();


        if (!resultList.isEmpty()){
            System.out.println("done");
            return String.valueOf(resultList.toArray()[0]);

        }

        return "";
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
