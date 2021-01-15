package com.firebase.allcode.Activity.AndroidTutorial;

import android.app.Dialog;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;
import com.firebase.allcode.Adapter.DownloadAdapter;
import com.firebase.allcode.Model.DownModel;
import com.firebase.allcode.Util.MyPre;
import com.firebase.allcode.R;
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
import java.util.ArrayList;
import java.util.Date;

public class AndroidDownloadFile extends AppCompatActivity {

    ProgressDialog pDialog;
    DownloadAdapter downloadAdapter;
    ArrayList<DownModel> download = new ArrayList<>();
    private RecyclerView rv;
    TextView titale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_file);

        pDialog = new ProgressDialog(AndroidDownloadFile.this);
        pDialog.setCancelable(false);

        initView();

        StorageReference storageRef = FirebaseStorage.getInstance().getReference().child(MyPre.getAndroidmaincatename() + "/" + MyPre.getAndroidfilepos());
        Dialog dialog = Util.startLoader(this, "" + MyPre.getsub_cate_list_title() + " File Loading...");

        storageRef.listAll().addOnSuccessListener(new OnSuccessListener<ListResult>() {
            @Override
            public void onSuccess(ListResult listResult) {

                for (StorageReference fileRef : listResult.getItems()) {

                    fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Util.stopLoader(dialog);

                            String removezip = fileRef.getName();
                            String newString = removezip.replace("T", " ");
                            String filename = newString.substring(0, newString.lastIndexOf("."));

                            String uri1 = uri.toString();
                            download.add(new DownModel(filename, uri1));
                            rv.setLayoutManager(new LinearLayoutManager(AndroidDownloadFile.this));
                            downloadAdapter = new DownloadAdapter(download, AndroidDownloadFile.this);
                            rv.setAdapter(downloadAdapter);

                        }
                    });
                }

            }
        });


    }

    private void downloadmanager(Context context, String filename, String fileExtension, String destinationDirectory, String uri) {

        DownloadManager downloadManager = (DownloadManager) this.getSystemService(Context.DOWNLOAD_SERVICE);


        Uri uri1 = Uri.parse(uri);
        DownloadManager.Request request = new DownloadManager.Request(uri1);
        Log.e("manager", request.toString());

        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalFilesDir(context, destinationDirectory, filename + fileExtension);
        request.setMimeType(".zip");
        downloadManager.enqueue(request);


    }


    private void initView() {
        rv = (RecyclerView) findViewById(R.id.rv);
        titale = (TextView) findViewById(R.id.titale);
        titale.setText(MyPre.getsub_cate_list_title());
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
            sb2.append(MyPre.getAndroidmaincatename() + newDateStr + SystemClock.uptimeMillis());
            sb2.append(".zip");

            try {
                URL u = new URL(args[0]);
                URLConnection conn = u.openConnection();
                int contentLength = conn.getContentLength();

                DataInputStream stream = new DataInputStream(u.openStream());

                byte[] buffer = new byte[contentLength];
                stream.readFully(buffer);
                stream.close();
                //DownloadPath = String.valueOf(sb2);
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

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pDialog.dismiss();
            Toast.makeText(context33, "download completed", Toast.LENGTH_SHORT).show();
        }

        //        public void onPostExecute(String args) {
//
//            isCheckDownload = true;
//
//            if (action == 0) {
//
//                //loading.setVisibility(View.GONE);
//                pDialog.dismiss();
//
//                if (SplashLaunchActivity.interstitialAd.isAdLoaded()) {
//                    SplashLaunchActivity.FBADSDialog(WallpaperViewActivity.this);
//                    SplashLaunchActivity.interstitialAd.setAdListener(new AbstractAdListener() {
//                        public void onInterstitialDismissed(Ad ad) {
//                            super.onInterstitialDismissed(ad);
//                            SplashLaunchActivity.interstitialAd.loadAd();
//
//                            Toast.makeText(WallpaperViewActivity.this, "Image Download Successfully", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//
//                } else {
//                    Toast.makeText(WallpaperViewActivity.this, "Image Download Successfully", Toast.LENGTH_SHORT).show();
//                }
//
//            } else if (action == 1) {
//
//                pDialog.dismiss();
//
//                File file123 = new File(DownloadPath);
//                Uri imageConvertToUri = FileProvider.getUriForFile(WallpaperViewActivity.this,
//                        BuildConfig.APPLICATION_ID + ".provider",
//                        file123);
//
//                String appName = getResources().getString(R.string.app_name);
//                final String appPackageName = getPackageName();
//                Intent share = new Intent("android.intent.action.SEND");
//                share.setType("image/jpeg");
//                share.setPackage("com.whatsapp");
//                share.putExtra(Intent.EXTRA_TEXT, appName + " \n : https://play.google.com/store/apps/details?id=" + appPackageName);
//                share.putExtra("android.intent.extra.STREAM", imageConvertToUri);
//                share.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//                startActivity(share);
//
//
//            } else if (action == 2) {
//
//                pDialog.dismiss();
//
//                File file123 = new File(DownloadPath);
//                Uri imageConvertToUri = FileProvider.getUriForFile(WallpaperViewActivity.this,
//                        BuildConfig.APPLICATION_ID + ".provider",
//                        file123);
//
//                String appName = getResources().getString(R.string.app_name);
//                final String appPackageName = getPackageName();
//                Intent shareIntent = new Intent();
//                shareIntent.setAction(Intent.ACTION_SEND);
//                shareIntent.putExtra(Intent.EXTRA_STREAM, imageConvertToUri);
//                shareIntent.putExtra(Intent.EXTRA_TEXT, appName + " \n : https://play.google.com/store/apps/details?id=" + appPackageName);
//                shareIntent.setType("image/*");
//                startActivity(Intent.createChooser(shareIntent, "Share Image"));
//
//
//            }
//
//        }
    }

    private void createDummyFiles(final int count) {

        int fileIndex = 0;
        while (fileIndex != count) {

            try {
                FileOutputStream fos = new FileOutputStream(new File(
                        Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "test_" + fileIndex + ".txt"));

                byte[] data = ("This is a string test." + fileIndex).getBytes();
                int repeat = 1000;
                while (--repeat != 0) {
                    fos.write(data);
                }

                fos.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

            fileIndex++;
        }
    }

}

//    StorageReference storageRef = FirebaseStorage.getInstance().getReference().child("Wallpaper/" + Catename);
//
//            storageRef.listAll().addOnSuccessListener(new OnSuccessListener<ListResult>() {
//@Override
//public void onSuccess(ListResult listResult) {
//
//        for (StorageReference fileRef : listResult.getItems()) {
//        fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//@Override
//public void onSuccess(Uri uri) {
//
//        aa = fileRef.getName();
//        String newString = aa.replace("T", " ");
//        String filename = newString.substring(0, newString.lastIndexOf("."));
//
//        list.add(new WallpaperData(uri.toString(), filename));
//
//        }
//        }).addOnSuccessListener(new OnSuccessListener<Uri>() {
//@Override
//public void onSuccess(Uri uri) {
//
//        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(WallpaperCategoryListActivity.this, 2);
//
//        recyclerviewWallpaper.setLayoutManager(mLayoutManager);
//
//        wallpaperListAdapter = new WallpaperListAdapter(WallpaperCategoryListActivity.this, Catename, list);
//
//        //FB Native Recycler List Ads Code
//        FBNativeAdAdapter fbAdapter = FBNativeAdAdapter.Builder
//        .with(SplashLaunchActivity.FB_Native, wallpaperListAdapter, 5)
//        .build();
//
//        recyclerviewWallpaper.setAdapter(fbAdapter);
//
//        loading.setVisibility(View.GONE);
//        }
//        }).addOnFailureListener(new OnFailureListener() {
//@Override
//public void onFailure(@NonNull Exception e) {
//
//        loading.setVisibility(View.GONE);
//
//        }
//        });
//        }
//
//        }
//        }).addOnFailureListener(new OnFailureListener() {
//
//@Override
//public void onFailure(@NonNull Exception e) {
//
//        loading.setVisibility(View.GONE);
//        //NoData.setVisibility(View.VISIBLE);
//        // Toast.makeText(getActivity(), "No Data 111", Toast.LENGTH_SHORT).show();
//
//        }
//        });