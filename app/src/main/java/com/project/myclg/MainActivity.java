package com.project.myclg;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SlidingDrawer;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import android.app.Activity;
import android.content.Context;

import com.android.volley.Network;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
//import com.example.sakshipc.profilemain.R;
import com.firebase.client.Firebase;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,SwipeRefreshLayout.OnRefreshListener {

    ImageView ivImage;
    FirebaseUser user;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
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
    ImageLoader imageloader=AppController.getInstance().getImageLoader();
    AlertDialog.Builder adb;
    private SwipeRefreshLayout swipeRefreshLayout;
    FragmentManager fm;
    private popupFragment SortingStandardFragmenttitleFragment;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      //  MobileAds.initialize(getApplicationContext(), "ca-app-pub-3940256099942544~3347511713");

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorAccent));
        swipeRefreshLayout.setOnRefreshListener(this);
      /* AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);*/
        mTitle = mDrawerTitle = getTitle();









    Firebase.setAndroidContext(this);
        adb = new AlertDialog.Builder(MainActivity.this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);





        fm=getSupportFragmentManager();

       //Network checking code
        if(!isNetworkAvailable())
        {    Snackbar snackbar = Snackbar
                .make(findViewById(R.id.fab),"No network available", Snackbar.LENGTH_LONG);

            View sbView = snackbar.getView();
            TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
            snackbar.show();
            //Toast.makeText(this,"No network available",Toast.LENGTH_LONG).show();
        }

        mDatabase=FirebaseDatabase.getInstance().getReference();
        final DatabaseReference dinosaursRef = database.getInstance().getReference("FeedItem");
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




        list = new ArrayList<FeedItem>();


        listView=(ListView)findViewById(R.id.listView2);

        auth = FirebaseAuth.getInstance();

        adapter=new FeedListAdapter(this,list,getApplicationContext(),fm,mDatabase);
        if(auth.getCurrentUser()!=null) {
            Log.e("logged in",auth.getCurrentUser()+" ");
            user=auth.getCurrentUser();
        }

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setBackgroundTintList(ColorStateList.valueOf(Color
                .parseColor("#3d587c")));
        fab.setOnClickListener(new View.OnClickListener() {

           // public String edit;

            @Override
            public void onClick(View view) {


             popupFragment Fragment=new popupFragment();
                 FragmentManager fm=getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.Relativelayout_for_fragment,Fragment).commit();
                   fab.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           finish();
                           Intent in =new Intent(getApplicationContext(),MainActivity.class);
                           startActivity(in);

                       }
                   });


                }


        });




            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
        hView =  navigationView.getHeaderView(0);
        TextView nav_id = (TextView)hView.findViewById(R.id.mailnav);
        dp=(ImageView)hView.findViewById(R.id.navdp);
         nav_user = (TextView)hView.findViewById(R.id.username);
         nav_user1 = (TextView)hView.findViewById(R.id.use);
        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                drawer,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        ) {
            @Override
            public void onDrawerClosed(View view) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {

                final DatabaseReference mDatabase = database.getInstance().getReference();


                mDatabase.child("signup").child(user.getUid()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot ds: dataSnapshot.getChildren())
                        {   signup dino = dataSnapshot.getValue(signup.class);
                            String key=dataSnapshot.getKey();
                            String id=user.getUid();
                            if(key.equals(id))
                            {
                                edit= dino.getName();
                                edit1=  dino.getUsername();
                                Log.e("update2",edit1);
                                nav_user.setText(edit);
                                edit2=   dino.getProfile();
                                String internetUrl = "http://i.imgur.com/DvpvklR.png";

                                Picasso
                                        .with(MainActivity.this)
                                        .load(edit2).resize(150,150).transform(new CircleTransform()).placeholder( R.layout.progress_animation )
                                        .into(dp);

//                                Log.e("update",edit2);
                                //dp.setImageUrl(edit2,imageloader);
                                nav_user1.setText(edit1);
                            }

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {


                    }
                });

            }

        };
        drawer.setDrawerListener(mDrawerToggle);
        nav_id.setText(user.getEmail());

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case Utility.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if(userChoosenTask.equals("Take Photo"))
                        cameraIntent();
                    else if(userChoosenTask.equals("Choose from Library"))
                        galleryIntent();
                } else {
                    //code for deny
                }
                break;
        }
    }
    private void selectImage() {
        final CharSequence[] items = { "Take Photo", "Choose from Library",
                "Cancel" };
ListView lv;

        builder = new AlertDialog.Builder(getApplicationContext());
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result=Utility.checkPermission(getApplicationContext());

                if (items[item].equals("Take Photo")) {
                    userChoosenTask ="Take Photo";
                    if(result)
                        cameraIntent();

                } else if (items[item].equals("Choose from Library")) {
                    userChoosenTask ="Choose from Library";
                    if(result)
                        galleryIntent();

                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private void galleryIntent()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"),SELECT_FILE);
    }

    private void cameraIntent()
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE)
                onSelectFromGalleryResult(data);
            else if (requestCode == REQUEST_CAMERA)
                onCaptureImageResult(data);
        }
    }

    private void onCaptureImageResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");

        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
d.show();
        ivImage.setImageBitmap(thumbnail);
    }

    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data) {

        Bitmap bm=null;
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
d.show();
        ivImage.setImageBitmap(bm);
    }









    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);


        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {




         if(item.getItemId()==R.id.action_refresh)
        {
            Toast.makeText(getApplicationContext(), "Refreshing....", Toast.LENGTH_LONG).show();
            finish();
            Intent in = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(in);

        }
        if(item.getItemId()==android.R.id.home){
        NavUtils.navigateUpFromSameTask(this);
        return true;}

        return super.onOptionsItemSelected(item);
    }




    @Override
    public void onBackPressed() {






        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }



        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                MainActivity.this);
        alertDialog.setTitle("Leave ?");
        alertDialog.setPositiveButton("YES",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        finish();

                        //System.exit(1);
                    }
                });
        // Setting Negative "NO" Button
        alertDialog.setNegativeButton("NO",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to invoke NO event
                        dialog.cancel();
                    }
                });
        // Showing Alert Message
        alertDialog.show();
        return;




    }





    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {


            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            Intent in=new Intent(MainActivity.this,GalleryActivity.class);
            startActivity(in);

        } else if (id == R.id.nav_slideshow) {
            Intent in=new Intent(MainActivity.this,main1.class);
            startActivity(in);


        } else if (id == R.id.nav_logout) {
            Log.e("--------------------->","signout");
            auth.getInstance().signOut();
            SharedPreferences pr=getSharedPreferences("logged",MODE_PRIVATE);
            SharedPreferences.Editor editor=pr.edit();
            editor.putString("log","false");
            editor.apply();
            finish();


        } else if (id == R.id.nav_query) {
            Intent in=new Intent(MainActivity.this,BookCard.class);
            startActivity(in);
        }

        else if (id == R.id.nav_profile) {

          Intent in =new Intent(MainActivity.this,Mainactivity321.class);
          startActivity(in);


        }
        else if (id == R.id.nav_upload) {

            Intent in =new Intent(MainActivity.this,upload.class);
            startActivity(in);


        }
        else if (id == R.id.nav_about) {

            Intent in =new Intent(MainActivity.this,Classshow.class);
            startActivity(in);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    //check net connection


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }







    @Override
    public void onRefresh() {

          finish();
            Intent in=new Intent(this,MainActivity.class);
            startActivity(in);


    }





    public class CircleTransform implements Transformation {
        @Override
        public Bitmap transform(Bitmap source) {
            int size = Math.min(source.getWidth(), source.getHeight());

            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;

            Bitmap squaredBitmap = Bitmap.createBitmap(source, x, y, size, size);
            if (squaredBitmap != source) {
                source.recycle();
            }

            Bitmap bitmap = Bitmap.createBitmap(size, size, source.getConfig());

            Canvas canvas = new Canvas(bitmap);
            Paint paint = new Paint();
            BitmapShader shader = new BitmapShader(squaredBitmap,
                    BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
            paint.setShader(shader);
            paint.setAntiAlias(true);

            float r = size / 2f;
            canvas.drawCircle(r, r, r, paint);

            squaredBitmap.recycle();
            return bitmap;
        }

        @Override
        public String key() {
            return "circle";
        }
    }



}







