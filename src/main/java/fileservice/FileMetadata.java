package fileservice;

import java.util.Date;

public class FileMetadata {

    private int SNO;
    private int BO_SNO;
    private int FOLDER_SNO;
    private String date;
    private String encryptionkey;
    private String fileName;


    public FileMetadata(int sno, int bo_sno, int folder_sno, String date, String fileName) {
        this.SNO = sno;
        this.BO_SNO = bo_sno;
        this.FOLDER_SNO = folder_sno;
        this.date = date;
        this.fileName = fileName;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEncryptionkey() {
        return encryptionkey;
    }

    public void setEncryptionkey(String encryptionkey) {
        this.encryptionkey = encryptionkey;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
