package com.project.myclg;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class selectstu1 extends AppCompatActivity {
    ListView listview ;
    String[] ListViewItems = new String[] {
            "Sakshi Mishra(2014021054)",
            "ListView ITEM-2",
            "ListView ITEM-3",
            "ListView ITEM-4",
            "ListView ITEM-5",
            "ListView ITEM-6",
            "ListView ITEM-7",
            "ListView ITEM-8",
            "ListView ITEM-9",
            "ListView ITEM-10"

    };

    SparseBooleanArray sparseBooleanArray ;
    int i = 0 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectstu1);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);
        listview = (ListView)findViewById(R.id.listView);
     //    String[] ValueHolder ;
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(selectstu1.this,
                android.R.layout.simple_list_item_multiple_choice,
                android.R.id.text1, ListViewItems );
        final String[] ValueHolder = new String[15];
        for(int i=0;i<15;i++)
            ValueHolder[i]= String.valueOf(-1);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub

                sparseBooleanArray = listview.getCheckedItemPositions();

      //    String[] ValueHolder = new String[sparseBooleanArray.size()];



                while (i < sparseBooleanArray.size()) {

                    if (sparseBooleanArray.valueAt(i)) {

                        ValueHolder[i] = ListViewItems [ sparseBooleanArray.keyAt(i) ] + ",";
                    }

                    i++ ;
                }


            }
        });

     final   FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final popupfrag123 Fragment=new popupfrag123();
                FragmentManager fm=getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.Relativelayout_for_fragment,Fragment).commit();
                fab.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                        Bundle b=new Bundle();
                        b.putStringArray("value", ValueHolder);
                        Fragment.setArguments(b);
                        Intent in =new Intent(getApplicationContext(),selectstu1.class);

                        startActivity(in);

                    }
                });

            }
        });
    }

}
