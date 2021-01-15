package com.firebase.allcode.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.allcode.Activity.AppCode.AppCodeDownloadActivity;
import com.firebase.allcode.Model.DownModel;
import com.firebase.allcode.R;

import java.util.ArrayList;

public class AppCodeAdapter extends RecyclerView.Adapter<AppCodeAdapter.MEMBER> {

    ArrayList<DownModel> download = new ArrayList<>();
    Context context;


    public AppCodeAdapter(ArrayList<DownModel> download, Context context) {
        this.download = download;
        this.context = context;
    }

    @NonNull
    @Override
    public MEMBER onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.app_code_item, parent, false);
        return new MEMBER(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MEMBER holder, int position) {

        holder.name.setText(download.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, AppCodeDownloadActivity.class);
                intent.putExtra("Name", download.get(position).getName());
                intent.putExtra("Uri", download.get(position).getUri());
                context.startActivity(intent);



            }
        });


    }

    @Override
    public int getItemCount() {
        return download.size();
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
}

