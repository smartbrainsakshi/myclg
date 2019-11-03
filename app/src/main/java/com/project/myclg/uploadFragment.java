package com.project.myclg;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

//
// import com.example.sakshipc.profilemain.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;


/**
 * A simple {@link Fragment} subclass.
 */
public class uploadFragment extends Fragment {
    StorageReference islandRef;
    StorageReference storageRef;
    FirebaseStorage storage;
    Button b1;
    FirebaseDatabase database;
    DatabaseReference dbref;
    public uploadFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_upload, container, false);
        b1=(Button)v.findViewById(R.id.button2);


        b1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            /*    database=FirebaseDatabase.getInstance();
                Log.e("databse", String.valueOf(database));
                dbref=database.getReference("message");
                dbref.setValue("Hello World");*/
                // Start the Signup activity
               storage=FirebaseStorage.getInstance();


                storageRef=storage.getReference("Books");
                islandRef = storageRef.child("firstbook/internet and basics of java.pdf");


               File localFile = null;
                try {
                    localFile = File.createTempFile("internet and basics of java", "pdf");
                } catch (IOException e) {
                    e.printStackTrace();
                }

                islandRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                        // Local temp file has been created
                        Toast.makeText(getContext(), "downloaded", Toast.LENGTH_LONG).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle any errors
                    }
                });




            }
        });
        return v;
    }

}
