package com.example.lawofdemand;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import com.quickblox.core.QBCallbackImpl;
import com.quickblox.core.result.Result;
import com.quickblox.customobjects.QBCustomObjects;
import com.quickblox.customobjects.model.QBCustomObject;
import com.quickblox.customobjects.result.QBCustomObjectResult;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class Nineth extends Activity implements OnClickListener{
	Button msgon1,msgon,btnAsubmit,btnAevidence;
	EditText edtmsg,edtfirstname,edtlastname,edtstate,edtcphone,edthphone,edtbphone,edtemail;
	LinearLayout line;
	int messegon,i=0,temp;
	

	boolean isSettingsClicked = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.accident_page);
		msgon1=(Button)findViewById(R.id.btntick);
		msgon=(Button)findViewById(R.id.btntick2);
		edtmsg=(EditText)findViewById(R.id.editcomment);

		edtfirstname=(EditText)findViewById(R.id.edtname);
		edtlastname=(EditText)findViewById(R.id.edtlast);
		edtstate=(EditText)findViewById(R.id.edtstate);
		edtcphone=(EditText)findViewById(R.id.edtphone);
		edthphone=(EditText)findViewById(R.id.edthphone);
		edtbphone=(EditText)findViewById(R.id.edtbphone);
		edtemail=(EditText)findViewById(R.id.edtemail);
		btnAsubmit=(Button)findViewById(R.id.btnasubmit);
		btnAevidence=(Button)findViewById(R.id.btnaaddevidence);
		
		line=(LinearLayout)findViewById(R.id.linear);
		btnAsubmit.setOnClickListener(this);
	btnAevidence.setOnClickListener(this);
		
	}
	
public void msgone(View v){
	if(isSettingsClicked == false){
		line.setVisibility(View.VISIBLE);
		isSettingsClicked =true;
		msgon.setBackgroundResource(R.drawable.msg_tick_box2);
		messegon=1;
	}
	 else if (isSettingsClicked == true) {
		 line.setVisibility(View.INVISIBLE);
		   isSettingsClicked = false;
		  msgon.setBackgroundResource(R.drawable.msg_tick_box);
	     
	     messegon=0;
			 }
	
}

//public void msgonee(View v){
//	if(isSettingsClicked == true){
//		
//		isSettingsClicked =true;
//		msgon1.setBackgroundResource(R.drawable.msg_tick_box2);
//	temp=1;
//	}
//}
@Override
public void onClick(View v) {
	// TODO Auto-generated method stub
	switch(v.getId()){
	case R.id.btnasubmit:

			
		
		
		HashMap<String,Object> field = new HashMap<String, Object>(); 
		SharedPreferences myprefs= getSharedPreferences("User", MODE_WORLD_READABLE);
		String user= myprefs.getString("Login", null);
	
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("dd:MMMM:yyyy HH:mm:ss a");
		String strDate = sdf.format(c.getTime());
		// TODO Auto-generated method stub
		String eafirstname=edtfirstname.getText().toString();
		String ealastname=edtlastname.getText().toString();
		String eastate=edtstate.getText().toString();
		String eacellphone=edtcphone.getText().toString();
		String eahomephone=edthphone.getText().toString();
		String eabusinessphone=edtbphone.getText().toString();
		String eaEditmsg=edtmsg.getText().toString();
		
		String eaemail=edtemail.getText().toString();
		field.put("user_login", user);
		field.put("post_date", strDate);
	field.put("contact1_first_name",eafirstname );
	field.put("contact1_last_name",ealastname);
	field.put("contact1_state_name",eastate);
	field.put("contact1_cell_phone",eacellphone);
	field.put("contact1_home_phone",eahomephone);
	field.put("contact1_business_phone", eabusinessphone);
	field.put("contact1_email", eaemail);
	field.put("summary", eaEditmsg)	;
		
QBCustomObject qbcustomobject = new QBCustomObject();
qbcustomobject.setClassName("EmergencyReports");
qbcustomobject.setFields(field);

		
QBCustomObjects.createObject(qbcustomobject, new QBCallbackImpl() {
    @Override
    public void onComplete(Result result) {
        if (result.isSuccess()) {
            QBCustomObjectResult qbCustomObjectResult = (QBCustomObjectResult) result;
            QBCustomObject qbcustomobject = qbCustomObjectResult.getCustomObject();
            Log.d("New record: ", qbcustomobject.toString());
                  } else {
            Log.e("Errors",result.getErrors().toString());
        }
    }


});
//Editor editor=myprefs.edit();
//editor.clear();
//editor.commit();
//		
		
break;

	case R.id.btnaaddevidence:
		Intent evidence = new Intent(Nineth.this,Eleventh.class);
		startActivity(evidence);
		}
		

}

}















