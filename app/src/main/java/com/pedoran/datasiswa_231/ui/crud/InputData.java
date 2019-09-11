package com.pedoran.datasiswa_231.ui.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.pedoran.datasiswa_231.R;
import com.pedoran.datasiswa_231.database.DatabaseSiswa;
import com.pedoran.datasiswa_231.database.Siswa;

public class InputData extends AppCompatActivity {
    TextView tNis,tNama,tLahir,tGender,tAlamat;
    Button btnSubmit;
    Context context;
    String nis,aksi = "Submit";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data);
        context = this;

        aksi = getIntent().getStringExtra("UPDATE_ACTION");

        tNis = findViewById(R.id.inputNIS);
        tNama = findViewById(R.id.inputNama);
        tLahir = findViewById(R.id.inputTglLahir);
        tGender = findViewById(R.id.inputGender);
        tAlamat = findViewById(R.id.inputAlamat);

        btnSubmit = findViewById(R.id.btnInputForm);
        if(aksi.equals("UPDATE")){
            btnSubmit.setText("Update Data");
            tNis.setText(nis);
            tNis.setFocusable(false);
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
                }
                if(btnStatus.equals("Update Data")){
                    jalmi.setNis(tNis.getText().toString());
                    jalmi.setNama(tNama.getText().toString());
                    jalmi.setTanggalLahir(tLahir.getText().toString());
                    jalmi.setJenisKelamin(tGender.getText().toString());
                    jalmi.setAlamat(tAlamat.getText().toString());
                    db.update(jalmi);
                }
            }
        });
    }


}
