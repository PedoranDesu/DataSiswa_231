package com.pedoran.datasiswa_231.ui.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.pedoran.datasiswa_231.R;
import com.pedoran.datasiswa_231.database.DatabaseSiswa;
import com.pedoran.datasiswa_231.database.Siswa;
import com.pedoran.datasiswa_231.ui.Dashboard;

public class InputData extends AppCompatActivity {
    TextView tNis,tNama,tLahir,tGender,tAlamat,label;
    Button btnSubmit;
    Context context;
    String nis,aksi = "Submit";
    Siswa updated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data);
        context = this;

        aksi = getIntent().getStringExtra("UPDATE_ACTION");
        updated = getIntent().getParcelableExtra("UPDATE_INTENT");
        if(aksi==null){
            aksi = "Submit";
        }else{
            nis = updated.getNis();
        }

        tNis = findViewById(R.id.inputNIS);
        tNama = findViewById(R.id.inputNama);
        tLahir = findViewById(R.id.inputTglLahir);
        tGender = findViewById(R.id.inputGender);
        tAlamat = findViewById(R.id.inputAlamat);
        label = findViewById(R.id.inputLabel);

        btnSubmit = findViewById(R.id.btnInputForm);
        if(aksi.equals("Update")){
            btnSubmit.setText("Update Data");
            tNis.setText(nis);
            tNis.setFocusable(false);
            tNama.setText(updated.getNama());
            tLahir.setText(updated.getTanggalLahir());
            tGender.setText(updated.getJenisKelamin());
            tAlamat.setText(updated.getAlamat());
            label.setText("UPDATE DATA");
        }

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseSiswa db = new DatabaseSiswa(context);
                Siswa jalmi = new Siswa();
                String btnStatus = btnSubmit.getText().toString();
                if(btnStatus.equals("Submit Data")){
                    jalmi.setNis(tNis.getText().toString());
                    jalmi.setNama(tNama.getText().toString());
                    jalmi.setTanggalLahir(tLahir.getText().toString());
                    jalmi.setJenisKelamin(tGender.getText().toString());
                    jalmi.setAlamat(tAlamat.getText().toString());
                    db.insert(jalmi);
                    Intent pindah = new Intent(context,Dashboard.class);
                    context.startActivity(pindah);
                }
                if(btnStatus.equals("Update Data")){
                    jalmi.setNis(tNis.getText().toString());
                    jalmi.setNama(tNama.getText().toString());
                    jalmi.setTanggalLahir(tLahir.getText().toString());
                    jalmi.setJenisKelamin(tGender.getText().toString());
                    jalmi.setAlamat(tAlamat.getText().toString());
                    db.update(jalmi);
                    Intent pindah = new Intent(context,LihatData.class);
                    context.startActivity(pindah);
                }

            }
        });
    }


}
