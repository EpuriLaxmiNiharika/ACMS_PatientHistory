package com.example.niharika.hospitalmanagementsystem;

import android.app.Activity;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.amazonaws.SDKGlobalConfiguration;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormatter;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DownloadPdf extends Activity {

    WebView webView;String patient_id = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();

        patient_id = i.getStringExtra("id");

        setContentView(R.layout.activity_download_pdf);
        webView = (WebView)findViewById(R.id.web_view_pdf);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("dd_MM_yyyy");
        String today_date = mdformat.format(calendar.getTime());

       // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd_MM_yyyy");
       // LocalDateTime now = LocalDateTime.now();

       // formatter.ge
       // String s = formatter.format(now);
        //13_04_2018ELAXMIdoctor3

        String file_name = today_date + patient_id +"doctor3";
        //date_of_visit + patient_id + doctor_id;

        String url = "https://s3.ap-south-1.amazonaws.com/patient-history/"+file_name;

      //  String doc="<iframe src='http://docs.google.com/viewer?url=+https://s3.ap-south-1.amazonaws.com/patient-history/06688454-f726-4eba-8cb6-46e823410585+' width='100%' height='100%' style='border: none;'></iframe>";

        url =  "http://docs.google.com/gview?embedded=true&url="+url;
        String doc = "<iframe src=" + url +" width='100%' height='100%' style='border: none;'></iframe>";
        webView.getSettings().setJavaScriptEnabled(true);
        WebSettings webSettings = webView.getSettings();
        webSettings.setAllowFileAccess(true);
        webSettings.setAllowFileAccessFromFileURLs(true);
        webSettings.setAllowUniversalAccessFromFileURLs(true);
        //webView.loadUrl(url);
        webView.loadData(doc,"text/html", "UTF-8");
    }

/*    public void execute_pdfDownload(View v){

        String url = "https://s3.ap-south-1.amazonaws.com/patient-history/06688454-f726-4eba-8cb6-46e823410585";

        webView.getSettings().setJavaScriptEnabled(true);

        webView.loadUrl(url); */

     /*   String keyName = "first_test2";
        String bucket_name = "patient-history";
        try {
            BasicAWSCredentials credentials = new BasicAWSCredentials("AKIAIEJSVFYSVM6S6WMA", "PhN9yIniM/dkacTU6dHHn/PxZea7GiDwgJ2i1Ak+");

            System.setProperty(SDKGlobalConfiguration.ENFORCE_S3_SIGV4_SYSTEM_PROPERTY, "true");

            AmazonS3Client s3Client = new AmazonS3Client(credentials);

            s3Client.setEndpoint("s3-ap-south-1.amazonaws.com");

        //    S3Object object = s3Client.getObject(new GetObjectRequest(bucket_name, keyName));

            GetObjectRequest obj = new GetObjectRequest(bucket_name,keyName);


           // obj.setRange(0,100);
            S3Object obje = s3Client.getObject(obj);

            InputStream obj_data = obje.getObjectContent();

            byte[] buffer = new byte[8192];
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();



            }

            if (obje != null) {

                System.out.println("Object content");
                System.out.println(obj_data);

                System.out.println("Object meta data");
                System.out.println(obj_data);

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(obj_data));

                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line=bufferedReader.readLine())!=null){

                   stringBuilder.append(line);
                }

                System.out.println("string is: "+stringBuilder.toString());
                Toast.makeText(this,"object content: "+obj_data.read(),Toast.LENGTH_SHORT).show();

            } else {
                System.out.println("Object  null");

            }
            obj_data.close();

        }
        catch (Exception e){
            System.out.println(e);
            System.out.println(e);
            System.out.println(e);
            System.out.println(e);
        }*/
//    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(this,Patient_after_loginActivity.class);
        i.putExtra("id",patient_id);
        startActivity(i);
    }
}
