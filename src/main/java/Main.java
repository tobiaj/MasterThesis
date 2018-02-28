import java.io.File;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.InvalidKeyException;

import database.DatabaseStorage;
import fileservice.Fileservice;
import org.xmlpull.v1.XmlPullParserException;
import security.SecurityHandler;
import storage.MinioConnection;


import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException, InvalidKeyException, XmlPullParserException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {

       /*
        try {
            // Create a minioClient with the Minio Server name, Port, Access key and Secret key.
            MinioClient minioClient = new MinioClient("http://127.0.0.1:9000", "C0H2BEHW5JJMCJOXOO7H", "RQai8TfWJ1s8hvjbqsBa5EVif6ejp/kGDUCHv+cA");

            // Check if the bucket already exists.
            boolean isExist = minioClient.bucketExists("testing");
            if(isExist) {
                System.out.println("Bucket already exists.");
            } else {
                // Make a new bucket called asiatrip to hold a zip file of photos.
                minioClient.makeBucket("testing");
            }

            // Upload the zip file to the bucket with putObject
            minioClient.putObject("testing","test.zip", "dockerkeys.zip");
            System.out.println("success");
        } catch(MinioException e) {
            System.out.println("Error occurred: " + e);
        }
        */

        SecurityHandler securityHandler = new SecurityHandler();
        DatabaseStorage databaseStorage = new DatabaseStorage();
        MinioConnection minioConnection = new MinioConnection();
        Fileservice fileservice = new Fileservice(securityHandler, databaseStorage, minioConnection);
        File file = new File("text.txt");


        fileservice.processFileStorage(1, 1, 1, "A", file);

       // aes.encryptFile(Cipher.DECRYPT_MODE, outputEncryptionFile, outputDecryptionFile);




    }
}