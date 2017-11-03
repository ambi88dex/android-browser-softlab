//package com.example.anishkelkar.androidbrowsersoftlab;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//public class adapter extends ArrayAdapter
//{
//    List list =new ArrayList();
//    public adapter(Context context, int resource)
//    {
//        super(context, resource);
//    }
//
//    @Override
//    public void add(Object object) {
//        super.add(object);
//        list.add(object);
//    }
//    static class datahandler
//    {
//        TextView header;
//        TextView url;
//    }
//    @Override
//    public int getCount() {
//        return this.list.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return this.list.get(position);
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        View row=convertView;
//        datahandler handler;
//        if(convertView==null)
//        {
//            LayoutInflater inflater=(LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            row=inflater.inflate(R.layout.row,parent,false);
//            handler=new datahandler();
//            handler.header=(TextView)row.findViewById(R.id.header);
//            handler.url=(TextView)row.findViewById(R.id.url);
//            row.setTag(handler);
//        }
//        else
//        {
//            handler=(datahandler)row.getTag();
//        }
//        data_provider dp;
//        dp=(data_provider)this.getItem(position);
//        handler.header.setText(dp.getHeader());
//        handler.url.setText(dp.getUrl());
//        return row;
//    }
//}
