package database;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "keyholder")
public class KeyHolder {

    private Integer SNO;

    private Integer BO_SNO;
    private Integer FOLDER_SNO;
    private String NAME;
    private Date CREATIONDATE;
    private String ENCRYPTIONKEY;

    @Id
    private String UNIQUE_ID;


    public Integer getSNO() {
        return SNO;
    }

    public void setSNO(Integer SNO) {
        this.SNO = SNO;
    }

    public Integer getBO_SNO() {
        return BO_SNO;
    }

    public void setBO_SNO(Integer BO_SNO) {
        this.BO_SNO = BO_SNO;
    }

    public Integer getFOLDER_SNO() {
        return FOLDER_SNO;
    }

    public void setFOLDER_SNO(Integer FOLDER_SNO) {
        this.FOLDER_SNO = FOLDER_SNO;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public Date getCREATIONDATE() {
        return CREATIONDATE;
    }

    public void setCREATIONDATE(Date CREATIONDATE) {
        this.CREATIONDATE = CREATIONDATE;
    }

    public String getENCRYPTIONKEY() {
        return ENCRYPTIONKEY;
    }

    public void setENCRYPTIONKEY(String ENCRYPTIONKEY) {
        this.ENCRYPTIONKEY = ENCRYPTIONKEY;
    }

    public String getUNIQUE_ID() {
        return UNIQUE_ID;
    }

    public void setUNIQUE_ID(String UNIQUE_ID) {
        this.UNIQUE_ID = UNIQUE_ID;
    }
}
