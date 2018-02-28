package fileservice;

import database.DatabaseStorage;
import security.SecurityHandler;
import storage.MinioConnection;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Fileservice {

    SecurityHandler securityHandler;
    DatabaseStorage databaseStorage;
    MinioConnection minioConnection;
    public Fileservice(SecurityHandler securityHandler, DatabaseStorage databaseStorage, MinioConnection minioConnection){
        this.securityHandler = securityHandler;
        this.databaseStorage = databaseStorage;
        this.minioConnection = minioConnection;

    }
    public void processFileStorage(int SNO, int BO_SNO, int FOLDER_SNO, String companyName, File inputFile) throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, BadPaddingException, IOException, IllegalBlockSizeException {

        AttachmentFile attachmentFile = new AttachmentFile(SNO, BO_SNO, FOLDER_SNO, companyName, inputFile);

        securityHandler.encryption(attachmentFile);
        System.out.println(attachmentFile.getAttachmentFile().getName());
        securityHandler.decryption(attachmentFile);



    }

    public void processFileRetrieval(){

    }

}
