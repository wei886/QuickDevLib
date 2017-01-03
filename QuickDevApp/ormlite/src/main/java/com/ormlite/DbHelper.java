package com.ormlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * author: midVictor
 * date: on 2016/12/19
 * description:
 */

public class DbHelper extends OrmLiteSqliteOpenHelper {

    private static final String TABLE_NAME = "sqlite-test-user.db";

    private static DbHelper instance = null;
    private Dao<User, Integer> userDao; //userDao ，每张表对于一个
    private Dao<Person, Integer> personDao; //userDao ，每张表对于一个

//    private DbHelper(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int databaseVersion) {
//        super(context, TABLE_NAME, null, 1);
//    }


    private DbHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {

        try {
            TableUtils.createTable(connectionSource, User.class);
            TableUtils.createTable(connectionSource, Person.class);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {

        try {
            TableUtils.dropTable(connectionSource, User.class, true);
            TableUtils.dropTable(connectionSource, Person.class, true);
            onCreate(sqLiteDatabase, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static DbHelper INSTANCE(Context context) {
        if (instance == null) {
            synchronized (DbHelper.class) {
                instance = new DbHelper(context);
            }
        }
        return instance;
    }


    public Dao<User, Integer> getUserDao() {
        try {
            userDao = getDao(User.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userDao;
    }


    public Dao<Person, Integer> getPersonDao() {
        try {
            personDao = getDao(Person.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personDao;
    }


}
