package com.sqlite.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * 数据库管理类
 *
 */
public class DBManager {
    private static DBManager dbManager;
    private DBHelper dbHelper;
    private SQLiteDatabase database;

    private DBManager(Context context) {
        Log.v("==========","==BBBB=="+context);
        dbHelper = new DBHelper(context);
    }

    /**
     * 使其成为单例类
     *
     * @param context
     * @return
     */
        public static DBManager getInstance(Context context) {
        Log.v("==========","==AAAA=="+context);
        if (dbManager == null) {
            dbManager = new DBManager(context);
        }
        return dbManager;
    }

    /**
     * 获取数据库连接
     */
    public void getConnection() {
        database = dbHelper.getWritableDatabase();  //得到连接后写入database

    }

    /**
     * 关闭数据库连接
     */
    public void closeConnection() {
        database.close();
    }

    /**
     * 实现数据的增删改
     *
     * @param sql
     * @param bindArgs
     * @return
     */
    public boolean updateDevice(String sql, Object[] bindArgs) {
        getConnection();
        try {
            database.execSQL(sql, bindArgs);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            this.closeConnection();
        }
    }

    /**
     * 查询数据库信息
     *
     * @param sql
     * @param selectionArgs
     * @Cursor 游标
     */
    public Cursor queryDevice(String sql, String[] selectionArgs) {
        getConnection();
        try {
            Cursor cursor = database.rawQuery(sql, selectionArgs);
            return cursor;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}