package com.example.formresepbaking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageButton  btninputresepbaru, resepkue;
    Button btn_about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btninputresepbaru = (ImageButton) findViewById(R.id.btninputresep);
        btninputresepbaru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;
                intent= new Intent(getApplicationContext(), ActivityForm.class);
                startActivity(intent);
            }
        });
        resepkue = findViewById(R.id.resepkue);
        resepkue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = null;
                intent= new Intent(getApplicationContext(), ActivityListDataResep.class);
                startActivity(intent);
            }
        });

    }
    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater().inflate(R.menu.menu_about,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.about:
                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                builder1.setMessage("Nama : Luh Putu Murniasih Pertiwi\nNIM : 1905551038\n\nAplikasi Nyam Nyam Cake merupakan aplikasi untuk mencari resep membuat kue");
                builder1.setTitle("ABOUT APP");
                builder1.setCancelable(true);
                builder1.setPositiveButton(
                        "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert11 = builder1.create();
                alert11.show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}