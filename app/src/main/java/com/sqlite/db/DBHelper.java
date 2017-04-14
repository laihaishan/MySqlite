package com.sqlite.db;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/9/12.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "sqlite_db";//数据库名称
    private static final int DATABASE_VERSION = 1;  //数据库版本

    public DBHelper(Context context) { // 必须添加的构造
        super(context, DATABASE_NAME, null,  DATABASE_VERSION);
        //数据库名                                数据库版本
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("create table user");
        buffer.append("(");    //PRIMARY KEY   要大写 不然报错
        buffer.append("id integer PRIMARY KEY autoincrement,");
        buffer.append("name text,");
        buffer.append("age text");
        buffer.append(")");
        db.execSQL(buffer.toString());

    }

    //发布项目  但是要更新数据库的时候使用这个方法
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
