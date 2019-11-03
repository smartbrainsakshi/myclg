package com.project.myclg;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

//import com.example.sakshipc.profilemain.R;
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
import java.util.List;

/**
 * Created by prince sharzeel on 14-02-2017.
 */
public class ScrollingActivity extends AppCompatActivity {


    FirebaseUser user;
    FirebaseStorage storage;
    StorageReference ref;
    FirebaseDatabase database;
    DatabaseReference mDatabase;
    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;
    private TextView del,reset;
    private Spinner spin;
    private TextView updyear,name,username,mobile,email,year;
    private ImageView dp;
    String new_year,strBase64,url;
    Uri value;
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_blank);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        try {
            getSupportActionBar().setTitle("Profile");

        }
        catch(Exception e)
        {Log.e("","");}

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setBackgroundTintList(ColorStateList.valueOf(Color
                .parseColor("#E91E63")));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickProfilePicture(view);
            }
        });
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);

       del=(TextView) findViewById(R.id.del6);
        updyear=(TextView)findViewById(R.id.yearupd);
        name=(TextView)findViewById(R.id.tvNumber1);
       email=(TextView)findViewById(R.id.tvNumber2);
        username=(TextView)findViewById(R.id.tvNumber5);
        mobile=(TextView)findViewById(R.id.tvNumber6);
        year=(TextView)findViewById(R.id.tvNumberyear);
        spin=(Spinner)findViewById(R.id.sp);
        reset=(TextView) findViewById(R.id.res);
        dp=(ImageView)findViewById(R.id.dppp);
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
                    startActivity(new Intent(ScrollingActivity.this, LoginActivity.class));
                    finish();
                }
            }
        };






        final DatabaseReference mDatabase = database.getInstance().getReference();


        mDatabase.child("signup").child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren())
                {   signup dino = dataSnapshot.getValue(signup.class);
                    String key=dataSnapshot.getKey();
                    String id=user.getUid();
                    if(key.equals(id))
                    {
                        String edit = dino.getName();
                        String edit1=  dino.getUsername();
                        Log.e("update2",edit1);
                        name.setText(edit);
                       String edit2=   dino.getProfile();
                        String mob=dino.getPhone();
                        String yr=dino.getYear();

                        Picasso
                                .with(ScrollingActivity.this)
                                .load(edit2).placeholder( R.layout.progress_animation )
                                .into(dp);

//                                Log.e("update",edit2);
                        //dp.setImageUrl(edit2,imageloader);
                       username.setText(edit1);
                        email.setText(user.getEmail());
                        mobile.setText(mob);
                        year.setText(yr);

                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {


            }
        });
























        //code tp year u[date


        spin.setVisibility(View.GONE);

        updyear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spin.setVisibility(View.VISIBLE);
                updyear.setText("Confirm");

                      updyear.setOnClickListener(new View.OnClickListener() {
                          @Override
                          public void onClick(View v) {
                              final DatabaseReference dinosaursRef2 = database.getInstance().getReference("signup");
                              dinosaursRef2.addChildEventListener(new ChildEventListener() {
                                  @Override
                                  public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {


                                      for(DataSnapshot ds:dataSnapshot.getChildren()) {
                                          signup dinosaur = dataSnapshot.getValue(signup.class);


                                          if(new_year!="Change Year")
                                              dinosaursRef2.child(user.getUid()).child("year").setValue(new_year);
                                          Log.e("year----", new_year);



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



                              spin.setVisibility(View.GONE);
                              updyear.setText("Update your year");


                          }
                      });












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
                                               //
                                           }

                                           @Override
                                           public void onNothingSelected(AdapterView<?> parent) {
                                               //spin.setVisibility(View.GONE);
                                           }
                                       }


        );








       final String email=user.getEmail();                  //chnage this dirty code bhai









        del.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // get prompts.xml view
                LayoutInflater li = LayoutInflater.from(ScrollingActivity.this);
                //View promptsView = li.inflate(R.layout.fragment_blank, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        ScrollingActivity.this);


                // set dialog message
                alertDialogBuilder
                        //.setView(promptsView)
                        .setCancelable(false)
                        .setPositiveButton("Yes",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {

                                        //progressBar.setVisibility(View.VISIBLE);
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
                                                                text.setText("Your Profile is deleted");

                                                                Toast toast = new Toast(getApplicationContext());
                                                                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                                                                toast.setDuration(Toast.LENGTH_LONG);
                                                                toast.setView(layout);
                                                                SharedPreferences pr=getSharedPreferences("logged",MODE_PRIVATE);
                                                                SharedPreferences.Editor editor=pr.edit();
                                                                editor.putString("log","false");
                                                                editor.apply();
                                                                toast.show();startActivity(new Intent(ScrollingActivity.this, LoginActivity.class));

                                                                finish();
                                                            } else {
                                                                Toast.makeText(ScrollingActivity.this, "Failed to delete your account!", Toast.LENGTH_SHORT).show();
                                                               // progressBar.setVisibility(View.GONE);
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







        reset.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View arg0) {

                // get prompts.xml view
                LayoutInflater li = LayoutInflater.from(ScrollingActivity.this);
                View promptsView = li.inflate(R.layout.pop, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        ScrollingActivity.this);

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
                                                            Toast.makeText(ScrollingActivity.this, "Reset password email is sent!", Toast.LENGTH_SHORT).show();
                                                            SharedPreferences pr=getSharedPreferences("logged",MODE_PRIVATE);
                                                            SharedPreferences.Editor editor=pr.edit();
                                                            editor.putString("log","false");
                                                            editor.apply();
                                                            auth.signOut();
                                                        } else {
                                                            Toast.makeText(ScrollingActivity.this, "Failed to send reset email!", Toast.LENGTH_SHORT).show();

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














    public void onClickProfilePicture(View view) {

        if(auth.getCurrentUser()!=null) {
            Log.e("logged in",auth.getCurrentUser()+" ");
            user=auth.getCurrentUser();
        }

        final CharSequence[] items = {"Take Photo", "Choose from Library",
                "Cancel"};
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(ScrollingActivity.this);
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
        dp.setImageBitmap(bitmap);
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
        dp.setImageBitmap(bitmap);
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









    @Override
    protected void onResume() {
        super.onResume();
        //progressBar.setVisibility(View.GONE);
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