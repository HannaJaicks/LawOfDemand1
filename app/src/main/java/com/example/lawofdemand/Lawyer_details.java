package com.example.lawofdemand;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.quickblox.core.QBCallbackImpl;
import com.quickblox.core.QBEntityCallbackImpl;
import com.quickblox.core.helper.StringifyArrayList;
import com.quickblox.core.result.Result;
import com.quickblox.customobjects.QBCustomObjects;
import com.quickblox.customobjects.model.QBCustomObject;
import com.quickblox.customobjects.result.QBCustomObjectResult;
import com.quickblox.users.QBUsers;
import com.quickblox.users.model.QBUser;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;


public class Lawyer_details extends Activity {
    EditText company, cphone, bphone, email, ad1, ad2, city,state,zipcode;
    Button lawyersubmit;
    private Spinner spinner;
    boolean invalid = false;
    private String[] speciality;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lawyer_details);
        company = (EditText) findViewById(R.id.edtcompany);
        cphone = (EditText) findViewById(R.id.edtcellphone);
        bphone = (EditText) findViewById(R.id.edtbphone);
        email = (EditText) findViewById(R.id.edtemail);
        ad1 = (EditText) findViewById(R.id.edtadr1);
        ad2 = (EditText) findViewById(R.id.edtadr2);
        city = (EditText) findViewById(R.id.edtcity);
        state = (EditText) findViewById(R.id.edtstate);
        zipcode = (EditText) findViewById(R.id.edtzip);
        spinner = (Spinner)findViewById(R.id.spinner);


        speciality = getResources().getStringArray(R.array.specialities);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, speciality);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });


        lawyersubmit = (Button) findViewById(R.id.btnsubmit);

        lawyersubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, Object> fields = new HashMap<String, Object>();
                SharedPreferences myprefs= getSharedPreferences("User", MODE_WORLD_READABLE);
                String user= myprefs.getString("Login", null);

                Calendar c = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("dd:MMMM:yyyy HH:mm:ss a");
                String strDate = sdf.format(c.getTime());

                String Company = company.getText().toString();
                String Cphone = cphone.getText().toString();
                String Bphone = bphone.getText().toString();
                String Email = email.getText().toString();
                String Ad1 = ad1.getText().toString();
                String Ad2 = ad2.getText().toString();
                String City = city.getText().toString();
                String State = state.getText().toString();
                String Zipcode = zipcode.getText().toString();



                if (Company.equals("")) {
                    invalid = true;
                    Toast.makeText(getApplicationContext(), "Enter your company name",
                            Toast.LENGTH_SHORT).show();
                } else if (Cphone.equals("")) {
                    invalid = true;
                    Toast.makeText(getApplicationContext(),
                            "Please enter your cellphone number", Toast.LENGTH_SHORT)
                            .show();
                }
               /* else if (Bphone.equals("")) {
                    invalid = true;
                    Toast.makeText(getApplicationContext(),
                            "Please enter your phone", Toast.LENGTH_SHORT)
                            .show();}*/

                else if (Email.equals("")) {
                    invalid = true;
                    Toast.makeText(getApplicationContext(),
                            "Please enter your email", Toast.LENGTH_SHORT)
                            .show();
                } else if (Ad1.equals("")) {
                    invalid = true;
                    Toast.makeText(getApplicationContext(),
                            "Please enter your Address", Toast.LENGTH_SHORT).show();
                }
            /*    else if (Ad2.equals("")) {
                    invalid = true;
                    Toast.makeText(getApplicationContext(),
                            "Please enter your Address", Toast.LENGTH_SHORT).show();
                } */
                else if (City.equals("")) {
                    invalid = true;
                    Toast.makeText(getApplicationContext(),
                            "Please enter your city", Toast.LENGTH_SHORT).show();
                } else if (State.equals("")) {
                    invalid = true;
                    Toast.makeText(getApplicationContext(),
                            "Please enter your State", Toast.LENGTH_SHORT).show();
                } else if (Zipcode.equals("")) {
                    invalid = true;
                    Toast.makeText(getApplicationContext(),
                            "Please enter your Zipcode", Toast.LENGTH_SHORT).show();
                }
                if (invalid == false) {
                    fields.put("user_name", user);
                    fields.put("post_date", strDate);
                    fields.put("Company", Company);
                    fields.put("Cellphone", Cphone);
                    fields.put("Business", Bphone);
                    fields.put("Email", Email);
                    fields.put("Address1", Ad1);
                    fields.put("Address2", Ad2);
                    fields.put("City", City);
                    fields.put("State", State);
                    fields.put("Zipcode", Zipcode);

                    QBCustomObject qbcustomobject = new QBCustomObject();
                    qbcustomobject.setClassName("Lawyer_details");
                    qbcustomobject.setFields(fields);
                    QBCustomObjects.createObject(qbcustomobject, new QBCallbackImpl() {
                        @Override
                        public void onComplete(Result result) {
                            if (result.isSuccess()) {
                                QBCustomObjectResult qbCustomObjectResult = (QBCustomObjectResult) result;
                                QBCustomObject qbcustomobject = qbCustomObjectResult.getCustomObject();
                                Log.d("New record: ", qbcustomobject.toString());

                                Intent lcase = new Intent(Lawyer_details.this,Case_options.class);

                                startActivity(lcase);

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















