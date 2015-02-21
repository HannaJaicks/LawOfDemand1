package com.example.lawofdemand;

import java.util.List;

import org.xbill.DNS.SIG0;

import com.quickblox.auth.QBAuth;
import com.quickblox.auth.model.QBSession;
import com.quickblox.core.QBEntityCallbackImpl;


import com.quickblox.users.QBUsers;
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

public class SignUpPage extends Activity {
	EditText Fullname,Username,Emailid,Password;
	Button signup,btnBack;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sign_up);
	
		Fullname=(EditText)findViewById(R.id.editfullname);
		Username=(EditText)findViewById(R.id.editusername);
		Emailid=(EditText)findViewById(R.id.editemail);
		Password=(EditText)findViewById(R.id.editpassword);
		signup=(Button)findViewById(R.id.btnsignup);
		btnBack=(Button)findViewById(R.id.btnback);
		signup.setOnClickListener(new OnClickListener() {

			
			@Override
			public void onClick(View v) {
				
				 QBUser qbUser = new QBUser();
	                qbUser.setLogin(Username.getText().toString());
	                qbUser.setPassword(Password.getText().toString());
				    qbUser.setFullName(Fullname.getText().toString());
				    qbUser.setEmail(Emailid.getText().toString());
				//    SharedPreferences   sharedpreferences = getSharedPreferences("User", Context.MODE_WORLD_READABLE);
				//    sharedpreferences.edit().putString("Login",Username.getText().toString()).commit();
				    SharedPreferences pref = getApplicationContext().getSharedPreferences("User", Context.MODE_WORLD_READABLE); 
				    Editor editor = pref.edit();
				    editor.putString("Login",Username.getText().toString());
				    editor.commit();
				    Intent m = new Intent(SignUpPage.this,Selection.class);
			    	 m.putExtra("Full Name",Fullname.getText().toString() );
			    	 m.putExtra("Login", Username.getText().toString());
			    	 m.putExtra("Email", Emailid.getText().toString());
			    	 m.putExtra("Password", Password.getText().toString());

			    	 startActivity(m);


				    
			/*	    QBUsers.signUpSignInTask(qbUser, new QBEntityCallbackImpl<QBUser>() {
	                    @Override
	                    public void onSuccess(QBUser qbUser, Bundle bundle) {
	                       Log.i("jjjjjjjjjjjjj", "fffffffffffffffffffffff");
	                       startGetAllUsersActivity();
	                      
	                    }


						@Override
	                    public void onError(List<String> strings) {
	                       Log.i("mmmmmmmmmmmmmmmmmmmm", "lllllllllllllllll");
	                    }
	                });*/
			/*	String fullname=Fullname.getText().toString();
				
				String username=Username.getText().toString();
			
				String emailid=Emailid.getText().toString();
				String password=Password.getText().toString();
				Fullname.setText("");
				Username.setText("");
				Emailid.setText("");
				Password.setText("");
				final QBUser user = new QBUser("username", "password");
				 
				QBUsers.signUp(user, new QBEntityCallbackImpl<QBUser>() {
				    @Override
				    public void onSuccess(QBUser user, Bundle args) {
				        Log.i("username","lllllllllllllllllllllllllllll");				
				            }
				 
				    @Override
				    public void onError(List<String> errors) {
				       // error
				    }*/
               /* Intent k = new Intent(SignUpPage.this,Selection.class);
                startActivity(k);*/
			}
				});
		btnBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			Intent back=new Intent(SignUpPage.this,SplashPage.class);	
			startActivity(back);
			}
		});
				
			}

		
	

 /*   private void startGetAllUsersActivity() {
		// TODO Auto-generated method stub
    	 Intent m = new Intent(SecondClass.this,Fourth.class);
    	 m.putExtra("Full Name",Fullname.getText().toString() );
    	 m.putExtra("Login", Username.getText().toString());
    	 m.putExtra("Email", Emailid.getText().toString());
    	 m.putExtra("Password", Password.getText().toString());
    	 startActivity(m);
         
        
	}*/
	
}