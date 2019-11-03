package com.project.myclg;



import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
//import com.example.sakshipc.profilemain.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class FeedListAdapter extends  BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private ArrayList<FeedItem> feedItems;
    private Context context;
    private FragmentManager mFragmentManager;
    ImageView profilePic;
    TextView id;
    TextView vemail;
    TextView vbranch;
    TextView vyear;
    TextView vuser;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
  int n=0;


    public FeedListAdapter(Activity activity, ArrayList<FeedItem> feedItems, Context context, FragmentManager mFragmentManager,DatabaseReference databaseReference) {
        this.activity = activity;
        this.feedItems = feedItems;
        this.context=context;
        this.mFragmentManager=mFragmentManager;
        this.databaseReference=databaseReference;
    }

    @Override
    public int getCount() {
        return feedItems.size();
    }

    @Override
    public Object getItem(int location) {
      //  System.out.print("location value--------------------->>"+location);
        return feedItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.feed_item,null);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();




        TextView statusMsg = (TextView) convertView
                .findViewById(R.id.txtStatusMsg);

        id = (TextView) convertView
                .findViewById(R.id.ID);
        TextView name = (TextView) convertView
                .findViewById(R.id.name);
        TextView email = (TextView) convertView
                .findViewById(R.id.textemail);
        TextView timestamp = (TextView) convertView
                .findViewById(R.id.timestamp);
        TextView url = (TextView) convertView.findViewById(R.id.txtUrl);
         profilePic = (ImageView) convertView
                .findViewById(R.id.profilePic);
        FeedImageView feedImageView = (FeedImageView) convertView
                .findViewById(R.id.feedImage1);

        FeedItem item = feedItems.get(position);



        if (!TextUtils.isEmpty(item.getId())) {
            id.setText(item.getId());
            id.setVisibility(View.INVISIBLE);
            // status is empty, remove from view

        }
       if (!TextUtils.isEmpty(item.getStatus())) {
            statusMsg.setText(item.getStatus());
            statusMsg.setVisibility(View.VISIBLE);
        } else {
            // status is empty, remove from view
            statusMsg.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(item.getName())) {
            name.setText(item.getName());
            name.setVisibility(View.VISIBLE);
            // status is empty, remove from view
        }  else {
            name.setVisibility(View.GONE);}
        if (!TextUtils.isEmpty(item.getEmail())) {
            email.setText(item.getEmail());
            email.setVisibility(View.VISIBLE);
            // status is empty, remove from view
        }  else {
            email.setVisibility(View.GONE);}

        if (!TextUtils.isEmpty(item.getTimestamp())) {
            timestamp.setText(item.getTimestamp());
            timestamp.setVisibility(View.VISIBLE);
        } else {
            // status is empty, remove from view
            timestamp.setVisibility(View.GONE);
        }

        // Checking for null feed url
        if (item.getUrl() != null) {
            url.setText(Html.fromHtml( item.getUrl()));

            // Making url clickable
            url.setMovementMethod(LinkMovementMethod.getInstance());
            url.setVisibility(View.VISIBLE);
        } else {
            // url is null, remove from the view
            url.setVisibility(View.GONE);
        }

        // user profile pic
        Picasso
                .with(parent.getContext())
                .load(item.getProfilePic()).resize(100,100).placeholder( R.drawable.usermale )
                .into(profilePic);
        //profilePic.setImageUrl(item.getProfilePic(), imageLoader);

        // Feed image
        if (item.getImage() != null) {
            feedImageView.setImageUrl(item.getImage(), imageLoader);
            feedImageView.setVisibility(View.VISIBLE);
            feedImageView
                    .setResponseObserver(new FeedImageView.ResponseObserver() {
                        @Override
                        public void onError() {
                        }

                        @Override
                        public void onSuccess() {
                        }
                    });
        } else {
            feedImageView.setVisibility(View.GONE);
        }
        name.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.e("id ki  value",id.getText().toString());

                          Intent in = new Intent(context, Show.class);
  in.putExtra("message",id.getText().toString());
                        v.getContext().startActivity(in);

                    }
                }
        );
        return convertView;
    }}

