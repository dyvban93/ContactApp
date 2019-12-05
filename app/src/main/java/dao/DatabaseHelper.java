package dao;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Bakota on 06/03/2019.
 */



public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "db_contact_seed";
    private static final int DB_VERSION = 1;
    private static final String CONTACTS_TABLE = "create table contacts (id_contact integer primary key autoincrement, "
            + "nom text not null," +
            " numero text not null, " +
            "adresse text not null);";


    /**
     * Database Helper constructor.
     * @param context
     */
    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase database) {

        database.execSQL(CONTACTS_TABLE);

    }
    /**
     * Handles the table version and the drop of a table.
     */
    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        Log.w(DatabaseHelper.class.getName(),
                "Upgrading databse from version" + oldVersion + "to "
                        + newVersion + ", which will destroy all old data");
        database.execSQL("DROP TABLE IF EXISTS contacts");

        onCreate(database);
    }
}
