package NativeDb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/8/24.
 */
public class QuickDbOpenHelper extends SQLiteOpenHelper {


    public static final String DB_NAME = "dbname";
    public static final int DB_VERSION = 1;

    public QuickDbOpenHelper(Context context) {
        super(context, DB_NAME + ".db", null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(QuickBaseTable.SQL_CREATE_TABLE_1);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(QuickBaseTable.SQL_DROP_TABLE_1);
        onCreate(db);
    }
}
