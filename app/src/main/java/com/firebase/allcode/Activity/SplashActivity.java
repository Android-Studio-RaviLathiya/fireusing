package com.firebase.allcode.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.firebase.allcode.R;
import com.firebase.allcode.Util.MyPre;

import java.util.ArrayList;

public class SplashActivity extends AppCompatActivity {

    private DatabaseReference databaseReference;
    ArrayList<String> androididmodels = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        MyPre.setAndroidID( Settings.Secure.getString(SplashActivity.this.getContentResolver(),
                Settings.Secure.ANDROID_ID));

//        Util.databaseReference.child("Download status").child(MyPre.getAndroidID()).child("status").setValue("0");
//       Queue xyz = (Queue) Util.databaseReference.child("Download status").child(MyPre.getAndroidID()).equalTo(MyPre.getAndroidID());




        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(SplashActivity.this, DisplayActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);



//
//        databaseReference.child("Download status").getRef().addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot snapshot) {
//
//                System.out.println(snapshot.getValue());
//
//                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
//                    androididmodels.add(postSnapshot.getValue().toString());
//                }
//
//                Log.e("Jsonss", new Gson().toJson(androididmodels));
////                Toast.makeText(SplashActivity.this, "" +  new Gson().toJson(androididmodels), Toast.LENGTH_SHORT).show();//prints "Do you have data? You'll love Firebase."
//            }
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//            }
//        });
    }
}