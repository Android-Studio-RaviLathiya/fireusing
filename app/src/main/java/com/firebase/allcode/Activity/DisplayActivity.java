package com.firebase.allcode.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.firebase.allcode.Activity.AndroidTutorial.AndroidMainActivity;
import com.firebase.allcode.Activity.AppCode.AppCodeActivity;
import com.firebase.allcode.R;
import com.firebase.allcode.Util.MyPre;
import com.firebase.allcode.Util.Util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class DisplayActivity extends AppCompatActivity {

    private TextView android;
    private TextView layout;
    private TextView appcode;
    private static final int STORAGE_PERMISSION_CODE = 101;
    Animation topanim;
    private CardView card1;
    private CardView card2;
    private CardView card3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        initView();
        animation();
        buttonclick();
        CheckFirebaseId();

    }

    private void buttonclick() {

        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisplayActivity.this, AndroidMainActivity.class);
                startActivity(intent);
                MyPre.setAndroidmaincatename("Android Tutorial");
            }
        });

        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisplayActivity.this, AndroidMainActivity.class);
                startActivity(intent);
                MyPre.setAndroidmaincatename("Layout");

            }
        });

        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisplayActivity.this, AppCodeActivity.class);
                startActivity(intent);
                MyPre.setAndroidmaincatename("App Code");

            }
        });
    }

    private void animation() {
        topanim = AnimationUtils.loadAnimation(DisplayActivity.this, R.anim.top_animation);
        android.setAnimation(topanim);
        layout.setAnimation(topanim);
        appcode.setAnimation(topanim);
    }

    private void CheckFirebaseId() {

        Util.databaseReference.child("Download status").orderByChild("android_id").equalTo(MyPre.getAndroidID()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                try {
                    if (snapshot.exists()) {

                        for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                            String clubkey = childSnapshot.getKey();
                            MyPre.setFirebaseKEY(clubkey);
                        }
                        checkfirebasestatus();
                    } else {
                        FirebaseAddId();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }

    private void checkfirebasestatus() {

        MyPre.setandroid_tutorial_Todaydate(new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date()));
        Util.databaseReference.child("Download status" + "/" + MyPre.getFirebaseKEY() + "/" + "android_tutorial_Todaydate").setValue(MyPre.getandroid_tutorial_Todaydate());

        MyPre.setlayout_Todaydate(new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date()));
        Util.databaseReference.child("Download status" + "/" + MyPre.getFirebaseKEY() + "/" + "layout_Todaydate").setValue(MyPre.getlayout_Todaydate());

        Util.databaseReference.child("Download status").child(MyPre.getFirebaseKEY()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                System.out.println(snapshot.getValue());  //prints "Do you have data? You'll love Firebase."


                MyPre.setandroid_tutorial_status(snapshot.child("android_tutorial_status").getValue().toString());
                MyPre.setlayout_status(snapshot.child("layout_status").getValue().toString());
                MyPre.setapp_code_status(snapshot.child("app_code_status").getValue().toString());

                MyPre.setandroid_tutorial_downlod_date(snapshot.child("android_tutorial_downlod_date").getValue().toString());
                MyPre.setlayout_downlod_date(snapshot.child("layout_downlod_date").getValue().toString());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }

    private void FirebaseAddId() {

        MyPre.setandroid_tutorial_status("0");
        MyPre.setlayout_status("0");
        MyPre.setapp_code_status("0");
        MyPre.setandroid_tutorial_Todaydate(new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date()));
        MyPre.setandroid_tutorial_downlod_date("11-11-2019");
        MyPre.setlayout_Todaydate(new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date()));
        MyPre.setlayout_downlod_date("11-11-2019");

        HashMap<String, Object> map = new HashMap<>();
        map.put("android_id", MyPre.getAndroidID());     //creat your object
        map.put("android_tutorial_status", MyPre.getandroid_tutorial_status());
        map.put("layout_status", MyPre.getlayout_status());
        map.put("app_code_status", MyPre.getapp_code_status());
        map.put("android_tutorial_Todaydate", MyPre.getandroid_tutorial_Todaydate());
        map.put("android_tutorial_downlod_date", MyPre.getandroid_tutorial_downlod_date());
        map.put("layout_Todaydate", MyPre.getlayout_Todaydate());
        map.put("layout_downlod_date", MyPre.getlayout_downlod_date());

        Util.databaseReference.child("Download status").push()
                .setValue(map)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });

        Util.databaseReference.child("Download status").orderByChild("android_id").equalTo(MyPre.getAndroidID()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                    String clubkey = childSnapshot.getKey();
                    MyPre.setFirebaseKEY(clubkey);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void initView() {
        android = (TextView) findViewById(R.id.android);
        layout = (TextView) findViewById(R.id.layout);
        appcode = (TextView) findViewById(R.id.appcode);
        card1 = (CardView) findViewById(R.id.card1);
        card2 = (CardView) findViewById(R.id.card2);
        card3 = (CardView) findViewById(R.id.card3);
        checkPermission(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                STORAGE_PERMISSION_CODE);
    }

    private void CreateFolder() {

        File file = new File(Environment.getExternalStorageDirectory() + "/Android Learn/");

        if (file.mkdir()) {
            System.out.println("Directory created");
        }


    }

    public void checkPermission(String permission, int requestCode) {
        if (ContextCompat.checkSelfPermission(DisplayActivity.this, permission)
                == PackageManager.PERMISSION_DENIED) {

            // Requesting the permission
            ActivityCompat.requestPermissions(DisplayActivity.this,
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
                Toast.makeText(DisplayActivity.this,
                        "Storage Permission Granted",
                        Toast.LENGTH_SHORT)
                        .show();
                CreateFolder();


            } else {
//                Toast.makeText(DisplayActivity.this,
//                        "Storage Permission Denied",
//                        Toast.LENGTH_SHORT)
//                        .show();

            }
        }
    }


}