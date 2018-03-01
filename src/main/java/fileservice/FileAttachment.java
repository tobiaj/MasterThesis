package fileservice;

import javax.crypto.spec.IvParameterSpec;
import java.io.File;
import java.sql.Date;

public class FileAttachment {

    private int SNO;
    private int BO_SNO;
    private int FOLDER_SNO;
    private Date date;
    private String encryptionkey;
    private File attachmentFile;
    private String fileName;

    private IvParameterSpec ivParameterSpec;


    public FileAttachment(int sno, int bo_sno, int folder_sno, File attachmentFile) {
        this.SNO = sno;
        this.BO_SNO = bo_sno;
        this.FOLDER_SNO = folder_sno;
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


    public File getAttachmentFile() {
        return attachmentFile;
    }

    public void setAttachmentFile(File attachmentFile) {
        this.attachmentFile = attachmentFile;
    }

    public IvParameterSpec getIvParameterSpec() {
        return ivParameterSpec;
    }

    public void setIvParameterSpec(IvParameterSpec ivParameterSpec) {
        this.ivParameterSpec = ivParameterSpec;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
