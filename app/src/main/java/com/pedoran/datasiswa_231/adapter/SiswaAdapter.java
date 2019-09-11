package com.pedoran.datasiswa_231.adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pedoran.datasiswa_231.R;
import com.pedoran.datasiswa_231.database.DatabaseSiswa;
import com.pedoran.datasiswa_231.database.Siswa;
import com.pedoran.datasiswa_231.ui.crud.DetailData;

import java.util.List;

public class SiswaAdapter extends RecyclerView.Adapter<SiswaAdapter.SiswaHolder>{
    List<Siswa> listSiswa;
    Context context;
    UserActionListener listener;

    public interface UserActionListener{
        void onUserAction(Siswa siswa);
    }

    public SiswaAdapter(Context context, List<Siswa> siswaList, UserActionListener listener){
        this.context = context;
        this.listSiswa = siswaList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SiswaAdapter.SiswaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.siswa_item,parent,false);
        SiswaHolder sh = new SiswaHolder(v);
        return sh;
    }

    @Override
    public void onBindViewHolder(@NonNull SiswaAdapter.SiswaHolder holder, int position) {
        final Siswa orang = listSiswa.get(position);
        holder.txtNama.setText(orang.getNama());
        holder.txtGender.setText(orang.getJenisKelamin());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onUserAction(orang);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listSiswa.size();
    }

    public class SiswaHolder extends RecyclerView.ViewHolder {
        TextView txtNama,txtGender;
        LinearLayout container;

        public SiswaHolder(@NonNull View itemView) {
            super(itemView);
            txtNama = itemView.findViewById(R.id.ctxtName);
            txtGender = itemView.findViewById(R.id.ctxtGender);
            container = itemView.findViewById(R.id.lay_item_siswa);
        }
    }
}
