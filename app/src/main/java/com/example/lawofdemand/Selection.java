package com.example.lawofdemand;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.quickblox.core.QBCallbackImpl;
import com.quickblox.core.QBEntityCallbackImpl;
import com.quickblox.core.helper.StringifyArrayList;
import com.quickblox.core.result.Result;
import com.quickblox.users.QBUsers;
import com.quickblox.users.model.QBUser;
import com.quickblox.users.result.QBUserResult;

import android.R.string;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Selection extends Activity  {
    Button btnindividual,btnlawyer,btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selection_page);
        btnindividual=(Button)findViewById(R.id.individual);
        btnlawyer=(Button)findViewById(R.id.lawyer);
        btnBack=(Button)findViewById(R.id.btnback);
        Intent intent = getIntent();
        String ffullname=intent.getStringExtra("Full Name");
        String flogin=intent.getStringExtra("Login");
        String femail=intent.getStringExtra("Email");
        String fpassword=intent.getStringExtra("Password");


        btnindividual.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent = getIntent();
                String ffullname=intent.getStringExtra("Full Name");
                String flogin=intent.getStringExtra("Login");
                String femail=intent.getStringExtra("Email");
                String fpassword=intent.getStringExtra("Password");



                String text = btnindividual.getTag().toString();
                Log.i("kkkkkkkkkkkkkkkkkkkkkk",""+text);
                StringifyArrayList<String> tags = new StringifyArrayList<String>();

                tags.add(text);


                QBUser qbUser = new QBUser();
                qbUser.setLogin(flogin);
                qbUser.setPassword(fpassword);
                qbUser.setFullName(ffullname);
                qbUser.setEmail(femail);
                qbUser.setTags(tags);
                Log.i("kkkkkkkkkkkkkkkkkkkkkkuuuuuuuuuuuuuuuuuuuu",""+ qbUser);
                QBUsers.signUpSignInTask(qbUser, new QBEntityCallbackImpl<QBUser>() {
                    @Override
                    public void onSuccess(QBUser qbUser, Bundle bundle) {
                        Log.i("yyyyyyyyyyyeeeeeessssssssss", "fffffffffffffffffffffff");
                        Intent kl = new Intent(Selection.this,EmergencyInfo.class);
                        startActivity(kl);

                    }


                    @Override
                    public void onError(List<String> strings) {
                        Log.i("noooooooooootttttttttt", "lllllllllllllllll");
                    }
                });

            }
        });

        btnlawyer.setOnClickListener(new OnClickListener() {



            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent intent = getIntent();
                String ffullname=intent.getStringExtra("Full Name");
                String flogin=intent.getStringExtra("Login");
                String femail=intent.getStringExtra("Email");
                String fpassword=intent.getStringExtra("Password");



                String text = btnlawyer.getTag().toString();
                Log.i("kkkkkkkkkkkkkkkkkkkkkk",""+text);
                StringifyArrayList<String> tags = new StringifyArrayList<String>();

                tags.add(text);


                QBUser qbUser = new QBUser();
                qbUser.setLogin(flogin);
                qbUser.setPassword(fpassword);
                qbUser.setFullName(ffullname);
                qbUser.setEmail(femail);
                qbUser.setTags(tags);
                Log.i("popopopo",""+ qbUser);
                QBUsers.signUpSignInTask(qbUser, new QBEntityCallbackImpl<QBUser>() {
                    @Override
                    public void onSuccess(QBUser qbUser, Bundle bundle) {
                        Log.i("oh yes", "ooooooooooooooooooooooooo");
                        Intent attorney=new Intent(Selection.this,Lawyer_details.class);
                        startActivity(attorney);

                    }


                    @Override
                    public void onError(List<String> strings) {
                        Log.i("oh no", "nnnooooooooo");
                    }
                });

            }
        });


    }

    private void startGetAllUsersActivity() {
        // TODO Auto-generated method stub




    }

}

