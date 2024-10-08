package com.bih.nic.bsphcl.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bih.nic.bsphcl.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;
    Button btn_download,btn_start_Sur,btn_upload;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=findViewById(R.id.toolbar_main);
        toolbar.setTitle("Survay");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        btn_download=findViewById(R.id.btn_download);
        btn_start_Sur=findViewById(R.id.btn_start_Sur);
        btn_upload=findViewById(R.id.btn_upload);
        btn_download.setOnClickListener(this);
        btn_start_Sur.setOnClickListener(this);
        btn_upload.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        final int id=v.getId();
            if(id==R.id.btn_download) {
                Toast.makeText(this, "Under Construction", Toast.LENGTH_SHORT).show();
            } else if (id==R.id.btn_start_Sur) {
                startActivity(new Intent(MainActivity.this,ConsumerListActivity.class));
            }
            else if (id==R.id.btn_upload) {
                Toast.makeText(this, "Under Construction", Toast.LENGTH_SHORT).show();
            }
    }
}