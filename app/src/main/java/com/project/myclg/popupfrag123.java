package com.project.myclg;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;




public class popupfrag123 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

   // private OnFragmentInteractionListener mListener;
    ListView lv;
    Uri value;
    EditText et1,et2,et3;
    //Button btn,btn1,btn2;

    ImageView btn1,btnt,btn;
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private Button btnSelect;
    private ImageView ivImage;

    FirebaseDatabase database;
    FirebaseAuth firebaseAuth;
    private DatabaseReference mDatabase;

    private String strBase64;
    String url;
    DatabaseReference dr;
    FirebaseStorage storage;
    StorageReference ref;
    Uri imageuri;
    Spinner spinner;
    String name="sakshi";
    String str;
    String branch="check";

    FirebaseUser user;
    int n=0;
    public popupfrag123() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final   String[] ValueHolder = getArguments().getStringArray("value");
        View v=inflater.inflate(R.layout.fragment_popupfrag123, container, false);
        firebaseAuth=FirebaseAuth.getInstance();
        btn= (ImageView) v.findViewById(R.id.button);
        btn1= (ImageView) v.findViewById(R.id.button1);
        //btn2=(Button)v.findViewById(R.id.button2);
        spinner= (Spinner)v.findViewById(R.id.txtUrl);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        final SharedPreferences mSharedPreference= PreferenceManager.getDefaultSharedPreferences(getContext());
        str = mSharedPreference.getString("profile pic","null");
        Log.e("profile",str);
        et1= (EditText)v.findViewById(R.id.txtStatusMsg);
        // et2= (EditText) v.findViewById(R.id.txtUrl);


        Calendar c = Calendar.getInstance();
        System.out.println("Current time => "+c.getTime());



        /*SimpleDateFormat  df = new SimpleDateFormat("yyyy-MM-dd      HH:mm");
        final String formattedDate = df.format(c.getTime());*/
        final String formattedDate="null";

        btnt=(ImageView) v.findViewById(R.id.btnt);
        btnt.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getActivity().finish();
                        Intent in = new Intent(getContext(), MainActivity.class);
                        startActivity(in);
                    }
                }
        );
        List<String> list = new ArrayList<String>();
        list.add("Select category");
        list.add("#Ch.E");
        list.add("#CSE ");
        list.add("#CE");
        list.add("#EE");
        list.add("#ECE");
        list.add("#ME");
        list.add("#All");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                              @Override
                                              public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                  Toast.makeText(getContext(), String.valueOf(parent.getItemAtPosition(position)), Toast.LENGTH_LONG).show();

                                                  branch=String.valueOf(parent.getItemAtPosition(position));
                                                  if(branch=="Select category")
                                                      branch="#All";

                                              }

                                              @Override
                                              public void onNothingSelected(AdapterView<?> parent) {

                                              }
                                          }


        );
        ivImage = (ImageView)v.findViewById(R.id.ivImage);
        btn1.setOnClickListener(
                new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        if(firebaseAuth.getCurrentUser()!=null) {

                            user=firebaseAuth.getCurrentUser();
                            Log.e("log in------------>",firebaseAuth.getCurrentUser()+" ");
                            System.out.println(user);
                            Log.e("reference", String.valueOf(mDatabase));


                            user = firebaseAuth.getCurrentUser();
                            final String userId =user.getUid().toString();
                            mDatabase.child("signup").child(userId).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    for(DataSnapshot ds: dataSnapshot.getChildren())
                                    {  n++;
                                        Log.e("data", String.valueOf(ds));
                                        name=  String.valueOf(ds.getValue());
                                        if(n==3) {
                                            long time = -1 * new Date().getTime();
                                            int i=0;
                                            while (i < ValueHolder.length) {


                                                FeedItem b = new FeedItem(user.getDisplayName(), name, formattedDate, branch, et1.getText().toString(), str, url,time,user.getEmail());
                                                Log.e("status", et1.toString());
                                                mDatabase.child("message").child(ValueHolder[i]).setValue(b);

                                                i++ ;


                                                // year=  String.valueOf(ds.getValue(signup.class).getYear());
                                                Log.e("child", name);
                                            }
                                            if(n>=3)
                                                break;
                                        }

                                    }

                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }});
//Log.e("name",name);

                            Log.e("added", "data");
                            getActivity().finish();
                            Intent in = new Intent(getContext(), MainActivity.class);
                            startActivity(in);
                        }
                    }
                }

        );

        return v;

    }

}
