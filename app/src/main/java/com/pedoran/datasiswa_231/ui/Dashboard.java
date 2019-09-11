package com.pedoran.datasiswa_231.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.pedoran.datasiswa_231.R;
import com.pedoran.datasiswa_231.ui.crud.InputData;
import com.pedoran.datasiswa_231.ui.crud.LihatData;

public class Dashboard extends AppCompatActivity {
    Button btnLihatData,btnInputData,btnInformasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        btnLihatData = findViewById(R.id.btnLihat);
        btnInputData = findViewById(R.id.btnInput);
        btnInformasi = findViewById(R.id.btnInfo);

        onClickIntent(btnLihatData, LihatData.class);
        onClickIntent(btnInputData, InputData.class);
        onClickIntent(btnInformasi,AboutUs.class);
    }

    public void onClickIntent(Button btn, final Class tujuan){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pindah = new Intent(Dashboard.this,tujuan);
                startActivity(pindah);
            }
        });
    }
}
