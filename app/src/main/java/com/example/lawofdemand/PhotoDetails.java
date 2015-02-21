package com.example.lawofdemand;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import com.quickblox.chat.utils.Utils;
import com.quickblox.content.QBContent;
import com.quickblox.content.model.QBFile;
import com.quickblox.core.QBCallbackImpl;
import com.quickblox.core.QBEntityCallbackImpl;
import com.quickblox.core.request.QBPagedRequestBuilder;
import com.quickblox.core.request.QBRequestGetBuilder;
import com.quickblox.core.result.Result;
import com.quickblox.customobjects.QBCustomObjects;
import com.quickblox.customobjects.QBCustomObjectsFiles;
import com.quickblox.customobjects.model.QBCustomObject;
import com.quickblox.customobjects.model.QBCustomObjectFileField;
import com.quickblox.customobjects.result.QBCOFileUploadResult;
import com.quickblox.customobjects.result.QBCustomObjectResult;
import com.quickblox.users.QBUsers;
import com.quickblox.users.model.QBUser;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class PhotoDetails extends Activity {
 ImageView viewImage;
 Button upload,btntake,btngallery,btnBac;
    String picturePath;
  //  public static final int GET_FROM_GALLERY = 3;
    Bitmap thumbnail;
    File file;
    File f;
    File cfile;
    private final boolean PUBLIC_ACCESS_TRUE = true;
    QBFile qbfile;

   Bitmap bitmap;
    Bitmap value;
    OutputStream outFile = null;
    String path;



 @Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.camera_page);
		viewImage=(ImageView)findViewById(R.id.viewImage);
		upload=(Button)findViewById(R.id.btnupload);
		btntake=(Button)findViewById(R.id.btntakecamera);
		btnBac=(Button)findViewById(R.id.btnapp);
		btngallery=(Button)findViewById(R.id.btncameragallery);




      btngallery.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 Intent intent = new   Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				 startActivityForResult(intent, 2);
			}
		});
		btntake.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File f = new File(android.os.Environment.getExternalStorageDirectory(), "temp.jpg");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                startActivityForResult(intent, 1);
				
			}
		});
		upload.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
                // TODO Auto-generated method stub

                //thumbnail=(BitmapFactory.decodeFile(picturePath));
                if (value==bitmap) {
                    Log.w("camera picture", String.valueOf(bitmap));
                    Log.w("path of image to upload......******************.........", path + "");
                   // cfile=new File(path);
                    QBCustomObject qbCustomObject1 = new QBCustomObject("Records", "54dd4885535c12527e013a08");
                    QBCustomObjectsFiles.uploadFile(f, qbCustomObject1, "Image", new QBCallbackImpl() {
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

                } else {
                    Log.w("path of image to upload......******************.........", picturePath + "");
                    HashMap<String, Object> fields = new HashMap<String, Object>();

                    SharedPreferences myprefs = getSharedPreferences("User", MODE_WORLD_READABLE);
                    String user = myprefs.getString("Login", null);


                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd:MMMM:yyyy HH:mm:ss a");
                    String strDate = sdf.format(c.getTime());
                 /*   QBCustomObject qbCustomObject1 = new QBCustomObject("Records", user);
                    qbCustomObject1.getId();
                    Log.d("ID", String.valueOf(qbCustomObject1.getId()));*/


                    file = new File(picturePath);

                    QBCustomObject qbCustomObject = new QBCustomObject("Records", "54dd4885535c12527e013a08");


                    QBCustomObjectsFiles.uploadFile(file, qbCustomObject, "Image", new QBCallbackImpl() {
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
            }
            });
		btnBac.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent toolkit=new Intent(PhotoDetails.this,Eleventh.class);
				startActivity(toolkit);
			}
		});
	}


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                f = new File(Environment.getExternalStorageDirectory().toString());
                for (File temp : f.listFiles()) {
                    if (temp.getName().equals("temp.jpg")) {
                        f = temp;
                        break;
                    }
                }
                try {

                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();

                    bitmap = BitmapFactory.decodeFile(f.getAbsolutePath(),bitmapOptions);

                    viewImage.setImageBitmap(bitmap);
                    value=bitmap;

                    path = android.os.Environment.getExternalStorageDirectory()+ File.separator+ "Phoenix" + File.separator + "default";
                //    f.delete();

                 File file = new File(path, String.valueOf(System.currentTimeMillis()) + ".jpg");
                   try {
                        outFile = new FileOutputStream(file);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 85, outFile);
                        outFile.flush();
                        outFile.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (requestCode == 2) {

                Uri selectedImage = data.getData();
                String[] filePath = { MediaStore.Images.Media.DATA };
                Cursor c = getContentResolver().query(selectedImage,filePath, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                picturePath = c.getString(columnIndex);
                c.close();
                thumbnail = (BitmapFactory.decodeFile(picturePath));
                Log.w("path of image from gallery......******************.........", picturePath+"");
                viewImage.setImageBitmap(thumbnail);
                value=thumbnail;

            }




     }
 }

 
}
