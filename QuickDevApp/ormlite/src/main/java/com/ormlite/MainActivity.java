package com.ormlite;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    Context con = MainActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_orm);


        final EditText mEdtId = (EditText) findViewById(R.id.edt_id);
        final  EditText mEdtId1 = (EditText) findViewById(R.id.edt_id_1);
        final EditText mEdtAge = (EditText) findViewById(R.id.edt_age);
        final  EditText mEdtAge1 = (EditText) findViewById(R.id.edt_age_1);
        final EditText mEdtName = (EditText) findViewById(R.id.edt_name);
        final  EditText mEdtName1 = (EditText) findViewById(R.id.edt_name_1);

        final TextView tvAll = (TextView) findViewById(R.id.tv_query_all);
        findViewById(R.id.btn_insert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new UserDao(con).addUser(new User(getEdtInt(mEdtId), getEdtStr(mEdtName), getEdtInt(mEdtAge)));
            }
        });


        findViewById(R.id.btn_query_all).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<User> query = new UserDao(con).query();

                if (query != null) {
                    String string = "";
                    for (User user : query) {
                        string += user.toString();
                    }
                    tvAll.setText(string);
                }
            }
        });


        findViewById(R.id.btn_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new UserDao(con).update(new User(getEdtInt(mEdtId1), getEdtStr(mEdtName1), getEdtInt(mEdtAge1)));
            }
        });
    }

    public String getEdtStr(EditText editText) {
        return editText.getText().toString().trim();
    }

    public int getEdtInt(EditText editText) {

        String trim = editText.getText().toString().trim();

        if (TextUtils.isEmpty(trim)) {
            return 0;
        }
        Integer integer = 0;
        try {
            integer = Integer.valueOf(trim);
        } catch (Exception e) {

            return 0;
        }
        return integer;
    }
}
