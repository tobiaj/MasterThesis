package security;

import fileservice.AttachmentFile;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.*;
import java.util.Base64;

public class AES {

    private static final String ALGORITHM = "AES";
    private static final String AES_MODE = "AES/CBC/PKCS5Padding";


    public void processFile(int mode, AttachmentFile attachmentFile) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, IOException, InvalidAlgorithmParameterException {


        if (mode == 1){

            encryption(attachmentFile);
        }
        else if (mode == 2) {

            decryption(attachmentFile);
        }

        else {
            System.out.println("No match on security mode");
        }

    }

    private void encryption(AttachmentFile attachmentFile) throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, BadPaddingException, IOException, InvalidAlgorithmParameterException {

        File inputFile = attachmentFile.getAttachmentFile();
        String fileName = inputFile.getName();

        File outputFile = new File("encrypted-" + fileName);

        /******************Generate secret key and save it as string for database storage*******************/
        SecretKey secretKey = createSecretKey();
        String encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
        attachmentFile.setEncryptionkey(encodedKey);

        byte[] initializationVector = new byte[128/8];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(initializationVector);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(initializationVector);

        attachmentFile.setIvParameterSpec(ivParameterSpec);

        File result = processEncryptionDecryption(1, secretKey, attachmentFile.getAttachmentFile(), outputFile, ivParameterSpec);

        attachmentFile.setEncryptedFile(result);
    }

    private void decryption(AttachmentFile attachmentFile) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IOException, IllegalBlockSizeException, InvalidAlgorithmParameterException {

        File inputFile = attachmentFile.getEncryptedFile();
        String fileName = inputFile.getName();
        File outputFile = new File("decrypted-" + fileName);


        /******************Generate secret key from database storage*******************/
        byte[] decodedKey = Base64.getDecoder().decode(attachmentFile.getEncryptionkey().getBytes());
        SecretKey orignialSecretKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");

        IvParameterSpec ivParameterSpec = attachmentFile.getIvParameterSpec();


        processEncryptionDecryption(2, orignialSecretKey, inputFile, outputFile, ivParameterSpec);

    }


    private File processEncryptionDecryption(int mode, SecretKey secretKey, File inputFile, File outputFile, IvParameterSpec ivParameterSpec) throws IOException, BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {

        Cipher cipher = Cipher.getInstance(AES_MODE);
        cipher.init(mode, secretKey, ivParameterSpec);

        FileInputStream in = new FileInputStream(inputFile);
        FileOutputStream out = new FileOutputStream(outputFile);
        int inputFileSize = (int) inputFile.length();
        byte[] inputBuffer = new byte[inputFileSize];
        System.out.println(inputBuffer.length);
        int length;
        while ((length = in.read(inputBuffer)) != -1) {
            byte[] outputBuffer = cipher.update(inputBuffer, 0, length);
            if (outputBuffer != null) {
                out.write(outputBuffer);
            }
        }

        byte[] outputBuffer = cipher.doFinal();
        if (outputBuffer != null) {
            out.write(outputBuffer);
        }

        in.close();
        out.flush();
        out.close();

        System.out.println("Encryption finished");

        return outputFile;
    }


    private SecretKey createSecretKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
        keyGenerator.init(128);
        byte[] key = keyGenerator.generateKey().getEncoded();
        SecretKey secretKey = new SecretKeySpec(key, ALGORITHM);

        return secretKey;
    }


}
