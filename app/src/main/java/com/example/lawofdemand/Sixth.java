package com.example.lawofdemand;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;


public class Sixth extends Activity implements OnClickListener {
	Button Emergency,btnBack;
    Button Legalnews;
    Button GuideLegalNews;
    Button FindLawyer;



	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub


		super.onCreate(savedInstanceState);
		setContentView(R.layout.sixth_page);
		btnBack=(Button)findViewById(R.id.btnapp);
        Legalnews=(Button) findViewById(R.id.btnlegal);
		Emergency=(Button)findViewById(R.id.btnemergency);
        GuideLegalNews=(Button) findViewById(R.id.btnguidelegal);
        FindLawyer=(Button) findViewById(R.id.btnfindalawyer);
		Emergency.setOnClickListener(this);
        Legalnews.setOnClickListener(this);
        GuideLegalNews.setOnClickListener(this);
        FindLawyer.setOnClickListener(this);


       /* Legalnews.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent legal= new Intent(Sixth.this,LegalNews.class);
                startActivity(legal);


            }
        });

        GuideLegalNews.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent guidelegal=new Intent(Sixth.this, GuideLegal.class);
                startActivity(guidelegal);

            }
        });*/
	}



	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.btnemergency:
			Intent emergency=new Intent(Sixth.this,Seventh.class);
			startActivity(emergency);
			break;
		case R.id.btnapp:
			Intent back= new Intent(Sixth.this,EmergencyInfo.class);
			startActivity(back);
			break;
        case R.id.btnlegal:
            Intent legal= new Intent(Sixth.this,LegalNews.class);
            startActivity(legal);
            break;
        case R.id.btnguidelegal:
            Intent guidelegal=new Intent(Sixth.this, GuideLegal.class);
            startActivity(guidelegal);
            break;
        case R.id.btnfindalawyer:
            Intent findlawyer=new Intent(Sixth.this, FindALawyer.class);
            startActivity(findlawyer);
            break;

        }
		
	}

   /* @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(this, Sixth.class);
        startActivity(intent);
    }*/
}

