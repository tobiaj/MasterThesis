package fileservice;

import javax.crypto.spec.IvParameterSpec;
import java.io.File;
import java.util.Date;

public class AttachmentFile {

    private int SNO;
    private int BO_SNO;
    private int FOLDER_SNO;
    private String companyName;
    private Date date;
    private String encryptionkey;
    private String uniqueId;
    private File attachmentFile;
    private File encryptedFile;

    private IvParameterSpec ivParameterSpec;


    public AttachmentFile(int sno, int bo_sno, int folder_sno, String companyName, File attachmentFile) {
        this.SNO = sno;
        this.BO_SNO = bo_sno;
        this.FOLDER_SNO = folder_sno;
        this.companyName = companyName;
        this.attachmentFile = attachmentFile;
    }

    public int getSNO() {
        return SNO;
    }

    public void setSNO(int SNO) {
        this.SNO = SNO;
    }

    public int getBO_SNO() {
        return BO_SNO;
    }

    public void setBO_SNO(int BO_SNO) {
        this.BO_SNO = BO_SNO;
    }

    public int getFOLDER_SNO() {
        return FOLDER_SNO;
    }

    public void setFOLDER_SNO(int FOLDER_SNO) {
        this.FOLDER_SNO = FOLDER_SNO;
    }

    public String getName() {
        return companyName;
    }

    public void setName(String companyName) {
        this.companyName = companyName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEncryptionkey() {
        return encryptionkey;
    }

    public void setEncryptionkey(String encryptionkey) {
        this.encryptionkey = encryptionkey;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public File getAttachmentFile() {
        return attachmentFile;
    }

    public void setAttachmentFile(File attachmentFile) {
        this.attachmentFile = attachmentFile;
    }

    public File getEncryptedFile() {
        return encryptedFile;
    }

    public void setEncryptedFile(File encryptedFile) {
        this.encryptedFile = encryptedFile;
    }

    public IvParameterSpec getIvParameterSpec() {
        return ivParameterSpec;
    }

    public void setIvParameterSpec(IvParameterSpec ivParameterSpec) {
        this.ivParameterSpec = ivParameterSpec;
    }
}
