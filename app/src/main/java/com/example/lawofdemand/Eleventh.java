package com.example.lawofdemand;

/**
 * Created by jaicksninan on 1/20/15.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Eleventh extends Activity implements OnClickListener {
    Button audio,video,photo,btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eleventh_page);
        audio=(Button)findViewById(R.id.btnaudio);
        video=(Button)findViewById(R.id.btnvideo);
      //  btnBack=(Button)findViewById(R.id.btnapp);
        photo=(Button)findViewById(R.id.btnphoto);
        audio.setOnClickListener(this);
        video.setOnClickListener(this);
        photo.setOnClickListener(this);
//        btnBack.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnaudio:
                Intent audio=new Intent(Eleventh.this,AudioDetails.class);
                startActivity(audio);
                break;
            case R.id.btnvideo:
                Intent video = new Intent(Eleventh.this,VideoDetails.class);
                startActivity(video);
                break;
            case R.id.btnphoto:
                Intent photo = new Intent(Eleventh.this,PhotoDetails.class);
                startActivity(photo);
                break;


        }


    }

}
