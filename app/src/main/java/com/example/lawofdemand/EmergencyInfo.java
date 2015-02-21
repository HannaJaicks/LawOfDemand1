package com.example.lawofdemand;

/**
 * Created by jaicksninan on 1/20/15.
 */
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import com.quickblox.auth.QBAuth;
import com.quickblox.auth.model.QBSession;
import com.quickblox.core.QBCallbackImpl;
import com.quickblox.core.QBEntityCallback;
import com.quickblox.core.result.Result;
import com.quickblox.customobjects.QBCustomObjects;
import com.quickblox.customobjects.model.QBCustomObject;
import com.quickblox.customobjects.result.QBCustomObjectResult;
import com.quickblox.users.model.QBUser;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EmergencyInfo extends Activity {
    EditText Firstname, Lastname, State, Cellphone, Homephone, Businessphone, Email;
    Button Submit, btnBack;
    Boolean invalid = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emergency_page);
        Firstname = (EditText) findViewById(R.id.edtfirstname);
        Lastname = (EditText) findViewById(R.id.edtlastname);
        State = (EditText) findViewById(R.id.edtstate);
        Cellphone = (EditText) findViewById(R.id.edtcell);
        Homephone = (EditText) findViewById(R.id.edthome);
        Businessphone = (EditText) findViewById(R.id.edtbusiness);
        Email = (EditText) findViewById(R.id.edtemail);
        Submit = (Button) findViewById(R.id.btnsubmit);
        btnBack = (Button) findViewById(R.id.btnback);
        Submit.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                HashMap<String, Object> fields = new HashMap<String, Object>();
                SharedPreferences myprefs = getSharedPreferences("User", MODE_WORLD_READABLE);
                String user = myprefs.getString("Login", null);


                Calendar c = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("dd:MMMM:yyyy HH:mm:ss a");
                String strDate = sdf.format(c.getTime());
                // TODO Auto-generated method stub
                String firstname = Firstname.getText().toString();
                String lastname = Lastname.getText().toString();
                String state = State.getText().toString();
                String cellphone = Cellphone.getText().toString();
                String homephone = Homephone.getText().toString();
                String businessphone = Businessphone.getText().toString();
                String email = Email.getText().toString();
//				  SharedPreferences pref = getApplicationContext().getSharedPreferences("User", Context.MODE_WORLD_READABLE);
//				    Editor editor = pref.edit();
//				    editor.putString("First_name",firstname);
//				    editor.putString("last_name",lastname);
//				    editor.putString("state_name",state);
//				    editor.putString("cell_phone",cellphone);
//				    editor.putString("home_phone",homephone);
//				    editor.putString("business_phone",businessphone);
//				    editor.putString("email_id",email);
//
//				    editor.commit();


                if (firstname.equals("")) {
                    invalid = true;
                    Toast.makeText(getApplicationContext(), "Enter your First Name",
                            Toast.LENGTH_SHORT).show();
                } else if (lastname.equals("")) {
                    invalid = true;
                    Toast.makeText(getApplicationContext(),
                            "Please enter your Last Name", Toast.LENGTH_SHORT)
                            .show();
                } else if (state.equals("")) {
                    invalid = true;
                    Toast.makeText(getApplicationContext(),
                            "Please enter your State", Toast.LENGTH_SHORT)
                            .show();

                } else if (cellphone.equals("")) {
                    invalid = true;
                    Toast.makeText(getApplicationContext(),
                            "Please enter your Cellphone", Toast.LENGTH_SHORT)
                            .show();
                }  else if (email.equals("")) {
                    invalid = true;
                    Toast.makeText(getApplicationContext(),
                            "Please enter your city", Toast.LENGTH_SHORT).show();
                }
                if (invalid == false) {
                    fields.put("user_name", user);
                    fields.put("post_date", strDate);
                    fields.put("first_name", firstname);
                    fields.put("last_name", lastname);
                    fields.put("state_name", state);
                    fields.put("cell_phone", cellphone);
                    fields.put("home_phone", homephone);
                    fields.put("business_phone", businessphone);
                    fields.put("email_id", email);


                    QBCustomObject qbcustomobject = new QBCustomObject();
                    qbcustomobject.setClassName("EmergencyContact");
                    qbcustomobject.setFields(fields);
                    QBCustomObjects.createObject(qbcustomobject, new QBCallbackImpl() {
                        @Override
                        public void onComplete(Result result) {
                            if (result.isSuccess()) {
                                QBCustomObjectResult qbCustomObjectResult = (QBCustomObjectResult) result;
                                QBCustomObject qbcustomobject = qbCustomObjectResult.getCustomObject();
                                Log.d("New record: ", qbcustomobject.toString());
                                Intent sub = new Intent(EmergencyInfo.this, Sixth.class);
                                startActivity(sub);

                            } else {
                                Log.e("Errors", result.getErrors().toString());
                            }
                        }


                    });
                }

            }
        });

    }



}

