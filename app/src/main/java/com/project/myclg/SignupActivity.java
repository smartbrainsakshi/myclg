package com.project.myclg;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

//import com.example.sakshipc.profilemain.R;
import com.firebase.client.Firebase;
import com.google.android.gms.internal.zzalt;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class
SignupActivity extends AppCompatActivity {

    private static final String TAG = "SignupActivity";
     public String email,password,st,st1;int pos,pos1;
     String[] year = new String[1];
     String[] branch = new String[1];
    @InjectView(R.id.input_name)
    EditText _nameText;
    @InjectView(R.id.input_email) EditText _emailText;
    EditText phone,user1;
    @InjectView(R.id.input_password) EditText _passwordText;
   // @InjectView(R.id.input_password) EditText _passwordText;
    @InjectView(R.id.btn_signup)
    Button _signupButton;
    @InjectView(R.id.link_login)
    TextView _loginLink;
    @InjectView(R.id.load)
    ProgressBar loader;


    FirebaseAuth fauth;
    FirebaseUser use;
    String url="data";
    Uri value;
    private ImageView imgProfilePicture;
    FirebaseStorage storage;
    FirebaseAuth firebaseAuth;
    FirebaseAuth auth;
    StorageReference ref;
    List<String> data;
    FirebaseUser user;
    private String strBase64;String username;
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    FirebaseDatabase database;
    private DatabaseReference mDatabase;
Spinner fSpinner,spinner2,spinner1;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.inject(this);
        imgProfilePicture= (ImageView)findViewById(R.id.imageView2);
        imgProfilePicture.setImageDrawable(getResources().getDrawable(R.drawable.usermale));
        email = getIntent().getStringExtra("email");
        password = getIntent().getStringExtra("password");
        _emailText.setText(email);
        _passwordText.setText(password);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        spinner2= (Spinner) findViewById(R.id.spinner2);
        phone=(EditText)findViewById(R.id.phone);
        spinner1= (Spinner) findViewById(R.id.spinner);
        user1=(EditText)findViewById(R.id.input_user);
        List<String> data = new ArrayList<String>();
        List<String> list = new ArrayList<String>();
        list.add("Select Branch");
        list.add("Chemical Engineering");
        list.add("Computer Science and Engineering ");
        list.add("Civil Engineering ");
        list.add("Electrical Engineering ");
        list.add("Electronics and Communication Engineering ");
        list.add("Mechanical Engineering ");
         auth = FirebaseAuth.getInstance();

        loader.setVisibility(View.GONE);

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this,R.color.logincolor));
        }






        if(auth.getCurrentUser()!=null) {
            Log.e("logged in",auth.getCurrentUser()+" ");
             use = auth.getCurrentUser();
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(dataAdapter);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                               @Override
                                               public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                 //  Toast.makeText(SignupActivity.this, String.valueOf(parent.getItemAtPosition(position)), Toast.LENGTH_LONG).show();
                                                   branch[0] =String.valueOf(parent.getItemAtPosition(position));
                                                   Log.e("spinner value----->>",year[0]);
                                                  st =spinner2.getSelectedItem().toString();
                                                   pos =spinner2.getSelectedItemPosition();
                                               }

                                               @Override
                                               public void onNothingSelected(AdapterView<?> parent) {

                                               }
                                           }


        );
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                               @Override
                                               public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                   Log.e("select",String.valueOf(parent.getItemAtPosition(position)));
                                                   //Toast.makeText(SignupActivity.this, String.valueOf(parent.getItemAtPosition(position)), Toast.LENGTH_LONG).show();
                                                   year[0] = String.valueOf(parent.getItemAtPosition(position));
                                                   st1 =spinner1.getSelectedItem().toString();
                                                   pos1 =spinner1.getSelectedItemPosition();
                                               }

                                               @Override
                                               public void onNothingSelected(AdapterView<?> parent) {

                                               }
                                           }
        );



       // PreferenceManager.getDefaultSharedPreferences(this).edit().putInt("value",fSpinner.getSelectedItemPosition()).commit();
      //  int value= PreferenceManager.getDefaultSharedPreferences(this).getInt("value",fSpinner.getSelectedItemPosition());
        _signupButton=(Button)findViewById(R.id.btn_signup);
                _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
                Log.e("email", "entered");


            }
        });

        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(SignupActivity.this,LoginActivity.class);
                startActivity(in);

            }
        });
    }


    @Override
    public void onBackPressed()

    {
         use.delete();
       // Toast.makeText(SignupActivity.this, "Acccount", Toast.LENGTH_SHORT).show();
        finish();
    }




    public void signup() {
      //  Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }
else
        {
            onSignupSuccess();
        }
        _signupButton.setEnabled(false);


    }


    public void onSignupSuccess() {
        String name = _nameText.getText().toString();
       // String email = _emailText.getText().toString();
       // String password = _passwordText.getText().toString();
        String phonenumber= phone.getText().toString();
        username = user1.getText().toString();
        final String[] uid = new String[1];
        storage= FirebaseStorage.getInstance();
         ref = storage.getReference("photos/image");
        Uri uri = Uri.parse("android.resource://com.example.sakshipc.profilemain/drawable/download");

        try {
            InputStream stream = getContentResolver().openInputStream(uri);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ref.child(String.valueOf(use.getUid())+"/"+"profilepic").putFile(uri).addOnSuccessListener(
                new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Log.e("succes","folder");
                    }
                }
        );;

        //  String username;
        signup b=new signup(password,email,name,phonenumber,branch[0],year[0],username,url);
        fauth= FirebaseAuth.getInstance();
        Log.e("connect", String.valueOf(fauth));
       // Log.e("connect", String.valueOf(fauth.getCurrentUser()));

        mDatabase.child("signup").child(fauth.getCurrentUser().getUid()).setValue(b);
        Log.e("added","data");



        _signupButton.setEnabled(true);
        setResult(RESULT_OK, null);








        // get prompts.xml view
        LayoutInflater li = LayoutInflater.from(SignupActivity.this);
        View promptsView = li.inflate(R.layout.resetemail, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                SignupActivity.this);

        alertDialogBuilder.setView(promptsView);
          email=auth.getCurrentUser().getEmail();

        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("SEND",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                loader.setVisibility(View.VISIBLE);
                                auth.sendPasswordResetEmail(email)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Toast.makeText(SignupActivity.this, "Reset password email is sent!", Toast.LENGTH_SHORT).show();

                                                    loader.setVisibility(View.GONE);
                                                    auth.signOut();
                                                    Toast.makeText(SignupActivity.this, "Login with the new password", Toast.LENGTH_SHORT).show();
                                                    finish();
                                                    Intent in=new Intent(SignupActivity.this,LoginActivity.class);
                                                    startActivity(in);

                                                } else {
                                                    loader.setVisibility(View.GONE);
                                                    Toast.makeText(SignupActivity.this, "Failed to send reset email!", Toast.LENGTH_SHORT).show();

                                                }
                                            }
                                        });
                            }
                        });


        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
















    }
    public void onClickProfilePicture(View view) {

        if(auth.getCurrentUser()!=null) {
            Log.e("logged in",auth.getCurrentUser()+" ");
            user=auth.getCurrentUser();
        }

        final CharSequence[] items = {"Take Photo", "Choose from Library",
                "Cancel"};
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(SignupActivity.this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Take Photo")) {
                    storage= FirebaseStorage.getInstance();
                    ref=storage.getReference("photos/image");
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, REQUEST_CAMERA);
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
        imgProfilePicture.setImageBitmap(bitmap);
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
        imgProfilePicture.setImageBitmap(bitmap);
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


    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = _nameText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();
String phonenumber=phone.getText().toString();
        String username=user1.getText().toString();
        if (name.isEmpty() || name.length() < 3) {
            _nameText.setError("at least 3 characters");
            valid = false;
        } else {
            _nameText.setError(null);
        }
        if (phonenumber.isEmpty() || phonenumber.length() < 10) {
            phone.setError("Enter valide Phone Number");
            valid = false;
        } else {
            phone.setError(null);
        }
        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        if (username.isEmpty() ||user1.length() != 10) {
            user1.setError("Valid Registration Number required");
            valid = false;
        } else {
            user1.setError(null);
        }




        if (st=="Select Branch"||pos==0) {
            Toast.makeText(SignupActivity.this, "Branch Required", Toast.LENGTH_SHORT).show();
            valid = false;
        } else {
            _passwordText.setError(null);
        }


        if (st1=="Select Year"||pos1==0) {
            Toast.makeText(SignupActivity.this, "Year Required", Toast.LENGTH_SHORT).show();
            valid = false;
        } else {
            _passwordText.setError(null);

        }
        if(imgProfilePicture.getDrawable() == null)
        {
            valid=false;
        }



        return valid;




    }






}


