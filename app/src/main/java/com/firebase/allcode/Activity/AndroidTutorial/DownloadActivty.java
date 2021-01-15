package com.firebase.allcode.Activity.AndroidTutorial;


import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.firebase.allcode.BuildConfig;
import com.firebase.allcode.R;
import com.firebase.allcode.Util.MyPre;
import com.firebase.allcode.Util.Util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class DownloadActivty extends AppCompatActivity {

    String Name, Uris;
    private Button btn_download;
    TextView tv_download_info;
    ProgressDialog pDialog;
    private static final int STORAGE_PERMISSION_CODE = 101;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_activty);
        initView();
        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            Name = bundle.getString("Name");
            Uris = bundle.getString("Uri");

        }
        tv_download_info.setText(Name);
        chechdownload();


    }

    private void chechdownload() {

        if (MyPre.getAndroidmaincatename().equals("Android Tutorial")) {

            if (MyPre.getandroid_tutorial_status().equals("0")) {

                btn_download.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        loadRewardedVideoAd();
//                        if (rewardedVideoAd.isLoaded()) {
//                            rewardedVideoAd.show();
//                        }


                        btn_download.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                checkPermission(
                                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                        STORAGE_PERMISSION_CODE);


                                new DownloadTask(DownloadActivty.this).execute(new String[]{Uris});

                                MyPre.setandroid_tutorial_status("1");
                                Util.databaseReference.child("Download status" + "/" + MyPre.getFirebaseKEY() + "/" + "android_tutorial_status").setValue("1");
                                MyPre.setandroid_tutorial_downlod_date(new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date()));
                                Util.databaseReference.child("Download status" + "/" + MyPre.getFirebaseKEY() + "/" + "android_tutorial_downlod_date").setValue(MyPre.getandroid_tutorial_downlod_date());
                            }
                        });


                    }
                });
            } else {
//                btn_download.setEnabled(false);
//                        oprdialog

                btn_download.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Dialog dialogs = new Dialog(DownloadActivty.this);
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

                        title.setText(R.string.title);
                        sub_title.setText(R.string.sub_title);

                        ok.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialogs.dismiss();
                            }
                        });
                        dialogs.show();

                    }
                });


            }

        } else if (MyPre.getAndroidmaincatename().equals("Layout")) {

            if (MyPre.getlayout_status().equals("0")) {
                btn_download.setEnabled(true);
                btn_download.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        btn_download.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                checkPermission(
                                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                        STORAGE_PERMISSION_CODE);

                                new DownloadTask(DownloadActivty.this).execute(new String[]{Uris});
                                MyPre.setlayout_status("1");
                                Util.databaseReference.child("Download status" + "/" + MyPre.getFirebaseKEY() + "/" + "layout_status").setValue("1");
                                MyPre.setlayout_downlod_date(new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date()));
                                Util.databaseReference.child("Download status" + "/" + MyPre.getFirebaseKEY() + "/" + "layout_downlod_date").setValue(MyPre.getandroid_tutorial_downlod_date());

                            }
                        });


                    }
                });
            } else {

//                btn_download.setEnabled(false);
//                        dialog

                btn_download.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Dialog dialogs = new Dialog(DownloadActivty.this);
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

                        title.setText(R.string.title);
                        sub_title.setText(R.string.sub_title);

                        ok.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialogs.dismiss();
                            }
                        });
                        dialogs.show();

                    }
                });


            }

        }

    }


    private void initView() {
        btn_download = (Button) findViewById(R.id.btn_download);
        tv_download_info = (TextView) findViewById(R.id.tv_download_info);
    }

    public void checkPermission(String permission, int requestCode) {
        if (ContextCompat.checkSelfPermission(DownloadActivty.this, permission)
                == PackageManager.PERMISSION_DENIED) {

            // Requesting the permission
            ActivityCompat.requestPermissions(DownloadActivty.this,
                    new String[]{permission},
                    requestCode);
        } else {
//            Toast.makeText(DisplayActivity.this,
//                    "Permission already granted",
//                    Toast.LENGTH_SHORT)
//                    .show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super
                .onRequestPermissionsResult(requestCode,
                        permissions,
                        grantResults);

        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(DownloadActivty.this,
                        "Storage Permission Granted",
                        Toast.LENGTH_SHORT)
                        .show();
                CreateFolder();


            } else {
                Toast.makeText(DownloadActivty.this,
                        "Storage Permission Denied",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }


    private void CreateFolder() {

        File file = new File(Environment.getExternalStorageDirectory() + "/Android Learn/");

        if (file.mkdir()) {
            System.out.println("Directory created");
        }


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

            pDialog = new ProgressDialog(DownloadActivty.this);

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