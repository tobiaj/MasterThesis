import java.io.File;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import database.DatabaseStorage;
import fileservice.FileAttachment;
import fileservice.Fileservice;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import security.AES;
import security.SecurityHandler;
import storage.MinioConnection;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;


public class Main {
    public static void main(String[] args) throws NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, IOException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException, ParseException {


        SecurityHandler securityHandler = new SecurityHandler();
        DatabaseStorage databaseStorage = new DatabaseStorage();
        MinioConnection minioConnection = new MinioConnection();
        Fileservice fileservice = new Fileservice(securityHandler, databaseStorage, minioConnection);
        File file = new File("ID2223-TEN1-2018-01-11.pdf");


        AES aes = new AES();

        FileAttachment fileAttachment = new FileAttachment(1, 1, 1, file);

        aes.encryption(fileAttachment);



        //fileservice.processFileStorage(1, 1, 1, file);

        fileservice.processFileRetrieval(1, 1, 1, "2018-03-01", "ID2223-TEN1-2018-01-11.pdf");





    }
}