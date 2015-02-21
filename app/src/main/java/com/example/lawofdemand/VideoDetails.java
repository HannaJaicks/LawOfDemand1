package com.example.lawofdemand;

import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.quickblox.core.QBCallbackImpl;
import com.quickblox.core.result.Result;
import com.quickblox.customobjects.QBCustomObjectsFiles;
import com.quickblox.customobjects.model.QBCustomObject;
import com.quickblox.customobjects.model.QBCustomObjectFileField;
import com.quickblox.customobjects.result.QBCOFileUploadResult;

public class VideoDetails extends Activity {
	 private Camera myCamera;
	    //private MyCameraSurfaceView myCameraSurfaceView;
	    private MediaRecorder mediaRecorder;
private Button btnvideo;
    private Button btngallery, upload;
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private Uri fileUri;
    static final int REQUEST_VIDEO_CAPTURE = 1;
    int requestCode;
    int resultCode;
    int ACTIVITY_SELECT_IMAGE;
    File vfile,f;
    String filePath;
    File mediaFile;
    Uri videoUri;
    String videoPath;

    int ACTION_TAKE_VIDEO = 1;
    Intent intent;
	    Button btnRecordvideo,btnBack,btnGallery;
	    SurfaceHolder surfaceHolder;
	    boolean recording = false;
   VideoView videoImage;
    private static final int VIDEO_CAPTURE = 101;
    private static final int RESULT_LOAD_VIDEO=100;
    File file;

    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.video_page);

            btnvideo=(Button)findViewById(R.id.btnvideo1);
            btngallery=(Button)findViewById(R.id.btngallery);
           videoImage=(VideoView) findViewById(R.id.videoview);
            upload=(Button) findViewById(R.id.btnupload1);

            btnvideo.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {

                    mediaFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath()
                                    + "/myvideo.mp4");

                    Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);

                    videoUri = Uri.fromFile(mediaFile);

                    intent.putExtra(MediaStore.EXTRA_OUTPUT, videoUri);
                    startActivityForResult(intent, VIDEO_CAPTURE);
                   /* Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                    startActivityForResult(takeVideoIntent, ACTION_TAKE_VIDEO);*/
                  /*  Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                    if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
                        startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
                    }*/

                }
                });

            btngallery.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent i=  new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    i.setType("*/*");
                    startActivityForResult(i, RESULT_LOAD_VIDEO);
/*
                    Intent i = new Intent(Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(i, ACTIVITY_SELECT_IMAGE);*/



                }
            });

            upload.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (videoPath != null) {
                        file = new File(videoPath);
                        //Log.w("camera picture", String.valueOf(bitmap));
                        Log.w("path of image to upload......******************.........", videoPath + "");
                        // cfile=new File(path);
                        QBCustomObject qbCustomObject1 = new QBCustomObject("Records", "54dd4885535c12527e013a08");
                        QBCustomObjectsFiles.uploadFile(file, qbCustomObject1, "Video", new QBCallbackImpl() {
                                    @Override
                                    public void onComplete(Result result) {
                                        if (result.isSuccess()) {
                                            QBCustomObjectFileField customObjectFileField = ((QBCOFileUploadResult) result).getCustomObjectFileField();
                                            Log.i("Image Uploaded", ">>>upload response:" + customObjectFileField.getFileName() + " " + customObjectFileField.getFileId() + " " +
                                                    customObjectFileField.getContentType());
                                        } else {
                                            Log.d("Error", result.getErrors().toString());

                                        }
                                    }
                                }

                        );


                    }
                    // vfile=new File(filePath);
                    else{
                        Log.d("File to upload", String.valueOf(mediaFile));
                    QBCustomObject qbCustomObject = new QBCustomObject("Records", "54dd4885535c12527e013a08");
                    QBCustomObjectsFiles.uploadFile(mediaFile, qbCustomObject, "Video", new QBCallbackImpl() {
                                @Override
                                public void onComplete(Result result) {
                                    if (result.isSuccess()) {
                                        QBCustomObjectFileField customObjectFileField = ((QBCOFileUploadResult) result).getCustomObjectFileField();
                                        Log.i("Video Uploaded", ">>>upload response:" + customObjectFileField.getFileName() + " " + customObjectFileField.getFileId() + " " +
                                                customObjectFileField.getContentType());
                                    } else {
                                        Log.d("Error", result.getErrors().toString());

                                    }
                                }
                            }

                    );
                }
                }
            });



        }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK)   {
            if (requestCode == VIDEO_CAPTURE) {
                Toast.makeText(this, "Video saved to:\n" +
                        data.getData(), Toast.LENGTH_LONG).show();
                videoImage.setVideoURI(videoUri);
                videoImage.setMediaController(new MediaController(this));
                videoImage.requestFocus();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Video recording cancelled.",
                        Toast.LENGTH_LONG).show();
            } else if(requestCode == RESULT_LOAD_VIDEO)
            {
                Uri selectedVideo = data.getData();
                String[] filePath = {MediaStore.Images.Media.DATA};
                Cursor c = getContentResolver().query(selectedVideo, filePath, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                videoPath = c.getString(columnIndex);
                c.close();
                videoImage.setVideoURI(videoUri);
                videoImage.setMediaController(new MediaController(this));
                videoImage.requestFocus();
            }

            else {
                Toast.makeText(this, "Failed to record video",
                        Toast.LENGTH_LONG).show();
            }


        }





    }

}
    /*    if (resultCode == RESULT_OK) {
            if (requestCode == ACTION_TAKE_VIDEO) {
              *//*  f = new File(Environment.getExternalStorageDirectory().toString());
                for (File temp : f.listFiles()) {
                    if (temp.getName().equals("temp.mp4")) {
                        f = temp;
                        break;
                    }
                }*//*


try {

    Uri mVideoUri = data.getData();
    videoImage.setVideoURI(mVideoUri);
    videoImage.setMediaController(new MediaController(this));
    videoImage.requestFocus();

   // Uri videoUri = data.getData();
    filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/VideoRecording.mp4";
    Log.d("LOGCAT", "Video path is: " + filePath);
    // BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
    f=new File(filePath);
    videoImage.setVideoURI(mVideoUri);
    //videoImage.start();
}
catch(Exception e){
    e.printStackTrace();
}

            }
        }*/

	      /* btnGallery=(Button)findViewById(R.id.btngallery);
	      
	        myCamera = getCameraInstance();
	        if(myCamera == null){
	            Toast.makeText(VideoDetails.this,  "Fail to get Camera" ,Toast.LENGTH_LONG).show();
	        }

	        myCameraSurfaceView = new MyCameraSurfaceView(this, myCamera);
	        FrameLayout myCameraPreview = (FrameLayout)findViewById(R.id.videoview);
	        myCameraPreview.addView(myCameraSurfaceView);

	        
	        btnRecordvideo = (Button)findViewById(R.id.btnvideo1);
	        //btnRecordvideo.setOnClickListener(myButtonOnClickListener);
            btnRecordvideo.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
}
	    Button.OnClickListener myButtonOnClickListener = new Button.OnClickListener(){

	        @Override
	        public void onClick(View v) {
	            // TODO Auto-generated method stub
	            if(recording){

	                mediaRecorder.stop();  
	                releaseMediaRecorder(); 
	              
	                finish();
}else{
    
   
    releaseCamera();
    
    if(!prepareMediaRecorder()){
        Toast.makeText(VideoDetails.this, "Fail in prepareMediaRecorder()!\n - Ended -", Toast.LENGTH_LONG).show();
        finish();
    }
    
    mediaRecorder.start();
    recording = true;
    btnRecordvideo.setText("STOP");
}
}};
private Camera getCameraInstance(){
    // TODO Auto-generated method stub
    Camera c = null;
    try {
        c = Camera.open();
    }
    catch (Exception e){
      
    }
    return c;
}

private boolean prepareMediaRecorder(){
    myCamera = getCameraInstance();
    mediaRecorder = new MediaRecorder();

    myCamera.unlock();
    mediaRecorder.setCamera(myCamera);

    mediaRecorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);
    mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);

    mediaRecorder.setProfile(CamcorderProfile.get(CamcorderProfile.QUALITY_HIGH));

    mediaRecorder.setOutputFile("/sdcard/myvideo.mp4");
    mediaRecorder.setMaxDuration(60000); 
    mediaRecorder.setMaxFileSize(5000000); 

    mediaRecorder.setPreviewDisplay(myCameraSurfaceView.getHolder().getSurface());

    try {
        mediaRecorder.prepare();
    } catch (IllegalStateException e) {
        releaseMediaRecorder();
        return false;
    } catch (IOException e) {
        releaseMediaRecorder();
        return false;
    }
    return true;
    
}

@Override
protected void onPause() {
    super.onPause();
    releaseMediaRecorder();      
    releaseCamera();            }

private void releaseMediaRecorder(){
    if (mediaRecorder != null) {
        mediaRecorder.reset();   
        mediaRecorder.release(); 
        mediaRecorder = null;
        myCamera.lock();          
    }
}

private void releaseCamera(){
    if (myCamera != null){
        myCamera.release();       
        myCamera = null;
    }
}

public class MyCameraSurfaceView extends SurfaceView implements SurfaceHolder.Callback{

    private SurfaceHolder mHolder;
    private Camera mCamera;
    //SurfaceView mSurfaceView;
    
    public MyCameraSurfaceView(Context context, Camera camera) {
        super(context);
        mCamera = camera;

       
        mHolder = getHolder();
        mHolder.addCallback(this);
       // mHolder = mSurfaceView.getHolder();
    
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int weight,
            int height) {
      

        if (mHolder.getSurface() == null){
          
          return;
        }

      
        try {
            mCamera.stopPreview();
        } catch (Exception e){
         
        }

       
        try {
            mCamera.setPreviewDisplay(mHolder);
            mCamera.startPreview();

        } catch (Exception e){
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // TODO Auto-generated method stub
    
        try {
            mCamera.setPreviewDisplay(holder);
            mCamera.startPreview();
        } catch (IOException e) {
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // TODO Auto-generated method stub
        
    }*/

