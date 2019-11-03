package com.project.myclg;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

//import com.example.sakshipc.profilemain.R;

public class TabFragment2 extends Fragment {
    String yr;
    DatabaseReference mDatabase;
     FirebaseUser user1;

  //  mDatabase= FirebaseDatabase.getInstance().getReference();
 //   mDatabase= FirebaseDatabase.getInstance().getReference();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        user1 = FirebaseAuth.getInstance().getCurrentUser();
        mDatabase= FirebaseDatabase.getInstance().getReference();
        mDatabase.child("signup").child(user1.getUid()).child("branch").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String    yr1=  dataSnapshot.getValue().toString();
                yr=yr1.replaceAll("\\s","");
                Log.e("value url",yr);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
       View v= inflater.inflate(R.layout.tab2, container, false);
        Button btn,btn2,btn3,btn4,btn5,btn6,btn7,btn8;
        btn=(Button)v.findViewById(R.id.buttonfirst);
        btn2=(Button)v.findViewById(R.id.buttonsecond);
        btn3=(Button)v.findViewById(R.id.buttonthird);
        btn4=(Button)v.findViewById(R.id.buttonfourth);
        btn5=(Button)v.findViewById(R.id.buttonfifth);
        btn6=(Button)v.findViewById(R.id.buttonsixth);
        btn7=(Button)v.findViewById(R.id.buttonseventh);
        btn8=(Button)v.findViewById(R.id.buttoneight);
            // yr= getArguments().getString("year value");
   //     tt=  getIntent().getStringExtra("put");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getContext(),syllabusshow.class);
                in.putExtra("url","http://prince.coolpage.biz/syll/"+yr+"/fr.pdf");
                getActivity().startActivity(in);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getContext(),syllabusshow.class);

                in.putExtra("url","http://prince.coolpage.biz/syll"+yr+"/se.pdf");
                getActivity().startActivity(in);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getContext(),syllabusshow.class);

                in.putExtra("url","http://prince.coolpage.biz/syll"+yr+"/th.pdf");
                getActivity().startActivity(in);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getContext(),syllabusshow.class);

                in.putExtra("url","http://prince.coolpage.biz/syll"+yr+"/fo.pdf");
                getActivity().startActivity(in);
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getContext(),syllabusshow.class);

                in.putExtra("url","http://prince.coolpage.biz/syll"+yr+"/fi.pdf");
                getActivity().startActivity(in);
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getContext(),syllabusshow.class);

                in.putExtra("url","http://prince.coolpage.biz/syll"+yr+"/si.pdf");
                getActivity().startActivity(in);
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getContext(),syllabusshow.class);

                in.putExtra("url","http://prince.coolpage.biz/syll"+yr+"/se.pdf");
                getActivity().startActivity(in);
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getContext(),syllabusshow.class);

                in.putExtra("url","http://prince.coolpage.biz/syll"+yr+"/ei.pdf");
                getActivity().startActivity(in);
            }
        });
         return v;
    }
}
