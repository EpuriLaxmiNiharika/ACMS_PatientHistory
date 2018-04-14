package com.example.niharika.hospitalmanagementsystem;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.amazonaws.SDKGlobalConfiguration;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferListener;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferState;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;


public class Patient_after_loginActivity extends Activity {

    String URL =  "http://ec2-52-66-43-37.ap-south-1.compute.amazonaws.com:8080/EHR-PatientHistoryService/api/patientHistory/Zf7NwmWcchWg4ztNFnWlaKrPFOlPFrtr";
    String[] json_response = {"userId","password"};
    ListView listView;
    int[] ids = {R.id.patient_img_reason,R.id.patient_reason,R.id.patient_subreason};
    String keys[] = {"image","reason","subreason"};
    int images[] = {R.drawable.book_appointment,R.drawable.report,R.drawable.patient_medical_history,R.drawable.charts,R.drawable.alarm,R.drawable.feedback};
    String reason[] = {"Book an appointment","View Appointments   ","Medical history form", "Patient Reports        ","Set Remainders          ","Feedback Form"};
    String subreason[] = {"Find doctors and book appointment in convinient slot", "View past current and upcoming Appointments","Details of recent Medical history form","Patient Progress Reports","Get alerts so that you never miss a dose",""};
    int num_fields = images.length;
    ArrayList arrayList;
    String patient_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_listview);
        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        listView = (ListView)findViewById(R.id.patient_lv);
        Intent i = getIntent();
        patient_id =  i.getStringExtra("id");
        arrayList = new ArrayList();

        for(int i1 = 0 ; i1<num_fields;i1++){
            HashMap hm = new HashMap();
            hm.put(keys[0],images[i1]);
            hm.put(keys[1],reason[i1]);
            hm.put(keys[2],subreason[i1]);
            arrayList.add(hm);
        }

        SimpleAdapter sa = new SimpleAdapter(this, arrayList, R.layout.patient_listview_item, keys, ids);

        listView.setAdapter(sa);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, final long id) {
                HashMap hashMap = (HashMap)arrayList.get(position);
                ImageView imageView = (ImageView)view.findViewById(R.id.arrow);
                if(id==0){ // Book Appointement
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                           // Toast.makeText(getApplicationContext(),"Book Appointment:--"+reason[position],Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(Patient_after_loginActivity.this,SearchDoctor.class);
                            i.putExtra("id",patient_id);
                            startActivity(i);
                        }
                    });
                }
                // http://ec2-52-66-43-37.ap-south-1.compute.amazonaws.com:8080/EHR-PatientHistoryService/api/patientHistory/Zf7NwmWcchWg4ztNFnWlaKrPFOlPFrtr
                if(id==1){ //View Patient medical history form
                   /* imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) { */
                       /*     Toast.makeText(getApplicationContext(),"Past History:--"+reason[position],Toast.LENGTH_SHORT).show();
                     //       Intent i = new Intent(Patient_after_loginActivity.this,MyLocationUsingLocationAPI.class);
                       //     i.putExtra("id",patient_id);
                         //   startActivity(i);

                            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                            JSONObject json = new JSONObject();
                            JsonObjectRequest jsonObjectRequest;

                            jsonObjectRequest = new JsonObjectRequest(
                                    Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    try {
                                        Toast.makeText(getApplicationContext(),response.toString()+"---resp",Toast.LENGTH_SHORT).show();
                                        String id = (String)response.get("allergies");
                                        String passwd = (String)response.get("conditions");

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    //  Log.e("response",error.toString());
                                    Toast.makeText(getApplicationContext(),error.toString()+"---err",Toast.LENGTH_SHORT).show();
                                }
                            }
                            );
                            requestQueue.add(jsonObjectRequest);
                            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                                    50000,
                                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

                            Toast.makeText(getApplicationContext(),"inside loop",Toast.LENGTH_SHORT).show();
                           
                        */


 //                           Object[] params = {"niharika", "condition", "symptom1", false, "medicine", false, "alergies1,2,3", "28_07_97"};
                        //    AsyncTask_PastHistory asyncT = new AsyncTask_PastHistory();
                          //  asyncT.execute(params);
                          //  new Upload_Download_S3();



                            /////////////////////////////////

                           // download_file();

                           startActivity( new Intent(getApplicationContext(),DownloadPdf.class));
                         //   new Async_download_background();
                      //      AsyncTask_PastHistory asyncT =  new AsyncTask_PastHistory();
                        //    asyncT.execute(params);
                       /*     startActivity(new Intent(getApplicationContext(),Display_patient_SugarLevels_Graph.class));
                        }
                    });*/
                }

                if(id==2){ // Medical history form
                    Intent i = new Intent(getApplicationContext(),DownloadPdf.class);
                    i.putExtra("id",patient_id);
                    startActivity(i);
                }

                if(id==3){

                    Intent i = new Intent(getApplicationContext(),Display_patient_SugarLevels_Graph.class);
                    i.putExtra("id",patient_id);
                    startActivity(i);

                 //   Object[] params = {"niharika", "condition", "symptom1", false, "medicine", false, "alergies1,2,3", "2018-04-13",230,80,74};
                 //   AsyncTask_PastHistory asyncT =  new AsyncTask_PastHistory();
                  //  asyncT.execute(params);
                }

                if(id==4){
                    Intent i = new Intent(Patient_after_loginActivity.this,AlarmMe.class);
                    i.putExtra("id",patient_id);
                    startActivity(i);
                }

                if(id == 5){
                    Intent i = new Intent(Patient_after_loginActivity.this,FeedBackFormActivity.class);
                    i.putExtra("id",patient_id);
                    startActivity(i);
                }
            }
        });

    }

    @Override
    public void onBackPressed(){
      //  Intent i = new Intent(Intent.ACTION_MAIN);
        Intent i = new Intent(this,Patient_after_loginActivity.class);
        i.putExtra("id",patient_id);
        i.addCategory(Intent.CATEGORY_HOME);
        startActivity(i);
    }

    public void download_file(){

        String keyName = "first_test2";
        String bucket_name     = "patient-history";
        try {
            BasicAWSCredentials credentials = new BasicAWSCredentials("AKIAIEJSVFYSVM6S6WMA", "PhN9yIniM/dkacTU6dHHn/PxZea7GiDwgJ2i1Ak+");

            System.setProperty(SDKGlobalConfiguration.ENFORCE_S3_SIGV4_SYSTEM_PROPERTY, "true");

            AmazonS3Client s3Client = new AmazonS3Client(credentials);

            s3Client.setEndpoint("s3-ap-south-1.amazonaws.com");

            TransferUtility transferUtility = new TransferUtility(s3Client,this);

            S3Object object = s3Client.getObject(new GetObjectRequest("patient-history", keyName));
            Toast.makeText(this,"aksalksaa: "+object,Toast.LENGTH_SHORT).show();

            String file_name = object.getKey();
            File f = Environment.getExternalStorageDirectory();
            String path = f.getAbsolutePath();
            //adding file name to Path
            path = path + "/" +file_name;

            File new_file = new File(path);

            if (object != null) {
                System.out.println("Object not null");
                System.out.println("Object not null");
                System.out.println("Object not null");
                System.out.println("Object not null");
                System.out.println("Object not null");

                System.out.println("Object content");
                System.out.println(object.getObjectContent());

                System.out.println("Object meta data");
                System.out.println(object.getObjectMetadata());
              //  TransferObserver transferObserver = transferUtility.download(bucket_name,"first_test2",new_file);


            //    transferObserverListerner(transferObserver);


               /*     FileOutputStream fout = new FileOutputStream(new_file);

                    fout.flush();
                    fout.close();
                    Toast.makeText(this, "Image Written to External", Toast.LENGTH_SHORT).show();
                */


            //    File root = new File(Environment.getExternalStorageDirectory());

                Toast.makeText(this,"object content: "+object.getObjectContent(),Toast.LENGTH_SHORT).show();

                // if external memory exists and folder with name Notes
           //     if (!root.exists()) {
             //       root.mkdirs(); // this will create folder.
              //  }
                /*File filepath = new File(root, "MY_AWS_FILE" + ".pdf");  // file path to save

                BufferedReader reader = new BufferedReader(new InputStreamReader(object.getObjectContent()));

                FileWriter writer = new FileWriter(filepath);

//                Writer writer = new OutputStreamWriter(new FileOutputStream(new_file));

                System.out.print("hey I AM DONE READING");
                while (true){
                    System.out.print("hey I am there");
                    String line = reader.readLine();
                    if(line==null){
                        break;
                    }
                    writer.write(line+"\n");
                }*/
              // writer.flush();
               // writer.close();
               // reader.close();
            } else {
                System.out.println("Object  null");
                System.out.println("Object  null");
                System.out.println("Object  null");
                System.out.println("Object  null");
                System.out.println("Object  null");
            }
        }
        catch (Exception e){
            System.out.println(e);
            System.out.println(e);
            System.out.println(e);
            System.out.println(e);
        }

      /*  InputStream reader = new BufferedInputStream(object.getObjectContent());
        File f = new File("new_file");

        try {
            OutputStream writer = new BufferedOutputStream(new FileOutputStream(f));
        }
        catch (FileNotFoundException e){
            System.out.println("Exception Name");
            System.out.println("Exception Name");
        }
        */
    }

   /* public void transferObserverListerner(TransferObserver transferObserver){
        transferObserver.setTransferListener(new TransferListener() {
            @Override
            public void onStateChanged(int id, TransferState state) {
                Toast.makeText(getApplicationContext(),"State Change"+state, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onProgressChanged(int id, long bytesCurrent, long bytesTotal) {

                int percentage = (int) (bytesCurrent/bytesTotal *100);
                Toast.makeText(getApplicationContext(),"Progress in %"+percentage, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onError(int id, Exception ex) {
                Toast.makeText(getApplicationContext(),"Exception --- " + ex.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }*/
/*
    public void createandDisplayPdf(String text) {

        Document doc = new Document();

        try {
            String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/PDF";

            File dir = new File(path);
            if(!dir.exists())
                dir.mkdirs();

            File file = new File(dir, "mypdffile.pdf");
            FileOutputStream fOut = new FileOutputStream(file);

            PdfWriter.getInstance(doc, fOut);

            //open the document
            doc.open();

            Paragraph p1 = new Paragraph(text);
            Font paraFont= new Font(Font.FontFamily.COURIER);
            p1.setAlignment(Paragraph.ALIGN_CENTER);
            p1.setFont(paraFont);

            //add paragraph to document
            doc.add(p1);

        } catch (Exception de) {
            Log.e("PDFCreator", "DocumentException:" + de);
        } catch (IOException e) {
            Log.e("PDFCreator", "ioException:" + e);
        }
        finally {
            doc.close();
        }

        viewPdf("mypdffile.pdf", "PDF");
    }

    // Method for opening a pdf file
    private void viewPdf(String file, String directory) {

        File pdfFile = new File(Environment.getExternalStorageDirectory() + "/" + directory + "/" + file);
        Uri path = Uri.fromFile(pdfFile);

        // Setting the intent for pdf reader
        Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
        pdfIntent.setDataAndType(path, "application/pdf");
        pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        try {
            startActivity(pdfIntent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "Can't read pdf file", Toast.LENGTH_SHORT).show();
        }
    }
    */
}
