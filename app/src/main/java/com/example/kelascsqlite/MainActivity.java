package com.example.kelascsqlite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;

import com.example.kelascsqlite.adapter.TemanAdapter;
import com.example.kelascsqlite.database.DBController;
import com.example.kelascsqlite.database.teman;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TemanAdapter adapter;
    private ArrayList<teman> temanArrayList;
    DBController controller = new DBController(this);
    String id,nm,tlp;
    CardView kartuku;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        fab = findViewById(R.id.floatingBtn);
        BacaData();
        adapter = new TemanAdapter(temanArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,TemanBaru.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.popupmenu, menu);
        menu.setHeaderTitle("Pilih Opsi");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.edit){
            Intent intent = new Intent(MainActivity.this, UpdateTeman.class);
            startActivity(intent);
            return true;
        } else if (item.getItemId() ==  R.id.hapus) {
            HashMap<String, String> qvalues = new HashMap<>();
            qvalues.put("id", id);
            controller.deleteData(qvalues);
            Intent in = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(in);
        }else{
            return false;
        }
        return true;
    }

    public void BacaData(){
        ArrayList<HashMap<String,String>> daftarTeman = controller.getAllTeman();
        temanArrayList = new ArrayList<>();
        //memindah dari hasil query kedalam Teman
        for (int i=0;i<daftarTeman.size();i++){
            teman teman = new teman();

            teman.setId(daftarTeman.get(i).get("id").toString());
            teman.setNama(daftarTeman.get(i).get("nama").toString());
            teman.setTelpon(daftarTeman.get(i).get("telpon").toString());
            //pindahkan dari teman ke dalam ArrayList teman di adapter
            temanArrayList.add(teman);
        }
    }
}