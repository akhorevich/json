package com.example.nortti.txt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends BaseAdapter {
    private Context mContext;
    ArrayList<Item> items;

    public Adapter(Context mContext, ArrayList<Item> items) {
        this.mContext = mContext;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Item getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
       if (view == null){
           view = LayoutInflater.from(mContext).
                   inflate(R.layout.item, viewGroup, false);
       }

        Item current = getItem(i);
        TextView name = (TextView) view.findViewById(R.id.name);
        name.setText(current.getName());

        TextView surname = (TextView) view.findViewById(R.id.surname);
        surname.setText(current.getSurname());



        return view;
    }
}
