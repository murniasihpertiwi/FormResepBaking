package com.example.formresepbaking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HasilInput extends AppCompatActivity {
    TextView showNamaResep, showIngredients, showStepbyStep, showNutritionInfo, showJenisKue, showCaraMemasak, showLamaMemasak, showWaktuMemasak;
    String NamaResep, Ingredients, StepbyStep, NutritionInfo, JenisKue, CaraMemasak, LamaMemasak, WaktuMemasak;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_input);

        showNamaResep = (TextView) findViewById(R.id.shownamaresep);
        showIngredients = (TextView) findViewById(R.id.showingredients);
        showStepbyStep= (TextView) findViewById(R.id.showstepbystep);
        showNutritionInfo = (TextView) findViewById(R.id.shownutritioninfo);
        showJenisKue = (TextView) findViewById(R.id.showjeniskue);
        showCaraMemasak= (TextView) findViewById(R.id.showcaramemasak);
        showLamaMemasak= (TextView) findViewById(R.id.showlamamemasak);
        showWaktuMemasak= (TextView) findViewById(R.id.showWaktuMemasak);

        //validasi, menyimpan nilai variabel dari intent, set text pada textview untuk menampilkan nilai
        if (getIntent().getStringExtra("namaresep") != null) {
            NamaResep = getIntent().getStringExtra("namaresep");
            showNamaResep.setText(NamaResep);
        }
        if (getIntent().getStringExtra("ingredients") != null) {
            Ingredients = getIntent().getStringExtra("ingredients");
            showIngredients.setText(Ingredients);
        }
        if (getIntent().getStringExtra("stepbystep") != null) {
            StepbyStep = getIntent().getStringExtra("stepbystep");
            showStepbyStep.setText(StepbyStep);
        }
        if (getIntent().getStringExtra("nutrition")  != null) {
            NutritionInfo = getIntent().getStringExtra("nutrition");
            showNutritionInfo.setText(NutritionInfo);
        }
        if (getIntent().getStringExtra("jeniskue") != null) {
            JenisKue = getIntent().getStringExtra("jeniskue");
            showJenisKue.setText(JenisKue);
        }
        if (getIntent().getStringExtra("kategori") != null) {
            CaraMemasak = getIntent().getStringExtra("kategori");
            showCaraMemasak.setText(CaraMemasak);
        }
        if (getIntent().getStringExtra("lamamemasak") != null) {
            LamaMemasak = getIntent().getStringExtra("lamamemasak");
            showLamaMemasak.setText(LamaMemasak);
        }
        if (getIntent().getStringExtra("waktu") != null) {
            WaktuMemasak = getIntent().getStringExtra("waktu");
            showWaktuMemasak.setText(WaktuMemasak);
        }
    }

    //button kembali
    public void Kembali (View view) {
        Intent intent = new Intent(HasilInput.this, ActivityForm.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "Tampilan resep dimulai",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "Tampilan resep sedang berjalan",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "Tampilan resep berhenti sementara",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "Tampilan resep berhenti",Toast.LENGTH_SHORT).show();
    }
}
