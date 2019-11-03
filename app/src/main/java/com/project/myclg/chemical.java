package com.project.myclg;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.media.MediaRouteProvider;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import static android.provider.Settings.System.AIRPLANE_MODE_ON;


import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
//import com.example.sakshipc.profilemain.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class chemical extends AppCompatActivity {
ImageView img;
    Uri uri;
    DatabaseReference mDatabase;
    String branch,year;
    String url;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    int n=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chemical);


        firebaseAuth= FirebaseAuth.getInstance();
       // TouchImageView img;
        //img=(TouchImageView)findViewById(R.id.imageView2);
      //  img.setMaxZoom(2f);
       // img.setImageResource(R.mipmap.ic_launcher);
        ImageLoader imgLoder = AppController.getInstance().getImageLoader();
        NetworkImageView img = (NetworkImageView)findViewById(R.id.imageView2);
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
                   // year=  String.valueOf(ds.getValue(signup.class).getYear());
                     Log.e("child",branch);


                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

     if(year=="First Year")
     { url="https://firebasestorage.googleapis.com/v0/b/profilemain-b8c23.appspot.com/o/Timetable%2FWhatsApp%20Image%202016-08-26%20at%2000.07.21%20(1).jpeg?alt=media&token=47311484-c004-4494-8408-a8831710c560";
      // uri = Uri.fromFile(new File(url));
     }
if(year=="Second Year")
{ url="https://firebasestorage.googleapis.com/v0/b/profilemain-b8c23.appspot.com/o/Timetable%2FWhatsApp%20Image%202016-08-26%20at%2000.07.21%20(1).jpeg?alt=media&token=47311484-c004-4494-8408-a8831710c560";
    //uri = Uri.fromFile(new File(url));
     }
        if(year=="Third Year")
        { url="https://firebasestorage.googleapis.com/v0/b/profilemain-b8c23.appspot.com/o/Timetable%2FWhatsApp%20Image%202016-08-26%20at%2000.07.21%20(1).jpeg?alt=media&token=47311484-c004-4494-8408-a8831710c560";
            // = Uri.fromFile(new File(url));
        }
        if(year=="Fourth Year")
        { url="https://firebasestorage.googleapis.com/v0/b/profilemain-b8c23.appspot.com/o/Timetable%2FWhatsApp%20Image%202016-08-26%20at%2000.07.21%20(1).jpeg?alt=media&token=47311484-c004-4494-8408-a8831710c560";
          //  uri = Uri.fromFile(new File(url));
        }
        else
        { Log.e("section","else");
            url="https://firebasestorage.googleapis.com/v0/b/profilemain-b8c23.appspot.com/o/Timetable%2FWhatsApp%20Image%202016-08-26%20at%2000.07.21%20(1).jpeg?alt=media&token=47311484-c004-4494-8408-a8831710c560";
          //  uri = Uri.fromFile(new File(url));
        }
        Log.e("here","staring");
        //Picasso.with(getApplicationContext()).load(url).placeholder(R.drawable.ic_media_play).into(img);

       if(!isAirplaneModeOn(getApplicationContext()))
       {
         //  Log.e("uploaded",url);
         //  Picasso.with(getApplicationContext()).load(url).resize(80,80).placeholder(R.mipmap.ic_launcher).into(img);
           img.setImageUrl(url, imgLoder);
          //feedImageView.setImageUrl(item.getImage(), imageLoader);
       }

    }
    static boolean isAirplaneModeOn(Context context) {
        ContentResolver contentResolver = context.getContentResolver();
        return Settings.System.getInt(contentResolver, AIRPLANE_MODE_ON, 0) != 0;
    }
}
