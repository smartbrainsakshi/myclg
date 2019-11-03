package com.project.myclg;


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


//import com.example.sakshipc.profilemain.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;


/**
 * A simple {@link Fragment} subclass.
 */
public class view extends Fragment {
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
    NetworkImageView img;

    public view() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_view, container, false);
     final  String id=getArguments().getString("message");
        Log.e("id value",id);
         imgLoder = AppController.getInstance().getImageLoader();
         img = (NetworkImageView)v.findViewById(R.id.user_profile_photo);
   //     DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
       /* final DatabaseReference dinosaursRef = database.getInstance().getReference("FeedItem");
        dinosaursRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren())
                {

                    FeedItem feedItem= ds.getValue(FeedItem.class);

                  String key= feedItem.id;
                    Log.e("id2 value",id);

                    Log.e("url ", String.valueOf(ds));
                    Log.e("key2 value",key);
                    if(id.equals(key))
                    {
                        Log.e("url ","here");
                      String profile= feedItem.profilePic;
                        Log.e("profile",profile);
                        img.setImageUrl(profile, imgLoder);
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        */




        close=(ImageButton)v.findViewById(R.id.imageButton);
        close.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getActivity().finish();
                        Intent in = new Intent(getContext(), MainActivity.class);
                        startActivity(in);
                    }
                }
        );
databaseReference = FirebaseDatabase.getInstance().getReference();
        vbranch = (TextView)v.findViewById(R.id.vbranch);
        vyear = (TextView)v.findViewById(R.id.vyear);
        vemail = (TextView)v.findViewById(R.id.vemail);
        vuser = (TextView)v.findViewById(R.id.user);
        String data;
        data=id;
        Log.e("user",data);
        databaseReference.child("signup").child(data).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    n++;


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
                    if (n == 6)
                        img.setImageUrl(Branch, imgLoder);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return v;




    }

}
