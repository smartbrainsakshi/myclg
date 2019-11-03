package com.project.myclg;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

//import com.example.sakshipc.profilemain.R;

public class TabFragment1 extends Fragment {
Button btn1,btn2,btn3,btn4,btn5,btn6;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.tab1, container, false);
       // ImageView img=(ImageView)v.findViewById(R.id.imageView2);
        final CardView cv=(CardView)v.findViewById(R.id.cv);
        final CardView cv1=(CardView)v.findViewById(R.id.cv1);
        final CardView cv2=(CardView)v.findViewById(R.id.cv2);
        final CardView cv3=(CardView)v.findViewById(R.id.cv3);
        final CardView cv4=(CardView)v.findViewById(R.id.cv4);
        final CardView cv5=(CardView)v.findViewById(R.id.cv5);
        cv.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), timetableshow.class);
                intent.putExtra("text", "che");
                startActivity(intent);
            }
        });
        cv1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), timetableshow.class);
                intent.putExtra("text", "cse");
                startActivity(intent);

            }
        });
        cv2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), timetableshow.class);
                intent.putExtra("text","ce");
                startActivity(intent);

            }
        });
        cv3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), timetableshow.class);
                intent.putExtra("text", "ee");
                startActivity(intent);

            }
        });
        cv4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), timetableshow.class);
                intent.putExtra("text","ece");
                startActivity(intent);

            }
        });
        cv5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), timetableshow.class);
                intent.putExtra("text","me");
                startActivity(intent);

            }
        });

        return v;
    }
}
