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
    public void processFileStorage(int SNO, int BO_SNO, int FOLDER_SNO, File inputFile) throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, BadPaddingException, IOException, IllegalBlockSizeException {

        FileAttachment fileAttachment = new FileAttachment(SNO, BO_SNO, FOLDER_SNO, inputFile);
        fileAttachment.setFileName(inputFile.getName());
        securityHandler.encryption(fileAttachment);
        boolean success = false;

        /**How to check if file is encrypted**/
        if (fileAttachment.getAttachmentFile().getName().split("-")[0].equals("encrypted")){
           success =  minioConnection.uploadToStorage(fileAttachment);
        }
        else{
            System.out.println("Encryption failed");
        }

        if (success){

            databaseStorage.storeKeys(fileAttachment);
        }

        // securityHandler.decryption(fileAttachment);



    }

    public void processFileRetrieval(int SNO, int BO_SNO, int FOLDER_SNO, String date, String fileName){

        FileMetadata fileMetadata = new FileMetadata(SNO, BO_SNO, FOLDER_SNO, date, fileName);
        boolean success = false;

        success = minioConnection.retrieveFromStorage(fileMetadata);

        if (success){

            String key = databaseStorage.getKey(fileMetadata);
            System.out.println("Fileservice got key: " + key);
            if (!key.isEmpty()){

                File newFile = new File("" + fileMetadata.getFileName());
                //securityHandler.decryption(newFile, key);
            }
        }
        else {
            System.out.println("Retriveal ");
        }


    }

}
