package com.project.myclg;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

//import com.example.sakshipc.profilemain.R;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class LoginActivity extends AppCompatActivity  {

    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    String email;
   String password;
    @InjectView(R.id.input_email)
    EditText _emailText;
    @InjectView(R.id.input_password) EditText _passwordText;
    @InjectView(R.id.btn_login)
    Button _loginButton;
    @InjectView(R.id.link_signup)
    TextView _signupLink;
    @InjectView(R.id.reset_password)
            TextView _reset;
    @InjectView(R.id.btn_reset)
            Button _mailButton;
    @InjectView(R.id.cancel)
    TextView _cancel;
    FirebaseAuth fauth;

    @InjectView(R.id.logo)
    ImageView logo;
    Switch sw;
    @InjectView(R.id.cv2)
    CardView tap;
    @InjectView(R.id.brief)
    TextView ok;
    String log;
    @InjectView(R.id.prob)
    ProgressBar progressBar;

    @InjectView(R.id.adView)
    AdView mAdView;
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
        _mailButton.setVisibility(View.GONE);
        _cancel.setVisibility(View.GONE);
        tap.setVisibility(View.GONE);

        progressBar.setVisibility(View.GONE);



        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this,R.color.logincolor));
        }












        SharedPreferences sp = getSharedPreferences("shr_pref", Activity.MODE_PRIVATE);
        String usr= sp.getString("username","");
        String psd= sp.getString("psd","");
        String chk=sp.getString("logged","");
        SharedPreferences lg = getSharedPreferences("logged", Activity.MODE_PRIVATE);
        log=lg.getString("log","false");
        Log.e("Logg State-----------",log);

        if(log.equals("true"))
        {
            Log.e("Han bhao fnn----------",log);
            Intent in0=new Intent(LoginActivity.this,MainActivity.class);
            startActivity(in0);

        }



        SwitchCompat switchCompat = (SwitchCompat) findViewById(R.id
                .mys);
        switchCompat.setChecked(false);
        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(isChecked){
                    SharedPreferences prf=getSharedPreferences("shr_pref",MODE_PRIVATE);
                    SharedPreferences.Editor editor=prf.edit();
                    editor.putString("username",_emailText.getText().toString());
                    editor.putString("psd",_passwordText.getText().toString());
                    editor.apply();



                }else{
                    SharedPreferences prf=getSharedPreferences("shr_pref",MODE_PRIVATE);

                    prf.edit().remove("username").commit();prf.edit().remove("psd").commit();


                }

            }
        });











        _emailText.setText(usr);
        _passwordText.setText(psd);








        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                progressBar.setVisibility(v.VISIBLE);
                login();
            }
        });

        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                 Log.e("Pressed","dfdfdfdfdfdfd");
                Intent intent = new Intent(getApplicationContext(), signemail.class);
                startActivity(intent);
            }
        });

        _cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                _emailText.setVisibility(v.VISIBLE);
                _passwordText.setVisibility(v.VISIBLE);
                _reset.setVisibility(v.VISIBLE);
                _loginButton.setVisibility(v.VISIBLE);
                _mailButton.setVisibility(v.GONE);
                _cancel.setVisibility(v.GONE);


            }
        });



        //To reset password via email in case of forgot passord
         _reset.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 //resting via email sending

                 _emailText.setVisibility(v.VISIBLE);
                 _passwordText.setVisibility(v.GONE);
                 _reset.setVisibility(v.GONE);
                 _loginButton.setVisibility(v.GONE);
                 _mailButton.setVisibility(v.VISIBLE);
                 _cancel.setVisibility(View.VISIBLE);


                             _mailButton.setOnClickListener(new View.OnClickListener() {
                                                 @Override
                                                 public void onClick(final View v) {

                                                   FirebaseAuth  auth = FirebaseAuth.getInstance();
                                                     if (!_emailText.getText().toString().trim().equals("")) {
                                                         auth.sendPasswordResetEmail(_emailText.getText().toString().trim())
                                                                 .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                     @Override
                                                                     public void onComplete(@NonNull Task<Void> task) {
                                                                         if (task.isSuccessful()) {
                                                                             Toast.makeText(LoginActivity.this, "Reset password email is sent!", Toast.LENGTH_SHORT).show();
                                                                             _passwordText.setVisibility(v.VISIBLE);
                                                                             _reset.setVisibility(v.VISIBLE);
                                                                             _loginButton.setVisibility(v.VISIBLE);
                                                                             _mailButton.setVisibility(v.GONE);

                                                                         } else {
                                                                             Toast.makeText(LoginActivity.this, "Failed to send reset email!", Toast.LENGTH_SHORT).show();

                                                                         }
                                                                     }
                                                                 });
                                                     } else {
                                                         _emailText.setError("Enter email");
                                                     }





                                                 }
                                             });









             }
         });






    }

    public void login() {
      //  Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            progressBar.setVisibility(View.GONE);
            return;
        }
       else{

            onLoginSuccess();
            SharedPreferences preef=getSharedPreferences("shr_pref",MODE_PRIVATE);
            SharedPreferences.Editor editor=preef.edit();
            editor.putString("logged","True");
            editor.apply();
        }

    /*    final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();*/

        String email = _emailText.getText().toString();
        this.email=email;
        String password = _passwordText.getText().toString();

      this.password=password;


    }

    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
       finish();
        System.exit(0);

    }

    public void onLoginSuccess() {
        _loginButton.setEnabled(false);

//   Log.e(email,password);
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();
        Log.e(email,password);
        fauth=FirebaseAuth.getInstance();
        Task<AuthResult> mauth = fauth.signInWithEmailAndPassword(email, password);
                Log.e("mauth", String.valueOf(mauth));
                mauth.addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
  Log.e("status", String.valueOf(task.isSuccessful()));



                if (task.isSuccessful()) {
                    Intent in = new Intent(LoginActivity.this,welcome.class);
                    //    in.putExtra("email", email);
                    SharedPreferences prf=getSharedPreferences("logged",MODE_PRIVATE);
                    SharedPreferences.Editor editor=prf.edit();
                    editor.putString("log","true");
                    editor.apply();

                    startActivity(in);
                    finish();
                } else {


                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.toast,
                            (ViewGroup) findViewById(R.id.toast_layout_root));

                    TextView text = (TextView) layout.findViewById(R.id.text);
                    text.setText("Try Again");
                    progressBar.setVisibility(View.GONE);

                    Toast toast = new Toast(getApplicationContext());
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setView(layout);
                    toast.show();

                    _loginButton.setEnabled(true);

                }
            }


        });
        _loginButton.setEnabled(true);
    }

    public void onLoginFailed() {
       // Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();


        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast,
                (ViewGroup) findViewById(R.id.toast_layout_root));

        TextView text = (TextView) layout.findViewById(R.id.text);
        text.setText("Login Failed");


        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();



        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

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


        return valid;
    }


}
