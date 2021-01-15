package com.firebase.allcode.Activity.AndroidTutorial;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.firebase.allcode.Adapter.AndroidturAdapter;
import com.firebase.allcode.R;
import com.firebase.allcode.Util.MyPre;
import com.firebase.allcode.Util.Util;

import java.util.ArrayList;

public class AndroidMainActivity extends AppCompatActivity {


    private RecyclerView rv;
    DatabaseReference databaseReference;
    ArrayList<Object> mDataSet = new ArrayList<>();
    AndroidturAdapter androidadepter;
    private CardView card1;
    private TextView titale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();


        titale.setText(MyPre.getAndroidmaincatename());
        databaseReference = FirebaseDatabase.getInstance().getReference();
        Dialog dialog = Util.startLoader(this, "" + MyPre.getAndroidmaincatename() + " Loading...");
        databaseReference.child(MyPre.getAndroidmaincatename()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Util.stopLoader(dialog);
                for (DataSnapshot snapshot : dataSnapshot.child("0").child("Category").getChildren()) {
                    String catename = snapshot.child("catename").getValue().toString();
                    mDataSet.add(catename);

                }
                rv.setLayoutManager(new LinearLayoutManager(AndroidMainActivity.this));
                androidadepter = new AndroidturAdapter(mDataSet, AndroidMainActivity.this);
                rv.setAdapter(androidadepter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Util.stopLoader(dialog);
                Toast.makeText(AndroidMainActivity.this, "Error " + error, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initView() {
        rv = (RecyclerView) findViewById(R.id.rv);
        card1 = (CardView) findViewById(R.id.card1);
        titale = (TextView) findViewById(R.id.titale);

        if (MyPre.getAndroidmaincatename().equals("Android Tutorial")) {

            if (MyPre.getandroid_tutorial_Todaydate().equals(MyPre.getandroid_tutorial_downlod_date())) {
                MyPre.setandroid_tutorial_status("1");
                Util.databaseReference.child("Download status" + "/" + MyPre.getFirebaseKEY() + "/" + "android_tutorial_status").setValue("1");

            } else {
                MyPre.setandroid_tutorial_status("0");
                Util.databaseReference.child("Download status" + "/" + MyPre.getFirebaseKEY() + "/" + "android_tutorial_status").setValue("0");

            }


        } else if (MyPre.getAndroidmaincatename().equals("Layout")) {

            if (MyPre.getlayout_Todaydate().equals(MyPre.getlayout_downlod_date())) {
                MyPre.setandroid_tutorial_status("1");
                Util.databaseReference.child("Download status" + "/" + MyPre.getFirebaseKEY() + "/" + "layout_status").setValue("1");

            } else {
                MyPre.setandroid_tutorial_status("0");
                Util.databaseReference.child("Download status" + "/" + MyPre.getFirebaseKEY() + "/" + "layout_status").setValue("0");

            }

        }


    }

    public void back(View view) {
        onBackPressed();
    }
}
