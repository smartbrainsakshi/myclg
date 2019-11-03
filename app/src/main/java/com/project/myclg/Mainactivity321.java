package com.project.myclg;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Mainactivity321 extends AppCompatActivity {
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
    String br,yr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainactivity321);
        spinner4= (Spinner) findViewById(R.id.spinner4);
        spinner3= (Spinner) findViewById(R.id.spinner3);

        btn=(Button)findViewById(R.id.button4);
        // phone=(EditText)findViewById(R.id.phone);
        //  spinner1= (Spinner) findViewById(R.id.spinner1);
        //  user=(EditText)findViewById(R.id.input_user);
        list1.add("SELECT SEMESTER");
        list1.add("FIRST SEMESTER");
        list1.add("SECOND SEMESTER");
        list1.add("THIRD SEMESTER");
        list1.add("FOURTH SEMESTER");
        list1.add("FIFTH SEMESTER");
        list1.add("SIXTH SEMESTER");
        list1.add("SEVENTH SEMESTER");
        list1.add("EIGHTH SEMESTER");
        data.add("SEMESTERS");
        data.add("SESSIONALS");
        list.add("SELECT BRANCH");
        list.add("CHEMICAL ENGINEERING");
        list.add("COMPUTER SCIENCE AND ENGINEERING");
        list.add("CIVIL ENGINEERING");
        list.add("ELECTRICAL ENGINEERING");
        list.add("ELECTRONICS AND COMMUNICATION ENGINEERING");
        list.add("MECHANICAL ENGINEERING");
        ArrayAdapter<String> Adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list1);
        Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final ArrayAdapter<String> subAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subAdapter  .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(Adapter);
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                               @Override
                                               public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                   // Toast.makeText(paper.this, String.valueOf(parent.getItemAtPosition(position)), Toast.LENGTH_LONG).show();
                                                   session[0] =String.valueOf(parent.getItemAtPosition(position));
                                                   yr=session[0];
                                                   Log.e("session value----->>",yr);

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
                                                   // Toast.makeText(paper.this, String.valueOf(parent.getItemAtPosition(position)), Toast.LENGTH_LONG).show();
                                                   session[0] =String.valueOf(parent.getItemAtPosition(position));
                                                   br=session[0];
                                                   Log.e("session value----->>",br);

                                               }

                                               @Override
                                               public void onNothingSelected(AdapterView<?> parent) {

                                               }
                                           }


        );

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in=new Intent(Mainactivity321.this,selectstu1.class);
                //  in.putExtra("url",urlpass);
                in.putExtra("branch",br);
                in.putExtra("year",yr);
               // in.putExtra("session",sess);
                startActivity(in);
            }
        });
    }
}
