package com.project.myclg;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

//import com.example.sakshipc.profilemain.R;

public class TabFragment3 extends Fragment {
    Button btn1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.tab3, container, false);
        btn1=(Button)v.findViewById(R.id.button3);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getContext(),paper.class);
                startActivity(in);
            }
        });
        return v;

    }
}
