package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import Adapters.Contacts;

/**
 * Created by Bakota on 06/03/2019.
 */

public class DatabaseAdapter {
    //table users
    private static final String CONTACTS_TABLE = "contacts";
    private static final String COL_NOM_CONTACTS = "nom";
    private static final String COL_ID_CONTACTS= "id_contact";
    private static final String COL_NUMERO_CONTACTS = "numero";
    private static final String COL_ADRESSE_CONTACTS = "adresse";



    private Context context;
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;





    public DatabaseAdapter(Context context) {
        this.context = context;
    }

    public long createContact(String nom,  String numero, String adresse){
        ContentValues values = new ContentValues();
        values.put(COL_NOM_CONTACTS, nom);
        values.put(COL_NUMERO_CONTACTS, numero);
        values.put(COL_ADRESSE_CONTACTS ,adresse);

        return database.insert(CONTACTS_TABLE,null,values);
    }

    public ArrayList<Contacts> fetchAllContact() {


        Cursor c = database.query(CONTACTS_TABLE, new String[] { COL_ID_CONTACTS, COL_NOM_CONTACTS, COL_NUMERO_CONTACTS, COL_ADRESSE_CONTACTS},
                null, null, null, null, null);

        if (c.getCount() == 0) return  new ArrayList<Contacts>(c.getCount());
        ArrayList<Contacts> allS = new ArrayList<Contacts>(c.getCount());
        c.moveToFirst();
        do{
            allS.add(new Contacts(c.getString(1), c.getString(2), c.getString(3) ));

        } while (c.moveToNext());


        return allS;
    }

    
    public ArrayList<Contacts> getContacts(String toSearch){

        Cursor c = database.query(CONTACTS_TABLE, new String[] { COL_ID_CONTACTS, COL_NOM_CONTACTS, COL_NUMERO_CONTACTS, COL_ADRESSE_CONTACTS},
                COL_NOM_CONTACTS + " LIKE ?", new String[]{"%"+toSearch +"%"}, null, null, null);

        if (c.getCount() == 0) return  new ArrayList<Contacts>(c.getCount());
        ArrayList<Contacts> allS = new ArrayList<Contacts>(c.getCount());
        c.moveToFirst();
        do{
            allS.add(new Contacts(c.getString(1), c.getString(2), c.getString(3) ));

        } while (c.moveToNext());


        return allS;

    }



    /**
     * Creates the database helper and gets the database.
     *
     * @return
     * @throws SQLException
     */
    public DatabaseAdapter open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    /**
     * Closes the database.
     */
    public void close() {
        dbHelper.close();
    }

}
