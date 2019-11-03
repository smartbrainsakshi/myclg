package com.project.myclg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
//import com.example.sakshipc.profilemain.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Show extends AppCompatActivity {
    TextView vemail;
    TextView vbranch;
    TextView vyear;
    TextView vuser;
    int n;
    FirebaseDatabase database;
    String url;
    Button btn;
    ImageButton close;
    DatabaseReference databaseReference;
    ImageLoader imgLoder;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        Intent i = getIntent();
        final String id = i.getStringExtra("message");

        //final  String id=getIntent("message");
        Log.e("id value",id);
        imgLoder = AppController.getInstance().getImageLoader();
        img = (ImageView)findViewById(R.id.user_profile_photo);

        close=(ImageButton)findViewById(R.id.imageButton);
        close.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();

                        Intent in = new Intent(Show.this, MainActivity.class);
                        startActivity(in);
                    }
                }
        );
        databaseReference = FirebaseDatabase.getInstance().getReference();
        vbranch = (TextView)findViewById(R.id.vbranch);
        vyear = (TextView)findViewById(R.id.vyear);
        vemail = (TextView)findViewById(R.id.vemail);
        vuser = (TextView)findViewById(R.id.user);
        String data;
        data=id;
        Log.e("user",data);
        databaseReference.child("signup").child(data).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    n++;
Log.e("datavalue",dataSnapshot.getValue().toString());

                    Log.e("data", String.valueOf(ds));
                    String Branch = String.valueOf(ds.getValue());
                    // year=  String.valueOf(ds.getValue(signup.class).getYear());
                    if (n == 1)
                        vbranch.setText(Branch);
                    if (n == 7)
                        vemail.setText(Branch);
                    if (n == 3)
                        vuser.setText(Branch);


                    if (n == 8)
                        vyear.setText(Branch);
                    if (n == 6){  Picasso.with(Show.this).load(Branch)
                            .into(img);}
                        //img.setImageUrl(Branch, imgLoder);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });





    }


    @Override
    public void onBackPressed()
    {
        finish();
    }
}
