
package com.example.niharika.hospitalmanagementsystem;
import java.util.ArrayList;
import java.util.HashMap;
 
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class SearchDoctor extends AppCompatActivity {
     
    // List view
    private ListView lv;
     
    // Listview Adapter
    String doctors[];
    String designation[];
    // Search EditText
    EditText inputSearch;
     String patient_id;
     
    // ArrayList for Listview
    ArrayList<HashMap<String,Object>>doctors_al;
    ArrayList<HashMap<String,Object>>search_res;
    int[] keys = {R.id.lv_docname,R.id.lv_doc_designation};
    String[] ids={"name","designation"};
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_doctors);
        lv = (ListView) findViewById(R.id.doctors_lv);
        doctors_al = new ArrayList<HashMap<String,Object>>();
        search_res = new ArrayList<HashMap<String,Object>>();
        Intent i = getIntent();
        patient_id =  i.getStringExtra("id");
        // Listview Data
        doctors = new String[]{"Dell Inspiron", "HTC One X", "HTC Wildfire S", "HTC Sense", "HTC Sensation XE",
                                "iPhone 4S", "Samsung Galaxy Note 800",
                                "Samsung Galaxy S3", "MacBook Air", "Mac Mini", "MacBook Pro"};


        designation = new String[]{"Dell Inspiron", "HTC One X", "HTC Wildfire S", "HTC Sense", "HTC Sensation XE",
                "iPhone 4S", "Samsung Galaxy Note 800",
                "Samsung Galaxy S3", "MacBook Air", "Mac Mini", "MacBook Pro"};


        inputSearch = (EditText) findViewById(R.id.search_docs);
        for(int i1 = 0; i1<doctors.length;i1++){
            HashMap hm = new HashMap();
            hm.put(ids[0],doctors[i1]);
            hm.put(ids[1],designation[i1]);
            doctors_al.add(hm);
            search_res.add(hm);
        }

        // Adding items to listview
        SimpleAdapter sa = new SimpleAdapter(
                this,doctors_al, R.layout.viewdoctor_listitem, ids,keys);
        lv.setAdapter(sa);
        //adapter = new ArrayAdapter<String>(this, R.layout.viewdoctor_listitem, R.id.lv_docname, doctors);
        //lv.setAdapter(adapter);

	/*inputSearch.addTextChangedListener(new TextWatcher() {
	     
	    @Override
	    public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
		// When user changed the Text
            SearchDoctor.this.adapter.getFilter().filter(cs);
	    }
	     
	    @Override
	    public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
		    int arg3) {
		// TODO Auto-generated method stub
		 
	    }
	     
	    @Override
	    public void afterTextChanged(Editable arg0) {
		// TODO Auto-generated method stub                          
	    }
	});    */


	inputSearch.addTextChangedListener(new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {

          int textlength = inputSearch.getText().length();
          String searchString= inputSearch.getText().toString();
          search_res.clear();
          String attr = null;
            for (int i = 0; i < doctors_al.size(); i++)
            {

              attr = doctors_al.get(i).get(ids[0]).toString().trim();

              Log.i("attr", attr+"");

                if (textlength  <= attr.length())
                {

                    if (searchString.equalsIgnoreCase(attr.substring(0,textlength)))
                    {
                      search_res.add(doctors_al.get(i));
                    }
                }
            }

            SimpleAdapter simple = new SimpleAdapter(SearchDoctor.this, search_res, R.layout.viewdoctor_listitem,
                   ids,keys);

          lv.setAdapter(simple);
        }



        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                int arg3) {
            // TODO Auto-generated method stub

        }

        @Override
        public void afterTextChanged(Editable arg0) {
            // TODO Auto-generated method stub    

        }
    });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap hm = search_res.get(position);
                String doctor_name = hm.get(ids[0]).toString().trim();
                Intent i = new Intent(getApplicationContext(),BookAppointmentActivity.class);
                i.putExtra("id",patient_id);
                i.putExtra("doctor",doctor_name);
                i.putExtra("designation",hm.get(ids[1]).toString().trim());
                startActivity(i);
            }
        });
    }
     
}
