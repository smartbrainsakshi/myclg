package com.project.myclg;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

//import com.example.sakshipc.profilemain.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class homefragment extends Fragment {
    StorageReference storageRef;
  FirebaseStorage storage;
    public homefragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        storageRef = storage.getReferenceFromUrl("gs://profilemain-b8c23.appspot.com");
        StorageReference imagesRef = storageRef.child("images");
        StorageReference islandRef = storageRef.child("images/island.jpg");


        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_homefragment, container, false);


        return v;
    }

}
