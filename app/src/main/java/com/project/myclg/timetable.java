package com.project.myclg;


import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
//import com.example.sakshipc.profilemain.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static android.provider.Settings.System.AIRPLANE_MODE_ON;


/**
 * A simple {@link Fragment} subclass.
 */
public class timetable extends Fragment {
    ImageView img;
    Uri uri;
    DatabaseReference mDatabase;
    String branch,year;
    String url;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    int n=0;
    public timetable() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View v= inflater.inflate(R.layout.fragment_timetable, container, false);
        firebaseAuth= FirebaseAuth.getInstance();
        // TouchImageView img;
        //img=(TouchImageView)findViewById(R.id.imageView2);
        //  img.setMaxZoom(2f);
        // img.setImageResource(R.mipmap.ic_launcher);
        ImageLoader imgLoder = AppController.getInstance().getImageLoader();
        NetworkImageView img = (NetworkImageView)v.findViewById(R.id.imageView2);
        // img=(ImageView)findViewById(R.id.imageView2);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        user=firebaseAuth.getCurrentUser();
        Log.e("value",mDatabase.toString());
        final String userId =user.getUid().toString();

        mDatabase.child("signup").child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren())
                {
                    Log.e("data", String.valueOf(ds));
                    branch=  String.valueOf(ds.getValue());
                    Log.e("child",branch);


                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        if(year=="First Year")
        { url="https://firebasestorage.googleapis.com/v0/b/profilemain-b8c23.appspot.com/o/Timetable%2FWhatsApp%20Image%202016-08-26%20at%2000.07.21%20(1).jpeg?alt=media&token=47311484-c004-4494-8408-a8831710c560";
            uri = Uri.fromFile(new File(url));}
        if(year=="Second Year")
        { url="https://firebasestorage.googleapis.com/v0/b/profilemain-b8c23.appspot.com/o/Timetable%2FWhatsApp%20Image%202016-08-26%20at%2000.07.21%20(1).jpeg?alt=media&token=47311484-c004-4494-8408-a8831710c560";
            uri = Uri.fromFile(new File(url));}
        if(year=="Third Year")
        { url="https://firebasestorage.googleapis.com/v0/b/profilemain-b8c23.appspot.com/o/Timetable%2FWhatsApp%20Image%202016-08-26%20at%2000.07.21%20(1).jpeg?alt=media&token=47311484-c004-4494-8408-a8831710c560";
            uri = Uri.fromFile(new File(url));}
        if(year=="Fourth Year")
        { url="https://firebasestorage.googleapis.com/v0/b/profilemain-b8c23.appspot.com/o/Timetable%2FWhatsApp%20Image%202016-08-26%20at%2000.07.21%20(1).jpeg?alt=media&token=47311484-c004-4494-8408-a8831710c560";
            uri = Uri.fromFile(new File(url));}
        else
        { Log.e("section","else");
            url="https://firebasestorage.googleapis.com/v0/b/profilemain-b8c23.appspot.com/o/Timetable%2FWhatsApp%20Image%202016-08-26%20at%2000.07.21%20(1).jpeg?alt=media&token=47311484-c004-4494-8408-a8831710c560";
            uri = Uri.fromFile(new File(url));
        }
        Log.e("here","staring");
        //Picasso.with(getApplicationContext()).load(url).placeholder(R.drawable.ic_media_play).into(img);


        return v;
    }
    static boolean isAirplaneModeOn(Context context) {
        ContentResolver contentResolver = context.getContentResolver();
        return Settings.System.getInt(contentResolver, AIRPLANE_MODE_ON, 0) != 0;

        // Inflate the layout for this fragment



    }

}
