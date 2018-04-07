package bananas.bananas_project_1;

/**
 * Created by thejacko42 on 5/2/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DBHelper extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 4;
    // Database Name
    private static final String DATABASE_NAME = "BananaDB";
    // Bananas table name
    private static final String TABLE_Banana = "Bananas";
    // Bananas Table Columns names
    private static final String Key_Id = "bananaId";
    private static final String Key_Date = "date";
    private static final String KEY_Time = "time";
    private static final String KEY_Color = "color";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_BANANA_TABLE =
                "CREATE TABLE " + TABLE_Banana + "(" +
                        Key_Id + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                        Key_Date + " TEXT, " +
                        KEY_Time + " TEXT, " +
                        KEY_Color + " TEXT);";

        db.execSQL(CREATE_BANANA_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Banana);
        // Creating tables again
        this.onCreate(db);
    }

    // Adding new banana
    public void addBanana(Banana banana) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Key_Date, banana.getDate());
        values.put(KEY_Time, banana.getTime());
        values.put(KEY_Color, banana.getColor());
        // Inserting Row
        db.insert(TABLE_Banana, null, values);
        db.close(); // Closing database connection
    }

    public Banana getBanana(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =
                db.query(TABLE_Banana, new String[]{Key_Id,
                                Key_Date, KEY_Time, KEY_Color}, Key_Id + "=?",
                        new String[]{String.valueOf(id)}, null, null, "date", null);

        Banana banana = null;

        // Make sure the cursor is not null
        if (cursor != null) {
            // Get the first row from the cursor
            cursor.moveToFirst();

            // If there was at least one row
            if (cursor.getCount() > 0) {
                // Initialize the Banana object with the values from the rows
                banana = new Banana(
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4));

            }
        }

        return banana;
    }

    // Updating a Banana
    public int updateBanana(Banana banana) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Key_Date, banana.getDate());
        values.put(KEY_Time, banana.getTime());
        values.put(KEY_Color, banana.getColor());
        // updating row
        return db.update(TABLE_Banana, values, Key_Id + " = ?",
                new String[]{String.valueOf(banana.getId())});
    }


    /**
     * Getting all Bananas
     * returns list of Bananas
     */
    public List<Banana> getAllBananas() {
        List<Banana> bananas = new ArrayList<Banana>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_Banana;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                bananas.add(new Banana(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3)));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning bananas
        return bananas;
    }

    public void deleteBanana(Banana person){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        db.delete(TABLE_Banana, Key_Id + "=?", new String[]{String.valueOf(person.getId())});
    }

    public String getCurDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

    public Date parseDateTime(String dateStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());

        Date date = null;
        try {
            date = dateFormat.parse(dateStr);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

}