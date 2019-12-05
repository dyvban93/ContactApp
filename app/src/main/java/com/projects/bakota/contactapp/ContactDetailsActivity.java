package com.projects.bakota.contactapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ContactDetailsActivity extends AppCompatActivity {

    TextView nom_text,adresse_text,numero_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        nom_text = (TextView) findViewById(R.id.nom_detail);
        adresse_text = (TextView) findViewById(R.id.adresse_detail);
        numero_text = (TextView) findViewById(R.id.phone_detail);

        if (getIntent() != null){

            Intent intent = getIntent();
            String nom,adresse,num;

            nom = intent.getStringExtra("nom");
            adresse = intent.getStringExtra("adresse");
            num = intent.getStringExtra("numero");

            nom_text.setText(nom);
            adresse_text.setText(adresse);
            numero_text.setText(num);
        }
    }
}
