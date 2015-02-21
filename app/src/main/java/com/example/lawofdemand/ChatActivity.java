package com.example.lawofdemand;


import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
//import android.support.v7.app.ActionBarActivity;
import android.os.StrictMode;
import android.util.Base64;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lawofdemand.adapters.ChatsArrayAdapter;
import com.example.lawofdemand.models.ChatData;
import com.quickblox.core.QBCallbackImpl;
import com.quickblox.core.result.Result;
import com.quickblox.customobjects.QBCustomObjects;
import com.quickblox.customobjects.model.QBCustomObject;
import com.quickblox.customobjects.result.QBCustomObjectResult;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;


public class ChatActivity extends Activity implements Runnable
{
    private static final String LOG_TAG = "ActionBarActivity";
    private ArrayList<ChatData> chatDataArrayList;
    private ChatsArrayAdapter chatsArrayAdapter;
    private ListView listView;
    private TextView headerchat;
    String resp;
    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        listView = (ListView) findViewById(R.id.listView);
      //  headerchat=(TextView) findViewById(R.id.headerchat);
       /* Typeface tf = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/Jelloween - Machinato ExtraLight.ttf");
        headerchat.setTypeface(tf);*/
        chatDataArrayList = new ArrayList<ChatData>();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

          run();


    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(this, Sixth.class);
        startActivity(intent);
    }

/*    private String loadChatFile() throws IOException
    {
        InputStream inputStream = getResources().openRawResource(R.raw.chat_data);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String receiveString;
        StringBuilder stringBuilder = new StringBuilder();

        while ((receiveString = bufferedReader.readLine()) != null )
        {
            stringBuilder.append(receiveString);
            stringBuilder.append("\n");
        }

        bufferedReader.close();
        inputStreamReader.close();
        inputStream.close();


        return stringBuilder.toString();
    }*/


    @TargetApi(Build.VERSION_CODES.FROYO)
    @Override
    public void run() {
        try {
        String str1 = "https://api.avvo.com/api/1/lawyers/search.json?page=1";


        String credentials = "joshua.cleetus@gmail.com" + ":" + "gotnerds";

        HttpUriRequest request = new HttpGet(str1); // Or HttpPost(), depends on your needs
        String base64EncodedCredentials = Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
        request.addHeader("Authorization", "Basic " + base64EncodedCredentials);

        HttpClient httpclient = new DefaultHttpClient();


        HttpResponse response = null;

            response = httpclient.execute(request);

//this is the login response, logged so you can see it - just use the second part of the log for anything you want to do with the data
            resp = EntityUtils.toString(response.getEntity());


            InputStream inputStream = new ByteArrayInputStream(resp.getBytes());

            //getResources().openRawResource(R.raw.chat_data);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String receiveString;
            StringBuilder stringBuilder = new StringBuilder();

            while ((receiveString = bufferedReader.readLine()) != null )
            {
                stringBuilder.append(receiveString);
                stringBuilder.append("\n");
            }

            Log.d("String Builder", String.valueOf(stringBuilder));


            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();


            try
            {


                String chatFileData = stringBuilder.toString();
                JSONObject jsonData = new JSONObject(chatFileData);
                JSONArray jsonArray = jsonData.getJSONArray("results");

                for (int i = 0; i < jsonArray.length(); i++)
                {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    ChatData chatData = new ChatData(jsonObject);

                    chatDataArrayList.add(chatData);

                }
            }
            catch (Exception e)
            {
                Log.w(LOG_TAG, e);
            }

            chatsArrayAdapter = new ChatsArrayAdapter(this, chatDataArrayList);
            listView.setAdapter(chatsArrayAdapter);






        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
