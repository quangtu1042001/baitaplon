package com.example.baitaplondidong.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.baitaplondidong.R;
import com.example.baitaplondidong.object.AnhTruyen;
import com.example.baitaplondidong.object.ChapTruyen;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AnhTruyenAdapter extends ArrayAdapter<AnhTruyen> {
    private Context ct;
    public ArrayList<AnhTruyen> arr;
    public AnhTruyenAdapter(@NonNull Context context, int resource, @NonNull List<AnhTruyen> objects) {
        super(context, resource, objects);
        this.ct = context;
        this.arr = new ArrayList<>(objects);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) ct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_anhtruyen, null);
        }
        if(arr.size()>0){
            AnhTruyen anhTruyen = this.arr.get(position);
            ImageView imgAnhtruyen = convertView.findViewById(R.id.anh_truyen);

            RequestOptions options = new RequestOptions()
                    .placeholder(R.mipmap.ic_launcher_round)
                    .error(R.mipmap.ic_launcher_round);

//            Glide.with(this.ct).load(anhTruyen.getLinkAnh()).apply(options).into(imgAnhtruyen);
            Picasso.with(this.ct).load(anhTruyen.getLinkAnh())
                    .placeholder(R.drawable.progress_animation)
                    .error(R.mipmap.ic_launcher_round).into(imgAnhtruyen);
        }
        return convertView;
    }
}
