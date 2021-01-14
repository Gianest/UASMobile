package com.example.uasmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class detailsAct2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details2);
        ImageView imgPhoto;
        TextView tvName, tvDetail;
        imgPhoto = findViewById(R.id.img_item_photo);
        tvName = findViewById(R.id.tv_item_name);
        tvDetail = findViewById(R.id.tv_item_detail);


        Intent intent = getIntent();
        String foto = intent.getStringExtra("photo");
        String name = intent.getStringExtra("nama");
        String detail = intent.getStringExtra("detail");

        setTitle(name);

        Glide.with(getApplicationContext())
                .load(foto)
                .override(Target.SIZE_ORIGINAL)
                .into(imgPhoto);
        tvName.setText(name);
        tvDetail.setText(detail);


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
