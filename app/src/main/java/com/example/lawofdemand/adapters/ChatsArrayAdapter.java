package com.example.lawofdemand.adapters;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lawofdemand.ChatActivity;
import com.example.lawofdemand.R;
//import com.example.lawofdemand.RetrieveFeedTask;
import com.example.lawofdemand.models.ChatData;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class ChatsArrayAdapter extends ArrayAdapter<ChatData>
{

    public ChatsArrayAdapter(Context context, List<ChatData> objects)
    {
        super(context, 0, objects);
    }


    @TargetApi(Build.VERSION_CODES.CUPCAKE)
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ChatCell chatCell = new ChatCell();

        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.cell_chat, parent, false);

        chatCell.usernameTextView = (TextView) convertView.findViewById(R.id.usernameTextView);
      /*  Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/Jelloween - Machinato.ttf");
        chatCell.usernameTextView.setTypeface(tf);*/
        chatCell.messageTextView = (TextView) convertView.findViewById(R.id.messageTextView);
       /* Typeface tf1 = Typeface.createFromAsset(getContext().getAssets(), "fonts/Jelloween - Machinato Light.ttf");
        chatCell.messageTextView.setTypeface(tf1);*/
        chatCell.imageView=(ImageView) convertView.findViewById(R.id.photoImageView);
        chatCell.address=(TextView) convertView.findViewById(R.id.address);

        ChatData chatData = getItem(position);

        new ImageDownloader(chatCell.imageView, chatData.avatarURL).execute();

        chatCell.usernameTextView.setText(chatData.username);
        chatCell.imageView.setImageBitmap(chatData.bitmap);
        chatCell.messageTextView.setText(chatData.phone);
        chatCell.address.setText(chatData.address);

        return convertView;
    }

    private static class ChatCell
    {
        TextView usernameTextView;
        TextView messageTextView;
        ImageView imageView;
        TextView address;

    }
}

@TargetApi(Build.VERSION_CODES.CUPCAKE)
class ImageDownloader extends AsyncTask<String, Void, Bitmap> {
    ImageView bmImage;
    String avatarURL;


    public ImageDownloader(ImageView bmImage, String avatarURL) {
        this.bmImage = bmImage;
        this.avatarURL = avatarURL;

    }

    protected Bitmap doInBackground(String... urls) {
        String url =avatarURL;
        Bitmap mIcon = null;
        try {
            InputStream in = new java.net.URL(url).openStream();
            mIcon = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
        }
        return mIcon;
    }

    protected void onPostExecute(Bitmap result) {

try {
    Bitmap output = Bitmap.createBitmap(result.getWidth(),
            result.getHeight(), Bitmap.Config.ARGB_8888);
    Canvas canvas = new Canvas(output);

    final int color = 0xff424242;
    final Paint paint = new Paint();
    final Rect rect = new Rect(0, 0, result.getWidth(), result.getHeight());
    final RectF rectF = new RectF(rect);
    final float roundPx = 100;

    paint.setAntiAlias(true);
    canvas.drawARGB(0, 0, 0, 0);
    paint.setColor(color);
    canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

    paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
    canvas.drawBitmap(result, rect, rect, paint);
    bmImage.setImageBitmap(output);
}
catch(Exception e){
    e.printStackTrace();
}
    }
}
