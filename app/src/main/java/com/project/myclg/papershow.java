package com.project.myclg;

import android.app.Activity;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

//import com.example.sakshipc.profilemain.R;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;


public class papershow extends Activity {

    // button to show progress dialog
    Button btnShowProgress,btn;
    ImageButton btn1,btn2;
    // Progress Dialog
    private ProgressDialog pDialog;
    ImageView my_image;
    ProgressBar progBar;
    TextView text;
    private Handler mHandler = new Handler();
    private int mProgressStatus=0;




    // Progress dialog type (0 - for Horizontal progress bar)
    public static final int progress_bar_type = 0;

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
    String todayDate = dateFormat.format(new Date());
    // File url to download
    public static String file_url;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_papershow);
        file_url=getIntent().getStringExtra("url");
        // show progress bar button
        btnShowProgress = (Button) findViewById(R.id.btnProgressBar);
        // Image view to show image after downloading
        my_image = (ImageView) findViewById(R.id.my_image);
        /**
         * Show Progress bar click event
         *
         * */
        displayInputDialog();
        btnShowProgress.setVisibility(View.GONE);


    }


    /**
     * Showing Dialog
     * */
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case progress_bar_type: // we set this to 0
                pDialog = new ProgressDialog(this);
                pDialog.setMessage("Downloading file. Please wait...");
                pDialog.setIndeterminate(false);
                pDialog.setMax(100);

                pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                pDialog.setCancelable(true);
                pDialog.show();
                return pDialog;
            default:
                return null;



        }
    }



    /**
     * Background Async Task to download file
     * */
    class DownloadFileFromURL extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread
         * Show Progress Bar Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showDialog(progress_bar_type);
        }

        /**
         * Downloading file in background thread
         * */
        @Override
        protected String doInBackground(String... f_url) {
            int count;
            try {
                URL url = new URL(f_url[0]);
                URLConnection conection = url.openConnection();
                conection.connect();
                int lenghtOfFile = conection.getContentLength();





                // download the file
                InputStream input = new BufferedInputStream(url.openStream(), 8192);



                File folder = new File(Environment.getExternalStorageDirectory() + File.separator +"myCLG/papers/");
                if(!folder.exists())

                {
                    folder.mkdirs();
                    Log.d("SDcard", "Folder created");
                }





                // Output stream
                OutputStream output = new FileOutputStream("/sdcard/myCLG/papers/Q"+todayDate+".pdf");

                byte data[] = new byte[1024];

                long total = 0;

                while ((count = input.read(data)) != -1) {
                    total += count;
                    publishProgress(""+(int)((total*100)/lenghtOfFile));

                    // writing data to file
                    output.write(data, 0, count);
                }

                // flushing output
                output.flush();

                // closing streams
                output.close();
                input.close();

            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
            }

            return null;
        }

        /**
         * Updating progress bar
         * */
        protected void onProgressUpdate(String... progress) {
            // setting progress percentage
            pDialog.setProgress(Integer.parseInt(progress[0]));
        }

        /**
         * After completing background task
         * Dismiss the progress dialog
         * **/
        @Override
        protected void onPostExecute(String file) {
            // dismiss the dialog after the file was downloaded
             dismissDialog(progress_bar_type);


            // Displaying downloaded image into image view
            WebView mWebView=new WebView(getApplicationContext());
            mWebView.getSettings().setJavaScriptEnabled(true);
            mWebView.loadUrl("https://docs.google.com/gview?embedded=true&url="+file_url);
            setContentView(mWebView);
            LayoutInflater inflater = getLayoutInflater();
            View layout = inflater.inflate(R.layout.toast,
                    (ViewGroup) findViewById(R.id.toast_layout_root));

            String imagePath = Environment.getExternalStorageDirectory().toString() + "/myCLG/papers/Q"+todayDate+".pdf";
            //Toast.makeText(AndroidDownloadFileByProgressBarActivity.this,imagePath ,Toast.LENGTH_LONG).show();
            // setting downloaded into image view
            TextView text = (TextView) layout.findViewById(R.id.text);
            text.setText("PDF Download complete at"+imagePath);

            Toast toast = new Toast(getApplicationContext());
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setView(layout);
            toast.show();

            //Toast.makeText(papershow.this,"PDF Download complete at"+imagePath ,Toast.LENGTH_LONG).setView(layout).show();
            my_image.setImageDrawable(Drawable.createFromPath(imagePath));
               //donload location open on click notif



            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext());
            mBuilder.setSmallIcon(R.drawable.ic_menu_send);
            mBuilder.setLights(Color.BLUE, 500, 500);
            long[] pattern = {500,500,500,500,500,500,500,500,500};
            mBuilder.setVibrate(pattern);
            mBuilder.setStyle(new NotificationCompat.InboxStyle());
            mBuilder.setSound(Settings.System.DEFAULT_NOTIFICATION_URI);

            Notification notifyDetails = new Notification(R.drawable.ic_plusone_standard_off_client, "New Alert!", System.currentTimeMillis());


            mBuilder.setContentTitle("myCLG |File Downloaded!");
            //mBuilder.setContentText("File Download completed!"+todayDate+"pdf");
            NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            Uri uri = Uri.parse(Environment.getExternalStorageDirectory().getPath()+ "/myCLG/papers");
            Intent intent = new Intent(Intent.ACTION_VIEW,uri);

            PendingIntent contentIntent = PendingIntent.getActivity(getApplicationContext(), 1, intent, PendingIntent.FLAG_ONE_SHOT);
            mBuilder.setContentIntent(contentIntent);
// notificationID allows you to update the notification later on.
            mNotificationManager.notify(1, mBuilder.build());






        }


    }

    private void displayInputDialog() {

       final Dialog d=new Dialog(this);
        d.setTitle("Select an Action");
        d.setContentView(R.layout.dialog);
        btn1=(ImageButton) d.findViewById(R.id.imageButton);
        btn2=(ImageButton) d.findViewById(R.id.imageButton2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DownloadFileFromURL().execute(file_url);
                d.dismiss();
            }

        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d.dismiss();
                WebView mWebView=new WebView(getApplicationContext());
                mWebView.getSettings().setJavaScriptEnabled(true);
                mWebView.loadUrl("https://docs.google.com/gview?embedded=true&url="+file_url);
                setContentView(mWebView);
            }
        });
        d.show();
    }
    public void dosomething() {

        new Thread(new Runnable() {
            public void run() {
                final int presentage=0;
                while (mProgressStatus < 63) {
                    mProgressStatus += 1;
                    // Update the progress bar
                    mHandler.post(new Runnable() {
                        public void run() {
                            progBar.setProgress(mProgressStatus);
                            text.setText(""+mProgressStatus+"%");

                        }
                    });
                    try {



                        Thread.sleep(50);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }



    public void openFolder()
    {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        Uri uri = Uri.parse(Environment.getExternalStorageDirectory().getPath()+ "/myCLG/");
        startActivity(Intent.createChooser(intent, "Open folder"));
    }






}






