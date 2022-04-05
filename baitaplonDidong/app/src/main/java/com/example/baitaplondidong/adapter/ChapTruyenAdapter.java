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

import com.example.baitaplondidong.R;
import com.example.baitaplondidong.object.ChapTruyen;

import java.util.ArrayList;
import java.util.List;

public class ChapTruyenAdapter extends ArrayAdapter<ChapTruyen> {
    private Context ct;
    public ArrayList<ChapTruyen> arr;
    public ChapTruyenAdapter(Context context, int resource, List<ChapTruyen> objects) {
        super(context, resource, objects);
        this.ct = context;
        this.arr = new ArrayList<>(objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) ct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_chap_truyen, null);
        }
        if(arr.size()>0) {
            TextView txvTenChaps, txvNgayNhap;
            ImageView imvTag;
            txvTenChaps = convertView.findViewById(R.id.txvTenChaps);
            txvNgayNhap = convertView.findViewById(R.id.txvNgayNhap);
            imvTag = convertView.findViewById(R.id.imgTagChap);
            ChapTruyen chapTruyen = arr.get(position);
            txvTenChaps.setText("Chap "+chapTruyen.getTenChap());
            txvNgayNhap.setText(chapTruyen.getNgayDang());
            if (chapTruyen.getIsMark().equals("1"))
            {
                imvTag.setVisibility(View.VISIBLE);
                imvTag.setImageResource(R.drawable.tag);
            }
            else
            {
                imvTag.setVisibility(View.INVISIBLE);
            }
        }
        return convertView;
    }

}
