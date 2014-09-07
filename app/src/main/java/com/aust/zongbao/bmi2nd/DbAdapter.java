package com.aust.zongbao.bmi2nd;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.sql.SQLException;

/**
 * Created by Administrator on 2014/9/4.
 */
public class DbAdapter {


    private static final String DATABASE_NAME = "bmi.db";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_TABLE = "bmidata";

    private static final String DATABASE_CREATE =
            "CREATE TABLE bmidata (id INTEGER PRIMARY KEY AUTOINCREMENT,bmi FLOAT(4,2), weight FLOAT(5,2),time DATATIME);";

    private static class DatabaseHelper extends SQLiteOpenHelper{


        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS"+DATABASE_CREATE);
            onCreate(db);
        }


    }


    private Context mCtx = null;
    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;

    public DbAdapter(Context ctx) {
//        super();
        this.mCtx = ctx;
    }

    public DbAdapter open() throws SQLException {
        dbHelper = new DatabaseHelper(mCtx);
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close()
    {
        dbHelper.close();
    }


    public static final String KEY_ROWID = "id";
    public static final String KEY_BMI = "bmi";
    public static final String KEY_WEIGHT = "weight";
    public static final String KEY_TIME = "time";

    //get all entries
    public Cursor getall () {
        return db.rawQuery("SELECT * FROM bmidata",null);
    }


}
