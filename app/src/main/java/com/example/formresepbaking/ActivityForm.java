package com.example.formresepbaking;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityForm extends AppCompatActivity {

    EditText namaresep, ingredients, nutrisi, stepbystep;
    RadioButton radiocookie, radiobolu, radiobrownies, radiodonat, rb, rbWaktu, radioMenit, radioJam, radioHari;
    RadioGroup rg, rgWaktu;
    SeekBar seekbarDurasi;
    TextView seekbarWaktu;
    TextView AlNamaResep, AlIngredients, AlNutrisi, AlStepByStep, AlJenisKue, AlKategori, AlLamaMemasak, ALWaktu;
    AlertDialog.Builder dialog;
    LayoutInflater inflater;
    View dialogView;
    String NamaResep;
    String Ingredients;
    String Nutrition;
    String StepbyStep;
    String seekbarLamaMemasak;
    String JenisKue;
    String Waktu;
    String kategori;
    CheckBox cb1, cb2, cb3;
    Button submit;
    DbMain dBmain;

    private long id;
    private Intent intent2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        dBmain = new DbMain(this);
        intent2 = getIntent();
        id = intent2.getLongExtra(DbMain.row_id,0);

        if(intent2.hasExtra(DbMain.row_id)){
            setTitle("Edit Data");
        }else{
            setTitle("Tambah Data");
        }
        namaresep = (EditText) findViewById(R.id.editnamaresep);
        ingredients = (EditText) findViewById(R.id.editingredients);
        nutrisi = (EditText) findViewById(R.id.editnutrisi);
        stepbystep = (EditText) findViewById(R.id.editstepbystep);

        rg = (RadioGroup) findViewById(R.id.rg);
        rgWaktu = (RadioGroup) findViewById(R.id.rgWaktu);
        radiocookie = (RadioButton) findViewById(R.id.radiocookie);
        radiobolu = (RadioButton) findViewById(R.id.radiobolu);
        radiobrownies = (RadioButton) findViewById(R.id.radiobrownnies);
        radioHari = (RadioButton) findViewById(R.id.radioHari);
        radioJam = (RadioButton) findViewById(R.id.radioJam);
        radioMenit = (RadioButton) findViewById(R.id.radioMenit);
        radiodonat = (RadioButton) findViewById(R.id.radiodonat);
        submit = (Button)findViewById(R.id.btnSubmit);

        cb1 = (CheckBox) findViewById(R.id.kategori1);
        cb2 = (CheckBox) findViewById(R.id.kategori2);
        cb3 = (CheckBox) findViewById(R.id.kategori3);


        seekbarDurasi = (SeekBar) findViewById(R.id.seekBarLamaMemasak);
        seekbarWaktu = (TextView) findViewById(R.id.seekbarWaktu);

        seekbarDurasi.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekbarWaktu.setText(String.valueOf(progress));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radio = rg.getCheckedRadioButtonId();
                rb = (RadioButton)findViewById(radio);

                int radioWaktu = rgWaktu.getCheckedRadioButtonId();
                rbWaktu = (RadioButton)findViewById(radioWaktu);

                NamaResep = namaresep.getText().toString();
                Ingredients = ingredients.getText().toString();
                Nutrition = nutrisi.getText().toString();
                StepbyStep = stepbystep.getText().toString();
                seekbarLamaMemasak = seekbarWaktu.getText().toString();
                JenisKue = rb.getText().toString();
                Waktu = rbWaktu.getText().toString();
                kategori = "";



                //Check Box
                if (cb1.isChecked()) {
                    kategori+="Panggang";
                }
                if (cb2.isChecked()) {
                    kategori+="Kukus";
                }
                if (cb3.isChecked()) {
                    kategori+="Goreng";
                }

                if (NamaResep.isEmpty() || Ingredients.isEmpty() || Nutrition.isEmpty() || StepbyStep.isEmpty()|| radio == -1 || radioWaktu == -1 ||seekbarLamaMemasak=="0") {
                    Toast.makeText(ActivityForm.this,"Please Complete Your Data First!",Toast.LENGTH_SHORT).show();
                }else{

                Dialogform();
            }}
        });
        getData();


    }
    private void getData(){
        Cursor cursor = dBmain.getData(id);
        if(cursor.moveToFirst()){
            @SuppressLint("Range") String namaresepdb = cursor.getString(cursor.getColumnIndex(DbMain.row_namaresep));
            @SuppressLint("Range") String jeniskuedb = cursor.getString(cursor.getColumnIndex(DbMain.row_jeniskue));
            @SuppressLint("Range") String caramemasakdb = cursor.getString(cursor.getColumnIndex(DbMain.row_kategori));
            @SuppressLint("Range") String ingredientsdb = cursor.getString(cursor.getColumnIndex(DbMain.row_ingredients));
            @SuppressLint("Range") String nutritiondb = cursor.getString(cursor.getColumnIndex(DbMain.row_nutrition));
            @SuppressLint("Range") String stepbystepdb = cursor.getString(cursor.getColumnIndex(DbMain.row_stepbystep));
            @SuppressLint("Range") String waktudb = cursor.getString(cursor.getColumnIndex(DbMain.row_waktu));
            @SuppressLint("Range") int lamamemasakdb = cursor.getInt(cursor.getColumnIndex(DbMain.row_lamamemasak));

            namaresep.setText(namaresepdb);
            ingredients.setText(ingredientsdb);
            stepbystep.setText(stepbystepdb);
            nutrisi.setText(nutritiondb);
            if(jeniskuedb.equals("cookie")){
                radiocookie.setChecked(true);
            }else if(jeniskuedb.equals("bolu")){
                radiocookie.setChecked(true);
            }else if(jeniskuedb.equals("brownies")){
            radiocookie.setChecked(true);
            }else if(jeniskuedb.equals("donat")){
            radiocookie.setChecked(true);
            }

            if(waktudb.equals("jam")){
                radioJam.setChecked(true);
            }else if(waktudb.equals("menit")){
                radioMenit.setChecked(true);
            }else if(waktudb.equals("hari")) {
                radioHari.setChecked(true);
            }


            if(caramemasakdb.equals("Panggang") || caramemasakdb.equals(",Panggang")){
                cb1.setChecked(true);
            }else if(caramemasakdb.equals("Kukus")|| caramemasakdb.equals(",Kukus")){
                cb2.setChecked(true);
            }else if(caramemasakdb.equals("Goreng")|| caramemasakdb.equals(",Goreng")){
                cb3.setChecked(true);
            }

            seekbarDurasi.setProgress(lamamemasakdb);
            seekbarDurasi.setMax(100);
            seekbarWaktu.setText(lamamemasakdb + "/" + seekbarDurasi.getMax());
       //     seekbarLamaMemasak = lamamemasakdb;

        }
    }

    @SuppressLint("SetTextI18n")
    private void Dialogform(){
        dialog = new AlertDialog.Builder(ActivityForm.this);
        inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.alert_dialog, null);
        dialog.setView(dialogView);
        dialog.setCancelable(true);

        AlNamaResep = (TextView) dialogView.findViewById(R.id.AlNamaResep);
        AlIngredients = (TextView) dialogView.findViewById(R.id.AlIngredients);
        AlNutrisi = (TextView) dialogView.findViewById(R.id.AlNutrisi);
        AlStepByStep = (TextView) dialogView.findViewById(R.id.AlStepByStep);
        AlJenisKue = (TextView) dialogView.findViewById(R.id.AlJenisKue);
        AlKategori = (TextView) dialogView.findViewById(R.id.AlKategori);
        AlLamaMemasak = (TextView) dialogView.findViewById(R.id.AlLamaMemasak);
        ALWaktu = (TextView) dialogView.findViewById(R.id.AlWaktu);

        AlNamaResep.setText("Nama Resep : " + NamaResep);
        AlIngredients.setText("Ingredients : " + Ingredients);
        AlNutrisi.setText("Nutrisi : " + Nutrition);
        AlStepByStep.setText("Step By Step :" + StepbyStep);
        AlJenisKue.setText("Jenis Kue : " + JenisKue);
        AlKategori.setText("Kategori : " + kategori);
        AlLamaMemasak.setText("Lama Memasak : " + seekbarLamaMemasak);
        ALWaktu.setText("Waktu Memasak : " + Waktu);

        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
                ContentValues values = new ContentValues();
                values.put(DbMain.row_namaresep, NamaResep);
                values.put(DbMain.row_ingredients, Ingredients);
                values.put(DbMain.row_nutrition, Nutrition);
                values.put(DbMain.row_stepbystep, StepbyStep);
                values.put(DbMain.row_jeniskue, JenisKue);
                values.put(DbMain.row_kategori, kategori);
                values.put(DbMain.row_lamamemasak, seekbarLamaMemasak);
                values.put(DbMain.row_waktu, Waktu);
                if(intent2.hasExtra(DbMain.row_id)){
                    dBmain.updateData(values,id);
                }else {
                    dBmain.insertData(values);
                }


                Intent intent1 = new Intent(ActivityForm.this, HasilInput.class);
                intent1.putExtra("namaresep", NamaResep);
                intent1.putExtra("ingredients", Ingredients);
                intent1.putExtra("nutrition", Nutrition);
                intent1.putExtra("stepbystep", StepbyStep);
                intent1.putExtra("lamamemasak", seekbarLamaMemasak);
                intent1.putExtra("jeniskue", JenisKue);
                intent1.putExtra("waktu", Waktu);
                intent1.putExtra("kategori", kategori);
                startActivity(intent1);
            }
        }).setNegativeButton("Batal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "Tampilan Resep dimulai",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "Tampilan Resep sedang berjalan",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "Tampilan Resep berhenti sementara",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "Tampilan Resep berhenti",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Aplikasi ditutup, selamat tinggal",Toast.LENGTH_SHORT).show();
    }
}


