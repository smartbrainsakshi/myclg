package com.project.myclg;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

//import com.example.sakshipc.profilemain.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class selectsub extends AppCompatActivity {
ListView lv;

    String yr,br,sess,urlpass;
    String str;
    FirebaseDatabase database;
    private GradientBackgroundPainter gradientBackgroundPainter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectsub);
        View backgroundImage =  findViewById(R.id.root_view);



        final int[] drawables = new int[3];
        drawables[0] = R.drawable.gradient1;
        drawables[1] = R.drawable.gradient2;
        drawables[2] = R.drawable.gradient3;

        gradientBackgroundPainter = new GradientBackgroundPainter(backgroundImage, drawables);
        gradientBackgroundPainter.start();
        yr=getIntent().getStringExtra("year");
        br=getIntent().getStringExtra("branch");
        sess=getIntent().getStringExtra("session");
        final DatabaseReference dinosaursRef = database.getInstance().getReference("paper");
        Log.e("reference",dinosaursRef.toString());

//        Log.e(" yrvalue----->>",br);
      //  Log.e(" yrvalue----->>",yr);
        lv=(ListView)findViewById(R.id.listView);
        final ArrayList<String> ls = new ArrayList<String>();
        final ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                R.layout.newlayout, ls);

        dinosaursRef.child(br+"/"+yr).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot ds:dataSnapshot.getChildren())
                {
                    Log.e("value--------->>>","hello"+ds.getKey().toString());

                    Log.e("spinner","cleaned");
                    ls.add(ds.getKey().toString());
                    lv.setAdapter(dataAdapter);

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
//lv.setOnItemClickListener();
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int position, long id) {
                Log.e("click listenr", "onItemLongClick" );

                ls.get(position);
                String str=(String)((TextView)view).getText();
                AlertDialog.Builder builder = new AlertDialog.Builder(selectsub.this);
                builder.setMessage("Look at this dialog!")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //do things
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();

                dinosaursRef.child(br+"/"+yr+"/"+str).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot ds: dataSnapshot.getChildren())
                        {


                                urlpass=ds.getValue().toString();
                                Log.e("paperurl",urlpass);
                                Intent in=new Intent(selectsub.this,pdf.class);
                                in.putExtra("url",urlpass);
                                startActivity(in);






                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });





















                Log.v("long clicked","pos: " + position);

                return true;
            }
        });







        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("click listenr", "onItemClick" );
                ls.get(position);
                Log.e("click listenr","no" );
                 str=(String)((TextView)view).getText();
                Log.e("click view listenr", str );
                dinosaursRef.child(br+"/"+yr+"/"+str).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot ds: dataSnapshot.getChildren())
                        {
                            Log.e("click view listenr", ds.getValue().toString() );
                            Log.e("click view listenr", ds.getKey() );

                                urlpass=ds.getValue().toString();
                                Log.e("paperurl",urlpass);
                                Intent in=new Intent(selectsub.this,pdf.class);
                                in.putExtra("url",urlpass);
                                startActivity(in);






                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });


    }

    @Override protected void onDestroy() {
        super.onDestroy();
        gradientBackgroundPainter.stop();
    }

}

