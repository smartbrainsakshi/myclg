package com.project.myclg;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;

//import com.example.sakshipc.profilemain.R;

import java.util.ArrayList;

/**
 * Created by sakshi pc on 30-06-2016.
 */

public class CustomAdapter extends BaseAdapter{
    LayoutInflater LI;
    ArrayList<studentDetail> sd;


    public CustomAdapter(MainActivity customlist, ArrayList<studentDetail>sd) {
        this.sd=sd;
        LI=(LayoutInflater)customlist.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }



    @Override
    public int getCount() {
        return sd.size();
    }

    @Override
    public Object getItem(int position) {
        return sd.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi;

         vi=   LI.inflate(R.layout.customelement,null,false);

        ImageButton im=(ImageButton)vi.findViewById(R.id.imageButton);
        ImageButton im2=(ImageButton)vi.findViewById(R.id.imageButton2);
      //  ImageButton im3=(ImageButton)vi.findViewById(R.id.imageButton3);

        studentDetail sd=(studentDetail)getItem(position);


        return vi;
    }






}
