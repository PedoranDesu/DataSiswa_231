package com.pedoran.datasiswa_231.ui.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.pedoran.datasiswa_231.R;
import com.pedoran.datasiswa_231.database.Siswa;

public class DetailData extends AppCompatActivity {
    EditText dtNis,dtNama,dtLahir,dtGender,dtAlamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_data);

        dtNis = findViewById(R.id.dtlNis);
        dtNama = findViewById(R.id.dtlNama);
        dtLahir = findViewById(R.id.dtlTgllahir);
        dtGender = findViewById(R.id.dtlJenkel);
        dtAlamat = findViewById(R.id.dtlAlamat);
        disableThis(dtNis);
        disableThis(dtNama);
        disableThis(dtLahir);
        disableThis(dtGender);
        disableThis(dtAlamat);

        Siswa siswa = getIntent().getParcelableExtra("DETAIL_INTENT");
        dtNis.setText(siswa.getNis());
        dtNama.setText(siswa.getNama());
        dtLahir.setText(siswa.getTanggalLahir());
        dtGender.setText(siswa.getJenisKelamin());
        dtAlamat.setText(siswa.getAlamat());
    }

    public void disableThis(EditText edt){
        edt.setFocusable(false);
    }
}
