package com.example.lawofdemand;

/**
 * Created by jaicksninan on 1/20/15.
 */
import java.lang.reflect.Array;
import java.util.List;

import com.quickblox.core.QBEntityCallbackImpl;
import com.quickblox.core.helper.StringifyArrayList;
import com.quickblox.users.QBUsers;
import com.quickblox.users.model.QBUser;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class LoginPage extends Activity {

    EditText edtusername,edtpassword;
    Button btnsignin,btnBack;
    public static String tagid="Tags";
    StringifyArrayList<String> tag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin_page);



        edtusername=(EditText)findViewById(R.id.edtsignusername);
        edtpassword=(EditText)findViewById(R.id.edtsignpassword);
        btnsignin=(Button)findViewById(R.id.btnlogin);
        btnBack=(Button)findViewById(R.id.btnback);
        btnsignin.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                QBUser qbUser = new QBUser();
                qbUser.setLogin(edtusername.getText().toString());
                qbUser.setPassword(edtpassword.getText().toString());

                QBUsers.signIn(qbUser, new QBEntityCallbackImpl<QBUser>() {
                    @Override
                    public void onSuccess(QBUser qbUser, Bundle args) {
                        Log.i("Login Button", "Success");
                        tag = qbUser.getTags();
                        String t =    tag.getItemsAsString();
                        Log.d("Value of t", t);


                        try {
                            if ((tag.getItemsAsString()).equals("Lawyer")) {
                                Intent j = new Intent(LoginPage.this, Case_options.class);
                                startActivity(j);
                            } else if ((tag.getItemsAsString()).equals("Individual")) {
                                Intent l = new Intent(LoginPage.this, Sixth.class);
                                startActivity(l);
                            }
                        }
                        catch(Exception e)
                        {
                            e.printStackTrace();
                        }




		   	/*if(tagid==null)
			       {
		    	   Intent k = new Intent(LoginPage.this,Fourth.class);
		   		startActivity(k);
			       }
			       else */
          /*  if(tagid=="INDIVIDUAL"){


				       }
			       else if (tagid == "LAWYER")
				       {

				       }*/
                       // guit();
                      /*  Intent j=new Intent(LoginPage.this,Sixth.class);
			    	   startActivity(j);*/


                    }






                    @Override
                    public void onError(List<String> errors) {
                        // error
                        Log.i("NOOOOOOOTTTTTTTTTTTT", "NOOOOOOTTTTTTTT");
                        Toast.makeText(getApplicationContext(),
                                "Enter valid Email and password", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
   /*     btnBack.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent back=new Intent(LoginPage.this,SplashPage.class);
                startActivity(back);
            }
        });*/

    }



   /* private void guit() {

    }*/

}

