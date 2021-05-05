package com.example.kelascsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.kelascsqlite.database.DBController;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;

public class UpdateTeman extends AppCompatActivity {
    private TextInputEditText tNama, tTelpon;
    private Button updateBtn, cancelBtn;
    String id,nm,tlp;
    DBController controller = new DBController(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_teman);

        tNama = (TextInputEditText)findViewById(R.id.tietNama);
        tTelpon = (TextInputEditText)findViewById(R.id.tietTelpon);
        updateBtn = (Button)findViewById(R.id.updatebtn);
        cancelBtn = (Button)findViewById(R.id.cancelbtn);

        id = getIntent().getStringExtra("id");
        nm = getIntent().getStringExtra("nm");
        tlp = getIntent().getStringExtra("tlp");

        tNama.setText(nm);
        tTelpon.setText(tlp);

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tNama.getText().toString().equals("")||tTelpon.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Data belum complete!",Toast.LENGTH_SHORT).show();
                } else {
                    nm = tNama.getText().toString();
                    tlp = tTelpon.getText().toString();

                    HashMap<String,String> qvalues = new HashMap<>();
                    qvalues.put("id",id);
                    qvalues.put("nama",nm);
                    qvalues.put("telpon",tlp);

                    controller.updateData(qvalues);
                    callHome();
                }
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
    }

    public void callHome(){
        Toast.makeText(getApplicationContext(), "Update Data Berhasil",
                Toast.LENGTH_LONG).show();
        Intent intent = new Intent(UpdateTeman.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}