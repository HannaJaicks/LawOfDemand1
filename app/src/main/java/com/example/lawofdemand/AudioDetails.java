package com.example.lawofdemand;

import java.io.File;
import java.io.IOException;



import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.quickblox.core.QBCallbackImpl;
import com.quickblox.core.result.Result;
import com.quickblox.customobjects.QBCustomObjectsFiles;
import com.quickblox.customobjects.model.QBCustomObject;
import com.quickblox.customobjects.model.QBCustomObjectFileField;
import com.quickblox.customobjects.result.QBCOFileUploadResult;

public class AudioDetails extends Activity {
	MediaRecorder myrecorder;
	MediaPlayer myplayer;
	String outputFile=null;
	Button btrecord,btnStop,btnPlay,btnBack,upload;
	TextView textaudio;
    File file;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.audio_page);
		btnBack=(Button)findViewById(R.id.btnapp);
		textaudio=(TextView)findViewById(R.id.textaudio);
        upload= (Button) findViewById(R.id.btnupload);

		 outputFile = Environment.getExternalStorageDirectory(). getAbsolutePath() + "/AudioRecording.3gpp";
				
				  
				 
				       myrecorder = new MediaRecorder();
				 
				       myrecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
				
				       myrecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
				 
				       myrecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
				 
				       myrecorder.setOutputFile(outputFile);
				 
				        
				 
				       btrecord = (Button)findViewById(R.id.btnrecord);
				 
				       btrecord.setOnClickListener(new OnClickListener() {
				
				      

						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							start(v);
							
						}
				
				       });
				
				        
				 
				      btnStop = (Button)findViewById(R.id.btnstop);
				
				      btnStop.setOnClickListener(new OnClickListener() {
				
				          
				
				         @Override
				
				         public void onClick(View v) {
				 
				             // TODO Auto-generated method stub
				
				             stop(v);
				
				         }
				 
				       });
				
				        
				 
				       btnPlay = (Button)findViewById(R.id.btnplay);
				
				       btnPlay.setOnClickListener(new OnClickListener() {
				
				         @Override
				
				         public void onClick(View v) {
				
				            
				
				                 play(v);   
				
				         }
				
				       });

        upload.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                file=new File(outputFile);
                QBCustomObject qbCustomObject = new QBCustomObject("Records", "54dd4885535c12527e013a08");
                QBCustomObjectsFiles.uploadFile(file, qbCustomObject, "Audio", new

                                QBCallbackImpl() {
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
        });
				
				        
				
				    }
				
				  
			
				    public void start(View view){
				  try {
				
				           myrecorder.prepare();
				
				           myrecorder.start();
				
				        } catch (IllegalStateException e) {
				
				           e.printStackTrace();
				
				        } catch (IOException e) {
				
				         
				            e.printStackTrace();
				
				         }
				          
				
				  textaudio.setText("Recording Point: Recording");
				
				        btrecord.setEnabled(false);
				
				        btnStop.setEnabled(true);
				 
				         
				
				        Toast.makeText(getApplicationContext(), "Start recording...",Toast.LENGTH_SHORT).show();
				
				    }
				
				
				    public void stop(View view){
				
				        try {
				
				           myrecorder.stop();
				
				           myrecorder.release();
				
				           myrecorder  = null;
				
				            
				
				           btnStop.setEnabled(false);
				
				           btnPlay.setEnabled(true);
				
				           textaudio.setText("Recording Point: Stop recording");
				
				            
				
				           Toast.makeText(getApplicationContext(), "Stop recording...", Toast.LENGTH_SHORT).show();
				
				        } catch (IllegalStateException e) {
				 
				             //  it is called before start()
				             e.printStackTrace();
				
				        } catch (RuntimeException e) {
				 
				             // no valid audio/video data has been received
				
				             e.printStackTrace();
				
				        }
				
				    }
				
				    
				
				    public void play(View view) {
				
				        try{
				
				            myplayer = new MediaPlayer();
				
				            myplayer.setDataSource(outputFile);
				
				            myplayer.prepare();
				
				            myplayer.start();
				 
				             
				
				            btnPlay.setEnabled(false);
				 
				          
				 
				            textaudio.setText("Recording Point: Playing");
				
				            Toast.makeText(getApplicationContext(), "Start playing the recording...", Toast.LENGTH_SHORT).show();
				 
				        } catch (Exception e) {
				
				             // TODO Auto-generated catch block
				
				             e.printStackTrace();
				
				         }
				        btnBack.setOnClickListener(new OnClickListener() {
							
							@Override
							public void onClick(View arg0) {
								// TODO Auto-generated method stub
								Intent back=new Intent(AudioDetails.this,Eleventh.class);
								startActivity(back);
							}
						});
				
				    }
				
				     
				
				    
				   

}
