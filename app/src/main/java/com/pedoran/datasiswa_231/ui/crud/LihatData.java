package com.pedoran.datasiswa_231.ui.crud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.pedoran.datasiswa_231.R;
import com.pedoran.datasiswa_231.adapter.SiswaAdapter;
import com.pedoran.datasiswa_231.database.DatabaseSiswa;
import com.pedoran.datasiswa_231.database.Siswa;
import com.pedoran.datasiswa_231.ui.Dashboard;

import java.util.List;

public class LihatData extends AppCompatActivity implements SiswaAdapter.UserActionListener{
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    List<Siswa> listSiswaInfo;
    Context context;

    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_data);
        context = this;

        recyclerView = findViewById(R.id.recyclerview);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        setupRV();

        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pindah = new Intent(context,InputData.class);
                startActivity(pindah);
            }
        });
    }

    public void setupRV(){
        DatabaseSiswa db = new DatabaseSiswa(this);
        listSiswaInfo = db.selectUserData();
        SiswaAdapter adapter = new SiswaAdapter(this,listSiswaInfo,this);

        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onUserAction(final Siswa siswa) {
//        Toast.makeText(context, "CLICKED!", Toast.LENGTH_SHORT).show();
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Pilihan")
                .setPositiveButton("Lihat Detail", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
//                        Toast.makeText(context, "LIHAT DATA!", Toast.LENGTH_SHORT).show();
                        Intent detailData = new Intent(context,DetailData.class);
                        detailData.putExtra("DETAIL_INTENT",siswa);
                        context.startActivity(detailData);
                    }
                })
                .setNegativeButton("Ubah Data", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
//                        Toast.makeText(context, "UBAH DATA!", Toast.LENGTH_SHORT).show();
                        Intent updateData = new Intent(context,InputData.class);
                        updateData.putExtra("UPDATE_INTENT",siswa);
                        updateData.putExtra("UPDATE_ACTION","Update");
                        context.startActivity(updateData);
                    }
                })
                .setNeutralButton("Hapus Data", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
//                        Toast.makeText(context, "HAPUS DATA!", Toast.LENGTH_SHORT).show();
                        DatabaseSiswa db = new DatabaseSiswa(context);
                        db.delete(siswa.getNis());
                        setupRV();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onBackPressed() {
        Intent pindah = new Intent(this,Dashboard.class);
        startActivity(pindah);
    }
}
