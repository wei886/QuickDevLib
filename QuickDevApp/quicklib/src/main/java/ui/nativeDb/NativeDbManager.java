package ui.nativeDb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import NativeDb.QuickDbOpenHelper;
import NativeDb.Table1DbDao;

/**
 * Created by Administrator on 2016/8/24.
 */
public class NativeDbManager {


    private SQLiteDatabase mSqlLiteDb;

    private NativeDbManager() {
    }

    private static NativeDbManager mInstance;
    private QuickDbOpenHelper mOpenHelperInstance;
    private AtomicInteger mDbOpenCounter = new AtomicInteger();


    public static NativeDbManager getInstance() {
        if (mInstance == null) {
            synchronized (NativeDbManager.class) {
                if (mInstance == null)
                    mInstance = new NativeDbManager();
            }
        }
        return mInstance;
    }


    private QuickDbOpenHelper getOpenHelperInstance(Context context) {
        if (mOpenHelperInstance == null) {
            mOpenHelperInstance = new QuickDbOpenHelper(context);
        }
        return mOpenHelperInstance;
    }

    public synchronized SQLiteDatabase openDatabase(Context context) {
        if (mDbOpenCounter.incrementAndGet() == 1) {
            mSqlLiteDb = getOpenHelperInstance(context).getWritableDatabase();
        }
        return mSqlLiteDb;
    }

    public synchronized void closeDatabase() {
        if (mDbOpenCounter.decrementAndGet() == 0) {
            if (mSqlLiteDb != null) {
                mSqlLiteDb.close();
            }
        }
    }


    /**
     * 查询
     * @param context
     * @param <T>
     * @return
     */
    public <T> List<T> query(Context context) {
        return Table1DbDao.query(context);
    }

}
