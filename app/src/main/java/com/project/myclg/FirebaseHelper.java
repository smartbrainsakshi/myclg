package com.project.myclg;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

/**
 * Created by sakshi pc on 25-07-2016.
 */
public class FirebaseHelper {
    DatabaseReference db;
    Boolean saved;
    ArrayList<FeedItem> feedItems=new ArrayList<>();
    public FirebaseHelper(DatabaseReference db)
    {
        this.db=db;
    }

    public Boolean Save(FeedItem feedItem)
    {
        if(feedItem==null)
        {
            saved=false;


        }
        else{

            try{

              db.child("").push().setValue(feedItem);
                saved=true;
            }
            catch (Exception e)
            {

                e.printStackTrace();
                saved=false;
            }
        }

     return saved;
    }

    private void FetchData(DataSnapshot dataSnapshot)
    {
        for(DataSnapshot ds:dataSnapshot.getChildren())
        {
            FeedItem feedItem=ds.getValue(FeedItem.class);
            feedItems.add(feedItem);
        }
    }

    public ArrayList<FeedItem> retrieve()
    {
        db.addChildEventListener(
                new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        FetchData(dataSnapshot);
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                        FetchData(dataSnapshot);
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
                }
        );
        return feedItems;
    }
}
