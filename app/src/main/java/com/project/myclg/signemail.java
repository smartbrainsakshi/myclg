package com.project.myclg;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

//import com.example.sakshipc.profilemain.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signemail extends AppCompatActivity {
EditText emailid,passwordid;
    ProgressBar pb;
String email,password;
    Button signup;
FirebaseAuth auth;
    TextView tp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signemail);
        emailid=(EditText)findViewById(R.id.input_email) ;
        passwordid=(EditText)findViewById(R.id.input_password) ;
        signup=(Button)findViewById(R.id.btn_signup);
        pb=(ProgressBar)findViewById(R.id.pb);

        final TextView log=(TextView)findViewById(R.id.link_login);
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });




        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this,R.color.logincolor));
        }

        pb.setVisibility(View.GONE);
        TextView textView =(TextView)findViewById(R.id.tp);
        textView.setClickable(true);
       textView.setMovementMethod(LinkMovementMethod.getInstance());
       String text = "<a href='http://prince.coolpage.biz/Policy.html'>Privacy Policy </a>";
       textView.setText(Html.fromHtml(text));

        final String[] uid = new String[1];
        signup.setOnClickListener(new View.OnClickListener() {
            @Override


            public void onClick(View v) {
                email=emailid.getText().toString();
                password=passwordid.getText().toString();
                auth=FirebaseAuth.getInstance();
                Log.e("reference",auth.toString());
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(signemail.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                               // Toast.makeText(signemail.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                 pb.setVisibility(View.VISIBLE);
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                   // uid[0] =task.getResult().getUser().getUid();
                                //    Log.e("uid",uid[0]);
                                    pb.setVisibility(View.GONE);
                                    Toast.makeText(signemail.this, "Authentication failed." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {

                                    Intent in =new Intent(signemail.this, SignupActivity.class);
                                    in.putExtra("email",email);
                                    in.putExtra("password",password);
                                    startActivity(in);
                                    finish();
                                }
                            }
                        });

            }
        });

    }
}
