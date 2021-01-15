package com.firebase.allcode.Activity.AppCode;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.allcode.R;
import com.firebase.allcode.Util.MyPre;
import com.firebase.allcode.Util.Util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AppCodeDownloadActivity extends AppCompatActivity {

    private Button btn_download;
    TextView tv_download_info;
    String Name, Uris;
    ProgressDialog pDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_code_download);
        initView();

        if (MyPre.getapp_code_status().equals("0")) {

            btn_download.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    MyPre.setapp_code_status("1");
                    Util.databaseReference.child("Download status").child(MyPre.getFirebaseKEY()).child("app_code_status").setValue("1");
                    new DownloadTask(AppCodeDownloadActivity.this).execute(new String[]{Uris});


                }
            });
        } else {

            btn_download.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Dialog dialogs = new Dialog(AppCodeDownloadActivity.this);
                    dialogs.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                    dialogs.setContentView(R.layout.alert);

                    Window window = dialogs.getWindow();
                    WindowManager.LayoutParams wlp = window.getAttributes();
                    wlp.gravity = Gravity.CENTER;
                    window.setAttributes(wlp);
                    dialogs.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

                    Button ok = dialogs.findViewById(R.id.ok);
                    TextView title = dialogs.findViewById(R.id.title);
                    TextView sub_title = dialogs.findViewById(R.id.sub_title);

                    title.setText(R.string.app_code_title);
                    sub_title.setText(R.string.app_code_sub_title);

                    ok.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialogs.dismiss();
                        }
                    });
                    dialogs.show();

                }});


        }


    }

    private void initView() {
        btn_download = (Button) findViewById(R.id.btn_download);
        tv_download_info = (TextView) findViewById(R.id.tv_download_info);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            Name = bundle.getString("Name");
            Uris = bundle.getString("Uri");

        }
        tv_download_info.setText(Name);


    }

    public void back(View view) {
        onBackPressed();
    }
    public class DownloadTask extends AsyncTask<String, String, String> {
        private Context context33;
        StringBuilder sb2;

        public DownloadTask(Context context2) {
            this.context33 = context2;
        }

        public void onPreExecute() {
            super.onPreExecute();

            //loading.setVisibility(View.GONE);

            pDialog = new ProgressDialog(AppCodeDownloadActivity.this);

            pDialog.setCancelable(false);
            pDialog.show();
            pDialog.setContentView(R.layout.download_dialog);

            //downloading.setVisibility(View.VISIBLE);

        }

        public String doInBackground(String... args) {

            String dateStr = "04/05/2010";

            SimpleDateFormat curFormater = new SimpleDateFormat("dd/MM/yyyy");
            Date dateObj = null;
            try {
                dateObj = curFormater.parse(dateStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            SimpleDateFormat postFormater = new SimpleDateFormat("MMddyyyy");

            String newDateStr = postFormater.format(dateObj);

            sb2 = new StringBuilder();
            sb2.append(Environment.getExternalStorageDirectory());
            sb2.append("/Android Learn/");
            sb2.append(Name + newDateStr + SystemClock.uptimeMillis());
            sb2.append(".zip");

            try {
                URL u = new URL(args[0]);
                URLConnection conn = u.openConnection();
                int contentLength = conn.getContentLength();

                DataInputStream stream = new DataInputStream(u.openStream());

                byte[] buffer = new byte[contentLength];
                stream.readFully(buffer);
                stream.close();
                // DownloadPath = String.valueOf(sb2);
                DataOutputStream fos = new DataOutputStream(new FileOutputStream(String.valueOf(sb2)));
                fos.write(buffer);
                fos.flush();
                fos.close();
            } catch (FileNotFoundException e) {
                return null; // swallow a 404
            } catch (IOException e) {
                return null; // swallow a 404
            }

            return null;
        }

        public void onPostExecute(String args) {
            pDialog.dismiss();
            Toast.makeText(context33, "Download Completed", Toast.LENGTH_SHORT).show();
        }
    }


}