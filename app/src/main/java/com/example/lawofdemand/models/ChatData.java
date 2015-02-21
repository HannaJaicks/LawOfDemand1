package com.example.lawofdemand.models;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;


public class ChatData{
    private static final String LOG_TAG = "ChatData";

    public int userID;
    public String username;
    public String avatarURL;
    public String phone;
    public String address;
    public Bitmap bitmap;

    public ChatData(JSONObject jsonObject)
    {
        if (jsonObject != null)
        {
            try
            {
                userID = jsonObject.getInt("id");
                username = jsonObject.getString("name");
                avatarURL = jsonObject.getString("image_url");
                phone = jsonObject.getString("phone");
                address= jsonObject.getString("address");

            }
            catch (JSONException e)
            {
                Log.w(LOG_TAG, e);
            }
        }
    }



}
