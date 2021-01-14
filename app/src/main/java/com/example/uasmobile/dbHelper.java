package com.example.uasmobile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.uasmobile.ui.favorite.Favorite;
import com.example.uasmobile.ui.favorite.FavoriteAdapter;
import com.example.uasmobile.ui.favorite.FavoriteFragment;

import java.util.ArrayList;
import java.util.List;

public class dbHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "favteam";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_STD = "favorite";
    private static final String KEY_IMG = "img";
    private static final String KEY_NAME = "name";
    private static final String KEY_DESK = "desk";

    private static final String CREATE_TABLE_STUDENTS = "CREATE TABLE "
            + TABLE_STD + "(" + KEY_IMG
            + " TEXT ," + KEY_NAME + " TEXT ,"+ KEY_DESK + " TEXT );";

    public dbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_STUDENTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_STD + "'");
        onCreate(db);
    }
    public long addRecord(String img,String desk, String name) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name);
        values.put(KEY_DESK, desk);
        values.put(KEY_IMG, img);
        long insert = db.insert(TABLE_STD, null, values);
        return insert;
    }
    public List<Favorite> getAllRecord() {
        List<Favorite> favlist = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_STD;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Favorite favModels = new Favorite();
                favModels.setImagev(cursor.getString(0));
                favModels.setTim(cursor.getString(1));
                favModels.setDesc(cursor.getString(2));

                favlist.add(favModels);
            } while (cursor.moveToNext());
        }

        // return contact list
        return favlist;
    }
    public int getUserModelCount() {
        String countQuery = "SELECT  * FROM " + TABLE_STD;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }
    public void deleteModel(Favorite contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_STD, KEY_IMG + " = ?",
                new String[] { String.valueOf(contact.getImagev()) });
        db.close();
    }
}

