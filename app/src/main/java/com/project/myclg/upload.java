package com.project.myclg;

import android.content.Intent;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.firebase.client.Firebase;
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

public class upload extends AppCompatActivity {
    Spinner spinner2,spinner1,spinner3,spinner4;
    DatabaseReference mdatabase;
    EditText phone,user;
    Button btn;
    FirebaseDatabase database;
    ListView listView;
    String[] year = new String[1];
    String[] branch = new String[1];
    String[] session = new String[1];
    String[] subject = new String[1];
    List<String> data = new ArrayList<String>();
    List<String> list = new ArrayList<String>();
    List<String> list1 = new ArrayList<String>();
    List<String> list2 = new ArrayList<String>();
    String br,sess,sub,yr,urlpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this,R.color.colorPrimary));
        }





        //  list = new ArrayList<FeedItem>();
        listView=(ListView)findViewById(R.id.listView2);

        btn=(Button)findViewById(R.id.button4);
        //  adapter=new FeedListAdapter(this,list,getApplicationContext(),fm,mDatabase);
        mdatabase= FirebaseDatabase.getInstance().getReference();
        //mDatabase.child("users").child(userId).setValue(user);
        MobileAds.initialize(getApplicationContext(), "ca-app-pub-5112744772970179/7447716247");
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        spinner2= (Spinner) findViewById(R.id.spinner2);
        spinner4= (Spinner) findViewById(R.id.spinner4);
        spinner3= (Spinner) findViewById(R.id.spinner3);
        phone=(EditText)findViewById(R.id.phone);
        spinner1= (Spinner) findViewById(R.id.spinner1);
        user=(EditText)findViewById(R.id.input_user);
        list1.add("SELECT SEMESTER");
        list1.add("FIRST SEMESTER");
        list1.add("SECOND SEMESTER");
        list1.add("THIRD SEMESTER");
        list1.add("FOURTH SEMESTER");
        list1.add("FIFTH SEMESTER");
        list1.add("SIXTH SEMESTER");
        list1.add("SEVENTH SEMESTER");
        list1.add("EIGHTH SEMESTER");
        data.add("Select Subject");
        list.add("SELECT BRANCH");
        list.add("CHEMICAL ENGINEERING");
        list.add("COMPUTER SCIENCE AND ENGINEERING");
        list.add("CIVIL ENGINEERING");
        list.add("ELECTRICAL ENGINEERING");
        list.add("ELECTRONICS AND COMMUNICATION ENGINEERING");
        list.add("MECHANICAL ENGINEERING");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        spinner2.setSelection(0, true);
        View v = spinner2.getSelectedView();
        spinner4.setVisibility(View.GONE);
        // ((TextView)v).setTextColor(Color.parseColor("#FFFFFF"));
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list1);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<String> Adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, data);
        Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final ArrayAdapter<String> subAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list2);
        Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(dataAdapter);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


                                               @Override
                                               public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                   //  Toast.makeText(paper.this, String.valueOf(parent.getItemAtPosition(position)), Toast.LENGTH_LONG).show();
                                                   br =String.valueOf(parent.getItemAtPosition(position));

                                                   //   br=branch[0];
                                                   Log.e("branch value----->>",br);
                                                   Log.e("seme value",yr);

                                               }

                                               @Override
                                               public void onNothingSelected(AdapterView<?> parent) {

                                               }
                                           }


        );


        spinner2.setAdapter(adapter);
        list2.add("SELECT SUBJECT");
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                               @Override
                                               public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                   // ((TextView) view).setTextColor(Color.parseColor("#FFFFFF"));
                                                   //  Toast.makeText(paper.this, String.valueOf(parent.getItemAtPosition(position)), Toast.LENGTH_LONG).show();
                                                   year[0] =String.valueOf(parent.getItemAtPosition(position));
                                                   yr=year[0];
                                                   Log.e(" year value----->>",yr);
//                                                  Log.e(" yrbranch----->>",br);
                                                   //  DatabaseReference dbase = ;

                                                   Log.e("branch selected","value add");

                                                   Firebase.setAndroidContext(getApplicationContext());
                                                   Firebase ref=new Firebase("https://console.firebase.google.com/project/profilemain-b8c23/database/data/paper/COMPUTER%20SCIENCE%20AND%20ENGINEERING/EIGHTH%20SEMESTER");
                                                   final DatabaseReference dinosaursRef = database.getInstance().getReference();
                                                   Log.e("reference",dinosaursRef.toString());
                                                   // Log.e("reference",dinosaursRef.toString());
                                                   if(br!=null)
                                                   { Log.e(" yrvalue----->>",br);
                                                       if((br=="COMPUTER SCIENCE AND ENGINEERING")&&(yr=="FIRST SEMESTER"))
                                                       {  data.add("Engineering Mathematics-I");
                                                           data.add("Engineering Physics-I ");
                                                           data.add("Introduction to C & Functional Programming");
                                                           data.add("Principles of Electrical Engineering");
                                                           data.add("Professional Communication");
                                                           // data.add("Engineering Graphics");
                                                           data.add("Environment & Ecology");
                                                           // data.add("Introduction to Computer Programming ");
                                                       }
                                                       if((br=="COMPUTER SCIENCE AND ENGINEERING")&&(yr=="SECOND SEMESTER"))
                                                       {  data.add("Engineering Mathematics-II");
                                                           data.add("Engineering Physics-II");
                                                           data.add("Graph Theory");
                                                           data.add("Object Oriented Modeling & C++");
                                                           data.add("Humanities & Social Science Electives");
                                                           data.add("Fundamentals of Electronics Engineering");
                                                       }
                                                       if((br=="COMPUTER SCIENCE AND ENGINEERING")&&(yr=="THIRD SEMESTER"))
                                                       {  data.add("Discrete Mathematics");
                                                           data.add("Applied Computational Methods");
                                                           //data.add("Basic Surveying");
                                                           data.add("Principles of Data Structures through C/C++");
                                                           data.add("Internet & JAVA Programming");
                                                           // data.add("Engineering Geology & Building Material");
                                                           //  data.add("Nano Technology");
                                                           // data.add("Introduction to Computer Programming ");
                                                       }

                                                       if((br=="COMPUTER SCIENCE AND ENGINEERING")&&(yr=="FOURTH SEMESTER"))
                                                       {  data.add("Optimization Techniques");
                                                           data.add("Management Information System");
                                                           data.add("Database Management Systems");
                                                           data.add("Theory of Computation");
                                                           data.add("Computer Organization & Design");
                                                           data.add("Microprocessors & Application");
                                                       }
                                                       if((br=="CIVIL ENGINEERING")&&(yr=="FIRST SEMESTER"))
                                                       {  data.add("Engineering Mathematics-I");
                                                           data.add("Engineering Physics-I ");
                                                           data.add("Mechanics of Structures");
                                                           data.add("Introduction to Computer Programming ");
                                                           data.add("Professional Communication");
                                                           data.add("Engineering Graphics");
                                                           data.add("Environment & Ecology");
                                                           // data.add("Introduction to Computer Programming ");
                                                       }
                                                       if((br=="CIVIL ENGINEERING")&&(yr=="SECOND SEMESTER"))
                                                       {  data.add("Engineering Mathematics-II");
                                                           data.add("Engineering Physics-II");
                                                           data.add("Engineering Chemistry");
                                                           data.add("Principles of Electrical Engineering");
                                                           data.add("Humanities & Social Science Electives");
                                                           data.add("Fundamentals of Electronics Engineering");
                                                       }
                                                       if((br=="CIVIL ENGINEERING")&&(yr=="THIRD SEMESTER"))
                                                       {  data.add("Engineering Mathematics-III");
                                                           data.add("Concrete & Concrete Structures");
                                                           data.add("Basic Surveying");
                                                           data.add("Fluid Mechanics");
                                                           data.add("Structural Mechanics-I");
                                                           data.add("Engineering Geology & Building Material");
                                                           data.add("Nano Technology");
                                                           // data.add("Introduction to Computer Programming ");
                                                       }
                                                       if((br=="CIVIL ENGINEERING")&&(yr=="FOURTH SEMESTER"))
                                                       {  data.add("Numerical Methods");
                                                           data.add("Industrial Management");
                                                           data.add("Hydraulic and Hydraulic Machines");
                                                           data.add("Structural Mechanics -II");
                                                           data.add("Advanced Surveying");
                                                           data.add("Building Construction and Planning Estimation and Costing");
                                                       }
                                                       dinosaursRef.child("myclass/"+br+"/"+yr).addListenerForSingleValueEvent(new ValueEventListener() {
                                                           @Override
                                                           public void onDataChange(DataSnapshot dataSnapshot) {
                                                               list2.clear();
                                                               for(DataSnapshot ds:dataSnapshot.getChildren())
                                                               {
                                                                   Log.e("value--------->>>",ds.getKey().toString());

                                                                   Log.e("spinner","cleaned");
                                                                   list2.add(ds.getKey().toString());
                                                                   spinner4.setAdapter(subAdapter);

                                                               }

                                                           }

                                                           @Override
                                                           public void onCancelled(DatabaseError databaseError) {

                                                           }
                                                       });}



                                               }

                                               @Override
                                               public void onNothingSelected(AdapterView<?> parent) {

                                               }
                                           }


        );


        spinner3.setAdapter(Adapter);
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                               @Override
                                               public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                                                   // Toast.makeText(paper.this, String.valueOf(parent.getItemAtPosition(position)), Toast.LENGTH_LONG).show();
                                                   session[0] =String.valueOf(parent.getItemAtPosition(position));
                                                   sess=session[0];
                                                   Log.e("session value----->>",sess);
                                                   if(br=="CIVIL ENGINEERING"){
                                                       if(sess=="FIRST SEMESTER")
                                                       {
                                                           urlpass="http://mmmut.ac.in/ModelPapers/sy_090916053027.pdf";

                                                       } urlpass="http://mmmut.ac.in/ModelPapers/sy_090916053027.pdf";
                                                       Log.e("referenceurlpass",urlpass);
                                                   }
                                               }

                                               @Override
                                               public void onNothingSelected(AdapterView<?> parent) {

                                               }
                                           }


        );



        spinner4.setAdapter(subAdapter);
        spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                               @Override
                                               public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                   //  Toast.makeText(paper.this, String.valueOf(parent.getItemAtPosition(position)), Toast.LENGTH_LONG).show();
                                                   subject[0] =String.valueOf(parent.getItemAtPosition(position));
                                                   sub=subject[0];
                                                   Log.e("spinner value----->>",subject[0]);
                                                   if(br!= null&&yr!=null&&sub!=null&&sess!=null)
                                                   {

                                                   }
                                               }

                                               @Override
                                               public void onNothingSelected(AdapterView<?> parent) {

                                               }
                                           }


        );




        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // mdatabase.child("paper").child(br).child(yr).child(sess).setValue("https://firebasestorage.googleapis.com/v0/b/profilemain-b8c23.appspot.com/o/Paper%2FCSE%2Ffirst%2FOBJECT%20ORIENTED%20PROGRAMMING%2FSEMESTERS%2FBCS-04.pdf?alt=media&token=8d5d4e7f-3489-4142-8221-94a488461e00");
                Intent in=new Intent(upload.this,uploadpdf.class);
               // in.putExtra("url",urlpass);
                  in.putExtra("branch",br);
                 in.putExtra("year",yr);
                in.putExtra("session",sess);
                startActivity(in);
                 /*   dinosaursRef.child("myclass/"+br+"/"+yr+"/"+sess).addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {

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
                    }));*/





            }
        });
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem menu) {

        Intent myIntent = new Intent(getApplicationContext(), GalleryActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }

}
