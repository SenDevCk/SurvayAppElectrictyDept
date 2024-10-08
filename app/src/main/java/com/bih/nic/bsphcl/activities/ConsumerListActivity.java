package com.bih.nic.bsphcl.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bih.nic.bsphcl.R;
import com.bih.nic.bsphcl.adapter.ConsumerItemAdapter;
import com.bih.nic.bsphcl.entities.MRUEntity;

import java.util.Arrays;
import java.util.List;

public class ConsumerListActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recycler_list_consumer;
    TextView text_no_data_found;
    ConsumerItemAdapter consumerItemAdapter;
    List<MRUEntity> mruEntities;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumer_list);
        toolbar=findViewById(R.id.toolbar_con_list);
        toolbar.setTitle("Consumer List");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        text_no_data_found= findViewById(R.id.text_no_data_found);
        recycler_list_consumer=findViewById(R.id.recycler_list_consumer);
       // rel_search=findViewById(R.id.rel_search);
        //rel_search.setOnClickListener(v -> setUpDialog());

        //String from=getIntent().getStringExtra("from");

//        if (from.equalsIgnoreCase("adapter")){
//            toolbar_sel_topup.setTitle(""+getIntent().getStringExtra("bookno"));
//            mruEntities=new DataBaseHelper(ConsumerListActivity.this).getMRU(getIntent().getStringExtra("bookno"), CommonPref.getUserDetails(ConsumerListActivity.this).getUserID());
//        }
//        else if (from.equalsIgnoreCase("main")){
//            mruEntities=new DataBaseHelper(ConsumerListActivity.this).getMRU(CommonPref.getUserDetails(ConsumerListActivity.this).getUserID(),getIntent().getStringArrayExtra("mstring"));
//        }
//        else {
//            mruEntities=new DataBaseHelper(ConsumerListActivity.this).getMRU("",CommonPref.getUserDetails(ConsumerListActivity.this).getUserID());
//        }
        MRUEntity mruEntity=new MRUEntity();
        mruEntity.setACT_NO("nsdj4dasd4sa56d");
        mruEntity.setCNAME("Abhishek Kumar");
        mruEntity.setBOOK_NO("ADHPA");
        mruEntity.setCON_ID("52345455");
        mruEntity.setBILL_ADDR1("Rasulpur,dariyapur,Saran");
        MRUEntity mruEntity2=new MRUEntity();
        mruEntity2.setACT_NO("nsdj4s4dsa54a56d");
        mruEntity2.setCNAME("Ranjay Kumar");
        mruEntity2.setBOOK_NO("ADHPA");
        mruEntity2.setCON_ID("523454544");
        mruEntity2.setBILL_ADDR1("Srirampur,Garkha,Saran");
        mruEntities=Arrays.asList(new MRUEntity[]{mruEntity,mruEntity2});
        if (mruEntities!=null) {
            if (mruEntities.size() > 0) {
                text_no_data_found.setVisibility(View.GONE);
                consumerItemAdapter = new ConsumerItemAdapter(mruEntities, ConsumerListActivity.this);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(ConsumerListActivity.this);
                recycler_list_consumer.setLayoutManager(mLayoutManager);
                recycler_list_consumer.setItemAnimator(new DefaultItemAnimator());
                recycler_list_consumer.setAdapter(consumerItemAdapter);
            } else {
                text_no_data_found.setVisibility(View.VISIBLE);
                recycler_list_consumer.setVisibility(View.GONE);
            }
        }

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}