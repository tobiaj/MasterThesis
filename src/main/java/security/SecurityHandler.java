package security;

import fileservice.AttachmentFile;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class SecurityHandler {
    AES aes;
    Blowfish blowfish;

    public SecurityHandler(){
        this.aes = new AES();
        this.blowfish = new Blowfish();
    }

    public void encryption(AttachmentFile attachmentFile) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IOException, IllegalBlockSizeException, InvalidAlgorithmParameterException {

        aes.processFile(Cipher.ENCRYPT_MODE, attachmentFile);

    }

    public void decryption(AttachmentFile attachmentFile) throws NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, IOException, InvalidAlgorithmParameterException {

        aes.processFile(Cipher.DECRYPT_MODE, attachmentFile);
    }


    private String getFileExtension(File file) {
        String name = file.getName();
        try {
            return name.substring(name.lastIndexOf("."));
        } catch (Exception e) {
            return "";
        }
    }

}
