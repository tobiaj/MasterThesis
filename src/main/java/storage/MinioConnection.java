package storage;

import fileservice.FileAttachment;
import fileservice.FileMetadata;
import io.minio.MinioClient;
import io.minio.errors.*;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.sql.Date;

public class MinioConnection {


    MinioClient minioClient;
    public MinioConnection() {
        try {
            this.minioClient = new MinioClient("http://127.0.0.1:9000", "491NX4JO2YVGB5LDLUHV","3W1/rhvRrqzGvFpf2X/28Zz4CQqnTVB6CIG7LYPv");
        } catch (InvalidEndpointException e) {
            e.printStackTrace();
        } catch (InvalidPortException e) {
            e.printStackTrace();
        }

    }


    public boolean uploadToStorage(FileAttachment fileAttachment){

        Date date = new Date(System.currentTimeMillis());
        System.out.println(date);

        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateResult = sdf.format(date);

        fileAttachment.setDate(date);

        String bucketName = "proceedo-" + dateResult.split("-")[0];

        String companyAndFilePrefix = createCompanyPrefixForUpload(fileAttachment, dateResult);


        System.out.println(bucketName);

        try {
            boolean bucketExist = minioClient.bucketExists(bucketName);
            if (bucketExist){
                System.out.println("Bucket exists");
            }
            else{
                minioClient.makeBucket(bucketName);
            }

            minioClient.putObject(bucketName, companyAndFilePrefix, fileAttachment.getAttachmentFile().getName());
            System.out.println("Upload successful");
            return true;


        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoResponseException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (InsufficientDataException e) {
            e.printStackTrace();
        } catch (InvalidBucketNameException e) {
            e.printStackTrace();
        } catch (ErrorResponseException e) {
            e.printStackTrace();
        } catch (InternalException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidArgumentException e) {
            e.printStackTrace();
        } catch (RegionConflictException e) {
            e.printStackTrace();
        }

        return false;
    }


    public boolean retrieveFromStorage(FileMetadata fileMetadata){

        String companyAndFilePrefix = createCompanyPrefixForDownload(fileMetadata);

        String bucketName = "proceedo-" + fileMetadata.getDate().split("-")[0];
        String fetchedFileName = "decrypted-" + fileMetadata.getFileName();

        try {

            boolean bucketExist = minioClient.bucketExists(bucketName);
            if (bucketExist){
                System.out.println("Bucket exists");
            }
            else{
                System.out.println("Bucket does not exist, or wrong bucket name");
            }

            minioClient.getObject(bucketName, companyAndFilePrefix, fetchedFileName);
            System.out.println("Upload successful");
            //fileMetadata.setFileName(fetchedFileName);
            return true;

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (InvalidBucketNameException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InsufficientDataException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (ErrorResponseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoResponseException e) {
            e.printStackTrace();
        } catch (InternalException e) {
            e.printStackTrace();
        } catch (InvalidArgumentException e) {
            e.printStackTrace();
        }

        return false;

    }


    private String createCompanyPrefixForUpload(FileAttachment fileAttachment, String dateSplit){

        int sno = fileAttachment.getSNO();
        int bo_sno = fileAttachment.getBO_SNO();
        int folder_sno = fileAttachment.getFOLDER_SNO();
        String fileName = fileAttachment.getAttachmentFile().getName();

        return (sno + "-" + bo_sno + "-" + folder_sno + "-" + dateSplit + "-" + fileName).toLowerCase();

    }

    private String createCompanyPrefixForDownload(FileMetadata fileMetadata){

        int sno = fileMetadata.getSNO();
        int bo_sno = fileMetadata.getBO_SNO();
        int folder_sno = fileMetadata.getFOLDER_SNO();
        String fileName = fileMetadata.getFileName();
        String date = fileMetadata.getDate();

        return (sno + "-" + bo_sno + "-" + folder_sno + "-" + date + "-encrypted-" + fileName).toLowerCase();
    }

    private String getFileExtension(String name) {
        try {
            return name.substring(name.lastIndexOf("."));
        } catch (Exception e) {
            return "";
        }
    }



}