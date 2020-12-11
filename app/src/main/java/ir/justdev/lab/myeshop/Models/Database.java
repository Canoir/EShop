package ir.justdev.lab.myeshop.Models;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import ir.justdev.lab.myeshop.Models.Model.User;

public class Database extends SQLiteOpenHelper {

    public Database(Context context) {
        super(context, "EShop", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE T_User (Id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "UserName VARCHAR (25)," +
                "Password VARCHAR (18))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
    public User get(String username) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM T_User WHERE UserName='" + username + "'", null);
        User user = null;
        if (cursor.moveToFirst())
            user = new User(cursor.getString(1), cursor.getString(2));
        cursor.close();
        db.close();
        return user;
    }

    public boolean add(User user) {
        try (SQLiteDatabase db = getWritableDatabase()) {
            db.execSQL("INSERT INTO T_User (UserName,Password) VALUES (" +
                    "'" + user.Username + "'" +
                    ", '" + user.Password + "')");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
