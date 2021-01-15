package com.firebase.allcode.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.allcode.Activity.AndroidTutorial.AndroidDownloadFile;
import com.firebase.allcode.Util.MyPre;
import com.firebase.allcode.R;

import java.util.ArrayList;

public class AndroidturAdapter extends RecyclerView.Adapter<AndroidturAdapter.MEMBER> {

    ArrayList<Object> mDataSet = new ArrayList<>();
    Context context;


    public AndroidturAdapter(ArrayList<Object> mDataSet, Context context) {
        this.mDataSet = mDataSet;
        this.context = context;
    }

    @NonNull
    @Override
    public MEMBER onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.androidtur_item, parent, false);
        return new MEMBER(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MEMBER holder, int position) {

        holder.name.setText(mDataSet.get(position).toString());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, AndroidDownloadFile.class));
                MyPre.setAndroidfilepos(mDataSet.get(position).toString());
                MyPre.setsub_cate_list_title(mDataSet.get(position).toString());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }


    public class MEMBER extends RecyclerView.ViewHolder {

        TextView name;

        public MEMBER(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
        }
    }
}

