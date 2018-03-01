package security;

import fileservice.FileAttachment;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
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

    public void encryption(FileAttachment fileAttachment) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IOException, IllegalBlockSizeException, InvalidAlgorithmParameterException {

        aes.encryption(fileAttachment);

    }

    public void decryption(FileAttachment fileAttachment) throws NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, IOException, InvalidAlgorithmParameterException {

        aes.decryption(fileAttachment);
    }


}
