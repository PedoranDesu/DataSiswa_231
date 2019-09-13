package com.pedoran.datasiswa_231.ui.crud;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.pedoran.datasiswa_231.R;
import com.pedoran.datasiswa_231.database.DatabaseSiswa;
import com.pedoran.datasiswa_231.database.Siswa;
import com.pedoran.datasiswa_231.ui.Dashboard;

import java.util.Calendar;

public class InputData extends AppCompatActivity {
    EditText tNis,tNama,tLahir,tGender,tAlamat;
    TextView label;
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
        customDateFormatter(tLahir);
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

    public void customDateFormatter(final EditText text){
        // Copied from https://stackoverflow.com/questions/16889502/how-to-mask-an-edittext-to-show-the-dd-mm-yyyy-date-format
        TextWatcher tw = new TextWatcher() {
            private String current = "";
            private String ddmmyyyy = "DDMMYYYY";
            private Calendar cal = Calendar.getInstance();
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals(current)) {
                    String clean = s.toString().replaceAll("[^\\d.]|\\.", "");
                    String cleanC = current.replaceAll("[^\\d.]|\\.", "");

                    int cl = clean.length();
                    int sel = cl;
                    for (int i = 2; i <= cl && i < 6; i += 2) {
                        sel++;
                    }
                    //Fix for pressing delete next to a forward slash
                    if (clean.equals(cleanC)) sel--;

                    if (clean.length() < 8){
                        clean = clean + ddmmyyyy.substring(clean.length());
                    }else{
                        //This part makes sure that when we finish entering numbers
                        //the date is correct, fixing it otherwise
                        int day  = Integer.parseInt(clean.substring(0,2));
                        int mon  = Integer.parseInt(clean.substring(2,4));
                        int year = Integer.parseInt(clean.substring(4,8));

                        mon = mon < 1 ? 1 : mon > 12 ? 12 : mon;
                        cal.set(Calendar.MONTH, mon-1);
                        year = (year<1900)?1900:(year>2100)?2100:year;
                        cal.set(Calendar.YEAR, year);
                        // ^ first set year for the line below to work correctly
                        //with leap years - otherwise, date e.g. 29/02/2012
                        //would be automatically corrected to 28/02/2012

                        day = (day > cal.getActualMaximum(Calendar.DATE))? cal.getActualMaximum(Calendar.DATE):day;
                        clean = String.format("%02d%02d%02d",day, mon, year);
                    }

                    clean = String.format("%s/%s/%s", clean.substring(0, 2),
                            clean.substring(2, 4),
                            clean.substring(4, 8));

                    sel = sel < 0 ? 0 : sel;
                    current = clean;
                    text.setText(current);
                    text.setSelection(sel < current.length() ? sel : current.length());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        text.addTextChangedListener(tw);
    }


}
