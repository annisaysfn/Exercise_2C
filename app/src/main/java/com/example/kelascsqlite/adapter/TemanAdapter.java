package com.example.kelascsqlite.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kelascsqlite.R;
import com.example.kelascsqlite.database.teman;

import java.util.ArrayList;

public class TemanAdapter extends RecyclerView.Adapter<TemanAdapter.TemanViewHolder> {
    private ArrayList<teman> listData;

    public TemanAdapter(ArrayList<teman> listData) {
        this.listData = listData;
    }

    @Override
    public TemanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInf = LayoutInflater.from(parent.getContext());
        View view = layoutInf.inflate(R.layout.row_data_teman,parent,false);
        return new TemanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TemanViewHolder holder, int position) {
        String nm,tlp;

        nm = listData.get(position).getNama();
        tlp = listData.get(position).getTelpon();

        holder.namaTxt.setTextColor(Color.BLUE);
        holder.namaTxt.setTextSize(20);
        holder.namaTxt.setText(nm);
        holder.telponTxt.setText(tlp);
    }

    @Override
    public int getItemCount() {
        return (listData != null)?listData.size() : 0; //Jika listData tdk kosong, maka kembalikan ukurannya. tapi kalo kosong, kembalikan 0.
    }

    public class TemanViewHolder extends RecyclerView.ViewHolder {
        private CardView cardku;
        private TextView namaTxt,telponTxt;
        public TemanViewHolder(View view) {
            super(view);
            cardku = (CardView) view.findViewById(R.id.kartuku);
            namaTxt = (TextView) view.findViewById(R.id.textNama);
            telponTxt = (TextView) view.findViewById(R.id.textTelpon);

            cardku.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    return true;
                }
            });
        }
    }
}
