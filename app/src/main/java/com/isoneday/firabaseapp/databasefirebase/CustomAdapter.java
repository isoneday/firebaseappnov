package com.isoneday.firabaseapp.databasefirebase;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.isoneday.firabaseapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

class CustomAdapter extends BaseAdapter {
    Context act;
    ArrayList<ModelHewan> datahewan;
    DatabaseReference reference;
    Firebase firebase;

    public CustomAdapter(Context act, ArrayList<ModelHewan> datahewan, DatabaseReference reference, Firebase firebase) {
    this.act=act;
    this.datahewan=datahewan;
    this.reference=reference;
    this.firebase=firebase;
    }

    @Override
    public int getCount() {
        return datahewan.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater)act.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view =inflater.inflate(R.layout.listview_layout,viewGroup,false);
        MyHolder holder= new MyHolder(view);
        holder.nameText.setText(datahewan.get(i).getNama());
        holder.infoText.setText(datahewan.get(i).getInfo());
        PicassoClient client = new PicassoClient();
        client.tampilgambar(act,datahewan.get(i).getUrl(),holder.img);
        return view;
    }

    private class MyHolder {
        public TextView nameText,infoText;
        public ImageView img;
        public MyHolder(View view){
            nameText = (TextView)view.findViewById(R.id.nameTxt);
            infoText = (TextView)view.findViewById(R.id.infoTxt);
            img = (ImageView)view.findViewById(R.id.beachimage);

        }

    }

    public class PicassoClient {

        public void tampilgambar(Context a,String url,ImageView img){
            if (url!=null&&url.length()>0){
                Picasso.with(a).load(url).placeholder(R.drawable.noimage).into(img);
            }else{
                Picasso.with(a).load(R.drawable.logofirebase);

            }
        }
    }
}
