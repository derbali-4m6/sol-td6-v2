package ca.qc.sol_td6_v2.models.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDbHelper extends SQLiteOpenHelper {

    public MyDbHelper(@Nullable Context context) {
        super(context, "contacts-v2.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Contact (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, phoneNumber TEXT, gender TEXT)");
        db.execSQL("INSERT INTO Contact (name, phonenumber, gender) VALUES ('Alfred Smith', '514-222-2729', 'Male')");
        db.execSQL("INSERT INTO Contact (name, phonenumber, gender) VALUES ('William Jackson', '514-232-8831', 'Male')");
        db.execSQL("INSERT INTO Contact (name, phonenumber, gender) VALUES ('Suzanne Lajoie', '514-200-0001', 'Female')");
        db.execSQL("INSERT INTO Contact (name, phonenumber, gender) VALUES ('Annie Dupont', '450-362-8992', 'Female')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
