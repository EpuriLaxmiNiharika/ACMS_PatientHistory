package upload_pdf;
import java.io.*;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
//import com.pdfcrowd.*;
import java.io.*;
import java.util.List;
import java.util.UUID;

public class UploadPdf {

    private static String bucket_name     = "patient-history";
    //public  String keyName = ; //generate a random new id everytime

    UploadPdf(String filepath){
    	 BasicAWSCredentials credentials = new BasicAWSCredentials("AKIAIEJSVFYSVM6S6WMA", "PhN9yIniM/dkacTU6dHHn/PxZea7GiDwgJ2i1Ak+");
         AmazonS3 client = AmazonS3ClientBuilder.standard().withRegion(Regions.AP_SOUTH_1)
                 .withCredentials(new AWSStaticCredentialsProvider(credentials))
                 .build();

         client.getBucketLocation(bucket_name);
         String filePath=filepath;
         File file = new File(filePath);
         String fileKey = uploadToS3(client,file);
         S3Object s3object = downloadFromS3(client,"first_test2");
         System.out.println("Content-Type: "  +
                 s3object.getObjectMetadata().getContentType());

    }
   
    private static String uploadToS3( AmazonS3 client, File file)
    {

        System.out.println("Uploading a new object to S3 from a file\n");
        String keyName  = UUID.randomUUID().toString();

        try {

            client.putObject(new PutObjectRequest(bucket_name, keyName, file));


        } catch (AmazonServiceException ase) {
            System.out.println("Caught an AmazonServiceException, which " +
                    "means your request made it " +
                    "to Amazon S3, but was rejected with an error response" +
                    " for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException, which " +
                    "means the client encountered " +
                    "an internal error while trying to " +
                    "communicate with S3, " +
                    "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
        }
        return keyName;
    }

    private static S3Object downloadFromS3( AmazonS3 client, String keyName)
    {

        System.out.println("Downloading an object from S3\n");

        try {

            S3Object s3object = client.getObject(
                    new GetObjectRequest(bucket_name, keyName));
            return s3object;
            //  displayTextInputStream(s3object.getObjectContent());


        } catch (AmazonServiceException ase) {
            System.out.println("Caught an AmazonServiceException, which " +
                    "means your request made it " +
                    "to Amazon S3, but was rejected with an error response" +
                    " for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException, which " +
                    "means the client encountered " +
                    "an internal error while trying to " +
                    "communicate with S3, " +
                    "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
        }
        return null;

    }
    private static void displayTextInputStream(InputStream input)
            throws IOException {
        // Read one text line at a time and display.
        BufferedReader reader = new BufferedReader(new
                InputStreamReader(input));
        while (true) {
            String line = reader.readLine();
            if (line == null) break;

            System.out.println("    " + line);
        }
        System.out.println();
    }

}