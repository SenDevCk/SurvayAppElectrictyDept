package com.bih.nic.bsphcl.activities;

import static androidx.core.content.FileProvider.getUriForFile;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.Toolbar;

import com.bih.nic.bsphcl.R;
import com.bih.nic.bsphcl.entities.MRUEntity;
import com.bih.nic.bsphcl.utilities.Utiilties;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class SurvayActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;
    AppCompatSpinner spiner_reading;
    String[] readings = {"--select--", "KWH", "KVH"};
    String reading = "";
    LinearLayout ll_meter_temp,ll_ser_wire;
    RadioGroup rg_met_temp,rdgp_ser_wire;
    ImageView take_met_img,take_serwire_img,take_mettemp_img;
    private static final int CAMERA_PIC = 1;
    Bitmap bitmap1,bitmap2,bitmap3;
    byte[] imageData1=null,imageData2=null,imageData3=null;
    String latitude1, longitude1, gps_time1;
    String latitude2, longitude2, gps_time2;
    String latitude3, longitude3, gps_time3;
    ImageView met_img,ser_wire_img,met_temp_img;
    Button button_submit;
    MRUEntity mruEntity;
    TextView text_deleer_name,text_acc_no,text_con_id,text_book_no,text_address;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survay);
        toolbar = findViewById(R.id.toolbar_survay);
        toolbar.setTitle("Start Survay");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mruEntity= (MRUEntity) getIntent().getSerializableExtra("object");
        text_deleer_name=findViewById(R.id.text_deleer_name);
        text_deleer_name.setText(""+mruEntity.getCNAME());
        text_acc_no=findViewById(R.id.text_acc_no);
        text_acc_no.setText(""+mruEntity.getACT_NO());
        text_con_id=findViewById(R.id.text_con_id);
        text_con_id.setText(""+mruEntity.getCON_ID());
        text_book_no=findViewById(R.id.text_book_no);
        text_book_no.setText(""+mruEntity.getBOOK_NO());
        text_address=findViewById(R.id.text_address);
        text_address.setText(""+mruEntity.getBILL_ADDR1());
        button_submit=findViewById(R.id.button_submit);
        button_submit.setOnClickListener(this);
        ll_meter_temp=findViewById(R.id.ll_meter_temp);
        ll_ser_wire=findViewById(R.id.ll_ser_wire);
        ll_meter_temp.setVisibility(View.GONE);
        ll_ser_wire.setVisibility(View.GONE);
        met_img=findViewById(R.id.met_img);
        ser_wire_img=findViewById(R.id.ser_wire_img);
        met_temp_img=findViewById(R.id.met_temp_img);
        take_met_img=findViewById(R.id.take_met_img);
        take_met_img.setOnClickListener(this);
        take_serwire_img=findViewById(R.id.take_serwire_img);
        take_serwire_img.setOnClickListener(this);
        take_mettemp_img=findViewById(R.id.take_mettemp_img);
        take_mettemp_img.setOnClickListener(this);
        spiner_reading = findViewById(R.id.spiner_reading);
        spiner_reading.setAdapter(new ArrayAdapter<String>(SurvayActivity.this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item, Arrays.asList(readings)));
        spiner_reading.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position==0){
                    reading="";
                }else{
                    reading=parent.getItemAtPosition(position).toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        rg_met_temp=findViewById(R.id.rg_met_temp);
        rg_met_temp.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId==R.id.met_temp_yes){
                ll_meter_temp.setVisibility(View.VISIBLE);
            }
            else if (checkedId==R.id.met_temp_no){
                ll_meter_temp.setVisibility(View.GONE);
                met_temp_img.setImageDrawable(getDrawable(R.drawable.noimage));
                bitmap3=null;
                imageData3=null;
            }
        });
        rdgp_ser_wire=findViewById(R.id.rdgp_ser_wire);
        rdgp_ser_wire.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId==R.id.wire_dam_yes){
                ll_ser_wire.setVisibility(View.VISIBLE);
            }
            else if (checkedId==R.id.wire_dam_no){
                ll_ser_wire.setVisibility(View.GONE);
                ser_wire_img.setImageDrawable(getDrawable(R.drawable.noimage));
                bitmap2=null;
                imageData2=null;
            }
        });
    }


    @Override
    public void onClick(View v) {
        final long id_btn=v.getId();
        if (id_btn==R.id.take_met_img){
            Intent iCamera = new Intent(SurvayActivity.this, CameraActivity.class);
            iCamera.putExtra("KEY_PIC", "1");
            startActivityForResult(iCamera, CAMERA_PIC);

        }else if (id_btn==R.id.take_serwire_img){
            Intent iCamera = new Intent(SurvayActivity.this, CameraActivity.class);
            iCamera.putExtra("KEY_PIC", "2");
            startActivityForResult(iCamera, CAMERA_PIC);
        }else if (id_btn==R.id.take_mettemp_img){
            Intent iCamera = new Intent(SurvayActivity.this, CameraActivity.class);
            iCamera.putExtra("KEY_PIC", "3");
            startActivityForResult(iCamera, CAMERA_PIC);
        }else if (id_btn==R.id.button_submit){
            Toast.makeText(this, "Submitted !", Toast.LENGTH_SHORT).show();
            finish();
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CAMERA_PIC:
                if (resultCode == Activity.RESULT_OK) {
                    byte[] imgData = data.getByteArrayExtra("CapturedImage");
                    switch (data.getIntExtra("KEY_PIC", 0)) {
                        case 1:
                            bitmap1 = BitmapFactory.decodeByteArray(imgData, 0, imgData.length);
                            met_img.setScaleType(ImageView.ScaleType.FIT_XY);
                            met_img.setImageBitmap(Utiilties.GenerateThumbnail(bitmap1, 700, 500));
                            imageData1 = imgData;
                            latitude1 = data.getStringExtra("Lat");
                            longitude1 = data.getStringExtra("Lng");
                            gps_time1 = data.getStringExtra("GPSTime");
                            break;
                        case 2:
                            bitmap2 = BitmapFactory.decodeByteArray(imgData, 0, imgData.length);
                            ser_wire_img.setScaleType(ImageView.ScaleType.FIT_XY);
                            ser_wire_img.setImageBitmap(Utiilties.GenerateThumbnail(bitmap2, 700, 500));
                            imageData2 = imgData;
                            latitude2 = data.getStringExtra("Lat");
                            longitude2 = data.getStringExtra("Lng");
                            gps_time2 = data.getStringExtra("GPSTime");
                            break;
                        case 3:
                            bitmap3 = BitmapFactory.decodeByteArray(imgData, 0, imgData.length);
                            met_temp_img.setScaleType(ImageView.ScaleType.FIT_XY);
                            met_temp_img.setImageBitmap(Utiilties.GenerateThumbnail(bitmap3, 700, 500));
                            imageData3 = imgData;
                            latitude3 = data.getStringExtra("Lat");
                            longitude3 = data.getStringExtra("Lng");
                            gps_time3 = data.getStringExtra("GPSTime");
                            break;
                    }
                }else{
                    Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
        }

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}