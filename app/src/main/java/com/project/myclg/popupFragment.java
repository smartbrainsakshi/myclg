package com.project.myclg;


import android.annotation.TargetApi;
import android.app.Activity;
import android.database.Cursor;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Base64;
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
import android.widget.TextView;

//import com.example.sakshipc.profilemain.R;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.Reference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.widget.Spinner;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class popupFragment extends Fragment  {
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
    public popupFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.statfrag, container, false);
         firebaseAuth=FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        final SharedPreferences mSharedPreference= PreferenceManager.getDefaultSharedPreferences(getContext());
         str = mSharedPreference.getString("profile pic","null");
        Log.e("profile",str);
        et1= (EditText)v.findViewById(R.id.txtStatusMsg);
       // et2= (EditText) v.findViewById(R.id.txtUrl);
        btnt=(ImageView) v.findViewById(R.id.btnt);
        btn= (ImageView) v.findViewById(R.id.button);
        btn1= (ImageView) v.findViewById(R.id.button1);
        //btn2=(Button)v.findViewById(R.id.button2);
        spinner= (Spinner)v.findViewById(R.id.txtUrl);
        Calendar c = Calendar.getInstance();
        System.out.println("Current time => "+c.getTime());









        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd      HH:mm");
        final String formattedDate = df.format(c.getTime());
  btn.setOnClickListener(
          new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  onClickProfilePicture(v);
              }
          }
  );


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

                                          FeedItem b = new FeedItem(user.getUid().toString(), name, formattedDate, branch, et1.getText().toString(), str, url,time,user.getEmail());
                                          Log.e("status", et1.toString());
                                          mDatabase.child("FeedItem").push().setValue(b);
                                          // year=  String.valueOf(ds.getValue(signup.class).getYear());
                                          Log.e("child", name);
                                      }
                                       if(n>=3)
                                           break;
                                    }

                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
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



    public void onClickProfilePicture(View view) {

        if(firebaseAuth.getCurrentUser()!=null) {
            Log.e("logged in",firebaseAuth.getCurrentUser()+" ");
            user=firebaseAuth.getCurrentUser();

        }

        final CharSequence[] items = {"Take Photo", "Choose from Library",
                "Cancel"};
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(getContext());
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Take Photo")) {
                    storage= FirebaseStorage.getInstance();
                    ref=storage.getReference("photos/image");
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent,REQUEST_CAMERA);
                } else if (items[item].equals("Choose from Library")) {
                    storage= FirebaseStorage.getInstance();
                    ref=storage.getReference("photos/image");
                    Intent intent = new Intent(
                            Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(
                            Intent.createChooser(intent, "Select File"),
                            SELECT_FILE);
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();

    }


    private void onCaptureImageResult(Intent data) {
        Bitmap bitmap = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File file = new File(Environment.getExternalStorageDirectory(), System.currentTimeMillis() + ".jpg");
        final Uri imageuri=Uri.fromFile(file);
        FileOutputStream fo;
        try {
            file.createNewFile();
            fo = new FileOutputStream(file);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ivImage.setImageBitmap(bitmap);
        ref.child(String.valueOf(user.getUid())+"/"+new Date().getTime()).putFile(imageuri).addOnSuccessListener(
                new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        value= taskSnapshot.getMetadata().getDownloadUrl();
                        url=value.toString();
                        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
                        SharedPreferences.Editor editor = prefs.edit();

                        editor.putString("image", url);

                        editor.commit();
                        Log.e("profile----------->>",url);
                        mDatabase=FirebaseDatabase.getInstance().getReference();



                    }
                }
        );
        //Convert Bitmap to Base64
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        strBase64 = Base64.encodeToString(byteArray, Base64.DEFAULT);

    }




    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data) {
        final Uri selectedImageUri = data.getData();

        ref.child(String.valueOf(user.getUid())+"/"+new Date().getTime()).putFile(selectedImageUri).addOnSuccessListener(
                new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        value= taskSnapshot.getMetadata().getDownloadUrl();
                        url=value.toString();
                        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
                        SharedPreferences.Editor editor = prefs.edit();

                        editor.putString("image", url);

                        editor.commit();
                        Log.e("profile----------->>",url);


                    }
                }
        );
        String[] projection = {MediaStore.MediaColumns.DATA};
        Cursor cursor = getActivity().managedQuery(selectedImageUri, projection, null, null,
                null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        cursor.moveToFirst();
        String selectedImagePath = cursor.getString(column_index);
        Bitmap bitmap;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(selectedImagePath, options);

        final int REQUIRED_SIZE = 200;
        int scale = 1;
        while (options.outWidth / scale / 2 >= REQUIRED_SIZE
                && options.outHeight / scale / 2 >= REQUIRED_SIZE)
            scale *= 2;
        options.inSampleSize = scale;
        options.inJustDecodeBounds = false;
        bitmap = BitmapFactory.decodeFile(selectedImagePath, options);
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        ivImage.setImageBitmap(bitmap);
        //Convert Bitmap to Base64
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        strBase64 = Base64.encodeToString(byteArray, Base64.DEFAULT);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE)
                onSelectFromGalleryResult(data);
            else if (requestCode == REQUEST_CAMERA)
                onCaptureImageResult(data);
        }
    }



}
