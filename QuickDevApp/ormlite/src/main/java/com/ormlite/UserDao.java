package com.ormlite;

import android.content.Context;

import java.sql.SQLException;
import java.util.List;

/**
 * author: midVictor
 * date: on 2016/12/19
 * description:
 */

public class UserDao {


    private Context context;

    public UserDao(Context context) {

        this.context = context;
    }


    public void addUser(User user) {
        try {
            DbHelper.INSTANCE(context).getUserDao().create(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<User> query() {
        List<User> users = null;

        try {
            users = DbHelper.INSTANCE(context).getUserDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }


    public void update(User user){
        try {
            DbHelper.INSTANCE(context).getUserDao().update(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
