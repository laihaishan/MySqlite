package com.sqlite.db;

import android.content.Context;
import android.database.Cursor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/12.
 */
public class UserService {
    private DBManager dbManager;

    public UserService(Context context) {
        dbManager = DBManager.getInstance(context);
    }

    public boolean add(User user) {
        try {
            String sql = "insert into user(name) values(?,?)";
            Object[] objects = new Object[]{user.getName(), user.getAge()};
            boolean flag = dbManager.updateDevice(sql, objects);
            return flag;
        } catch (Exception e) {
            return false;
        } finally {
            dbManager.closeConnection();  //关闭数据库
        }
    }


    public boolean delete(String name) {
        try {
            String sql = "delete from user where name=?";
            Object[] objects = new Object[]{
                    name
            };
            boolean flag = dbManager.updateDevice(sql, objects);
            return flag;
        } catch (Exception e) {
            return false;
        } finally {
            dbManager.closeConnection();
        }
    }

    public List<User> query() {
        List<User> users = new ArrayList<User>();
        Cursor cursor = null;
        try {
            String sql = "select * from user";
            cursor = dbManager.queryDevice(sql, null);
            if (cursor == null && cursor.getCount() == 0) {
                return users;
            }
            while (cursor.moveToNext()) {
                User user = getUserCursor(cursor);
                users.add(user);
            }
            return users;
        } catch (Exception e) {
            return users;
        } finally {
            if (cursor != null)  //游标有东西就关闭
                cursor.close();
            dbManager.closeConnection();
        }

    }

    /**
     * 从游标中获取数据
     *
     * @param cursor
     * @return
     */
    // id 和user  必须一致
    private User getUserCursor(Cursor cursor) {
        if (cursor == null)
            return null;
        int id = cursor.getInt(cursor.getColumnIndex("id"));
        String name = cursor.getString(cursor
                .getColumnIndex("name"));
        String age = cursor.getString(cursor
                .getColumnIndex("age"));
        User user = new User(id, name, age);
        return user;
    }
}
