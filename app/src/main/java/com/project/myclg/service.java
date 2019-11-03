package com.project.myclg;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

//import com.example.sakshipc.profilemain.R;
import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
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
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class service extends AppCompatActivity {

    private Button btnChangeEmail, btnChangePassword, btnSendResetEmail,dpbtn, btnRemoveUser,
            changeEmail, changePassword, sendEmail, remove, signOut,data,done,cancel;
    private CardView datacard;

    private EditText reg,name,year;
    private ProgressBar progressBar;
    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;
    private ImageView dpv;
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private String strBase64;
     String url="data";
    int n;
    String oldp;
    Spinner spin;
    FirebaseUser user;
    FirebaseStorage storage;
    StorageReference ref;
    FirebaseDatabase database;
    String set,set1,set2,new_regnumber,new_year;

    DatabaseReference mDatabase;
    Uri value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_homefragment);


spin=(Spinner)findViewById(R.id.spin);
reg=(EditText)findViewById(R.id.Reg);
name=(EditText)findViewById(R.id.Name);
        reg.setVisibility(View.GONE);
        name.setVisibility(View.GONE);
        dpv=(ImageView) findViewById(R.id.dpview);
        dpv.setImageDrawable(getResources().getDrawable(R.drawable.usermale));
        mDatabase= FirebaseDatabase.getInstance().getReference();
        // imgProfilePicture = (ImageView) findViewById(R.id.dp);
        //url="https://firebasestorage.googleapis.com/v0/b/profilemain-b8c23.appspot.com/o/profile%2Fprofilepic?alt=media&token=64478866-60f7-4ad8-9c4a-0e2b83691bb9";
        //  Log.e("here","staring");
        Picasso.with(getApplicationContext()).load(url).placeholder(R.drawable.userfemale).into(dpv);
        // PreferenceManager.getSharedPreferences(this).edit().putString("profile pic",url);
        Log.e("profile----------->>>>",url);






        //get firebase auth instance
        auth = FirebaseAuth.getInstance();

        //get current user
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(new Intent(service.this, LoginActivity.class));
                    finish();
                }
            }
        };
        cancel=(Button)findViewById(R.id.cancel);
        dpbtn=(Button)findViewById(R.id.dp);
       // btnChangePassword = (Button) findViewById(R.id.change_password_button);
        btnRemoveUser = (Button) findViewById(R.id.remove_user_button);
        sendEmail = (Button) findViewById(R.id.send_reset);

        done=(Button)findViewById(R.id.submit);
        data=(Button)findViewById(R.id.change_data);
        datacard=(CardView)findViewById(R.id.cv3);
        datacard.setVisibility(View.GONE);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        if (progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }
         final String email=user.getEmail();







        mDatabase.child("signup").child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren())
                { n++;
                    Log.e("data", String.valueOf(ds));
                    if(n==2)

                        oldp=  String.valueOf(ds.getValue());



                         }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {


            }
        });




      data.setOnClickListener(new OnClickListener() {
          @Override
          public void onClick(View v) {
              datacard.setVisibility(View.VISIBLE);


       }
      });



        cancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                datacard.setVisibility(View.GONE);
            }
        });



        List<String> list = new ArrayList<String>();
        list.add("Change Year");
        list.add("First Year");
        list.add("Second Year");
        list.add("Third Year");
        list.add("Fourth Year");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(dataAdapter);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                              @Override
                                              public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                  Toast.makeText(getApplicationContext(), String.valueOf(parent.getItemAtPosition(position)), Toast.LENGTH_LONG).show();

                                                  new_year=String.valueOf(parent.getItemAtPosition(position));

                                              }

                                              @Override
                                              public void onNothingSelected(AdapterView<?> parent) {

                                              }
                                          }


        );


        done.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatabaseReference dinosaursRef2 = database.getInstance().getReference("signup");
                dinosaursRef2.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {


                        for(DataSnapshot ds:dataSnapshot.getChildren()) {
                            signup dinosaur = dataSnapshot.getValue(signup.class);

                            set= reg.getText().toString();
                            if(new_year!="Change Year")
                            set1= new_year;
                            set2= name.getText().toString();
                            Log.e("reg-----", String.valueOf(set));
                            Log.e("year----", new_year);
                            Log.e("name---", String.valueOf(set2));
                            if(set!="") {
                                dinosaursRef2.child(user.getUid()).child("username").setValue(set);
                            }

                            dinosaursRef2.child(user.getUid()).child("year").setValue(set1);
                            if(set2!="") {
                                dinosaursRef2.child(user.getUid()).child("name").setValue(set2);
                            }


                        }

                    }


                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

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

                    // ...
                });
                datacard.setVisibility(View.GONE);



            }
        });









        btnRemoveUser.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // get prompts.xml view
                LayoutInflater li = LayoutInflater.from(service.this);
                View promptsView = li.inflate(R.layout.fragment_blank, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        service.this);


                // set dialog message
                alertDialogBuilder
                        .setView(promptsView)
                        .setCancelable(false)
                        .setPositiveButton("Yes",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {

                                        progressBar.setVisibility(View.VISIBLE);
                                        if (user != null) {
                                            user.delete()
                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            if (task.isSuccessful()) {
                                                                LayoutInflater inflater = getLayoutInflater();
                                                                View layout = inflater.inflate(R.layout.toast,
                                                                        (ViewGroup) findViewById(R.id.toast_layout_root));
                                                                TextView text = (TextView) layout.findViewById(R.id.text);
                                                                text.setText("Your Profile is deleted.Create an account now!");

                                                                Toast toast = new Toast(getApplicationContext());
                                                                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                                                                toast.setDuration(Toast.LENGTH_LONG);
                                                                toast.setView(layout);
                                                                toast.show();startActivity(new Intent(service.this, SignupActivity.class));
                                                                finish();
                                                                progressBar.setVisibility(View.GONE);
                                                            } else {
                                                                Toast.makeText(service.this, "Failed to delete your account!", Toast.LENGTH_SHORT).show();
                                                                progressBar.setVisibility(View.GONE);
                                                            }
                                                        }
                                                    });
                                        }






                                    }
                                })
                        .setNegativeButton("No",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();

            }
        });




        sendEmail.setOnClickListener(new View.OnClickListener() {


                    @Override
                    public void onClick(View arg0) {

                        // get prompts.xml view
                        LayoutInflater li = LayoutInflater.from(service.this);
                        View promptsView = li.inflate(R.layout.fragment_blank, null);

                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                                service.this);

                        // set dialog message
                        alertDialogBuilder
                                .setView(promptsView)
                                .setCancelable(false)
                                .setPositiveButton("Yes",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog,int id) {
                                                auth.sendPasswordResetEmail(email)
                                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                if (task.isSuccessful()) {
                                                                    Toast.makeText(service.this, "Reset password email is sent!", Toast.LENGTH_SHORT).show();

                                                                    auth.signOut();
                                                                } else {
                                                                    Toast.makeText(service.this, "Failed to send reset email!", Toast.LENGTH_SHORT).show();

                                                                }
                                                            }
                                                        });
                                            }
                                        })
                                .setNegativeButton("No",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog,int id) {
                                                dialog.cancel();
                                            }
                                        });

                        // create alert dialog
                        AlertDialog alertDialog = alertDialogBuilder.create();

                        // show it
                        alertDialog.show();

                    }
                });













    }



    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void onClickProfilePicture(View view) {

        if(auth.getCurrentUser()!=null) {
            Log.e("logged in",auth.getCurrentUser()+" ");
            user=auth.getCurrentUser();
        }

        final CharSequence[] items = {"Take Photo", "Choose from Library",
                "Cancel"};
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(service.this);
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
        dpv.setImageBitmap(bitmap);
        ref.child(String.valueOf(user.getUid())+"/"+"profilepic").putFile(imageuri).addOnSuccessListener(
                new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        value= taskSnapshot.getMetadata().getDownloadUrl();
                        url=value.toString();
                        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                        SharedPreferences.Editor editor = prefs.edit();

                        editor.putString("profile pic", url);

                        editor.commit();
                        Log.e("profile----------->>",url);

                        final DatabaseReference dinosaursRef2 = database.getInstance().getReference("signup");
                        dinosaursRef2.addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {


                                for(DataSnapshot ds:dataSnapshot.getChildren()) {
                                    signup dinosaur = dataSnapshot.getValue(signup.class);
                                    Log.e("dinosaur",dinosaur.getProfile());
                                    dinosaursRef2.child(user.getUid()).child("profile").setValue(url);



                                }

                            }


                            @Override
                            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

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

                            // ...
                        });

                        mDatabase=FirebaseDatabase.getInstance().getReference();
                        final DatabaseReference dinosaursRef = database.getInstance().getReference("FeedItem");
                        dinosaursRef.addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {


                                for(DataSnapshot ds:dataSnapshot.getChildren()) {
                                    FeedItem dinosaur = dataSnapshot.getValue(FeedItem.class);
                                    String key2=ds.getKey();
                                    Log.e("dinosaur",dinosaur.getProfilePic());
                                    String parent= dinosaur.getId();
                                  //  Log.e("parent",parent);
                                    if(parent.equals(user.getUid()))
                                    {
                                        dinosaursRef.child(key2).child("profilePic").setValue(url);
                                    }


                                }

                            }


                            @Override
                            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

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

                            // ...
                        });




                    /*    dinosaursRef.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for(DataSnapshot ds:dataSnapshot.getChildren()) {
                                    String key2=ds.getKey();
                                    Log.e("database", String.valueOf(ds));
                                    FeedItem feedItem= ds.getValue(FeedItem.class);
                                    String parent= feedItem.getId();
                                    if(parent.equals(user.getUid()))
                                    {
                                        dinosaursRef.child(key2).child("profilePic").setValue(url);
                                    }


                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });*/
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

        ref.child(String.valueOf(user.getUid())+"/"+"profilepic").putFile(selectedImageUri).addOnSuccessListener(
                new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        value= taskSnapshot.getMetadata().getDownloadUrl();
                        url=value.toString();
                        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                        SharedPreferences.Editor editor = prefs.edit();

                        editor.putString("profile pic", url);

                        editor.commit();
                        Log.e("profile----------->>",url);

                        final DatabaseReference dinosaursRef2 = database.getInstance().getReference("signup");
                        dinosaursRef2.addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {


                                for(DataSnapshot ds:dataSnapshot.getChildren()) {
                                    signup dinosaur = dataSnapshot.getValue(signup.class);
                                    Log.e("dinosaur",dinosaur.getProfile());
                                    dinosaursRef2.child(user.getUid()).child("profile").setValue(url);



                                }

                            }


                            @Override
                            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

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

                            // ...
                        });

                        final DatabaseReference dinosaursRef = database.getInstance().getReference("FeedItem");
                        dinosaursRef.addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {


                                for(DataSnapshot ds:dataSnapshot.getChildren()) {
                                    FeedItem dinosaur = dataSnapshot.getValue(FeedItem.class);

                                    String key2=dataSnapshot.getKey();

                                    Log.e("dinosaur",dataSnapshot.getKey().toString());
                                    String parent= dinosaur.getId();
//                                    Log.e("parent",parent);
                                    if(parent.equals(user.getUid()))
                                    {  Log.e("parent",parent);
                                        dinosaursRef.child(key2).child("profilePic").setValue(url);
                                    }
                                   else if(parent==null)
                                    {
                                        break;
                                    }

                                }

                            }


                            @Override
                            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

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

                            // ...
                        });


                    }
                }
        );
        String[] projection = {MediaStore.MediaColumns.DATA};
        Cursor cursor = managedQuery(selectedImageUri, projection, null, null,
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
        dpv.setImageBitmap(bitmap);
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









    //sign out method
    public void signOut() {
        auth.signOut();
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onStart() {
        super.onStart();
        auth.addAuthStateListener(authListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (authListener != null) {
            auth.removeAuthStateListener(authListener);
        }
    }
}