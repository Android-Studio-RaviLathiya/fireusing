package com.firebase.allcode.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.firebase.allcode.Activity.AndroidTutorial.DownloadActivty;
import com.firebase.allcode.Model.DownModel;
import com.firebase.allcode.R;

import java.util.ArrayList;

public class DownloadAdapter extends RecyclerView.Adapter<DownloadAdapter.MEMBER> implements RewardedVideoAdListener {

    ArrayList<DownModel> download = new ArrayList<>();
    Context context;
    private  RewardedVideoAd rewardedVideoAd;


    public DownloadAdapter(ArrayList<DownModel> download, Context context) {
        this.download = download;
        this.context = context;

    }

    @NonNull
    @Override
    public MEMBER onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.download_item, parent, false);
        return new MEMBER(view);


    }

    @Override
    public void onBindViewHolder(@NonNull MEMBER holder, int position) {

//        MobileAds.initialize(context, "ca-app-pub-3940256099942544~3347511713");
//        rewardedVideoAd = MobileAds.getRewardedVideoAdInstance(context);
//        rewardedVideoAd.setRewardedVideoAdListener((RewardedVideoAdListener) context);
        holder.name.setText(download.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(context, DownloadActivty.class);
                intent.putExtra("Name", download.get(position).getName());
                intent.putExtra("Uri", download.get(position).getUri());
                context.startActivity(intent);

            }
        });




//        if (MyPre.getAndroidmaincatename().equals("Android Tutorial")) {
//
//            if (MyPre.getandroid_tutorial_status().equals("0")) {
//                holder.btndownload.setEnabled(true);
//                holder.btndownload.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//
//
////                        loadRewardedVideoAd();
//
////                        if (rewardedVideoAd.isLoaded()) {
////                            rewardedVideoAd.show();
////                        }
//
//
////                        MyPre.setandroid_tutorial_status("1");
////                        Util.databaseReference.child("Download status" + "/" + MyPre.getFirebaseKEY() + "/" + "android_tutorial_status").setValue("1");
////                        MyPre.setandroid_tutorial_downlod_date(new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date()));
////                        Util.databaseReference.child("Download status" + "/" + MyPre.getFirebaseKEY() + "/" + "android_tutorial_downlod_date").setValue(MyPre.getandroid_tutorial_downlod_date());
////                        notifyDataSetChanged();
//                    }
//                });
//            } else {
//                holder.btndownload.setEnabled(false);
//            }
//
//        } else if (MyPre.getAndroidmaincatename().equals("Layout")) {
//
//            if (MyPre.getlayout_status().equals("0")) {
//                holder.btndownload.setEnabled(true);
//                holder.btndownload.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
////                    Util.downloadmanager(context, download.get(position).getName(), ".zip", DIRECTORY_DOWNLOADS, download.get(position).getUri());
//                        MyPre.setlayout_status("1");
////                        Util.databaseReference.child("Download status").child(MyPre.getFirebaseKEY()).child("layout_status").setValue("1");
//                        Util.databaseReference.child("Download status" + "/" + MyPre.getFirebaseKEY() + "/" + "layout_status").setValue("1");
//
//                        MyPre.setlayout_downlod_date(new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date()));
////                        Util.databaseReference.child("Download status").child(MyPre.getFirebaseKEY()).child("layout_downlod_date").setValue(MyPre.getlayout_downlod_date());
//                        Util.databaseReference.child("Download status" + "/" + MyPre.getFirebaseKEY() + "/" + "layout_downlod_date").setValue(MyPre.getandroid_tutorial_downlod_date());
//
//                        notifyDataSetChanged();
//                    }
//                });
//            } else {
//
//                holder.btndownload.setEnabled(false);
//            }
//
//        }


    }

    @Override
    public int getItemCount() {
        return download.size();
    }

    @Override
    public void onRewardedVideoAdLoaded() {

        Toast.makeText(context, "Loaded", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onRewardedVideoAdOpened() {
        Toast.makeText(context, "open", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onRewardedVideoStarted() {
        Toast.makeText(context, "start", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onRewardedVideoAdClosed() {
        loadRewardedVideoAd();
//        dialog.dismiss();
        Toast.makeText(context, "close", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onRewarded(RewardItem rewardItem) {

    }

    @Override
    public void onRewardedVideoAdLeftApplication() {

    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {
        Toast.makeText(context, "fail " + i , Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onRewardedVideoCompleted() {

    }

    public class MEMBER extends RecyclerView.ViewHolder {

        TextView name;
        Button btndownload;

        public MEMBER(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            btndownload = itemView.findViewById(R.id.btn_download);
        }
    }

    public void loadRewardedVideoAd() {

            rewardedVideoAd.loadAd(("ca-app-pub-3940256099942544/5224354917"),
                    new AdRequest.Builder().build());

    }


}

