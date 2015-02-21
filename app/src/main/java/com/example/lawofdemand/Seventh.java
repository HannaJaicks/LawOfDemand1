package com.example.lawofdemand;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Seventh extends Activity implements OnClickListener {
	Button btnCall,btnArrested,btnTraffic,btnAccident;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.call_page);
		btnCall=(Button)findViewById(R.id.btndialno);
		btnArrested=(Button)findViewById(R.id.btnArrest);
		btnTraffic=(Button)findViewById(R.id.btntraffic);
		btnAccident=(Button)findViewById(R.id.btnaccident);
		btnCall.setOnClickListener(this);
		btnArrested.setOnClickListener(this);
		btnTraffic.setOnClickListener(this);
		btnAccident.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.btndialno:
			AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(this);
			alertDialog2.setTitle("Are you sure you want to call 911?");	
			alertDialog2.setPositiveButton("Call", new DialogInterface.OnClickListener(){

				@Override
				public void onClick(DialogInterface dialog, int id) {
					// TODO Auto-generated method stub
					/*Intent call =new Intent(Intent.ACTION_CALL);
					String dial="+18622239628"+btnCall.getText().toString();
					Log.i("opppppppppppppppppppppp",""+ dial);
					call.setData(Uri.parse(dial));
					startActivity(call);*/
                   /* String dial="tel:"+btnCall.getText().toString();
                    Log.i("call dialed",""+ dial);
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("+1" + "8622239628"));
                    startActivity(intent);*/

                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:+1"+Uri.encode("8622239628".trim())));
                    callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(callIntent);
				}
				
			});
		
			alertDialog2.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
				
				                public void onClick(DialogInterface dialog,int id) {
				
				                    // cancel the alert box and put a Toast to the user
				
				                    dialog.cancel();
				
				                
				
				                }
				
				            });
			AlertDialog alertdialog=alertDialog2.create();
			alertdialog.show();
         break;
		case R.id.btnArrest:
			Intent arrest = new Intent(Seventh.this,Eigth.class);
			startActivity(arrest);
			break;
		case R.id.btnaccident:
			Intent accident = new Intent(Seventh.this,Nineth.class);
			startActivity(accident);
			break;
		case R.id.btntraffic:
			Intent traffic=new Intent(Seventh.this,Tenth.class);
			startActivity(traffic);
			break;
			
         
		}
		
			
		
	}
	

}
