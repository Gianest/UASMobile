package com.example.uasmobile;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

public class detailsAct extends AppCompatActivity {
    private dbHelper db;
    private int NOTIFICATION_ID = 1;
    private static final String CHANNEL_ID = "channel_01";
    private static final CharSequence CHANNEL_NAME = "my channel";


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        final ImageView imgPhoto;
        final TextView tvName, tvDetail;

        Intent intent = getIntent();
        final String foto = intent.getStringExtra("photo");
        final String name = intent.getStringExtra("nama");
        final String detail = intent.getStringExtra("detail");
        imgPhoto = findViewById(R.id.img_item_photo);
        tvName = findViewById(R.id.tv_item_name);
        tvDetail = findViewById(R.id.tv_item_detail);


        setTitle(name);

        Glide.with(getApplicationContext())
                .load(foto)
                .override(Target.SIZE_ORIGINAL)
                .into(imgPhoto);
        tvName.setText(name);
        tvDetail.setText(detail);
        FloatingActionButton fab1 = findViewById(R.id.fab);

        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), name+ " disimpan di Favorites", Toast.LENGTH_SHORT).show();

                db = new dbHelper(getApplicationContext());
                db.addRecord(foto,detail,name);

                /*NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(detailsAct.this)
                        .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                        .setContentTitle(" disimpan di Favorites")
                        .setContentText("Notify");

                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(notificationId, mBuilder.build());*/

            }
        });

    }
    public void sendNotification(View view) {
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                .setContentTitle(" disimpan di Favorites")
                .setContentText(" disimpan di Favorites")
                .setSubText(" disimpan di Favorites")
                .setAutoCancel(true);

        Notification notification = mBuilder.build();
        if (mNotificationManager != null) {
            mNotificationManager.notify(NOTIFICATION_ID, notification);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(CHANNEL_NAME.toString());
            mBuilder.setChannelId(CHANNEL_ID);
            if (mNotificationManager != null) {
                mNotificationManager.createNotificationChannel(channel);
            }
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}