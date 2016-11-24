package NativeDb;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import ui.nativeDb.NativeDbManager;

/**
 * 某个表的增删查改
 */
public class Table1DbDao {

    public static <T> List<T> query(Context context) {
        SQLiteDatabase sqLiteDatabase = NativeDbManager.getInstance().openDatabase(context);
        return null;
    }


}
