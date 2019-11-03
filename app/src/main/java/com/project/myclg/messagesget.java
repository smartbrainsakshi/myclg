package com.project.myclg;

import android.app.Dialog;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;

public class messagesget extends AppCompatActivity {
    ImageView ivImage;
    FirebaseUser user;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    String yr;
    private DrawerLayout mDrawerLayout;
    FirebaseAuth auth;
    AlertDialog.Builder builder;
    ActionBarDrawerToggle mDrawerToggle;
    Button btn;
    View hView ;
    static boolean flag=false;
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private String userChoosenTask;
    ImageView dp;
    FirebaseDatabase database;
    ListView listView;
    FeedListAdapter adapter;
    ArrayList<FeedItem> list;
    DatabaseReference mDatabase;
    Dialog d;
    TextView nav_user,nav_user1;
    String uname,edit,edit1,edit2;
   // ImageLoader imageloader=AppController.getInstance().getImageLoader();
    AlertDialog.Builder adb;
    private SwipeRefreshLayout swipeRefreshLayout;
  //  FragmentMan ager fm;
    //private popupFragment SortingStandardFragmenttitleFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messagesget);

        mDatabase= FirebaseDatabase.getInstance().getReference();
        final DatabaseReference dinosaursRef = database.getInstance().getReference("messages");
        dinosaursRef.limitToFirst(100).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {

                FeedItem dinosaur = dataSnapshot.getValue(FeedItem.class);
                list.add(dinosaur);
                int iswapCount=listView.getCount();
                int iPosition=listView.getCount();
                for(int i=0;i<iswapCount;i++)
                {
                    Collections.swap(list,iPosition,iPosition-1);
                    iPosition=iPosition-1;
                }

                listView.setAdapter(adapter);



            }


            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


            // ...
        });



    }
}
