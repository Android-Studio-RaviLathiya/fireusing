package com.firebase.allcode.Activity.AppCode;

import android.app.Dialog;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;
import com.firebase.allcode.Adapter.AppCodeAdapter;
import com.firebase.allcode.Model.DownModel;
import com.firebase.allcode.R;
import com.firebase.allcode.Util.MyPre;
import com.firebase.allcode.Util.Util;

import java.util.ArrayList;

public class AppCodeActivity extends AppCompatActivity {

    private RecyclerView appcodeRv;
    ArrayList<DownModel> download = new ArrayList<>();
    AppCodeAdapter appCodeAdapter;
    private CardView card1;
    private TextView titale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_code);
        initView();
        titale.setText(MyPre.getAndroidmaincatename());


        StorageReference storageRef = FirebaseStorage.getInstance().getReference().child(MyPre.getAndroidmaincatename());
        Dialog dialog = Util.startLoader(this, "App Code Loading...");
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
                            Log.e("namess", filename + " " + uri1);
                            download.add(new DownModel(filename, uri1));

                            GridLayoutManager manager = new GridLayoutManager(AppCodeActivity.this, 2, GridLayoutManager.VERTICAL, false);
                            appcodeRv.setLayoutManager(manager);
                            appCodeAdapter = new AppCodeAdapter(download, AppCodeActivity.this);
                            appcodeRv.setAdapter(appCodeAdapter);

                        }
                    });
                }

            }
        });


    }

    private void initView() {
        appcodeRv = (RecyclerView) findViewById(R.id.appcode_rv);
        card1 = (CardView) findViewById(R.id.card1);
        titale = (TextView) findViewById(R.id.titale);
    }

    public void back(View view) {
        onBackPressed();
    }
}