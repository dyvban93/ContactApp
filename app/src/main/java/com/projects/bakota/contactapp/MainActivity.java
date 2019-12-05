package com.projects.bakota.contactapp;

import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

import Adapters.Contacts;
import Adapters.CustomAdapters;
import dao.DatabaseAdapter;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton actionButton;
    ListView mListContact;

    EditText nom,adresse,numero;
    DatabaseAdapter dbHelper;

    private ArrayList<Contacts> mContacts = new ArrayList<Contacts>(); //liste des contacts
    private CustomAdapters mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseAdapter(this);
        actionButton = (FloatingActionButton) findViewById(R.id.new_cont_floating);
        mListContact = (ListView) findViewById(R.id.contact_listview);

        dbHelper.open();
        mContacts = dbHelper.fetchAllContact();
        // on crée l'adapter
        mAdapter = new CustomAdapters(mContacts,this);
        //on attache l'adapter à la listview
        mListContact.setAdapter(mAdapter);

        mListContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(MainActivity.this, ContactDetailsActivity.class);

                intent.putExtra("nom",mContacts.get(i).getNom());
                intent.putExtra("numero",mContacts.get(i).getNumero());
                intent.putExtra("adresse",mContacts.get(i).getAdresse());

                startActivity(intent);
            }
        });
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newContact();

            }
        });


        mAdapter.notifyDataSetChanged();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);


        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        return true;
    }





    /*
 * Ajout du nouveau contact dans le listview
 * @Params
 * */
    private void addContact(String nom, String numero, String adresse) {
        //on l'ajoute dans la bd
       long l= dbHelper.createContact(nom,numero,adresse);

        if(l>0) Toast.makeText(MainActivity.this,"Ajout réussit",Toast.LENGTH_LONG).show();

        mContacts.add(new Contacts(nom,numero,adresse));
       mAdapter.notifyDataSetChanged();
       // scrollToBottom();
    }

    // c'est méthode qui va faire défiler les contact automatiquement
    public void scrollToBottom() {
        mListContact.post(new Runnable() {
            @Override
            public void run() {
                // Select the last row so it will scroll into view...
                mAdapter.notifyDataSetChanged();
                mListContact.setSelection(mAdapter.getCount() - 1);
            }
        });
    }


    public void newContact(){

        AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
        // Get the layout inflater
        LayoutInflater inflater = getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder2.setView(inflater.inflate(R.layout.add_contact, null))
                // Add action buttons
                .setPositiveButton("Ajouter", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                         addContact(nom.getText().toString(),numero.getText().toString(),adresse.getText().toString());
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        //on quitte
                    }
                });

        AlertDialog dialog1 = builder2.create();
        // Display the alert dialog on interface
        dialog1.show();

        nom = (EditText) dialog1.findViewById(R.id.nom_edit);
        numero = (EditText) dialog1.findViewById(R.id.numero_edit);
        adresse = (EditText) dialog1.findViewById(R.id.adresse_edit);
    }



}
