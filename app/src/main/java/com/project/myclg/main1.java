package com.project.myclg;


import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

//import com.example.sakshipc.profilemain.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;
import java.util.List;

public class main1 extends AppCompatActivity {
    private List<Contact> ContactList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ContactsAdapter mAdapter;
    private Button btn,btn2,btn3,btn4,btn5,btn6;
    private TextView as;
    int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);




        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this,R.color.colorPrimary));
        }


        MobileAds.initialize(getApplicationContext(), "ca-app-pub-3940256099942544~3347511713");
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);



        //final  FloatingActionButton fb=(FloatingActionButton)findViewById(R.id.fabmain);
        final CardView cv0=(CardView)findViewById(R.id.cv0);;
        final CardView cv=(CardView)findViewById(R.id.cv);
        final CardView cv1=(CardView)findViewById(R.id.cv1);;
        final CardView cv2=(CardView)findViewById(R.id.cv2);
        final CardView cv3=(CardView)findViewById(R.id.cv3);
        final CardView cv4=(CardView)findViewById(R.id.cv4);
        final CardView cv5=(CardView)findViewById(R.id.cv5);

        cv0.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                i=0;
                prepareContactDataas();
                as.setText("Applied Science- Tap to call");

                recyclerView.setVisibility(View.VISIBLE);
                cv0.setVisibility(View.GONE);
                cv1.setVisibility(View.GONE);
                cv2.setVisibility(View.GONE);
                cv3.setVisibility(View.GONE);
                cv4.setVisibility(View.GONE);
                cv5.setVisibility(View.GONE);
                cv.setVisibility(View.GONE);




            }
        });

        cv.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                i=1;
                prepareContactDataChe();
                as.setText("Chemical- Tap to call");

                recyclerView.setVisibility(View.VISIBLE);
                cv0.setVisibility(View.GONE);
                cv1.setVisibility(View.GONE);
                cv2.setVisibility(View.GONE);
                cv3.setVisibility(View.GONE);
                cv4.setVisibility(View.GONE);
                cv5.setVisibility(View.GONE);
                cv.setVisibility(View.GONE);


            }
        });
        cv1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                i=2;
                prepareContactDataCse();
                as.setText("CSE- Tap to call");

                recyclerView.setVisibility(View.VISIBLE);
                cv0.setVisibility(View.GONE);
                cv1.setVisibility(View.GONE);
                cv2.setVisibility(View.GONE);
                cv3.setVisibility(View.GONE);
                cv4.setVisibility(View.GONE);
                cv5.setVisibility(View.GONE);
                cv.setVisibility(View.GONE);

            }
        });
        cv2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                i=3;
                prepareContactDataCe();

                recyclerView.setVisibility(View.VISIBLE);
                as.setText("CE- Tap to call");
                cv1.setVisibility(View.GONE);
                cv0.setVisibility(View.GONE);
                cv2.setVisibility(View.GONE);
                cv3.setVisibility(View.GONE);
                cv4.setVisibility(View.GONE);
                cv5.setVisibility(View.GONE);
                cv.setVisibility(View.GONE);

            }
        });
        cv3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                i=4;
                prepareContactDataee();
                as.setText("EE- Tap to call");

                recyclerView.setVisibility(View.VISIBLE);

                cv1.setVisibility(View.GONE);
                cv2.setVisibility(View.GONE);
                cv0.setVisibility(View.GONE);
                cv3.setVisibility(View.GONE);
                cv4.setVisibility(View.GONE);
                cv5.setVisibility(View.GONE);
                cv.setVisibility(View.GONE);

            }
        });

        cv4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                i=5;
                prepareContactDataEce();

                recyclerView.setVisibility(View.VISIBLE);
                as.setText("ECE- Tap to call");
                cv1.setVisibility(View.GONE);
                cv2.setVisibility(View.GONE);
                cv0.setVisibility(View.GONE);
                cv3.setVisibility(View.GONE);
                cv4.setVisibility(View.GONE);
                cv5.setVisibility(View.GONE);
                cv.setVisibility(View.GONE);


            }
        });
        cv5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                i=6;
                prepareContactDataMe();
                as.setText("ME- Tap to call");

                recyclerView.setVisibility(View.VISIBLE);

                cv1.setVisibility(View.GONE);
                cv2.setVisibility(View.GONE);
                cv3.setVisibility(View.GONE);
                cv0.setVisibility(View.GONE);
                cv4.setVisibility(View.GONE);
                cv5.setVisibility(View.GONE);
                cv.setVisibility(View.GONE);


            }
        });




        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        //recyclerView.setVisibility(View.GONE);
        as=(TextView)findViewById(R.id.textView);

        mAdapter = new ContactsAdapter(ContactList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);



        // Some extra functionalities like scroll to top... also adding heading to collapsing heading






        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Contact Contact = ContactList.get(position);
                        Intent callIntent = new Intent(Intent.ACTION_CALL);

                        if(i==0) {
                            if (position == 0) {

                                callIntent.setData(Uri.parse("tel:09999902569"));//change the number
                                startActivity(callIntent);
                                Toast.makeText(getApplicationContext(), "call  "+Contact.getname(), Toast.LENGTH_SHORT).show();
                            }


                            if (position == 1) {

                                callIntent.setData(Uri.parse("tel:08423034074"));//change the number
                                startActivity(callIntent);
                                Toast.makeText(getApplicationContext(), "call  "+Contact.getname(), Toast.LENGTH_SHORT).show();
                            }


                            if (position == 2) {

                                callIntent.setData(Uri.parse("tel:09235501646"));//change the number
                                startActivity(callIntent);
                                Toast.makeText(getApplicationContext(), "call  "+Contact.getname(), Toast.LENGTH_SHORT).show();
                            }


                            if (position == 3) {

                                callIntent.setData(Uri.parse("tel:09235500509"));//change the number
                                startActivity(callIntent);
                                Toast.makeText(getApplicationContext(), "call  "+Contact.getname(), Toast.LENGTH_SHORT).show();
                            }


                            if (position == 4) {

                                callIntent.setData(Uri.parse("tel:09415244487"));//change the number
                                startActivity(callIntent);
                                Toast.makeText(getApplicationContext(), "call  "+Contact.getname(), Toast.LENGTH_SHORT).show();
                            }


                            if (position == 5) {

                                callIntent.setData(Uri.parse("tel:09235500511"));//change the number
                                startActivity(callIntent);
                                Toast.makeText(getApplicationContext(), "call  "+Contact.getname(), Toast.LENGTH_SHORT).show();
                            }
                            if (position == 6) {

                                callIntent.setData(Uri.parse("tel:09235500510"));//change the number
                                startActivity(callIntent);
                                Toast.makeText(getApplicationContext(), "call  "+Contact.getname(), Toast.LENGTH_SHORT).show();
                            }

                            if (position == 7) {

                                callIntent.setData(Uri.parse("tel:09235501647"));//change the number
                                startActivity(callIntent);
                                Toast.makeText(getApplicationContext(), "call  "+Contact.getname(), Toast.LENGTH_SHORT).show();
                            }

                            if (position == 8) {

                                callIntent.setData(Uri.parse("tel:09235552356"));//change the number
                                startActivity(callIntent);
                                Toast.makeText(getApplicationContext(), "call  "+Contact.getname(), Toast.LENGTH_SHORT).show();
                            }
                            if (position == 9) {

                                callIntent.setData(Uri.parse("tel:09235500513"));//change the number
                                startActivity(callIntent);
                                Toast.makeText(getApplicationContext(), "call  "+Contact.getname(), Toast.LENGTH_SHORT).show();
                            }
                            if (position == 10) {

                                callIntent.setData(Uri.parse("tel:09760263413"));//change the number
                                startActivity(callIntent);
                                Toast.makeText(getApplicationContext(), "call  "+Contact.getname(), Toast.LENGTH_SHORT).show();
                            }
                            if (position == 11) {

                                callIntent.setData(Uri.parse("tel:09235552355"));//change the number
                                startActivity(callIntent);
                                Toast.makeText(getApplicationContext(), "call  "+Contact.getname(), Toast.LENGTH_SHORT).show();
                            }
                            if (position == 12) {

                                callIntent.setData(Uri.parse("tel:09450422506"));//change the number
                                startActivity(callIntent);
                                Toast.makeText(getApplicationContext(), "call  "+Contact.getname(), Toast.LENGTH_SHORT).show();
                            }




                        }
                        //////////////////////////////////////////The onitemtouch listener to attach numbers to each item///////////////////////////////////////////////////////////////


                        if(i==1){


                            if (position == 0) {

                                callIntent.setData(Uri.parse("tel:09235500566"));//change the number
                                startActivity(callIntent);

                                Toast.makeText(getApplicationContext(), "call  "+Contact.getname(), Toast.LENGTH_SHORT).show();
                            }
                            if (position == 1) {

                                callIntent.setData(Uri.parse("tel:07521078408"));//change the number
                                startActivity(callIntent);

                                Toast.makeText(getApplicationContext(), "call  "+Contact.getname(), Toast.LENGTH_SHORT).show();
                            }





                        }





                        if(i==2){

                            if (position == 0) {

                                callIntent.setData(Uri.parse("tel:09839284278"));//change the number
                                startActivity(callIntent);

                                Toast.makeText(getApplicationContext(), "call  "+Contact.getname(), Toast.LENGTH_SHORT).show();
                            }


                            if (position == 1) {

                                callIntent.setData(Uri.parse("tel:09235500527"));//change the number
                                startActivity(callIntent);

                                Toast.makeText(getApplicationContext(), "call  "+Contact.getname(), Toast.LENGTH_SHORT).show();
                            }


                            if (position == 2) {

                                callIntent.setData(Uri.parse("tel:09235500535"));//change the number
                                startActivity(callIntent);

                                Toast.makeText(getApplicationContext(), "call  "+Contact.getname(), Toast.LENGTH_SHORT).show();
                            }


                            if (position == 3) {

                                callIntent.setData(Uri.parse("tel:08765783658"));//change the number
                                startActivity(callIntent);

                                Toast.makeText(getApplicationContext(), "call  "+Contact.getname(), Toast.LENGTH_SHORT).show();
                            }


                            if (position == 4) {

                                callIntent.setData(Uri.parse("tel:07460059240"));//change the number
                                startActivity(callIntent);

                                Toast.makeText(getApplicationContext(), "call  "+Contact.getname(), Toast.LENGTH_SHORT).show();
                            }


                            if (position == 5) {

                                callIntent.setData(Uri.parse("tel:09235500536"));//change the number
                                startActivity(callIntent);

                                Toast.makeText(getApplicationContext(), "call  "+Contact.getname(), Toast.LENGTH_SHORT).show();
                            }

                            if (position == 6) {

                                callIntent.setData(Uri.parse("tel:09415994465"));//change the number
                                startActivity(callIntent);

                                Toast.makeText(getApplicationContext(), "call  "+Contact.getname(), Toast.LENGTH_SHORT).show();
                            }


                            if (position == 7) {

                                callIntent.setData(Uri.parse("tel:09235500538"));//change the number
                                startActivity(callIntent);

                                Toast.makeText(getApplicationContext(), "call  "+Contact.getname(), Toast.LENGTH_SHORT).show();
                            }


                            if (position == 8) {

                                callIntent.setData(Uri.parse("tel:08127902564"));//change the number
                                startActivity(callIntent);

                                Toast.makeText(getApplicationContext(), "call  "+Contact.getname(), Toast.LENGTH_SHORT).show();
                            }


                            if (position == 9) {

                                callIntent.setData(Uri.parse("tel:09235500531"));//change the number
                                startActivity(callIntent);

                                Toast.makeText(getApplicationContext(), "call  "+Contact.getname(), Toast.LENGTH_SHORT).show();
                            }


                            if (position == 10) {

                                callIntent.setData(Uri.parse("tel:09235501648"));//change the number
                                startActivity(callIntent);

                                Toast.makeText(getApplicationContext(), "call  "+Contact.getname(), Toast.LENGTH_SHORT).show();
                            }


                            if (position == 11) {

                                callIntent.setData(Uri.parse("tel:09235500529"));//change the number
                                startActivity(callIntent);

                                Toast.makeText(getApplicationContext(), "call  "+Contact.getname(), Toast.LENGTH_SHORT).show();
                            }


                            if (position == 12) {

                                callIntent.setData(Uri.parse("tel:09118470035"));//change the number
                                startActivity(callIntent);
                                Toast.makeText(getApplicationContext(), "call  "+Contact.getname(), Toast.LENGTH_SHORT).show();
                            }


                            if (position == 13) {

                                callIntent.setData(Uri.parse("tel:09235500532"));//change the number
                                startActivity(callIntent);

                                Toast.makeText(getApplicationContext(), "call  "+Contact.getname(), Toast.LENGTH_SHORT).show();
                            }


                            if (position == 14) {

                                callIntent.setData(Uri.parse("tel:09235500533"));//change the number
                                startActivity(callIntent);

                                Toast.makeText(getApplicationContext(), "call  "+Contact.getname(), Toast.LENGTH_SHORT).show();
                            }


                            if (position == 15) {

                                callIntent.setData(Uri.parse("tel:09235500539"));//change the number
                                startActivity(callIntent);

                                Toast.makeText(getApplicationContext(), "call  "+Contact.getname(), Toast.LENGTH_SHORT).show();
                            }


                            if (position == 16) {

                                callIntent.setData(Uri.parse("tel:08174021986"));//change the number
                                startActivity(callIntent);

                                Toast.makeText(getApplicationContext(), "call  "+Contact.getname(), Toast.LENGTH_SHORT).show();
                            }


                            if (position == 17) {

                                callIntent.setData(Uri.parse("tel:09235500526"));//change the number
                                startActivity(callIntent);

                                Toast.makeText(getApplicationContext(), "call  "+Contact.getname(), Toast.LENGTH_SHORT).show();
                            }


                            if (position == 18) {

                                callIntent.setData(Uri.parse("tel:09235500528"));//change the number
                                startActivity(callIntent);

                                Toast.makeText(getApplicationContext(), "call  "+Contact.getname(), Toast.LENGTH_SHORT).show();
                            }






                        }


                        if(i==3){


                            if (position == 0) {

                                callIntent.setData(Uri.parse("tel:09235500524"));//change the number
                                startActivity(callIntent);

                                Toast.makeText(getApplicationContext(), "call  "+Contact.getname(), Toast.LENGTH_SHORT).show();
                            }


                            if (position == 1) {

                                callIntent.setData(Uri.parse("tel:09235500518"));//change the number
                                startActivity(callIntent);

                                Toast.makeText(getApplicationContext(), "call  "+Contact.getname(), Toast.LENGTH_SHORT).show();
                            }


                            if (position == 2) {

                                callIntent.setData(Uri.parse("tel:09451800047"));//change the number
                                startActivity(callIntent);

                                Toast.makeText(getApplicationContext(), "call  "+Contact.getname(), Toast.LENGTH_SHORT).show();
                            }


                            if (position == 3) {

                                callIntent.setData(Uri.parse("tel:09235500517"));//change the number
                                startActivity(callIntent);

                                Toast.makeText(getApplicationContext(), "call  "+Contact.getname(), Toast.LENGTH_SHORT).show();
                            }



                            if (position == 4) {

                                callIntent.setData(Uri.parse("tel:09235500520"));//change the number
                                startActivity(callIntent);

                                Toast.makeText(getApplicationContext(), "call  "+Contact.getname(), Toast.LENGTH_SHORT).show();
                            }

                            if (position == 5) {

                                callIntent.setData(Uri.parse("tel:09235500521"));//change the number
                                startActivity(callIntent);

                                Toast.makeText(getApplicationContext(), "call  "+Contact.getname(), Toast.LENGTH_SHORT).show();
                            }
                            if (position == 6) {

                                callIntent.setData(Uri.parse("tel:09235500696"));//change the number
                                startActivity(callIntent);

                                Toast.makeText(getApplicationContext(), "call  "+Contact.getname(), Toast.LENGTH_SHORT).show();
                            }

                            if (position == 7) {

                                callIntent.setData(Uri.parse("tel:09235500519"));//change the number
                                startActivity(callIntent);

                                Toast.makeText(getApplicationContext(), "call  "+Contact.getname(), Toast.LENGTH_SHORT).show();
                            }

                            if (position == 8) {

                                callIntent.setData(Uri.parse("tel:09792727677"));//change the number
                                startActivity(callIntent);

                                Toast.makeText(getApplicationContext(), "call  "+Contact.getname(), Toast.LENGTH_SHORT).show();
                            }
                            if (position == 9) {

                                callIntent.setData(Uri.parse("tel:09780973247"));//change the number
                                startActivity(callIntent);

                                Toast.makeText(getApplicationContext(), "call  "+Contact.getname(), Toast.LENGTH_SHORT).show();
                            }
                            if (position == 10) {

                                callIntent.setData(Uri.parse("tel:09235552358"));//change the number
                                startActivity(callIntent);

                                Toast.makeText(getApplicationContext(), "call  "+Contact.getname(), Toast.LENGTH_SHORT).show();
                            }










                        }


                        if(i==4){


                            if (position == 0) {

                                callIntent.setData(Uri.parse("tel:09935451768"));//change the number
                                startActivity(callIntent);

                                Toast.makeText(getApplicationContext(), "call  "+Contact.getname(), Toast.LENGTH_SHORT).show();
                            }


                            if (position == 1) {

                                callIntent.setData(Uri.parse("tel:09451215400"));//change the number
                                startActivity(callIntent);

                                Toast.makeText(getApplicationContext(), "call  "+Contact.getname(), Toast.LENGTH_SHORT).show();
                            }


                            if (position == 2) {

                                callIntent.setData(Uri.parse("tel:09235501645"));//change the number
                                startActivity(callIntent);

                                Toast.makeText(getApplicationContext(), "call  "+Contact.getname(), Toast.LENGTH_SHORT).show();
                            }


                            if (position == 3) {

                                callIntent.setData(Uri.parse("tel:09235500541"));//change the number
                                startActivity(callIntent);

                                Toast.makeText(getApplicationContext(), "call  "+Contact.getname(), Toast.LENGTH_SHORT).show();
                            }


                            if (position == 4) {

                                callIntent.setData(Uri.parse("tel:09235500550"));//change the number
                                startActivity(callIntent);

                                Toast.makeText(getApplicationContext(), "call  "+Contact.getname(), Toast.LENGTH_SHORT).show();
                            }


                            if (position == 5) {

                                callIntent.setData(Uri.parse("tel:08285224779"));//change the number
                                startActivity(callIntent);

                                Toast.makeText(getApplicationContext(), "call  "+Contact.getname(), Toast.LENGTH_SHORT).show();
                            }
                            if (position == 6) {

                                callIntent.setData(Uri.parse("tel:09235500544"));//change the number
                                startActivity(callIntent);

                                Toast.makeText(getApplicationContext(), "call  "+Contact.getname(), Toast.LENGTH_SHORT).show();
                            }
                            if (position == 7) {

                                callIntent.setData(Uri.parse("tel:09411222122"));//change the number
                                startActivity(callIntent);

                                Toast.makeText(getApplicationContext(), "call  "+Contact.getname(), Toast.LENGTH_SHORT).show();
                            }
                            if (position == 8) {

                                callIntent.setData(Uri.parse("tel:09235500542"));//change the number
                                startActivity(callIntent);

                                Toast.makeText(getApplicationContext(), "call  "+Contact.getname(), Toast.LENGTH_SHORT).show();
                            }






                        }

                        if(i==5){


                            if (position == 0) {

                                callIntent.setData(Uri.parse("tel:09455134477"));//change the number
                                startActivity(callIntent);

                                Toast.makeText(getApplicationContext(), "call  "+Contact.getname(), Toast.LENGTH_SHORT).show();
                            }


                            if (position == 1) {

                                callIntent.setData(Uri.parse("tel:09235500552"));//change the number
                                startActivity(callIntent);

                                Toast.makeText(getApplicationContext(), "call  "+Contact.getname(), Toast.LENGTH_SHORT).show();
                            }


                            if (position == 2) {

                                callIntent.setData(Uri.parse("tel:08765783716"));//change the number
                                startActivity(callIntent);

                                Toast.makeText(getApplicationContext(), "call  "+Contact.getname(), Toast.LENGTH_SHORT).show();
                            }


                            if (position == 3) {

                                callIntent.setData(Uri.parse("tel:09235500559"));//change the number
                                startActivity(callIntent);

                                Toast.makeText(getApplicationContext(), "call  "+Contact.getname(), Toast.LENGTH_SHORT).show();
                            }


                            if (position == 4) {

                                callIntent.setData(Uri.parse("tel:09235501650"));//change the number
                                startActivity(callIntent);

                                Toast.makeText(getApplicationContext(), "call  "+Contact.getname(), Toast.LENGTH_SHORT).show();
                            }


                            if (position == 5) {

                                callIntent.setData(Uri.parse("tel:09774335528"));//change the number
                                startActivity(callIntent);

                                Toast.makeText(getApplicationContext(), "call  "+Contact.getname(), Toast.LENGTH_SHORT).show();
                            }

                            if (position == 6) {

                                callIntent.setData(Uri.parse("tel:07607100888"));//change the number
                                startActivity(callIntent);

                                Toast.makeText(getApplicationContext(), "call  "+Contact.getname(), Toast.LENGTH_SHORT).show();
                            }

                            if (position == 7) {

                                callIntent.setData(Uri.parse("tel:09235500556"));//change the number
                                startActivity(callIntent);

                                Toast.makeText(getApplicationContext(), "call  "+Contact.getname(), Toast.LENGTH_SHORT).show();
                            }
                            if (position == 8) {

                                callIntent.setData(Uri.parse("tel:09235500560"));//change the number
                                startActivity(callIntent);

                                Toast.makeText(getApplicationContext(), "call  "+Contact.getname(), Toast.LENGTH_SHORT).show();
                            }
                            if (position == 9) {

                                callIntent.setData(Uri.parse("tel:09235500557"));//change the number
                                startActivity(callIntent);

                                Toast.makeText(getApplicationContext(), "call  "+Contact.getname(), Toast.LENGTH_SHORT).show();
                            }







                        }

                        if(i==6){


                            if (position == 0) {

                                callIntent.setData(Uri.parse("tel:09235500565"));//change the number
                                startActivity(callIntent);

                                Toast.makeText(getApplicationContext(), "call  "+Contact.getname(), Toast.LENGTH_SHORT).show();
                            }


                            if (position == 1) {

                                callIntent.setData(Uri.parse("tel:09235500682"));//change the number
                                startActivity(callIntent);
                                Toast.makeText(getApplicationContext(), "call  "+Contact.getname(), Toast.LENGTH_SHORT).show();
                            }


                            if (position == 2) {

                                callIntent.setData(Uri.parse("tel:09235552352"));//change the number
                                startActivity(callIntent);

                                Toast.makeText(getApplicationContext(), "call  "+Contact.getname(), Toast.LENGTH_SHORT).show();
                            }


                            if (position == 3) {

                                callIntent.setData(Uri.parse("tel:09431382595"));//change the number
                                startActivity(callIntent);

                                Toast.makeText(getApplicationContext(), "call  "+Contact.getname(), Toast.LENGTH_SHORT).show();
                            }


                            if (position == 4) {

                                callIntent.setData(Uri.parse("tel:07840043269"));//change the number
                                startActivity(callIntent);

                                Toast.makeText(getApplicationContext(), "call  "+Contact.getname(), Toast.LENGTH_SHORT).show();
                            }


                            if (position == 5) {

                                callIntent.setData(Uri.parse("tel:09235552360"));//change the number
                                startActivity(callIntent);

                                Toast.makeText(getApplicationContext(), "call  "+Contact.getname(), Toast.LENGTH_SHORT).show();
                            }



                            if (position == 6) {

                                callIntent.setData(Uri.parse("tel:09235501651"));//change the number
                                startActivity(callIntent);

                                Toast.makeText(getApplicationContext(), "call  "+Contact.getname(), Toast.LENGTH_SHORT).show();
                            }


                            if (position == 7) {

                                callIntent.setData(Uri.parse("tel:09235501649"));//change the number
                                startActivity(callIntent);

                                Toast.makeText(getApplicationContext(), "call  "+Contact.getname(), Toast.LENGTH_SHORT).show();
                            }


                            if (position == 8) {

                                callIntent.setData(Uri.parse("tel:09415210542"));//change the number
                                startActivity(callIntent);

                                Toast.makeText(getApplicationContext(), "call "+Contact.getname(), Toast.LENGTH_SHORT).show();
                            }


                            if (position == 9) {

                                callIntent.setData(Uri.parse("tel:09235500569"));//change the number
                                startActivity(callIntent);

                                Toast.makeText(getApplicationContext(), "call "+Contact.getname(), Toast.LENGTH_SHORT).show();
                            }


                            if (position == 10) {

                                callIntent.setData(Uri.parse("tel:09235500566"));//change the number
                                startActivity(callIntent);

                                Toast.makeText(getApplicationContext(), "call "+Contact.getname(), Toast.LENGTH_SHORT).show();
                            }


                            if (position == 11) {

                                callIntent.setData(Uri.parse("tel:09235552353"));//change the number
                                startActivity(callIntent);

                                Toast.makeText(getApplicationContext(), "call "+Contact.getname(), Toast.LENGTH_SHORT).show();
                            }



                            if (position == 12) {

                                callIntent.setData(Uri.parse("tel:09235500689"));//change the number
                                startActivity(callIntent);

                                Toast.makeText(getApplicationContext(), "call "+Contact.getname(), Toast.LENGTH_SHORT).show();
                            }










                        }






                    }
                })
        );



        Log.e("MEssage3>>>>",Integer.toString(i));






    }



    //////// prepareContactData#branch# to feed input based on branch as below////



    private void prepareContactDataChe() {
        Contact Contact = new Contact("Sunil Kumar Srivastava", "9235500566","H.O.D");
        ContactList.add(Contact);

        Contact = new Contact("Ravi Shankar","7521078408", "Asst. Prof");
        ContactList.add(Contact);





        mAdapter.notifyDataSetChanged();
    }




    private void prepareContactDataas() {
        Contact Contact = new Contact("Abhijit Mishra", "9999902569","Asst. Prof");
        ContactList.add(Contact);

        Contact = new Contact("Abhishek Kumar Gupta","8423034074", "Asst. Prof");
        ContactList.add(Contact);

        Contact = new Contact("Amit Kumar Barnwal", "9235501646","Asst. Prof");
        ContactList.add(Contact);

        Contact = new Contact("Arjun Dubey","9235500509", "Assoc. Prof");
        ContactList.add(Contact);

        Contact = new Contact("B. K. Pandey", "9415244487","Asst. Prof");
        ContactList.add(Contact);

        Contact = new Contact("D. Kandu","9235500511", "Asst. Prof");
        ContactList.add(Contact);

        Contact = new Contact("D.K. Dwivedi","9235500510", "H.O.D");
        ContactList.add(Contact);

        Contact = new Contact("Harish Chandra","9235501647", "Asst. Prof");
        ContactList.add(Contact);

        Contact = new Contact("Krishna Kumar","9235552356", "Asst. Prof");
        ContactList.add(Contact);

        Contact = new Contact("P. P. Pande","9235500513", "Asst. Prof");
        ContactList.add(Contact);

        Contact = new Contact("Ram Keval","9760263413", "Asst. Prof");
        ContactList.add(Contact);

        Contact = new Contact("Ravi Kumar Gupta","9235552355", "Asst. Prof");
        ContactList.add(Contact);

        Contact = new Contact("Satya Pal Singh","9450422506", "Asst. Prof");
        ContactList.add(Contact);


        mAdapter.notifyDataSetChanged();
    }





    private void prepareContactDataCse() {
        Contact Contact = new Contact("A. K. Daniel", "9839284278","Assoc. Prof");
        ContactList.add(Contact);

        Contact = new Contact("Awadhesh Kumar Sharma","9235500527", "Assoc. Prof");
        ContactList.add(Contact);

        Contact = new Contact("D. S. Singh", "9235500535","Asst. Prof");
        ContactList.add(Contact);

        Contact = new Contact("Divakar Yadav","8765783658", "Assoc. Prof");
        ContactList.add(Contact);

        Contact = new Contact("Gaurav Baranwal", "7460059240","Asst. Prof");
        ContactList.add(Contact);

        Contact = new Contact("Jay Prakash","9235500536", "Asst. Prof");
        ContactList.add(Contact);

        Contact = new Contact("M. K. Srivastava","9415994465", "Asst. Prof");
        ContactList.add(Contact);

        Contact = new Contact("Muzammil Hasan","9235500538", "Asst. Prof");
        ContactList.add(Contact);

        Contact = new Contact("Nagendra Pratap Singh","8127902564", "Asst. Prof");
        ContactList.add(Contact);

        Contact = new Contact("P.K Singh","9235500531", "Prof");
        ContactList.add(Contact);

        Contact = new Contact("R. K. Dwivedi","9235501648", "Asst. Prof");
        ContactList.add(Contact);

        Contact = new Contact("Rakesh Kumar","9235500529", "H.O.D");
        ContactList.add(Contact);

        Contact = new Contact("Rohit Kumar Tiwari","9118470035", "Asst. Prof");
        ContactList.add(Contact);

        Contact = new Contact("Sarvpal Singh","9235500532", "Assoc. Prof");
        ContactList.add(Contact);

        Contact = new Contact("Shiva Prakash","9235500533", "Assoc. Prof");
        ContactList.add(Contact);

        Contact = new Contact("Smt. Meenu","9235500539", "Asst. Prof");
        ContactList.add(Contact);
        Contact = new Contact("Sushil Kumar Saroj","8174021986", "Asst. Prof");
        ContactList.add(Contact);
        Contact = new Contact("Udai Shanker","9235500526", "Prof");
        ContactList.add(Contact);

        Contact = new Contact("Umesh Chandra Jaiswal","9235500528", "Assoc. Prof");
        ContactList.add(Contact);


        mAdapter.notifyDataSetChanged();

    }


    private void prepareContactDataCe() {
        Contact Contact = new Contact("A. K. Mishra", "9235500524","Assoc. Prof");
        ContactList.add(Contact);

        Contact = new Contact("Govind Pandey","9235500518", "Assoc. Prof");
        ContactList.add(Contact);

        Contact = new Contact("Madan Chandra Maurya", "9451800047","Asst. Prof");
        ContactList.add(Contact);

        Contact = new Contact("R. K. Shukla","9235500517", "Assoc. Prof");
        ContactList.add(Contact);

        Contact = new Contact("R.D. Patel", "9235500520","Assoc. Prof");
        ContactList.add(Contact);

        Contact = new Contact("S. N. Chaudhary","9235500521", "Assoc. Prof");
        ContactList.add(Contact);

        Contact = new Contact("Sana Zafar","9235500696", "Asst. Prof");
        ContactList.add(Contact);

        Contact = new Contact("Shri Ram Chaurasia","9235500519", "Assoc. Prof");
        ContactList.add(Contact);

        Contact = new Contact("Shweta Yadav","9792727677", "Asst. Prof");
        ContactList.add(Contact);

        Contact = new Contact("Vikas Kumar","9780973247", "Asst. Prof");
        ContactList.add(Contact);

        Contact = new Contact("Vinay Kumar Singh","9235552358", "Asst. Prof");
        ContactList.add(Contact);


        mAdapter.notifyDataSetChanged();
    }



    private void prepareContactDataMe() {
        Contact Contact = new Contact("D. K. Singh", "9235500565","Prof");
        ContactList.add(Contact);

        Contact = new Contact("Devesh Kumar","9235500682", "Asst. Prof");
        ContactList.add(Contact);

        Contact = new Contact("Dheerandra Singh", "9235552352","Asst. Prof");
        ContactList.add(Contact);

        Contact = new Contact("Jeeoot Singh","9431382595", "Assoc. Prof");
        ContactList.add(Contact);

        Contact = new Contact("Laxmikant Yadav", "7840043269","Asst. Prof");
        ContactList.add(Contact);

        Contact = new Contact("Prashant Saini","9235552360", "Asst. Prof");
        ContactList.add(Contact);

        Contact = new Contact("Radha Krishna Lal","9235501651", "Asst. Prof");
        ContactList.add(Contact);

        Contact = new Contact("Ram Bilas Prasad","9235501649", "Asst. Prof");
        ContactList.add(Contact);

        Contact = new Contact("RN Mall","9415210542", "Asst. Prof");
        ContactList.add(Contact);

        Contact = new Contact("S. C. Jayswal","9235500569", "H.O.D");
        ContactList.add(Contact);

        Contact = new Contact("Sunil Kumar Srivastava","9235500566", "Prof");
        ContactList.add(Contact);

        Contact = new Contact("Sunil Kumar Yadav","9235552353", "Asst. Prof");
        ContactList.add(Contact);

        Contact = new Contact("Swati Gangwar","9235500689", "Asst. Prof");
        ContactList.add(Contact);


        mAdapter.notifyDataSetChanged();
    }



    private void prepareContactDataEce() {
        Contact Contact = new Contact("Anupam Sahu", "9455134477","Asst. Prof");
        ContactList.add(Contact);

        Contact = new Contact("Brijesh Kumar","9235500552", "Assoc. Prof");
        ContactList.add(Contact);

        Contact = new Contact("Dharmendra Kumar", "8765783716","Asst. Prof");
        ContactList.add(Contact);

        Contact = new Contact("G. S. Tripathi","9235500559", "Prof");
        ContactList.add(Contact);

        Contact = new Contact("Gagandeep Bharti", "9235501650","Asst. Prof");
        ContactList.add(Contact);

        Contact = new Contact("Manish Kumar","9774335528", "Assoc. Prof");
        ContactList.add(Contact);

        Contact = new Contact("Pooja Lohia","7607100888", "Asst. Prof");
        ContactList.add(Contact);

        Contact = new Contact("R. K. Chauhan","9235500556", "Assoc. Prof");
        ContactList.add(Contact);

        Contact = new Contact("Rajan Mishra","9235500560", "Asst. Prof");
        ContactList.add(Contact);

        Contact = new Contact("Sudhanshu Verma","9235500557", "Asst. Prof");
        ContactList.add(Contact);


        mAdapter.notifyDataSetChanged();
    }



    private void prepareContactDataee() {
        Contact Contact = new Contact("A. K. Pandey", "9935451768","Assoc. Prof");
        ContactList.add(Contact);

        Contact = new Contact("A. N. Tiwari","9451215400", "Assoc. Prof");
        ContactList.add(Contact);

        Contact = new Contact("Awadhesh Kumar", "9235501645","Asst. Prof");
        ContactList.add(Contact);

        Contact = new Contact("K. G. Upadhyay","9235500541", "Prof");
        ContactList.add(Contact);

        Contact = new Contact("K. P Singh", "9235500550","Assoc. Prof");
        ContactList.add(Contact);

        Contact = new Contact("Kishan Bhushan Sahay","8285224779", "Asst. Prof");
        ContactList.add(Contact);

        Contact = new Contact("S. K. Srivastava","9235500544", "H.O.D");
        ContactList.add(Contact);

        Contact = new Contact("Shekhar Yadav","9411222122", "Asst. Prof");
        ContactList.add(Contact);

        Contact = new Contact("Vinod Kumar Giri","9235500542", "Prof");
        ContactList.add(Contact);


        mAdapter.notifyDataSetChanged();
    }







}


//Contact
class Contact {
    private String name, post, dep;

    public Contact() {
    }

    public Contact(String name, String post, String dep) {
        this.name = name;
        this.post = post;
        this.dep = dep;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getdep() {
        return dep;
    }

    public void setdep(String dep) {
        this.dep = dep;
    }

    public String getpost() {
        return post;
    }

    public void setpost(String post) {
        this.post = post;
    }
}






// Contacts adapter java

class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.MyViewHolder> {

    private List<Contact> ContactsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, dep, post;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            post = (TextView) view.findViewById(R.id.post);
            dep = (TextView) view.findViewById(R.id.dep);
        }
    }


    public ContactsAdapter(List<Contact> ContactsList) {
        this.ContactsList = ContactsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_ist_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Contact Contact = ContactsList.get(position);
        holder.name.setText(Contact.getname());
        holder.post.setText(Contact.getpost());
        holder.dep.setText(Contact.getdep());
    }

    @Override
    public int getItemCount() {
        return ContactsList.size();
    }





}



class DividerItemDecoration extends RecyclerView.ItemDecoration {

    private static final int[] ATTRS = new int[]{
            android.R.attr.listDivider
    };

    public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;

    public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;

    private Drawable mDivider;

    private int mOrientation;

    public DividerItemDecoration(Context context, int orientation) {
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0);
        a.recycle();
        setOrientation(orientation);
    }

    public void setOrientation(int orientation) {
        if (orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST) {
            throw new IllegalArgumentException("invalid orientation");
        }
        mOrientation = orientation;
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (mOrientation == VERTICAL_LIST) {
            drawVertical(c, parent);
        } else {
            drawHorizontal(c, parent);
        }
    }

    public void drawVertical(Canvas c, RecyclerView parent) {
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    public void drawHorizontal(Canvas c, RecyclerView parent) {
        final int top = parent.getPaddingTop();
        final int bottom = parent.getHeight() - parent.getPaddingBottom();

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int left = child.getRight() + params.rightMargin;
            final int right = left + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (mOrientation == VERTICAL_LIST) {
            outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
        } else {
            outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
        }
    }
}



class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    GestureDetector mGestureDetector;

    public RecyclerItemClickListener(Context context, OnItemClickListener listener) {
        mListener = listener;
        mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
        View childView = view.findChildViewUnder(e.getX(), e.getY());
        if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
            mListener.onItemClick(childView, view.getChildAdapterPosition(childView));
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView view, MotionEvent motionEvent) {
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}

