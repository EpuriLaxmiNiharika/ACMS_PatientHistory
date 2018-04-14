package com.example.niharika.hospitalmanagementsystem;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends Activity {

    EditText id;
    EditText pass;
  //  SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        id = (EditText)findViewById(R.id.login_id);
        pass = (EditText)findViewById(R.id.login_pass);

      //  settings = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
       // SharedPreferences.Editor editor = settings.edit();
      //  editor.putBoolean("is_logged",false);
      //  editor.putBoolean("is_registered",false);
       // editor.commit();

    }

    public void registerUser(View v){
        Intent i = new Intent(this,RegisterActivity_1.class);
        startActivity(i);
    }

    public void loginUser(View v){
   //     SharedPreferences.Editor editor = settings.edit();
     //   editor.putBoolean("is_logged",true);
      //  editor.commit();

        String login_id = id.getText().toString();
        String login_pass = pass.getText().toString();

        if(login_id.isEmpty()){
            id.requestFocus();
            id.setError("Empty");
        }
        else{
            if(login_pass.isEmpty()){
                pass.requestFocus();
                pass.setError("Empty");
            }
            else{
              //  if(!settings.getBoolean("is_logged",false)){
                    Intent i = new Intent(this,Patient_after_loginActivity.class);
                    i.putExtra("id",login_id);
                    startActivity(i);
                //}
            }
        }
    }
    @Override
    public void onBackPressed(){
          Intent i = new Intent(Intent.ACTION_MAIN);
      //  Intent i = new Intent(this,Patient_after_loginActivity.class);
        //i.putExtra("id",patient_id);
        i.addCategory(Intent.CATEGORY_HOME);
        startActivity(i);
    }
}
